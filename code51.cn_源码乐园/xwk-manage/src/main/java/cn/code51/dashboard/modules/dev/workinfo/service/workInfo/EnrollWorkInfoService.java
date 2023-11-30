package cn.code51.dashboard.modules.dev.workinfo.service.workInfo;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoExportReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoPageReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.dal.dataobject.workInfo.EnrollWorkInfoDO;

import java.util.*;
import javax.validation.*;


/**
 * 参赛作品 Service 接口
 *
 * @author 教研源码
 */
public interface EnrollWorkInfoService {

    /**
     * 创建参赛作品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorkInfo(@Valid EnrollWorkInfoCreateReqVO createReqVO);

    /**
     * 更新参赛作品
     *
     * @param updateReqVO 更新信息
     */
    void updateWorkInfo(@Valid EnrollWorkInfoUpdateReqVO updateReqVO);

    /**
     * 删除参赛作品
     *
     * @param id 编号
     */
    void deleteWorkInfo(Long id);

    /**
     * 获得参赛作品
     *
     * @param id 编号
     * @return 参赛作品
     */
    EnrollWorkInfoDO getWorkInfo(Long id);

    /**
     * 获得参赛作品列表
     *
     * @param ids 编号
     * @return 参赛作品列表
     */
    List<EnrollWorkInfoDO> getWorkInfoList(Collection<Long> ids);

    /**
     * 获得参赛作品分页
     *
     * @param pageReqVO 分页查询
     * @return 参赛作品分页
     */
    PageResult<EnrollWorkInfoDO> getWorkInfoPage(EnrollWorkInfoPageReqVO pageReqVO);

    /**
     * 获得参赛作品列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 参赛作品分页
     */
    List<EnrollWorkInfoDO> getWorkInfoList(EnrollWorkInfoExportReqVO exportReqVO);

}
