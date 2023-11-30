package cn.code51.dashboard.framework.logger.apilog.core.service;

import cn.code51.dashboard.framework.logger.apilog.core.service.dto.ApiErrorLogCreateDTO;

import javax.validation.Valid;
import java.util.concurrent.Future;

/**
 * API 错误日志 Framework Service 接口
 *
 * @author 教研源码
 */
public interface ApiErrorLogFrameworkService {

    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     * @return 是否创建成功
     */
    Future<Boolean> createApiErrorLogAsync(@Valid ApiErrorLogCreateDTO createDTO);

}
