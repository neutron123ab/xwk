package cn.code51.dashboard.modules.infra.service.file.impl;

import cn.code51.dashboard.common.exception.util.ServiceExceptionUtil;
import cn.code51.dashboard.common.pojo.CommonResult;
import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.framework.file.config.FileProperties;
import cn.code51.dashboard.modules.infra.dal.dataobject.file.InfFileDO;
import cn.code51.dashboard.modules.infra.dal.mysql.file.InfFileMapper;
import cn.code51.dashboard.modules.infra.enums.InfErrorCodeConstants;
import cn.hutool.core.io.FileTypeUtil;
import cn.code51.dashboard.modules.infra.controller.file.vo.InfFilePageReqVO;
import cn.code51.dashboard.modules.infra.service.file.InfFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;

import static cn.code51.dashboard.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 文件 Service 实现类
 *
 * @author 教研源码
 */
@Service
public class InfFileServiceImpl implements InfFileService {

    @Resource
    private InfFileMapper fileMapper;

    @Resource
    private FileProperties fileProperties;

    @Override
    public CommonResult createFile(String path, byte[] content) {

        if (fileMapper.selectCountById(path)> 0) {
            CommonResult result=new CommonResult();
            result.setCode(500);
            result.setMsg("文件已存在");
            return   result;
        }
        // 保存到数据库
        InfFileDO file = new InfFileDO();
        file.setId(path);
        file.setType(FileTypeUtil.getType(new ByteArrayInputStream(content)));
        file.setContent(content);
        fileMapper.insert(file);
        // 拼接路径返回
        CommonResult result=new CommonResult();
        result.setCode(200);
        result.setMsg(fileProperties.getBasePath() + path);
        return result;
    }

    @Override
    public void deleteFile(String id) {
        // 校验存在
        this.validateFileExists(id);
        // 更新
        fileMapper.deleteById(id);
    }

    private void validateFileExists(String id) {
        if (fileMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.FILE_NOT_EXISTS);
        }
    }

    @Override
    public InfFileDO getFile(String path) {
        return fileMapper.selectById(path);
    }

    @Override
    public PageResult<InfFileDO> getFilePage(InfFilePageReqVO pageReqVO) {
        return fileMapper.selectPage(pageReqVO);
    }

}
