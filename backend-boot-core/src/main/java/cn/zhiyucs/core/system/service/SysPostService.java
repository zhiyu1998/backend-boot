package cn.zhiyucs.core.system.service;

import cn.zhiyucs.common.basic.service.BaseService;
import cn.zhiyucs.core.system.entity.SysPostEntity;
import cn.zhiyucs.core.system.query.SysPostQuery;
import cn.zhiyucs.core.system.vo.SysPostVO;
import cn.zhiyucs.common.utils.PageResult;

import java.util.List;

/**
 * 岗位管理
 *
 * @author zhiyu1998
 */
public interface SysPostService extends BaseService<SysPostEntity> {

    PageResult<SysPostVO> page(SysPostQuery query);

    List<SysPostVO> getList();

    void save(SysPostVO vo);

    void update(SysPostVO vo);

    void delete(List<Long> idList);
}