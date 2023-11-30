package cn.code51.dashboard.modules.tool.convert.test;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.modules.tool.controller.test.vo.ToolTestDemoCreateReqVO;
import cn.code51.dashboard.modules.tool.controller.test.vo.ToolTestDemoExcelVO;
import cn.code51.dashboard.modules.tool.controller.test.vo.ToolTestDemoRespVO;
import cn.code51.dashboard.modules.tool.controller.test.vo.ToolTestDemoUpdateReqVO;
import cn.code51.dashboard.modules.tool.dal.dataobject.test.ToolTestDemoDO;
import cn.code51.dashboard.modules.tool.dal.dataobject.test.ToolTestDemoDO.ToolTestDemoDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-19T10:37:36+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
public class ToolTestDemoConvertImpl implements ToolTestDemoConvert {

    @Override
    public ToolTestDemoDO convert(ToolTestDemoCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        ToolTestDemoDOBuilder toolTestDemoDO = ToolTestDemoDO.builder();

        toolTestDemoDO.name( bean.getName() );
        toolTestDemoDO.status( bean.getStatus() );
        toolTestDemoDO.type( bean.getType() );
        toolTestDemoDO.category( bean.getCategory() );
        toolTestDemoDO.remark( bean.getRemark() );

        return toolTestDemoDO.build();
    }

    @Override
    public ToolTestDemoDO convert(ToolTestDemoUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        ToolTestDemoDOBuilder toolTestDemoDO = ToolTestDemoDO.builder();

        toolTestDemoDO.id( bean.getId() );
        toolTestDemoDO.name( bean.getName() );
        toolTestDemoDO.status( bean.getStatus() );
        toolTestDemoDO.type( bean.getType() );
        toolTestDemoDO.category( bean.getCategory() );
        toolTestDemoDO.remark( bean.getRemark() );

        return toolTestDemoDO.build();
    }

    @Override
    public ToolTestDemoRespVO convert(ToolTestDemoDO bean) {
        if ( bean == null ) {
            return null;
        }

        ToolTestDemoRespVO toolTestDemoRespVO = new ToolTestDemoRespVO();

        toolTestDemoRespVO.setName( bean.getName() );
        toolTestDemoRespVO.setStatus( bean.getStatus() );
        toolTestDemoRespVO.setType( bean.getType() );
        toolTestDemoRespVO.setCategory( bean.getCategory() );
        toolTestDemoRespVO.setRemark( bean.getRemark() );
        toolTestDemoRespVO.setId( bean.getId() );
        toolTestDemoRespVO.setCreateTime( bean.getCreateTime() );

        return toolTestDemoRespVO;
    }

    @Override
    public List<ToolTestDemoRespVO> convertList(List<ToolTestDemoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ToolTestDemoRespVO> list1 = new ArrayList<ToolTestDemoRespVO>( list.size() );
        for ( ToolTestDemoDO toolTestDemoDO : list ) {
            list1.add( convert( toolTestDemoDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<ToolTestDemoRespVO> convertPage(PageResult<ToolTestDemoDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<ToolTestDemoRespVO> pageResult = new PageResult<ToolTestDemoRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<ToolTestDemoExcelVO> convertList02(List<ToolTestDemoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<ToolTestDemoExcelVO> list1 = new ArrayList<ToolTestDemoExcelVO>( list.size() );
        for ( ToolTestDemoDO toolTestDemoDO : list ) {
            list1.add( toolTestDemoDOToToolTestDemoExcelVO( toolTestDemoDO ) );
        }

        return list1;
    }

    protected ToolTestDemoExcelVO toolTestDemoDOToToolTestDemoExcelVO(ToolTestDemoDO toolTestDemoDO) {
        if ( toolTestDemoDO == null ) {
            return null;
        }

        ToolTestDemoExcelVO toolTestDemoExcelVO = new ToolTestDemoExcelVO();

        toolTestDemoExcelVO.setId( toolTestDemoDO.getId() );
        toolTestDemoExcelVO.setName( toolTestDemoDO.getName() );
        toolTestDemoExcelVO.setStatus( toolTestDemoDO.getStatus() );
        toolTestDemoExcelVO.setType( toolTestDemoDO.getType() );
        toolTestDemoExcelVO.setCategory( toolTestDemoDO.getCategory() );
        toolTestDemoExcelVO.setRemark( toolTestDemoDO.getRemark() );
        toolTestDemoExcelVO.setCreateTime( toolTestDemoDO.getCreateTime() );

        return toolTestDemoExcelVO;
    }
}
