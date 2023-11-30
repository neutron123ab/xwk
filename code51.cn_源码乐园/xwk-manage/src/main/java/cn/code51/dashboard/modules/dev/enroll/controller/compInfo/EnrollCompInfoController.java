package cn.code51.dashboard.modules.dev.enroll.controller.compInfo;

import cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo.*;
import cn.code51.dashboard.modules.dev.enroll.convert.compInfo.EnrollCompInfoConvert;
import cn.code51.dashboard.modules.dev.enroll.dal.dataobject.compInfo.EnrollCompInfoDO;
import cn.code51.dashboard.modules.dev.enroll.service.compInfo.EnrollCompInfoService;
import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.EnrollWorkInfoExportReqVO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.common.pojo.CommonResult;
import static cn.code51.dashboard.common.pojo.CommonResult.success;

import cn.code51.dashboard.framework.excel.core.util.ExcelUtils;

import cn.code51.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import static cn.code51.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum.*;


@Api(tags = "竞赛报名")
@RestController
@RequestMapping("/enroll/comp-info")
@Validated
public class EnrollCompInfoController {

    @Resource
    private EnrollCompInfoService compInfoService;

    @PostMapping("/create")
    @ApiOperation("创建竞赛报名")

    public CommonResult<Long> createCompInfo(@Valid @RequestBody EnrollCompInfoCreateReqVO createReqVO) {
        return success(compInfoService.createCompInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新竞赛报名")
    public CommonResult<Boolean> updateCompInfo(@Valid @RequestBody EnrollCompInfoUpdateReqVO updateReqVO) {
        compInfoService.updateCompInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除竞赛报名")
    @ApiImplicitParam(name = "id", value = "编号", required = true)
    public CommonResult<Boolean> deleteCompInfo(@RequestParam("id") Long id) {
        compInfoService.deleteCompInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得竞赛报名")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<EnrollCompInfoRespVO> getCompInfo(@RequestParam("id") Long id) {
        EnrollCompInfoDO compInfo = compInfoService.getCompInfo(id);
        return success(EnrollCompInfoConvert.INSTANCE.convert(compInfo));
    }

    @RequestMapping("/getCompInfoByEnrollInfo")
    public CommonResult<EnrollCompInfoRespVO> getCompInfoByEnrollInfo( @RequestBody EnrollCompInfoRespVO enrollCompInfoRespVO) {
       EnrollCompInfoDO  enrollCompInfoDO= compInfoService.getCompInfoByEnrollInfo(enrollCompInfoRespVO);
        return success(EnrollCompInfoConvert.INSTANCE.convert(enrollCompInfoDO));
    }

    //通过 报名人id和  竞赛名字  获取已报名记录
    @RequestMapping("/getCompInfoByEnrollInfoList")
    public CommonResult<List<EnrollCompInfoRespVO>> getCompInfoByEnrollInfoList( @RequestBody EnrollCompInfoRespVO enrollCompInfoRespVO) {
        List<EnrollCompInfoDO>  enrollCompInfoDOs= compInfoService.getCompInfoByEnrollInfoList(enrollCompInfoRespVO);
        List<EnrollCompInfoRespVO> enrollCompInfoRespVOS = EnrollCompInfoConvert.INSTANCE.convertList(enrollCompInfoDOs);
        return success(enrollCompInfoRespVOS);
    }
    @GetMapping("/list")
    @ApiOperation("获得竞赛报名列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    public CommonResult<List<EnrollCompInfoRespVO>> getCompInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<EnrollCompInfoDO> list = compInfoService.getCompInfoList(ids);
        return success(EnrollCompInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得竞赛报名分页")

    public CommonResult<PageResult<EnrollCompInfoRespVO>> getCompInfoPage(@Valid EnrollCompInfoPageReqVO pageVO) {
        PageResult<EnrollCompInfoDO> pageResult = compInfoService.getCompInfoPage(pageVO);
        return success(EnrollCompInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出竞赛报名 Excel")

    @OperateLog(type = EXPORT)
    public void exportCompInfoExcel(@Valid EnrollCompInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EnrollCompInfoDO> list = compInfoService.getCompInfoList(exportReqVO);
        // 导出 Excel
        List<EnrollCompInfoExcelVO> datas = EnrollCompInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "竞赛报名.xls", "数据", EnrollCompInfoExcelVO.class, datas);
    }

}
