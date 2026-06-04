package top.lll44556.auth.entity;

import common.lll44556.top.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 * 系统权限表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_permission")
@AttributeOverrides({
        @AttributeOverride(name = "createdTime", column = @Column(name = "created_time")),
        @AttributeOverride(name = "operator", column = @Column(name = "operator", length = 200))
})
public class Permission extends BaseEntity {
    /**
     * 父级权限ID，根节点为空
     */
    @Column(name = "c_parent_id", length = 32)
    private String parentId;

    /**
     * 权限编码，例如system:user:list
     */
    @Column(name = "c_permission_code", nullable = false, length = 200)
    private String permissionCode;

    /**
     * 权限名称
     */
    @Column(name = "c_permission_name", nullable = false, length = 200)
    private String permissionName;

    /**
     * 权限类型，例如MENU菜单、BUTTON按钮、API接口
     */
    @Column(name = "c_permission_type", nullable = false, length = 50)
    private String permissionType;

    /**
     * 前端路由路径
     */
    @Column(name = "c_route_path", length = 500)
    private String routePath;

    /**
     * 前端组件路径
     */
    @Column(name = "c_component_path", length = 500)
    private String componentPath;

    /**
     * 菜单图标
     */
    @Column(name = "c_icon", length = 100)
    private String icon;

    /**
     * 排序号，数值越小越靠前
     */
    @ColumnDefault("0")
    @Column(name = "c_sort_no")
    private Integer sortNo;

    /**
     * 是否可见，1可见，0隐藏
     */
    @ColumnDefault("1")
    @Column(name = "c_visible", nullable = false)
    private Integer visible;

    /**
     * 权限状态，1正常，0禁用
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