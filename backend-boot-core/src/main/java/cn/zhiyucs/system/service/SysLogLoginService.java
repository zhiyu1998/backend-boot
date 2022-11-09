package cn.zhiyucs.system.service;

import cn.zhiyucs.service.BaseService;
import cn.zhiyucs.system.entity.SysLogLoginEntity;
import cn.zhiyucs.system.query.SysLogLoginQuery;
import cn.zhiyucs.system.vo.SysLogLoginVO;
import cn.zhiyucs.utils.PageResult;


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