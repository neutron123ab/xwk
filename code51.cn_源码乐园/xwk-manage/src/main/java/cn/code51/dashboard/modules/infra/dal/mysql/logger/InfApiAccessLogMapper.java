package cn.code51.dashboard.modules.infra.dal.mysql.logger;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExportReqVO;
import cn.code51.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import cn.code51.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * API 访问日志 Mapper
 *
 * @author 教研源码
 */
@Mapper
public interface InfApiAccessLogMapper extends BaseMapperX<InfApiAccessLogDO> {

    default PageResult<InfApiAccessLogDO> selectPage(InfApiAccessLogPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<InfApiAccessLogDO>()
                .eqIfPresent("user_id", reqVO.getUserId())
                .eqIfPresent("user_type", reqVO.getUserType())
                .eqIfPresent("application_name", reqVO.getApplicationName())
                .likeIfPresent("request_url", reqVO.getRequestUrl())
                .betweenIfPresent("begin_time", reqVO.getBeginBeginTime(), reqVO.getEndBeginTime())
                .geIfPresent("duration", reqVO.getDuration())
                .eqIfPresent("result_code", reqVO.getResultCode())
                .orderByDesc("id")
        );
    }

    default List<InfApiAccessLogDO> selectList(InfApiAccessLogExportReqVO reqVO) {
        return selectList(new QueryWrapperX<InfApiAccessLogDO>()
                .eqIfPresent("user_id", reqVO.getUserId())
                .eqIfPresent("user_type", reqVO.getUserType())
                .eqIfPresent("application_name", reqVO.getApplicationName())
                .likeIfPresent("request_url", reqVO.getRequestUrl())
                .betweenIfPresent("begin_time", reqVO.getBeginBeginTime(), reqVO.getEndBeginTime())
                .geIfPresent("duration", reqVO.getDuration())
                .eqIfPresent("result_code", reqVO.getResultCode())
                .orderByDesc("id")
        );
    }

}
