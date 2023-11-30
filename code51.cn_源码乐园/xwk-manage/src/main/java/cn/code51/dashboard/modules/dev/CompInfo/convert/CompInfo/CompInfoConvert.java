package cn.code51.dashboard.modules.dev.CompInfo.convert.CompInfo;

import java.util.*;

import cn.code51.dashboard.common.pojo.PageResult;

import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoExcelVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoRespVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo.CompInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 竞赛 Convert
 *
 * @author coder
 */
@Mapper
public interface CompInfoConvert {

    CompInfoConvert INSTANCE = Mappers.getMapper(CompInfoConvert.class);

    CompInfoDO convert(CompInfoCreateReqVO bean);

    CompInfoDO convert(CompInfoUpdateReqVO bean);

    CompInfoRespVO convert(CompInfoDO bean);

    List<CompInfoRespVO> convertList(List<CompInfoDO> list);

    PageResult<CompInfoRespVO> convertPage(PageResult<CompInfoDO> page);

    List<CompInfoExcelVO> convertList02(List<CompInfoDO> list);

}
