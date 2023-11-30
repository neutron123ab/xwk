package cn.code51.dashboard.modules.system.service.dept;

import cn.code51.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import cn.code51.dashboard.util.collection.CollectionUtils;
import cn.hutool.core.collection.CollUtil;
import cn.code51.dashboard.modules.system.controller.dept.vo.dept.SysDeptCreateReqVO;
import cn.code51.dashboard.modules.system.controller.dept.vo.dept.SysDeptListReqVO;
import cn.code51.dashboard.modules.system.controller.dept.vo.dept.SysDeptUpdateReqVO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 学院 Service 接口
 *
 * @author 教研源码
 */
public interface SysDeptService {

    /**
     * 初始化学院的本地缓存
     */
    void initLocalCache();

    /**
     * 创建学院
     *
     * @param reqVO 学院信息
     * @return 学院编号
     */
    Long createDept(SysDeptCreateReqVO reqVO);

    /**
     * 更新学院
     *
     * @param reqVO 学院信息
     */
    void updateDept(SysDeptUpdateReqVO reqVO);

    /**
     * 删除学院
     *
     * @param id 学院编号
     */
    void deleteDept(Long id);

    /**
     * 获得指定编号的学院列表
     *
     * @param ids 学院编号数组
     * @return 学院列表
     */
    List<SysDeptDO> getSimpleDepts(Collection<Long> ids);

    /**
     * 获得指定编号的学院 Map
     *
     * @param ids 学院编号数组
     * @return 学院 Map
     */
    default Map<Long, SysDeptDO> getDeptMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<SysDeptDO> list = getSimpleDepts(ids);
        return CollectionUtils.convertMap(list, SysDeptDO::getId);
    }

    /**
     * 筛选学院列表
     *
     * @param reqVO 筛选条件请求 VO
     * @return 学院列表
     */
    List<SysDeptDO> getSimpleDepts(SysDeptListReqVO reqVO);

    /**
     * 获得学院信息
     *
     * @param id 学院编号
     * @return 学院信息
     */
    SysDeptDO getDept(Long id);

    /**
     * 获得所有子学院，从缓存中
     *
     * @param parentId 学院编号
     * @param recursive 是否递归获取所有
     * @return 子学院列表
     */
    List<SysDeptDO> getDeptsByParentIdFromCache(Long parentId, boolean recursive);

    List<SysDeptDO> getSubDepts(SysDeptListReqVO reqVO);

}
