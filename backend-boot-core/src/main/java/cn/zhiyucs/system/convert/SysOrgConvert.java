package cn.zhiyucs.system.convert;


import cn.zhiyucs.system.entity.SysOrgEntity;
import cn.zhiyucs.system.vo.SysOrgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysOrgConvert {
    SysOrgConvert INSTANCE = Mappers.getMapper(SysOrgConvert.class);

    SysOrgEntity convert(SysOrgVO vo);

    SysOrgVO convert(SysOrgEntity entity);

    List<SysOrgVO> convertList(List<SysOrgEntity> list);

}
