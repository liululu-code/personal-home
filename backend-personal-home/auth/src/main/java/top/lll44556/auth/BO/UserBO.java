package top.lll44556.auth.BO;

import common.lll44556.top.bean.BaseBO;

/**
 * @author liululu
 * @date 2026/9/24 17:28
 * @description:
 */
public class UserBO extends BaseBO {
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像URL
     */
    private String avatarUrl;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户状态，1正常，0禁用
     */
    private Integer status;

    /**
     * 最后登录时间，时间戳毫秒
     */
    private Long lastLoginTime;
}
