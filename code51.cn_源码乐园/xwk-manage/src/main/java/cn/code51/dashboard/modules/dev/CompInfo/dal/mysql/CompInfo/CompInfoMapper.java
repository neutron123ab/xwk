package cn.code51.dashboard.modules.dev.CompInfo.dal.mysql.CompInfo;

import java.util.*;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;

import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoExportReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoPageReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo.CompInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 竞赛 Mapper
 *
 * @author coder
 */
@Mapper
public interface CompInfoMapper extends BaseMapperX<CompInfoDO> {

    default PageResult<CompInfoDO> selectPage(CompInfoPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<CompInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("condition_info", reqVO.getConditionInfo())
                .eqIfPresent("xk_type", reqVO.getXkType())
                .eqIfPresent("dept_id", reqVO.getDeptId())
                .eqIfPresent("check_status", reqVO.getCheckStatus())
                .eqIfPresent("desc_info", reqVO.getDescInfo())
                .likeIfPresent("name", reqVO.getName())
                .orderByDesc("id")        );
    }

    default List<CompInfoDO> selectList(CompInfoExportReqVO reqVO) {
        return selectList(new QueryWrapperX<CompInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("condition_info", reqVO.getConditionInfo())
                .eqIfPresent("xk_type", reqVO.getXkType())
                .eqIfPresent("dept_id", reqVO.getDeptId())
                .eqIfPresent("check_status", reqVO.getCheckStatus())
                .eqIfPresent("desc_info", reqVO.getDescInfo())
                .likeIfPresent("name", reqVO.getName())
                .orderByDesc("id")        );
    }

}
