package top.lll44556.auth.service;

import top.lll44556.auth.BO.UserBO;

/**
 * @author liululu
 * @date 2026/9/24 16:44
 * @description:
 */
public interface UserService {
    UserBO findById(String id);
}
