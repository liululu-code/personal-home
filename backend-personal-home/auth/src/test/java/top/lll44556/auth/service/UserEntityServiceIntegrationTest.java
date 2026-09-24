package top.lll44556.auth.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.convert.DataSizeUnit;
import top.lll44556.auth.base.AbstractIntegrationTest;

/**
 * @author liululu
 * @date 2026/9/24 16:48
 * @description:
 */
public class UserEntityServiceIntegrationTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("查询用户信息")
    public void queryUserInfo() {
        UserService userService = getBean(UserService.class);
        userService.queryUserInfo("USER_ID_1");
    }

}
