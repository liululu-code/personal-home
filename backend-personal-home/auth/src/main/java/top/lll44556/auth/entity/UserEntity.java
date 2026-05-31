package top.lll44556.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 * 用户表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user")
public class UserEntity {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    /**
     * 登录账号
     */
    @Column(name = "username", length = 100)
    private String username;

    /**
     * 登录密码，社交登录用户可为空
     */
    @Column(name = "password")
    private String password;

    /**
     * 真实姓名
     */
    @Column(name = "real_name", length = 100)
    private String realName;

    /**
     * 用户昵称
     */
    @Column(name = "nickname", length = 100)
    private String nickname;

    /**
     * 头像地址
     */
    @Column(name = "avatar", length = 500)
    private String avatar;

    /**
     * 手机号
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email", length = 100)
    private String email;

    /**
     * 状态，1启用，0禁用
     */
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * 最后登录时间，Unix时间戳毫秒
     */
    @Column(name = "last_login_time")
    private Long lastLoginTime;

    /**
     * 备注
     */
    @Column(name = "remark", length = 500)
    private String remark;

    /**
     * 创建时间，Unix时间戳毫秒
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新时间，Unix时间戳毫秒
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * 逻辑删除标识，1有效，0无效
     */
    @ColumnDefault("1")
    @Column(name = "valid", nullable = false)
    private Integer valid;

}