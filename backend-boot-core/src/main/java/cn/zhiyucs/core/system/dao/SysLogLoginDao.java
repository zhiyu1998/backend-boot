package cn.zhiyucs.core.system.dao;

import cn.zhiyucs.common.basic.dao.BaseDao;
import cn.zhiyucs.core.system.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author zhiyu1998
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {

}