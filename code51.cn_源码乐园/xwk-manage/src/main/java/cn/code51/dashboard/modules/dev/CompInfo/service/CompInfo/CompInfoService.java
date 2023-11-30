package cn.code51.dashboard.modules.dev.CompInfo.service.CompInfo;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoExportReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoPageReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo.CompInfoDO;

import java.util.*;
import javax.validation.*;


/**
 * 竞赛 Service 接口
 *
 * @author coder
 */
public interface CompInfoService {

    /**
     * 创建竞赛
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInfo(@Valid CompInfoCreateReqVO createReqVO);

    /**
     * 更新竞赛
     *
     * @param updateReqVO 更新信息
     */
    void updateInfo(@Valid CompInfoUpdateReqVO updateReqVO);

    /**
     * 删除竞赛
     *
     * @param id 编号
     */
    void deleteInfo(Long id);

    /**
     * 获得竞赛
     *
     * @param id 编号
     * @return 竞赛
     */
    CompInfoDO getInfo(Long id);

    /**
     * 获得竞赛列表
     *
     * @param ids 编号
     * @return 竞赛列表
     */
    List<CompInfoDO> getInfoList(Collection<Long> ids);

    /**
     * 获得竞赛分页
     *
     * @param pageReqVO 分页查询
     * @return 竞赛分页
     */
    PageResult<CompInfoDO> getInfoPage(CompInfoPageReqVO pageReqVO);

    /**
     * 获得竞赛列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 竞赛分页
     */
    List<CompInfoDO> getInfoList(CompInfoExportReqVO exportReqVO);

}
