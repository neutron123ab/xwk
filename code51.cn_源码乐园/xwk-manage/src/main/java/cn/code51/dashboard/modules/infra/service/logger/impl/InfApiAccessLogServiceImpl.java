package cn.code51.dashboard.modules.infra.service.logger.impl;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.logger.apilog.core.service.dto.ApiAccessLogCreateDTO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExportReqVO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import cn.code51.dashboard.modules.infra.convert.logger.InfApiAccessLogConvert;
import cn.code51.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import cn.code51.dashboard.modules.infra.dal.mysql.logger.InfApiAccessLogMapper;
import cn.code51.dashboard.modules.infra.service.logger.InfApiAccessLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * API 访问日志 Service 实现类
 *
 * @author 教研源码
 */
@Service
@Validated
public class InfApiAccessLogServiceImpl implements InfApiAccessLogService {

    @Resource
    private InfApiAccessLogMapper apiAccessLogMapper;

    @Override
    @Async
    public Future<Boolean> createApiAccessLogAsync(ApiAccessLogCreateDTO createDTO) {
        // 插入
        InfApiAccessLogDO apiAccessLog = InfApiAccessLogConvert.INSTANCE.convert(createDTO);
        int insert = apiAccessLogMapper.insert(apiAccessLog);
        return new AsyncResult<>(insert == 1);
    }

    @Override
    public PageResult<InfApiAccessLogDO> getApiAccessLogPage(InfApiAccessLogPageReqVO pageReqVO) {
        return apiAccessLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<InfApiAccessLogDO> getApiAccessLogList(InfApiAccessLogExportReqVO exportReqVO) {
        return apiAccessLogMapper.selectList(exportReqVO);
    }

}
