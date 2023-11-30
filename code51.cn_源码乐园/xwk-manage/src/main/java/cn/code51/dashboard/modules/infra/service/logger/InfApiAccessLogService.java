package cn.code51.dashboard.modules.infra.service.logger;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.logger.apilog.core.service.ApiAccessLogFrameworkService;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExportReqVO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import cn.code51.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;

import java.util.List;

/**
 * API 访问日志 Service 接口
 *
 * @author 教研源码
 */
public interface InfApiAccessLogService extends ApiAccessLogFrameworkService {

    /**
     * 获得 API 访问日志分页
     *
     * @param pageReqVO 分页查询
     * @return API 访问日志分页
     */
    PageResult<InfApiAccessLogDO> getApiAccessLogPage(InfApiAccessLogPageReqVO pageReqVO);

    /**
     * 获得 API 访问日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return API 访问日志分页
     */
    List<InfApiAccessLogDO> getApiAccessLogList(InfApiAccessLogExportReqVO exportReqVO);

}
