package cn.code51.dashboard.modules.dev.CompInfo.convert.CompInfo;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoCreateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoExcelVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoRespVO;
import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.CompInfoUpdateReqVO;
import cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo.CompInfoDO;
import cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo.CompInfoDO.CompInfoDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-19T10:37:36+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class CompInfoConvertImpl implements CompInfoConvert {

    @Override
    public CompInfoDO convert(CompInfoCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        CompInfoDOBuilder compInfoDO = CompInfoDO.builder();

        compInfoDO.conditionInfo( bean.getConditionInfo() );
        compInfoDO.xkType( bean.getXkType() );
        compInfoDO.deptId( bean.getDeptId() );
        compInfoDO.checkStatus( bean.getCheckStatus() );
        compInfoDO.descInfo( bean.getDescInfo() );
        compInfoDO.name( bean.getName() );

        return compInfoDO.build();
    }

    @Override
    public CompInfoDO convert(CompInfoUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        CompInfoDOBuilder compInfoDO = CompInfoDO.builder();

        compInfoDO.id( bean.getId() );
        compInfoDO.conditionInfo( bean.getConditionInfo() );
        compInfoDO.xkType( bean.getXkType() );
        compInfoDO.deptId( bean.getDeptId() );
        compInfoDO.checkStatus( bean.getCheckStatus() );
        compInfoDO.descInfo( bean.getDescInfo() );
        compInfoDO.name( bean.getName() );

        return compInfoDO.build();
    }

    @Override
    public CompInfoRespVO convert(CompInfoDO bean) {
        if ( bean == null ) {
            return null;
        }

        CompInfoRespVO compInfoRespVO = new CompInfoRespVO();

        compInfoRespVO.setConditionInfo( bean.getConditionInfo() );
        compInfoRespVO.setXkType( bean.getXkType() );
        compInfoRespVO.setDeptId( bean.getDeptId() );
        compInfoRespVO.setCheckStatus( bean.getCheckStatus() );
        compInfoRespVO.setDescInfo( bean.getDescInfo() );
        compInfoRespVO.setName( bean.getName() );
        compInfoRespVO.setId( bean.getId() );
        compInfoRespVO.setCreateTime( bean.getCreateTime() );

        return compInfoRespVO;
    }

    @Override
    public List<CompInfoRespVO> convertList(List<CompInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<CompInfoRespVO> list1 = new ArrayList<CompInfoRespVO>( list.size() );
        for ( CompInfoDO compInfoDO : list ) {
            list1.add( convert( compInfoDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<CompInfoRespVO> convertPage(PageResult<CompInfoDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<CompInfoRespVO> pageResult = new PageResult<CompInfoRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<CompInfoExcelVO> convertList02(List<CompInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<CompInfoExcelVO> list1 = new ArrayList<CompInfoExcelVO>( list.size() );
        for ( CompInfoDO compInfoDO : list ) {
            list1.add( compInfoDOToCompInfoExcelVO( compInfoDO ) );
        }

        return list1;
    }

    protected CompInfoExcelVO compInfoDOToCompInfoExcelVO(CompInfoDO compInfoDO) {
        if ( compInfoDO == null ) {
            return null;
        }

        CompInfoExcelVO compInfoExcelVO = new CompInfoExcelVO();

        compInfoExcelVO.setId( compInfoDO.getId() );
        compInfoExcelVO.setCreateTime( compInfoDO.getCreateTime() );
        compInfoExcelVO.setConditionInfo( compInfoDO.getConditionInfo() );
        compInfoExcelVO.setXkType( compInfoDO.getXkType() );
        compInfoExcelVO.setDeptId( compInfoDO.getDeptId() );
        compInfoExcelVO.setCheckStatus( compInfoDO.getCheckStatus() );
        compInfoExcelVO.setDescInfo( compInfoDO.getDescInfo() );
        compInfoExcelVO.setName( compInfoDO.getName() );

        return compInfoExcelVO;
    }
}
