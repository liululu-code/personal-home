package top.lll44556.auth.entity;

import common.lll44556.top.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 * 系统用户表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user")
@AttributeOverrides({
        @AttributeOverride(name = "createdTime", column = @Column(name = "created_time")),
        @AttributeOverride(name = "operator", column = @Column(name = "operator", length = 200))
})
public class User extends BaseEntity {
    /**
     * 用户昵称
     */
    @Column(name = "c_nickname", length = 100)
    private String nickname;

    /**
     * 用户头像URL
     */
    @Column(name = "c_avatar_url", length = Integer.MAX_VALUE)
    private String avatarUrl;

    /**
     * 真实姓名
     */
    @Column(name = "c_real_name", length = 100)
    private String realName;

    /**
     * 用户状态，1正常，0禁用
     */
    @ColumnDefault("1")
    @Column(name = "c_status", nullable = false)
    private Integer status;

    /**
     * 最后登录时间，时间戳毫秒
     */
    @Column(name = "c_last_login_time")
    private Long lastLoginTime;

}