package top.lll44556.auth.entity;

import common.lll44556.top.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 * 系统角色表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role")
@AttributeOverrides({
        @AttributeOverride(name = "createdTime", column = @Column(name = "created_time")),
        @AttributeOverride(name = "operator", column = @Column(name = "operator", length = 200))
})
public class Role extends BaseEntity {
    /**
     * 角色编码
     */
    @Column(name = "c_role_code", nullable = false, length = 100)
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "c_role_name", nullable = false, length = 100)
    private String roleName;

    /**
     * 排序号，数值越小越靠前
     */
    @ColumnDefault("0")
    @Column(name = "c_sort_no")
    private Integer sortNo;

    /**
     * 角色状态，1正常，0禁用
     */
    @ColumnDefault("1")
    @Column(name = "c_status", nullable = false)
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "c_remark", length = 500)
    private String remark;

}