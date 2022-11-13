package cn.zhiyucs.core.system.service;

import cn.zhiyucs.common.basic.service.BaseService;
import cn.zhiyucs.core.system.entity.SysOrgEntity;
import cn.zhiyucs.core.system.vo.SysOrgVO;

import java.util.List;

/**
 * 机构管理
 *
 * @author zhiyu1998
 */
public interface SysOrgService extends BaseService<SysOrgEntity> {

    List<SysOrgVO> getList();

    void save(SysOrgVO vo);

    void update(SysOrgVO vo);

    void delete(Long id);

    /**
     * 根据机构ID，获取子机构ID列表(包含本机构ID)
     *
     * @param id 机构ID
     */
    List<Long> getSubOrgIdList(Long id);
}