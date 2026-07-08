package top.lll44556.auth.entity;

import common.lll44556.top.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统用户角色关联表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user_role")
@AttributeOverrides({
        @AttributeOverride(name = "createdTime", column = @Column(name = "created_time")),
        @AttributeOverride(name = "operator", column = @Column(name = "operator", length = 200))
})
public class UserRole extends BaseEntity {
    /**
     * 用户ID，关联sys_user.id
     */
    @Column(name = "c_user_id", nullable = false, length = 32)
    private String userId;

    /**
     * 角色ID，关联sys_role.id
     */
    @Column(name = "c_role_id", nullable = false, length = 32)
    private String roleId;

}