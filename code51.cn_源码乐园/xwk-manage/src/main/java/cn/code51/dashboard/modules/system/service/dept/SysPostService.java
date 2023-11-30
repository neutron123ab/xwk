package cn.code51.dashboard.modules.system.service.dept;

import cn.code51.dashboard.common.enums.CommonStatusEnum;
import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import cn.code51.dashboard.util.collection.SetUtils;
import cn.code51.dashboard.modules.system.controller.dept.vo.post.SysPostCreateReqVO;
import cn.code51.dashboard.modules.system.controller.dept.vo.post.SysPostExportReqVO;
import cn.code51.dashboard.modules.system.controller.dept.vo.post.SysPostPageReqVO;
import cn.code51.dashboard.modules.system.controller.dept.vo.post.SysPostUpdateReqVO;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * 岗位 Service 接口
 *
 * @author 教研源码
 */
public interface SysPostService {

    /**
     * 创建岗位
     *
     * @param reqVO 岗位信息
     * @return 岗位编号
     */
    Long createPost(SysPostCreateReqVO reqVO);

    /**
     * 更新岗位
     *
     * @param reqVO 岗位信息
     */
    void updatePost(SysPostUpdateReqVO reqVO);

    /**
     * 删除岗位信息
     *
     * @param id 岗位编号
     */
    void deletePost(Long id);

    /**
     * 获得岗位列表
     *
     * @param ids 岗位编号数组。如果为空，不进行筛选
     * @return 学院列表
     */
    default List<SysPostDO> getPosts(@Nullable Collection<Long> ids) {
        return getPosts(ids, SetUtils.asSet(CommonStatusEnum.ENABLE.getStatus(), CommonStatusEnum.DISABLE.getStatus()));
    }

    /**
     * 获得符合条件的岗位列表
     *
     * @param ids 岗位编号数组。如果为空，不进行筛选
     * @param statuses 状态数组。如果为空，不进行筛选
     * @return 学院列表
     */
    List<SysPostDO> getPosts(@Nullable Collection<Long> ids, @Nullable Collection<Integer> statuses);

    /**
     * 获得岗位分页列表
     *
     * @param reqVO 分页条件
     * @return 学院分页列表
     */
    PageResult<SysPostDO> getPostPage(SysPostPageReqVO reqVO);

    /**
     * 获得岗位列表
     *
     * @param reqVO 查询条件
     * @return 学院列表
     */
    List<SysPostDO> getPosts(SysPostExportReqVO reqVO);

    /**
     * 获得岗位信息
     *
     * @param id 岗位编号
     * @return 岗位信息
     */
    SysPostDO getPost(Long id);

}
