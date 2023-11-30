package cn.code51.dashboard.modules.dev.enroll.dal.mysql.compInfo;

import java.util.*;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;

import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoExportReqVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoPageReqVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoRespVO;
import cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo.EnrollCompInfoDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 竞赛报名 Mapper
 *
 * @author 教研源码
 */
@Mapper
public interface EnrollCompInfoMapper extends BaseMapperX<EnrollCompInfoDO> {

    default PageResult<EnrollCompInfoDO> selectPage(EnrollCompInfoPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<EnrollCompInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("comp_name", reqVO.getCompName())
                .eqIfPresent("enroller_id", reqVO.getEnrollerId())
                .likeIfPresent("enroller_name", reqVO.getEnrollerName())
                .eqIfPresent("status_info", reqVO.getStatusInfo())
                .orderByDesc("id")        );
    }

    default List<EnrollCompInfoDO> selectList(EnrollCompInfoExportReqVO reqVO) {
        return selectList(new QueryWrapperX<EnrollCompInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("comp_name", reqVO.getCompName())
                .eqIfPresent("enroller_id", reqVO.getEnrollerId())
                .likeIfPresent("enroller_name", reqVO.getEnrollerName())
                .eqIfPresent("status_info", reqVO.getStatusInfo())
                .orderByDesc("id")        );
    }


    default List<EnrollCompInfoDO> selectList(EnrollCompInfoRespVO reqVO) {
        return selectList(new QueryWrapperX<EnrollCompInfoDO>()
              .eq("deleted", false)
              .eqIfPresent("enroller_id", reqVO.getEnrollerId())
              .likeIfPresent("comp_name", reqVO.getCompName())
              .orderByDesc("id")        );
    }

}
