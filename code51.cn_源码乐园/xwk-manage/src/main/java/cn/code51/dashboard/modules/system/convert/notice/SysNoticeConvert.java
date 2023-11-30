package cn.code51.dashboard.modules.system.convert.notice;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.system.controller.notice.vo.SysNoticeCreateReqVO;
import cn.code51.dashboard.modules.system.controller.notice.vo.SysNoticeRespVO;
import cn.code51.dashboard.modules.system.controller.notice.vo.SysNoticeUpdateReqVO;
import cn.code51.dashboard.modules.system.dal.dataobject.notice.SysNoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysNoticeConvert {

    SysNoticeConvert INSTANCE = Mappers.getMapper(SysNoticeConvert.class);

    PageResult<SysNoticeRespVO> convertPage(PageResult<SysNoticeDO> page);

    SysNoticeRespVO convert(SysNoticeDO bean);

    SysNoticeDO convert(SysNoticeUpdateReqVO bean);

    SysNoticeDO convert(SysNoticeCreateReqVO bean);

}
