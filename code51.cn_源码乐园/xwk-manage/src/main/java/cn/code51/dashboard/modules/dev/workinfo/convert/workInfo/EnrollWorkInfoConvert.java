package cn.code51.dashboard.modules.dev.workinfo.convert.workInfo;

import java.util.*;

import cn.code51.dashboard.common.pojo.PageResult;

import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoExcelVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoRespVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.dal.dataobject.workInfo.EnrollWorkInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 参赛作品 Convert
 *
 * @author 教研源码
 */
@Mapper
public interface EnrollWorkInfoConvert {

    EnrollWorkInfoConvert INSTANCE = Mappers.getMapper(EnrollWorkInfoConvert.class);

    EnrollWorkInfoDO convert(EnrollWorkInfoCreateReqVO bean);

    EnrollWorkInfoDO convert(EnrollWorkInfoUpdateReqVO bean);

    EnrollWorkInfoRespVO convert(EnrollWorkInfoDO bean);

    List<EnrollWorkInfoRespVO> convertList(List<EnrollWorkInfoDO> list);

    PageResult<EnrollWorkInfoRespVO> convertPage(PageResult<EnrollWorkInfoDO> page);

    List<EnrollWorkInfoExcelVO> convertList02(List<EnrollWorkInfoDO> list);

}
