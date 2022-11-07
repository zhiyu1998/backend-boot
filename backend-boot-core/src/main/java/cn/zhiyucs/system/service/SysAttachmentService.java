package cn.zhiyucs.system.service;

import cn.zhiyucs.common.service.BaseService;
import cn.zhiyucs.system.entity.SysAttachmentEntity;
import cn.zhiyucs.system.query.SysAttachmentQuery;
import cn.zhiyucs.system.vo.SysAttachmentVO;
import cn.zhiyucs.utils.PageResult;

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