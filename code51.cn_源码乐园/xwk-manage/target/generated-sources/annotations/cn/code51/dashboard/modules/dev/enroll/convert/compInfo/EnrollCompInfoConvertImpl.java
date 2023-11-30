package cn.code51.dashboard.modules.dev.enroll.convert.compInfo;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoExcelVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoRespVO;
import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.EnrollCompInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo.EnrollCompInfoDO;
import cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo.EnrollCompInfoDO.EnrollCompInfoDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-19T10:37:36+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class EnrollCompInfoConvertImpl implements EnrollCompInfoConvert {

    @Override
    public EnrollCompInfoDO convert(EnrollCompInfoCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        EnrollCompInfoDOBuilder enrollCompInfoDO = EnrollCompInfoDO.builder();

        enrollCompInfoDO.compName( bean.getCompName() );
        enrollCompInfoDO.enrollerId( bean.getEnrollerId() );
        enrollCompInfoDO.enrollerName( bean.getEnrollerName() );
        enrollCompInfoDO.statusInfo( bean.getStatusInfo() );

        return enrollCompInfoDO.build();
    }

    @Override
    public EnrollCompInfoDO convert(EnrollCompInfoUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        EnrollCompInfoDOBuilder enrollCompInfoDO = EnrollCompInfoDO.builder();

        enrollCompInfoDO.id( bean.getId() );
        enrollCompInfoDO.compName( bean.getCompName() );
        enrollCompInfoDO.enrollerId( bean.getEnrollerId() );
        enrollCompInfoDO.enrollerName( bean.getEnrollerName() );
        enrollCompInfoDO.statusInfo( bean.getStatusInfo() );

        return enrollCompInfoDO.build();
    }

    @Override
    public EnrollCompInfoRespVO convert(EnrollCompInfoDO bean) {
        if ( bean == null ) {
            return null;
        }

        EnrollCompInfoRespVO enrollCompInfoRespVO = new EnrollCompInfoRespVO();

        enrollCompInfoRespVO.setEnrollerId( bean.getEnrollerId() );
        enrollCompInfoRespVO.setEnrollerName( bean.getEnrollerName() );
        enrollCompInfoRespVO.setStatusInfo( bean.getStatusInfo() );
        enrollCompInfoRespVO.setId( bean.getId() );
        enrollCompInfoRespVO.setCreateTime( bean.getCreateTime() );
        enrollCompInfoRespVO.setCompName( bean.getCompName() );

        return enrollCompInfoRespVO;
    }

    @Override
    public List<EnrollCompInfoRespVO> convertList(List<EnrollCompInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<EnrollCompInfoRespVO> list1 = new ArrayList<EnrollCompInfoRespVO>( list.size() );
        for ( EnrollCompInfoDO enrollCompInfoDO : list ) {
            list1.add( convert( enrollCompInfoDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<EnrollCompInfoRespVO> convertPage(PageResult<EnrollCompInfoDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<EnrollCompInfoRespVO> pageResult = new PageResult<EnrollCompInfoRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<EnrollCompInfoExcelVO> convertList02(List<EnrollCompInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<EnrollCompInfoExcelVO> list1 = new ArrayList<EnrollCompInfoExcelVO>( list.size() );
        for ( EnrollCompInfoDO enrollCompInfoDO : list ) {
            list1.add( enrollCompInfoDOToEnrollCompInfoExcelVO( enrollCompInfoDO ) );
        }

        return list1;
    }

    protected EnrollCompInfoExcelVO enrollCompInfoDOToEnrollCompInfoExcelVO(EnrollCompInfoDO enrollCompInfoDO) {
        if ( enrollCompInfoDO == null ) {
            return null;
        }

        EnrollCompInfoExcelVO enrollCompInfoExcelVO = new EnrollCompInfoExcelVO();

        enrollCompInfoExcelVO.setId( enrollCompInfoDO.getId() );
        enrollCompInfoExcelVO.setCreateTime( enrollCompInfoDO.getCreateTime() );
        enrollCompInfoExcelVO.setCompName( enrollCompInfoDO.getCompName() );
        enrollCompInfoExcelVO.setEnrollerId( enrollCompInfoDO.getEnrollerId() );
        enrollCompInfoExcelVO.setEnrollerName( enrollCompInfoDO.getEnrollerName() );
        enrollCompInfoExcelVO.setStatusInfo( enrollCompInfoDO.getStatusInfo() );

        return enrollCompInfoExcelVO;
    }
}
