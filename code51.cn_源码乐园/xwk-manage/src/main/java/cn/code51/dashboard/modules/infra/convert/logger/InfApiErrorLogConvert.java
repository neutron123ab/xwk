package cn.code51.dashboard.modules.infra.convert.logger;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.logger.apilog.core.service.dto.ApiErrorLogCreateDTO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExcelVO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogRespVO;
import cn.code51.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 错误日志 Convert
 *
 * @author 教研源码
 */
@Mapper
public interface InfApiErrorLogConvert {

    InfApiErrorLogConvert INSTANCE = Mappers.getMapper(InfApiErrorLogConvert.class);

    InfApiErrorLogDO convert(ApiErrorLogCreateDTO bean);

    InfApiErrorLogRespVO convert(InfApiErrorLogDO bean);

    PageResult<InfApiErrorLogRespVO> convertPage(PageResult<InfApiErrorLogDO> page);

    List<InfApiErrorLogExcelVO> convertList02(List<InfApiErrorLogDO> list);

}
