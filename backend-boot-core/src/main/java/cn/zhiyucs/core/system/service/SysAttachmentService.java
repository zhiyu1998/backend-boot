package cn.zhiyucs.core.system.service;

import cn.zhiyucs.common.basic.service.BaseService;
import cn.zhiyucs.core.system.entity.SysAttachmentEntity;
import cn.zhiyucs.core.system.query.SysAttachmentQuery;
import cn.zhiyucs.core.system.vo.SysAttachmentVO;
import cn.zhiyucs.common.utils.PageResult;

import java.util.List;

/**
 * 附件管理
 *
 * @author zhiyu1998
 */
public interface SysAttachmentService extends BaseService<SysAttachmentEntity> {

    PageResult<SysAttachmentVO> page(SysAttachmentQuery query);

    void save(SysAttachmentVO vo);

    void update(SysAttachmentVO vo);

    void delete(List<Long> idList);
}