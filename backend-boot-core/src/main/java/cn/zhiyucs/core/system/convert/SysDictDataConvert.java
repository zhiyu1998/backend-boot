package cn.zhiyucs.core.system.convert;


import cn.zhiyucs.core.system.entity.SysDictDataEntity;
import cn.zhiyucs.core.system.vo.SysDictDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictDataConvert {
    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    SysDictDataVO convert(SysDictDataEntity entity);

    SysDictDataEntity convert(SysDictDataVO vo);

    List<SysDictDataVO> convertList(List<SysDictDataEntity> list);

}
