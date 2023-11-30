package cn.code51.dashboard.modules.infra.convert.logger;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.logger.apilog.core.service.dto.ApiAccessLogCreateDTO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExcelVO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogRespVO;
import cn.code51.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 访问日志 Convert
 *
 * @author 教研源码
 */
@Mapper
public interface InfApiAccessLogConvert {

    InfApiAccessLogConvert INSTANCE = Mappers.getMapper(InfApiAccessLogConvert.class);

    InfApiAccessLogDO convert(ApiAccessLogCreateDTO bean);

    InfApiAccessLogRespVO convert(InfApiAccessLogDO bean);

    List<InfApiAccessLogRespVO> convertList(List<InfApiAccessLogDO> list);

    PageResult<InfApiAccessLogRespVO> convertPage(PageResult<InfApiAccessLogDO> page);

    List<InfApiAccessLogExcelVO> convertList02(List<InfApiAccessLogDO> list);

}
