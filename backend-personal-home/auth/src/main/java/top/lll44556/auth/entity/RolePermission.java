package top.lll44556.auth.entity;

import common.lll44556.top.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统角色权限关联表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role_permission")
@AttributeOverrides({
        @AttributeOverride(name = "createdTime", column = @Column(name = "created_time")),
        @AttributeOverride(name = "operator", column = @Column(name = "operator", length = 200))
})
public class RolePermission extends BaseEntity {
    /**
     * 角色ID，关联sys_role.id
     */
    @Column(name = "c_role_id", nullable = false, length = 32)
    private String roleId;

    /**
     * 权限ID，关联sys_permission.id
     */
    @Column(name = "c_permission_id", nullable = false, length = 32)
    private String permissionId;

}