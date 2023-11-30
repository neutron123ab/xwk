package cn.code51.dashboard.modules.infra.convert.file;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.infra.dal.dataobject.file.InfFileDO;
import cn.code51.dashboard.modules.infra.controller.file.vo.InfFileRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InfFileConvert {

    InfFileConvert INSTANCE = Mappers.getMapper(InfFileConvert.class);

    InfFileRespVO convert(InfFileDO bean);

    PageResult<InfFileRespVO> convertPage(PageResult<InfFileDO> page);

}
