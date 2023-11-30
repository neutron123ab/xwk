package cn.code51.dashboard.modules.dev.workinfo.convert.workInfo;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoExcelVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoRespVO;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.workinfo.dal.dataobject.workInfo.EnrollWorkInfoDO;
import cn.code51.dashboard.modules.dev.workinfo.dal.dataobject.workInfo.EnrollWorkInfoDO.EnrollWorkInfoDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-19T10:37:36+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class EnrollWorkInfoConvertImpl implements EnrollWorkInfoConvert {

    @Override
    public EnrollWorkInfoDO convert(EnrollWorkInfoCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        EnrollWorkInfoDOBuilder enrollWorkInfoDO = EnrollWorkInfoDO.builder();

        enrollWorkInfoDO.compName( bean.getCompName() );
        enrollWorkInfoDO.enrollerId( bean.getEnrollerId() );
        enrollWorkInfoDO.enrollerName( bean.getEnrollerName() );
        enrollWorkInfoDO.statusInfo( bean.getStatusInfo() );
        enrollWorkInfoDO.workInfo( bean.getWorkInfo() );
        enrollWorkInfoDO.getScore( bean.getGetScore() );

        return enrollWorkInfoDO.build();
    }

    @Override
    public EnrollWorkInfoDO convert(EnrollWorkInfoUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        EnrollWorkInfoDOBuilder enrollWorkInfoDO = EnrollWorkInfoDO.builder();

        enrollWorkInfoDO.id( bean.getId() );
        enrollWorkInfoDO.compName( bean.getCompName() );
        enrollWorkInfoDO.enrollerId( bean.getEnrollerId() );
        enrollWorkInfoDO.enrollerName( bean.getEnrollerName() );
        enrollWorkInfoDO.statusInfo( bean.getStatusInfo() );
        enrollWorkInfoDO.workInfo( bean.getWorkInfo() );
        enrollWorkInfoDO.getScore( bean.getGetScore() );

        return enrollWorkInfoDO.build();
    }

    @Override
    public EnrollWorkInfoRespVO convert(EnrollWorkInfoDO bean) {
        if ( bean == null ) {
            return null;
        }

        EnrollWorkInfoRespVO enrollWorkInfoRespVO = new EnrollWorkInfoRespVO();

        enrollWorkInfoRespVO.setCompName( bean.getCompName() );
        enrollWorkInfoRespVO.setEnrollerId( bean.getEnrollerId() );
        enrollWorkInfoRespVO.setEnrollerName( bean.getEnrollerName() );
        enrollWorkInfoRespVO.setStatusInfo( bean.getStatusInfo() );
        enrollWorkInfoRespVO.setWorkInfo( bean.getWorkInfo() );
        enrollWorkInfoRespVO.setGetScore( bean.getGetScore() );
        enrollWorkInfoRespVO.setId( bean.getId() );
        enrollWorkInfoRespVO.setCreateTime( bean.getCreateTime() );

        return enrollWorkInfoRespVO;
    }

    @Override
    public List<EnrollWorkInfoRespVO> convertList(List<EnrollWorkInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<EnrollWorkInfoRespVO> list1 = new ArrayList<EnrollWorkInfoRespVO>( list.size() );
        for ( EnrollWorkInfoDO enrollWorkInfoDO : list ) {
            list1.add( convert( enrollWorkInfoDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<EnrollWorkInfoRespVO> convertPage(PageResult<EnrollWorkInfoDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<EnrollWorkInfoRespVO> pageResult = new PageResult<EnrollWorkInfoRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<EnrollWorkInfoExcelVO> convertList02(List<EnrollWorkInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<EnrollWorkInfoExcelVO> list1 = new ArrayList<EnrollWorkInfoExcelVO>( list.size() );
        for ( EnrollWorkInfoDO enrollWorkInfoDO : list ) {
            list1.add( enrollWorkInfoDOToEnrollWorkInfoExcelVO( enrollWorkInfoDO ) );
        }

        return list1;
    }

    protected EnrollWorkInfoExcelVO enrollWorkInfoDOToEnrollWorkInfoExcelVO(EnrollWorkInfoDO enrollWorkInfoDO) {
        if ( enrollWorkInfoDO == null ) {
            return null;
        }

        EnrollWorkInfoExcelVO enrollWorkInfoExcelVO = new EnrollWorkInfoExcelVO();

        enrollWorkInfoExcelVO.setId( enrollWorkInfoDO.getId() );
        enrollWorkInfoExcelVO.setCreateTime( enrollWorkInfoDO.getCreateTime() );
        enrollWorkInfoExcelVO.setCompName( enrollWorkInfoDO.getCompName() );
        enrollWorkInfoExcelVO.setEnrollerId( enrollWorkInfoDO.getEnrollerId() );
        enrollWorkInfoExcelVO.setEnrollerName( enrollWorkInfoDO.getEnrollerName() );
        enrollWorkInfoExcelVO.setStatusInfo( enrollWorkInfoDO.getStatusInfo() );
        enrollWorkInfoExcelVO.setWorkInfo( enrollWorkInfoDO.getWorkInfo() );
        enrollWorkInfoExcelVO.setGetScore( enrollWorkInfoDO.getGetScore() );

        return enrollWorkInfoExcelVO;
    }
}
