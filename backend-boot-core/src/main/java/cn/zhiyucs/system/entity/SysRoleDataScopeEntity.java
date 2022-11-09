package cn.zhiyucs.system.entity;

import cn.zhiyucs.basic.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据权限
 *
 * @author zhiyu1998
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_data_scope")
public class SysRoleDataScopeEntity extends BaseEntity {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 机构ID
     */
    private Long orgId;

}