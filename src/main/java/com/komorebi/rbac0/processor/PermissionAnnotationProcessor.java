package com.komorebi.rbac0.processor;

import com.komorebi.rbac0.mapper.PermissionMapper;
import com.komorebi.rbac0.model.Permission;
import com.komorebi.rbac0.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
public class PermissionAnnotationProcessor implements BeanPostProcessor {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanType = bean.getClass();
        String baseRouter="";
        Annotation[] classAnnotations = beanType.getDeclaredAnnotations();
        Boolean notController = true;
        for (Annotation annotation : classAnnotations) {
            // 处理每个注解
            if (annotation instanceof RequestMapping) {
                RequestMapping customAnnotation = (RequestMapping) annotation;
                String classPath = customAnnotation.value()[0];
                baseRouter = classPath;
                notController = false;
                break;
            } else if (annotation instanceof RestController){
                notController = false;
            }
        }
        // 不是controller直接让返回
        if (notController) {
           return bean;
        }
        Method[] methods = beanType.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ApiOperation.class)) {
                ApiOperation annotation = method.getAnnotation(ApiOperation.class);
                String name = annotation.value();
                String desc = annotation.notes();
                // 打印其他注解信息
                Annotation[] annotations = method.getDeclaredAnnotations();
                String httpMethod = "";
                String router = "";
                Boolean flag = false;
                String path = "";
                for (Annotation anno : annotations) {
                    String annoStr = anno.annotationType().getSimpleName();
                    switch (annoStr) {
                        case "GetMapping":
                            httpMethod = "Get";
                            GetMapping getMapping = method.getAnnotation(GetMapping.class);
                            path = getMapping.value()[0];
                            flag = true;
                            break;
                        case "PostMapping":
                            httpMethod = "Post";
                            PostMapping postMapping = method.getAnnotation(PostMapping.class);
                            path = postMapping.value()[0];
                            flag = true;
                            break;
                        case "PutMapping":
                            httpMethod = "Put";
                            PutMapping putMapping = method.getAnnotation(PutMapping.class);
                            path = putMapping.value()[0];
                            flag = true;
                            break;
                        case "DeleteMapping":
                            httpMethod = "Delete";
                            DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                            path = deleteMapping.value()[0];
                            flag = true;
                            break;
                        default:
                    }
                    if (flag) {
                        router = baseRouter+path;
                        break;
                    }
                }
                Permission p = new Permission();
                p.setName(name);
                p.setDescription(desc);
                p.setMethod(httpMethod);
                p.setRouter(router);
                LocalDateTime now = LocalDateTime.now();
                p.setCreateAt(now);
                p.setUpdateAt(now);
                permissionMapper.upsert(p);
            }
        }
        return bean;
    }
}
