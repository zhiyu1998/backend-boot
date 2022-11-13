package cn.zhiyucs.core.system.service;

import cn.zhiyucs.common.basic.service.BaseService;
import cn.zhiyucs.core.system.entity.SysLogLoginEntity;
import cn.zhiyucs.core.system.query.SysLogLoginQuery;
import cn.zhiyucs.core.system.vo.SysLogLoginVO;
import cn.zhiyucs.common.utils.PageResult;


/**
 * 登录日志
 *
 * @author zhiyu1998
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);
}