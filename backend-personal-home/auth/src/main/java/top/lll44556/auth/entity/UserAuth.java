package top.lll44556.auth.entity;

import common.lll44556.top.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录身份表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user_auth")
@AttributeOverrides({
        @AttributeOverride(name = "createdTime", column = @Column(name = "created_time")),
        @AttributeOverride(name = "operator", column = @Column(name = "operator", length = 200))
})
public class UserAuth extends BaseEntity {
    /**
     * 用户ID，关联sys_user.id
     */
    @Column(name = "c_user_id", nullable = false, length = 32)
    private String userId;

    /**
     * 登录身份类型，例如LOCAL、SOCIAL、PHONE、EMAIL
     */
    @Column(name = "c_identity_type", nullable = false, length = 32)
    private String identityType;

    /**
     * 登录提供方，例如LOCAL、WECHAT、GITHUB、GOOGLE、QQ
     */
    @Column(name = "c_provider", nullable = false, length = 32)
    private String provider;

    /**
     * 登录唯一标识。本地账号可存用户名、手机号、邮箱；社交账号可存openid、sub、第三方用户ID
     */
    @Column(name = "c_identifier", nullable = false)
    private String identifier;

    /**
     * 第三方平台统一ID，例如微信unionid，可为空
     */
    @Column(name = "c_union_id")
    private String unionId;

    /**
     * 密码哈希值，仅本地账号使用，社交账号为空
     */
    @Column(name = "c_password_hash")
    private String passwordHash;

    /**
     * 密码加密算法，例如BCrypt、Argon2
     */
    @Column(name = "c_password_algo", length = 50)
    private String passwordAlgo;

    /**
     * 第三方平台昵称
     */
    @Column(name = "c_provider_nickname", length = 100)
    private String providerNickname;

    /**
     * 第三方平台头像URL
     */
    @Column(name = "c_provider_avatar_url", length = Integer.MAX_VALUE)
    private String providerAvatarUrl;

    /**
     * 绑定时间，时间戳毫秒
     */
    @Column(name = "c_bind_time")
    private Long bindTime;

    /**
     * 该登录身份最后登录时间，时间戳毫秒
     */
    @Column(name = "c_last_login_time")
    private Long lastLoginTime;

}