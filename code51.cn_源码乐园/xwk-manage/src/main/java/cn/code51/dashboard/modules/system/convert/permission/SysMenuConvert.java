package cn.code51.dashboard.modules.system.convert.permission;

import cn.code51.dashboard.modules.system.controller.permission.vo.menu.SysMenuCreateReqVO;
import cn.code51.dashboard.modules.system.controller.permission.vo.menu.SysMenuRespVO;
import cn.code51.dashboard.modules.system.controller.permission.vo.menu.SysMenuSimpleRespVO;
import cn.code51.dashboard.modules.system.controller.permission.vo.menu.SysMenuUpdateReqVO;
import cn.code51.dashboard.modules.system.dal.dataobject.permission.SysMenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysMenuConvert {

    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    List<SysMenuRespVO> convertList(List<SysMenuDO> list);

    SysMenuDO convert(SysMenuCreateReqVO bean);

    SysMenuDO convert(SysMenuUpdateReqVO bean);

    SysMenuRespVO convert(SysMenuDO bean);

    List<SysMenuSimpleRespVO> convertList02(List<SysMenuDO> list);

}
