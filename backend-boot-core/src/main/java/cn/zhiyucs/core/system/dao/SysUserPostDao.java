package cn.zhiyucs.core.system.dao;

import cn.zhiyucs.common.basic.dao.BaseDao;
import cn.zhiyucs.core.system.entity.SysUserPostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户岗位关系
 *
 * @author zhiyu1998
 */
@Mapper
public interface SysUserPostDao extends BaseDao<SysUserPostEntity> {

    /**
     * 岗位ID列表
     *
     * @param userId 用户ID
     */
    List<Long> getPostIdList(@Param("userId") Long userId);
}