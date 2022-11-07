package cn.zhiyucs.system.service;

import cn.zhiyucs.common.service.BaseService;
import cn.zhiyucs.system.entity.SysDictTypeEntity;
import cn.zhiyucs.system.query.SysDictTypeQuery;
import cn.zhiyucs.system.vo.SysDictTypeVO;
import cn.zhiyucs.system.vo.SysDictVO;
import cn.zhiyucs.utils.PageResult;

import java.util.List;

/**
 * 数据字典
 *
 * @author zhiyu1998
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageResult<SysDictTypeVO> page(SysDictTypeQuery query);

    void save(SysDictTypeVO vo);

    void update(SysDictTypeVO vo);

    void delete(List<Long> idList);

    /**
     * 获取全部字典列表
     */
    List<SysDictVO> getDictList();

}