package cn.code51.dashboard.modules.infra.dal.mysql.logger;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExportReqVO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import cn.code51.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * API 错误日志 Mapper
 *
 * @author 教研源码
 */
@Mapper
public interface InfApiErrorLogMapper extends BaseMapperX<InfApiErrorLogDO> {

    default PageResult<InfApiErrorLogDO> selectPage(InfApiErrorLogPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<InfApiErrorLogDO>()
                .eqIfPresent("user_id", reqVO.getUserId())
                .eqIfPresent("user_type", reqVO.getUserType())
                .eqIfPresent("application_name", reqVO.getApplicationName())
                .likeIfPresent("request_url", reqVO.getRequestUrl())
                .betweenIfPresent("exception_time", reqVO.getBeginExceptionTime(), reqVO.getEndExceptionTime())
                .eqIfPresent("process_status", reqVO.getProcessStatus())
                .orderByDesc("id")
        );
    }

    default List<InfApiErrorLogDO> selectList(InfApiErrorLogExportReqVO reqVO) {
        return selectList(new QueryWrapperX<InfApiErrorLogDO>()
                .eqIfPresent("user_id", reqVO.getUserId())
                .eqIfPresent("user_type", reqVO.getUserType())
                .eqIfPresent("application_name", reqVO.getApplicationName())
                .likeIfPresent("request_url", reqVO.getRequestUrl())
                .betweenIfPresent("exception_time", reqVO.getBeginExceptionTime(), reqVO.getEndExceptionTime())
                .eqIfPresent("process_status", reqVO.getProcessStatus())
				.orderByDesc("id")
        );
    }

}
