package cn.code51.dashboard.modules.system.convert.dict;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.system.controller.dict.vo.type.*;
import cn.code51.dashboard.modules.system.dal.dataobject.dict.SysDictTypeDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictTypeConvert {

    SysDictTypeConvert INSTANCE = Mappers.getMapper(SysDictTypeConvert.class);

    PageResult<SysDictTypeRespVO> convertPage(PageResult<SysDictTypeDO> bean);

    SysDictTypeRespVO convert(SysDictTypeDO bean);

    SysDictTypeDO convert(SysDictTypeCreateReqVO bean);

    SysDictTypeDO convert(SysDictTypeUpdateReqVO bean);

    List<SysDictTypeSimpleRespVO> convertList(List<SysDictTypeDO> list);

    List<SysDictTypeExcelVO> convertList02(List<SysDictTypeDO> list);

}
