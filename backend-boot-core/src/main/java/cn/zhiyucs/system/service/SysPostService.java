package cn.zhiyucs.system.service;

import cn.zhiyucs.basic.service.BaseService;
import cn.zhiyucs.system.entity.SysPostEntity;
import cn.zhiyucs.system.query.SysPostQuery;
import cn.zhiyucs.system.vo.SysPostVO;
import cn.zhiyucs.utils.PageResult;

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