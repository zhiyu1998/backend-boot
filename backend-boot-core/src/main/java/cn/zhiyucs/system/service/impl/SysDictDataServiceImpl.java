package cn.zhiyucs.system.service.impl;

import cn.zhiyucs.common.service.impl.BaseServiceImpl;
import cn.zhiyucs.system.convert.SysDictDataConvert;
import cn.zhiyucs.system.dao.SysDictDataDao;
import cn.zhiyucs.system.entity.SysDictDataEntity;
import cn.zhiyucs.system.query.SysDictDataQuery;
import cn.zhiyucs.system.service.SysDictDataService;
import cn.zhiyucs.system.vo.SysDictDataVO;
import cn.zhiyucs.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典
 *
 * @author zhiyu1998
 */
@Service
@AllArgsConstructor
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataDao, SysDictDataEntity> implements SysDictDataService {

    @Override
    public PageResult<SysDictDataVO> page(SysDictDataQuery query) {
        IPage<SysDictDataEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysDictDataConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private Wrapper<SysDictDataEntity> getWrapper(SysDictDataQuery query) {
        LambdaQueryWrapper<SysDictDataEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictDataEntity::getDictTypeId, query.getDictTypeId());
        wrapper.orderByAsc(SysDictDataEntity::getSort);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDataVO vo) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDataVO vo) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}