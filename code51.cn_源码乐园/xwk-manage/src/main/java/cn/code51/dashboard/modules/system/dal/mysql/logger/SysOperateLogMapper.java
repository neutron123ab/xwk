package cn.code51.dashboard.modules.system.dal.mysql.logger;

import cn.code51.dashboard.common.exception.enums.GlobalErrorCodeConstants;
import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogExportReqVO;
import cn.code51.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogPageReqVO;
import cn.code51.dashboard.modules.system.dal.dataobject.logger.SysOperateLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysOperateLogMapper extends BaseMapperX<SysOperateLogDO> {

    default PageResult<SysOperateLogDO> selectPage(SysOperateLogPageReqVO reqVO, Collection<Long> userIds) {
        QueryWrapperX<SysOperateLogDO> query = new QueryWrapperX<SysOperateLogDO>()
                .likeIfPresent("module", reqVO.getModule())
                .inIfPresent("user_id", userIds)
                .eqIfPresent("operate_type", reqVO.getType())
                .betweenIfPresent("start_time", reqVO.getBeginTime(), reqVO.getEndTime());
        if (Boolean.TRUE.equals(reqVO.getSuccess())) {
            query.eq("result_code", GlobalErrorCodeConstants.SUCCESS.getCode());
        } else if (Boolean.FALSE.equals(reqVO.getSuccess())) {
            query.gt("result_code", GlobalErrorCodeConstants.SUCCESS.getCode());
        }
        query.orderByDesc("id"); // 降序
        return selectPage(reqVO, query);
    }

    default List<SysOperateLogDO> selectList(SysOperateLogExportReqVO reqVO, Collection<Long> userIds) {
        QueryWrapperX<SysOperateLogDO> query = new QueryWrapperX<SysOperateLogDO>()
                .likeIfPresent("module", reqVO.getModule())
                .inIfPresent("user_id", userIds)
                .eqIfPresent("operate_type", reqVO.getType())
                .betweenIfPresent("start_time", reqVO.getBeginTime(), reqVO.getEndTime());
        if (Boolean.TRUE.equals(reqVO.getSuccess())) {
            query.eq("result_code", GlobalErrorCodeConstants.SUCCESS.getCode());
        } else if (Boolean.FALSE.equals(reqVO.getSuccess())) {
            query.gt("result_code", GlobalErrorCodeConstants.SUCCESS.getCode());
        }
        query.orderByDesc("id"); // 降序
        return selectList(query);
    }

}
