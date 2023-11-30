package cn.code51.dashboard.modules.infra.service.logger;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.logger.apilog.core.service.ApiErrorLogFrameworkService;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExportReqVO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import cn.code51.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;

import java.util.List;

/**
 * API 错误日志 Service 接口
 *
 * @author 教研源码
 */
public interface InfApiErrorLogService extends ApiErrorLogFrameworkService {

    /**
     * 获得 API 错误日志分页
     *
     * @param pageReqVO 分页查询
     * @return API 错误日志分页
     */
    PageResult<InfApiErrorLogDO> getApiErrorLogPage(InfApiErrorLogPageReqVO pageReqVO);

    /**
     * 获得 API 错误日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return API 错误日志分页
     */
    List<InfApiErrorLogDO> getApiErrorLogList(InfApiErrorLogExportReqVO exportReqVO);

    /**
     * 更新 API 错误日志已处理
     *
     * @param id API 日志编号
     * @param processStatus 处理结果
     * @param processUserId 处理人
     */
    void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId);

}
