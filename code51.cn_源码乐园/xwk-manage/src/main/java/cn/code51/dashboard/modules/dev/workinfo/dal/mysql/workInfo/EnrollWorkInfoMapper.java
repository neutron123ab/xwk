package cn.code51.dashboard.modules.dev.workinfo.dal.mysql.workInfo;

import java.util.*;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;

import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoExportReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoPageReqVO;
import cn.code51.dashboard.modules.dev.workinfo.dal.dataobject.workInfo.EnrollWorkInfoDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 参赛作品 Mapper
 *
 * @author 教研源码
 */
@Mapper
public interface EnrollWorkInfoMapper extends BaseMapperX<EnrollWorkInfoDO> {

    default PageResult<EnrollWorkInfoDO> selectPage(EnrollWorkInfoPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<EnrollWorkInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("comp_name", reqVO.getCompName())
                .eqIfPresent("enroller_id", reqVO.getEnrollerId())
                .likeIfPresent("enroller_name", reqVO.getEnrollerName())
                .eqIfPresent("status_info", reqVO.getStatusInfo())
                .eqIfPresent("work_info", reqVO.getWorkInfo())
                .eqIfPresent("get_score", reqVO.getGetScore())
                .orderByDesc("id")        );
    }

    default List<EnrollWorkInfoDO> selectList(EnrollWorkInfoExportReqVO reqVO) {
        return selectList(new QueryWrapperX<EnrollWorkInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("comp_name", reqVO.getCompName())
                .eqIfPresent("enroller_id", reqVO.getEnrollerId())
                .likeIfPresent("enroller_name", reqVO.getEnrollerName())
                .eqIfPresent("status_info", reqVO.getStatusInfo())
                .eqIfPresent("work_info", reqVO.getWorkInfo())
                .eqIfPresent("get_score", reqVO.getGetScore())
                .orderByDesc("id")        );
    }

}
