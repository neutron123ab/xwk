package cn.code51.dashboard.modules.dev.CompInfo.service.CompInfo.impl;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoExportReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoPageReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.convert.CompInfo.CompInfoConvert;
import cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo.CompInfoDO;
import cn.code51.dashboard.modules.dev.CompInfo.dal.mysql.CompInfo.CompInfoMapper;
import cn.code51.dashboard.modules.dev.CompInfo.service.CompInfo.CompInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;


/**
 * 竞赛 Service 实现类
 *
 * @author coder
 */
@Service
@Validated
public class CompInfoServiceImpl implements CompInfoService {

    @Resource
    private CompInfoMapper compInfoMapper;

    @Override
    public Long createInfo(CompInfoCreateReqVO createReqVO) {
        // 插入
        CompInfoDO info = CompInfoConvert.INSTANCE.convert(createReqVO);
        compInfoMapper.insert(info);
        // 返回
        return info.getId();
    }

    @Override
    public void updateInfo(CompInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateInfoExists(updateReqVO.getId());
        // 更新
        CompInfoDO updateObj = CompInfoConvert.INSTANCE.convert(updateReqVO);
        compInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteInfo(Long id) {
        // 校验存在
        this.validateInfoExists(id);
        // 更新
        compInfoMapper.deleteById(id);
    }

    private void validateInfoExists(Long id) {
        if (compInfoMapper.selectById(id) == null) {

        }
    }

    @Override
    public CompInfoDO getInfo(Long id) {
        return compInfoMapper.selectById(id);
    }

    @Override
    public List<CompInfoDO> getInfoList(Collection<Long> ids) {
        return compInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CompInfoDO> getInfoPage(CompInfoPageReqVO pageReqVO) {
        return compInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CompInfoDO> getInfoList(CompInfoExportReqVO exportReqVO) {
        return compInfoMapper.selectList(exportReqVO);
    }

}
