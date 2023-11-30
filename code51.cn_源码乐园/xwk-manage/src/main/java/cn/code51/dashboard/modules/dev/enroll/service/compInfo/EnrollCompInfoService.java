package cn.code51.dashboard.modules.dev.enroll.service.compInfo;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.*;
import cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo.EnrollCompInfoDO;

import java.util.*;
import javax.validation.*;


/**
 * 竞赛报名 Service 接口
 *
 * @author 教研源码
 */
public interface EnrollCompInfoService {

    /**
     * 创建竞赛报名
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompInfo(@Valid EnrollCompInfoCreateReqVO createReqVO);

    /**
     * 更新竞赛报名
     *
     * @param updateReqVO 更新信息
     */
    void updateCompInfo(@Valid EnrollCompInfoUpdateReqVO updateReqVO);

    /**
     * 删除竞赛报名
     *
     * @param id 编号
     */
    void deleteCompInfo(Long id);

    /**
     * 获得竞赛报名
     *
     * @param id 编号
     * @return 竞赛报名
     */
    EnrollCompInfoDO getCompInfo(Long id);

    /**
     * 获得竞赛报名列表
     *
     * @param ids 编号
     * @return 竞赛报名列表
     */
    List<EnrollCompInfoDO> getCompInfoList(Collection<Long> ids);

    /**
     * 获得竞赛报名分页
     *
     * @param pageReqVO 分页查询
     * @return 竞赛报名分页
     */
    PageResult<EnrollCompInfoDO> getCompInfoPage(EnrollCompInfoPageReqVO pageReqVO);

    /**
     * 获得竞赛报名列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 竞赛报名分页
     */
    List<EnrollCompInfoDO> getCompInfoList(EnrollCompInfoExportReqVO exportReqVO);

    EnrollCompInfoDO getCompInfoByEnrollInfo(EnrollCompInfoRespVO enrollCompInfoRespVO);

    List<EnrollCompInfoDO> getCompInfoByEnrollInfoList(EnrollCompInfoRespVO enrollCompInfoRespVO);

}
