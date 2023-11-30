package cn.code51.dashboard.modules.system.dal.mysql.dict;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import cn.code51.dashboard.framework.mybatis.core.query.QueryWrapperX;
import cn.code51.dashboard.modules.system.controller.dict.vo.type.SysDictTypeExportReqVO;
import cn.code51.dashboard.modules.system.controller.dict.vo.type.SysDictTypePageReqVO;
import cn.code51.dashboard.modules.system.dal.dataobject.dict.SysDictTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictTypeMapper extends BaseMapperX<SysDictTypeDO> {

    default PageResult<SysDictTypeDO> selectPage(SysDictTypePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysDictTypeDO>()
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("`type`", reqVO.getType())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime()));
    }

    default List<SysDictTypeDO> selectList(SysDictTypeExportReqVO reqVO) {
        return selectList(new QueryWrapperX<SysDictTypeDO>()
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("`type`", reqVO.getType())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime()));
    }

    default SysDictTypeDO selectByType(String type) {
        return selectOne(new QueryWrapperX<SysDictTypeDO>().eq("`type`", type));
    }

    default SysDictTypeDO selectByName(String name) {
        return selectOne(new QueryWrapperX<SysDictTypeDO>().eq("name", name));
    }

}
