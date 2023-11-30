package cn.code51.dashboard.modules.system.convert.dept;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.system.controller.dept.vo.post.*;
import cn.code51.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysPostConvert {

    SysPostConvert INSTANCE = Mappers.getMapper(SysPostConvert.class);

    List<SysPostSimpleRespVO> convertList02(List<SysPostDO> list);

    PageResult<SysPostRespVO> convertPage(PageResult<SysPostDO> page);

    SysPostRespVO convert(SysPostDO id);

    SysPostDO convert(SysPostCreateReqVO bean);

    SysPostDO convert(SysPostUpdateReqVO reqVO);

    List<SysPostExcelVO> convertList03(List<SysPostDO> list);

}
