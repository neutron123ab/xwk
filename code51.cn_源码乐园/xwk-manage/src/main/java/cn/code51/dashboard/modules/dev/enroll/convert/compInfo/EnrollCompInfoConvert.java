package cn.code51.dashboard.modules.dev.enroll.convert.compInfo;

import java.util.*;

import cn.code51.dashboard.common.pojo.PageResult;

import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoExcelVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoRespVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo.EnrollCompInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 竞赛报名 Convert
 *
 * @author 教研源码
 */
@Mapper
public interface EnrollCompInfoConvert {

    EnrollCompInfoConvert INSTANCE = Mappers.getMapper(EnrollCompInfoConvert.class);

    EnrollCompInfoDO convert(EnrollCompInfoCreateReqVO bean);

    EnrollCompInfoDO convert(EnrollCompInfoUpdateReqVO bean);

    EnrollCompInfoRespVO convert(EnrollCompInfoDO bean);

    List<EnrollCompInfoRespVO> convertList(List<EnrollCompInfoDO> list);

    PageResult<EnrollCompInfoRespVO> convertPage(PageResult<EnrollCompInfoDO> page);

    List<EnrollCompInfoExcelVO> convertList02(List<EnrollCompInfoDO> list);

}
