package cn.code51.dashboard.modules.dev.workinfo.controller.workInfo;

import cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo.*;
import cn.code51.dashboard.modules.dev.workinfo.convert.workInfo.EnrollWorkInfoConvert;
import cn.code51.dashboard.modules.dev.workinfo.dal.dataobject.workInfo.EnrollWorkInfoDO;
import cn.code51.dashboard.modules.dev.workinfo.service.workInfo.EnrollWorkInfoService;
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


@Api(tags = "参赛作品")
@RestController
@RequestMapping("/enroll/work-info")
@Validated
public class EnrollWorkInfoController {

    @Resource
    private EnrollWorkInfoService enrollWorkInfoService;

    @PostMapping("/create")
    @ApiOperation("创建参赛作品")

    public CommonResult<Long> createWorkInfo(@Valid @RequestBody EnrollWorkInfoCreateReqVO createReqVO) {
        return success(enrollWorkInfoService.createWorkInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新参赛作品")

    public CommonResult<Boolean> updateWorkInfo(@Valid @RequestBody EnrollWorkInfoUpdateReqVO updateReqVO) {
        enrollWorkInfoService.updateWorkInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除参赛作品")
    @ApiImplicitParam(name = "id", value = "编号", required = true)

    public CommonResult<Boolean> deleteWorkInfo(@RequestParam("id") Long id) {
        enrollWorkInfoService.deleteWorkInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得参赛作品")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<EnrollWorkInfoRespVO> getWorkInfo(@RequestParam("id") Long id) {
        EnrollWorkInfoDO workInfo = enrollWorkInfoService.getWorkInfo(id);
        return success(EnrollWorkInfoConvert.INSTANCE.convert(workInfo));
    }

    @GetMapping("/list")
    @ApiOperation("获得参赛作品列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)

    public CommonResult<List<EnrollWorkInfoRespVO>> getWorkInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<EnrollWorkInfoDO> list = enrollWorkInfoService.getWorkInfoList(ids);
        return success(EnrollWorkInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得参赛作品分页")

    public CommonResult<PageResult<EnrollWorkInfoRespVO>> getWorkInfoPage(@Valid EnrollWorkInfoPageReqVO pageVO) {
        PageResult<EnrollWorkInfoDO> pageResult = enrollWorkInfoService.getWorkInfoPage(pageVO);
        return success(EnrollWorkInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出参赛作品 Excel")

    @OperateLog(type = EXPORT)
    public void exportWorkInfoExcel(@Valid EnrollWorkInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EnrollWorkInfoDO> list = enrollWorkInfoService.getWorkInfoList(exportReqVO);
        // 导出 Excel
        List<EnrollWorkInfoExcelVO> datas = EnrollWorkInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "参赛作品.xls", "数据", EnrollWorkInfoExcelVO.class, datas);
    }

}
