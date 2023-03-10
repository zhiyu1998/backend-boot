package cn.zhiyucs.core.system.convert;


import cn.zhiyucs.core.system.entity.SysDictTypeEntity;
import cn.zhiyucs.core.system.vo.SysDictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictTypeConvert {
    SysDictTypeConvert INSTANCE = Mappers.getMapper(SysDictTypeConvert.class);

    SysDictTypeVO convert(SysDictTypeEntity entity);

    SysDictTypeEntity convert(SysDictTypeVO vo);

    List<SysDictTypeVO> convertList(List<SysDictTypeEntity> list);

}
