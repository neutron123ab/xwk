package cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo;

import cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo.*;
import cn.code51.dashboard.modules.dev.CompInfo.convert.CompInfo.CompInfoConvert;
import cn.code51.dashboard.modules.dev.CompInfo.dal.dataobject.CompInfo.CompInfoDO;
import cn.code51.dashboard.modules.dev.CompInfo.service.CompInfo.CompInfoService;
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


@Api(tags = "竞赛")
@RestController
@RequestMapping("/comp/info")
@Validated
public class CompInfoController {

    @Resource
    private CompInfoService infoService;

    @PostMapping("/create")
    @ApiOperation("创建竞赛")
    public CommonResult<Long> createInfo(@Valid @RequestBody CompInfoCreateReqVO createReqVO) {
        return success(infoService.createInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新竞赛")
    public CommonResult<Boolean> updateInfo(@Valid @RequestBody CompInfoUpdateReqVO updateReqVO) {
        infoService.updateInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除竞赛")
    @ApiImplicitParam(name = "id", value = "编号", required = true)
    public CommonResult<Boolean> deleteInfo(@RequestParam("id") Long id) {
        infoService.deleteInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得竞赛")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<CompInfoRespVO> getInfo(@RequestParam("id") Long id) {
        CompInfoDO info = infoService.getInfo(id);
        return success(CompInfoConvert.INSTANCE.convert(info));
    }

    @GetMapping("/list")
    @ApiOperation("获得竞赛列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    public CommonResult<List<CompInfoRespVO>> getInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<CompInfoDO> list = infoService.getInfoList(ids);
        return success(CompInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得竞赛分页")
    public CommonResult<PageResult<CompInfoRespVO>> getInfoPage(@Valid CompInfoPageReqVO pageVO) {
        PageResult<CompInfoDO> pageResult = infoService.getInfoPage(pageVO);
        return success(CompInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出竞赛 Excel")
    @OperateLog(type = EXPORT)
    public void exportInfoExcel(@Valid CompInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CompInfoDO> list = infoService.getInfoList(exportReqVO);
        // 导出 Excel
        List<CompInfoExcelVO> datas = CompInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "竞赛.xls", "数据", CompInfoExcelVO.class, datas);
    }

}
