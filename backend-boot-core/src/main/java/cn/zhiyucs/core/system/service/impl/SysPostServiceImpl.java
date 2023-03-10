package cn.zhiyucs.core.system.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.zhiyucs.common.basic.service.impl.BaseServiceImpl;
import cn.zhiyucs.core.system.convert.SysPostConvert;
import cn.zhiyucs.core.system.dao.SysPostDao;
import cn.zhiyucs.core.system.entity.SysPostEntity;
import cn.zhiyucs.core.system.query.SysPostQuery;
import cn.zhiyucs.core.system.service.SysPostService;
import cn.zhiyucs.core.system.service.SysUserPostService;
import cn.zhiyucs.core.system.vo.SysPostVO;
import cn.zhiyucs.common.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 岗位管理
 *
 * @author zhiyu1998
 */
@Service
public class SysPostServiceImpl extends BaseServiceImpl<SysPostDao, SysPostEntity> implements SysPostService {

    @Resource
    private SysUserPostService sysUserPostService;

    @Override
    public PageResult<SysPostVO> page(SysPostQuery query) {
        IPage<SysPostEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysPostConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<SysPostVO> getList() {
        SysPostQuery query = new SysPostQuery();
        //正常岗位列表
        query.setStatus(1);
        List<SysPostEntity> entityList = baseMapper.selectList(getWrapper(query));

        return SysPostConvert.INSTANCE.convertList(entityList);
    }

    private Wrapper<SysPostEntity> getWrapper(SysPostQuery query) {
        LambdaQueryWrapper<SysPostEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(CharSequenceUtil.isNotBlank(query.getPostCode()), SysPostEntity::getPostCode, query.getPostCode());
        wrapper.like(CharSequenceUtil.isNotBlank(query.getPostName()), SysPostEntity::getPostName, query.getPostName());
        wrapper.eq(query.getStatus() != null, SysPostEntity::getStatus, query.getStatus());
        wrapper.orderByAsc(SysPostEntity::getSort);

        return wrapper;
    }

    @Override
    public void save(SysPostVO vo) {
        SysPostEntity entity = SysPostConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysPostVO vo) {
        SysPostEntity entity = SysPostConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        // 删除岗位
        removeByIds(idList);

        // 删除岗位用户关系
        sysUserPostService.deleteByPostIdList(idList);
    }

}