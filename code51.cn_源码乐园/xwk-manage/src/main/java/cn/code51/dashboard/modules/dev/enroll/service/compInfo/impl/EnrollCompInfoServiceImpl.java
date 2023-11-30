package cn.code51.dashboard.modules.dev.enroll.service.compInfo.impl;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.*;
import cn.code51.dashboard.modules.dev.enroll.convert.compInfo.EnrollCompInfoConvert;
import cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo.EnrollCompInfoDO;
import cn.code51.dashboard.modules.dev.enroll.dal.mysql.compInfo.EnrollCompInfoMapper;
import cn.code51.dashboard.modules.dev.enroll.service.compInfo.EnrollCompInfoService;
import cn.code51.dashboard.util.collection.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * 竞赛报名 Service 实现类
 *
 * @author 教研源码
 */
@Service
@Validated
public class EnrollCompInfoServiceImpl implements EnrollCompInfoService {

    @Resource
    private EnrollCompInfoMapper enrollCompInfoMapper;

    @Override
    public Long createCompInfo(EnrollCompInfoCreateReqVO createReqVO) {
        // 插入
        EnrollCompInfoDO compInfo = EnrollCompInfoConvert.INSTANCE.convert(createReqVO);
        compInfo.setStatusInfo("待审核");
        enrollCompInfoMapper.insert(compInfo);
        // 返回
        return compInfo.getId();
    }

    @Override
    public void updateCompInfo(EnrollCompInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCompInfoExists(updateReqVO.getId());
        // 更新
        EnrollCompInfoDO updateObj = EnrollCompInfoConvert.INSTANCE.convert(updateReqVO);
        enrollCompInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompInfo(Long id) {
        // 校验存在
        this.validateCompInfoExists(id);
        // 更新
        enrollCompInfoMapper.deleteById(id);
    }

    private void validateCompInfoExists(Long id) {
        if (enrollCompInfoMapper.selectById(id) == null) {

        }
    }

    @Override
    public EnrollCompInfoDO getCompInfo(Long id) {
        return enrollCompInfoMapper.selectById(id);
    }

    @Override
    public List<EnrollCompInfoDO> getCompInfoList(Collection<Long> ids) {
        return enrollCompInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EnrollCompInfoDO> getCompInfoPage(EnrollCompInfoPageReqVO pageReqVO) {
        return enrollCompInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EnrollCompInfoDO> getCompInfoList(EnrollCompInfoExportReqVO exportReqVO) {
        return enrollCompInfoMapper.selectList(exportReqVO);
    }

    @Override
    public EnrollCompInfoDO getCompInfoByEnrollInfo(EnrollCompInfoRespVO enrollCompInfoRespVO) {
        List<EnrollCompInfoDO> enrollCompInfoDOS = enrollCompInfoMapper.selectList(enrollCompInfoRespVO);
        if(CollectionUtils.isAnyEmpty(enrollCompInfoDOS)){
            return  new EnrollCompInfoDO();
        }

        return  enrollCompInfoDOS.get(0);
    }

    @Override
    public List<EnrollCompInfoDO> getCompInfoByEnrollInfoList(EnrollCompInfoRespVO enrollCompInfoRespVO) {
        List<EnrollCompInfoDO> enrollCompInfoDOS = enrollCompInfoMapper.selectList(enrollCompInfoRespVO);
        if(CollectionUtils.isAnyEmpty(enrollCompInfoDOS)){
            return  new ArrayList<>();
        }

        return  enrollCompInfoDOS;
    }
}
