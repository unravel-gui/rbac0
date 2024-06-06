package com.komorebi.rbac0.filter;

import com.komorebi.rbac0.common.Common;
import com.komorebi.rbac0.common.utils.JWTUtils;
import com.komorebi.rbac0.model.Permission;
import com.komorebi.rbac0.service.PermissionService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Data
public class AuthFilter extends OncePerRequestFilter {

    private String headerKey;
    private String authPrefix;


    private PermissionService permissionService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中提取JWT令牌
        String token = extractJwtFromRequest(request);
        // 获得当前的HTTP方法
        String httpMethod = request.getMethod();
        // 获得当前的路由
        String route = request.getRequestURI();
        // 判断是否是游客可访问的接口
        if(isVisitReq(httpMethod,route)) {
            // 继续执行过滤器链
            filterChain.doFilter(request, response);
            return;
        }
        // 以下都需要对应权限
        // 没有token的处理
        if(token==null||token=="") {
            // 返回401 Unauthorized
            response401(response,"请先登录");
            return;
        }

        Integer uid = null;
        try {
            String uidStr = JWTUtils.validateToken(token);
            uid = Integer.valueOf(uidStr);
            if (auth(uid,httpMethod,route)){
                setParseUserId(request,uidStr);
                // 继续执行过滤器链
                filterChain.doFilter(request, response);
                return;
            }
            // 权限不足,返回403
            response403(response,"无权访问");
        } catch (ExpiredJwtException e){
            //TODO:返回401jwt令牌过期
            response401(response,"令牌过期");
        }catch (Exception e) {
            //TODO:返回401jwt令牌无效
            response401(response,"令牌无效");
        }
    }

    private void setParseUserId(HttpServletRequest request, String uid) {
        request.setAttribute(Common.CONTEXT_UID, null);
        if (uid != null) {
            request.setAttribute(Common.CONTEXT_UID, uid);
        }
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        // 从请求头中提取Authorization字段
        String bearerToken = request.getHeader(headerKey);

        // 如果Authorization字段不为空并且以Bearer 开头，则返回令牌部分
        if (bearerToken != null && bearerToken.startsWith(authPrefix)) {
            return bearerToken.substring(7); // 截取令牌部分
        }

        return null;
    }

    private Boolean isVisitReq(String httpMethod,String router) {
        // 方法一查询，可引入redis
//        List<Permission> ps = permissionService.getPermission4Visitor();
        // 方法二直接查询
        Boolean b=permissionService.checkPermission4Visitor(httpMethod, router);
        return b;
    }

    private Boolean auth(Integer uid, String httpMethod, String route) {
        List<Permission> ps = permissionService.getPermission4User(uid);
        // 方法二直接查询
        Boolean b = permissionService.checkPermission4User(uid, httpMethod, route);
        return b;
    }

    private void response401(HttpServletResponse response,String msg) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"code\":401,\"msg\":\""+msg+"\"}");
        return;
    }
    private void response403(HttpServletResponse response,String msg) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"code\":403,\"msg\":\""+msg+"\"}");
        return;
    }

}
