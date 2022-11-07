package cn.zhiyucs.system.entity;

import cn.zhiyucs.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户岗位关系
 *
 * @author zhiyu1998
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_post")
public class SysUserPostEntity extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 岗位ID
     */
    private Long postId;
}