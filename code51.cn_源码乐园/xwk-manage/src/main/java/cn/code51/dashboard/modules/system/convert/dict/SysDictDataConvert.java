package cn.code51.dashboard.modules.system.convert.dict;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.system.controller.dict.vo.data.*;
import cn.code51.dashboard.modules.system.dal.dataobject.dict.SysDictDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictDataConvert {

    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    List<SysDictDataSimpleVO> convertList(List<SysDictDataDO> list);

    SysDictDataRespVO convert(SysDictDataDO bean);

    PageResult<SysDictDataRespVO> convertPage(PageResult<SysDictDataDO> page);

    SysDictDataDO convert(SysDictDataUpdateReqVO bean);

    SysDictDataDO convert(SysDictDataCreateReqVO bean);

    List<SysDictDataExcelVO> convertList02(List<SysDictDataDO> bean);

}
