package cn.code51.dashboard.modules.dev.workinfo.service.workInfo.impl;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoExportReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoPageReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.convert.workInfo.EnrollWorkInfoConvert;
import cn.code51.dashboard.modules.dev.workinfo.dal.dataobject.workInfo.EnrollWorkInfoDO;
import cn.code51.dashboard.modules.dev.workinfo.dal.mysql.workInfo.EnrollWorkInfoMapper;
import cn.code51.dashboard.modules.dev.workinfo.service.workInfo.EnrollWorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * 参赛作品 Service 实现类
 *
 * @author 教研源码
 */
@Service
@Validated
public class EnrollWorkInfoServiceImpl implements EnrollWorkInfoService {

    @Resource
    private EnrollWorkInfoMapper enrollWorkInfoMapper;

    @Override
    public Long createWorkInfo(EnrollWorkInfoCreateReqVO createReqVO) {
        // 插入
        EnrollWorkInfoDO workInfo = EnrollWorkInfoConvert.INSTANCE.convert(createReqVO);
        workInfo.setStatusInfo("待审核");
        workInfo.setGetScore("--");
        enrollWorkInfoMapper.insert(workInfo);
        // 返回
        return workInfo.getId();
    }

    @Override
    public void updateWorkInfo(EnrollWorkInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateWorkInfoExists(updateReqVO.getId());
        // 更新
        EnrollWorkInfoDO updateObj = EnrollWorkInfoConvert.INSTANCE.convert(updateReqVO);
        enrollWorkInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkInfo(Long id) {
        // 校验存在
        this.validateWorkInfoExists(id);
        // 更新
        enrollWorkInfoMapper.deleteById(id);
    }

    private void validateWorkInfoExists(Long id) {
        if (enrollWorkInfoMapper.selectById(id) == null) {

        }
    }

    @Override
    public EnrollWorkInfoDO getWorkInfo(Long id) {
        return enrollWorkInfoMapper.selectById(id);
    }

    @Override
    public List<EnrollWorkInfoDO> getWorkInfoList(Collection<Long> ids) {
        return enrollWorkInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EnrollWorkInfoDO> getWorkInfoPage(EnrollWorkInfoPageReqVO pageReqVO) {
        return enrollWorkInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EnrollWorkInfoDO> getWorkInfoList(EnrollWorkInfoExportReqVO exportReqVO) {
        return enrollWorkInfoMapper.selectList(exportReqVO);
    }

}
