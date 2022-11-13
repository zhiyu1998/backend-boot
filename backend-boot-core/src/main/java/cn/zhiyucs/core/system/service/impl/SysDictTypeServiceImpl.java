package cn.zhiyucs.core.system.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.zhiyucs.common.basic.service.impl.BaseServiceImpl;
import cn.zhiyucs.core.system.convert.SysDictTypeConvert;
import cn.zhiyucs.core.system.dao.SysDictDataDao;
import cn.zhiyucs.core.system.dao.SysDictTypeDao;
import cn.zhiyucs.core.system.entity.SysDictDataEntity;
import cn.zhiyucs.core.system.entity.SysDictTypeEntity;
import cn.zhiyucs.core.system.query.SysDictTypeQuery;
import cn.zhiyucs.core.system.service.SysDictTypeService;
import cn.zhiyucs.core.system.vo.SysDictTypeVO;
import cn.zhiyucs.core.system.vo.SysDictVO;
import cn.zhiyucs.common.utils.PageResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型
 *
 * @author zhiyu1998
 */
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {

    @Resource
    private SysDictDataDao sysDictDataDao;

    @Override
    public PageResult<SysDictTypeVO> page(SysDictTypeQuery query) {
        IPage<SysDictTypeEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));
        return new PageResult<>(SysDictTypeConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private Wrapper<SysDictTypeEntity> getWrapper(SysDictTypeQuery query) {
        LambdaQueryWrapper<SysDictTypeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(CharSequenceUtil.isNotBlank(query.getDictType()), SysDictTypeEntity::getDictType, query.getDictType());
        wrapper.like(CharSequenceUtil.isNotBlank(query.getDictName()), SysDictTypeEntity::getDictName, query.getDictName());
        wrapper.orderByAsc(SysDictTypeEntity::getSort);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictTypeVO vo) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictTypeVO vo) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public List<SysDictVO> getDictList() {
        // 全部字典类型列表
        List<SysDictTypeEntity> typeList = this.list(Wrappers.emptyWrapper());

        // 全部字典数据列表
        QueryWrapper<SysDictDataEntity> query = new QueryWrapper<SysDictDataEntity>().orderByAsc("sort");
        List<SysDictDataEntity> dataList = sysDictDataDao.selectList(query);

        // 全部字典列表
        List<SysDictVO> dictList = new ArrayList<>(typeList.size());
        for (SysDictTypeEntity type : typeList) {
            SysDictVO dict = new SysDictVO();
            dict.setDictType(type.getDictType());

            for (SysDictDataEntity data : dataList) {
                if (type.getId().equals(data.getDictTypeId())) {
                    dict.getDataList().add(new SysDictVO.DictData(data.getDictLabel(), data.getDictValue()));
                }
            }

            dictList.add(dict);
        }

        return dictList;
    }

}