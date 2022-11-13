package cn.zhiyucs.core.system.service;

import cn.zhiyucs.common.basic.service.BaseService;
import cn.zhiyucs.core.system.entity.SysDictDataEntity;
import cn.zhiyucs.core.system.query.SysDictDataQuery;
import cn.zhiyucs.core.system.vo.SysDictDataVO;
import cn.zhiyucs.common.utils.PageResult;

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