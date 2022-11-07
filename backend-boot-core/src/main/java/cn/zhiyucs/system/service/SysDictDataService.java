package cn.zhiyucs.system.service;

import cn.zhiyucs.common.service.BaseService;
import cn.zhiyucs.system.entity.SysDictDataEntity;
import cn.zhiyucs.system.query.SysDictDataQuery;
import cn.zhiyucs.system.vo.SysDictDataVO;
import cn.zhiyucs.utils.PageResult;

import java.util.List;

/**
 * 数据字典
 *
 * @author zhiyu1998
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageResult<SysDictDataVO> page(SysDictDataQuery query);

    void save(SysDictDataVO vo);

    void update(SysDictDataVO vo);

    void delete(List<Long> idList);

}