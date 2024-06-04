package com.komorebi.rbac0.mapper;

import com.komorebi.rbac0.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserTest {


    @Mock
    private UserMapper usersMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertUser() {
        // 创建一个测试用户
        User testUser = new User();
        testUser.setUsername("testUser");
        testUser.setPassword("testPassword");
        testUser.setIsActivce(true);

        // 模拟JUsersMapper的insertUser方法，返回1表示插入成功
        when(usersMapper.insert(testUser)).thenReturn(1);

        // 调用user类的insertUser方法
        int rowsInserted = usersMapper.insert(testUser);

        // 验证插入操作是否成功
        assertEquals(1, rowsInserted, "User should be inserted successfully.");
    }
}