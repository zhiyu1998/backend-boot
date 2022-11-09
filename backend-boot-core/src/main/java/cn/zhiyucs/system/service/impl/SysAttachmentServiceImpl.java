package cn.zhiyucs.system.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.zhiyucs.basic.service.impl.BaseServiceImpl;
import cn.zhiyucs.system.convert.SysAttachmentConvert;
import cn.zhiyucs.system.dao.SysAttachmentDao;
import cn.zhiyucs.system.entity.SysAttachmentEntity;
import cn.zhiyucs.system.query.SysAttachmentQuery;
import cn.zhiyucs.system.service.SysAttachmentService;
import cn.zhiyucs.system.vo.SysAttachmentVO;
import cn.zhiyucs.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 附件管理
 *
 * @author zhiyu1998
 */
@Service
public class SysAttachmentServiceImpl extends BaseServiceImpl<SysAttachmentDao, SysAttachmentEntity> implements SysAttachmentService {

    @Override
    public PageResult<SysAttachmentVO> page(SysAttachmentQuery query) {
        IPage<SysAttachmentEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysAttachmentConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<SysAttachmentEntity> getWrapper(SysAttachmentQuery query) {
        LambdaQueryWrapper<SysAttachmentEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CharSequenceUtil.isNotBlank(query.getPlatform()), SysAttachmentEntity::getPlatform, query.getPlatform());
        wrapper.like(CharSequenceUtil.isNotBlank(query.getName()), SysAttachmentEntity::getName, query.getName());
        wrapper.orderByDesc(SysAttachmentEntity::getId);
        return wrapper;
    }

    @Override
    public void save(SysAttachmentVO vo) {
        SysAttachmentEntity entity = SysAttachmentConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysAttachmentVO vo) {
        SysAttachmentEntity entity = SysAttachmentConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}