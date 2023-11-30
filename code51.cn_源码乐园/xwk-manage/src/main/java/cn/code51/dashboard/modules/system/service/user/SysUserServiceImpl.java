package cn.code51.dashboard.modules.system.service.user;

import cn.code51.dashboard.common.enums.CommonStatusEnum;
import cn.code51.dashboard.common.exception.ServiceException;
import cn.code51.dashboard.common.exception.util.ServiceExceptionUtil;
import cn.code51.dashboard.common.pojo.PageResult;
import cn.code51.dashboard.common.utils.FileUtils;
import cn.code51.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import cn.code51.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import cn.code51.dashboard.modules.system.controller.user.vo.user.*;
import cn.code51.dashboard.modules.system.convert.user.SysUserConvert;
import cn.code51.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import cn.code51.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import cn.code51.dashboard.modules.system.dal.dataobject.permission.SysUserRoleDO;
import cn.code51.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import cn.code51.dashboard.modules.system.dal.mysql.permission.SysUserRoleMapper;
import cn.code51.dashboard.modules.system.dal.mysql.user.SysUserMapper;
import cn.code51.dashboard.modules.system.enums.SysErrorCodeConstants;
import cn.code51.dashboard.modules.system.service.dept.SysDeptService;
import cn.code51.dashboard.modules.system.service.dept.SysPostService;
import cn.code51.dashboard.modules.system.service.permission.SysPermissionService;
import cn.code51.dashboard.util.collection.CollectionUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;


/**
 * 用户 Service 实现类
 *
 * @author 教研源码
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysDeptService deptService;
    @Resource
    private SysPostService postService;
    @Resource
    private SysPermissionService permissionService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Long createUser(SysUserCreateReqVO reqVO) {
        // 校验正确性
//        this.checkCreateOrUpdate(null, reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail(),
//              reqVO.getDeptId(), reqVO.getPostIds());
        // 插入用户
        SysUserDO user = SysUserConvert.INSTANCE.convert(reqVO);
        user.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 默认开启
        user.setPassword(passwordEncoder.encode(reqVO.getPassword())); // 加密密码
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public void updateUser(SysUserUpdateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(reqVO.getId(), reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail(),
              reqVO.getDeptId(), reqVO.getPostIds());
        // 更新用户
        SysUserDO updateObj = SysUserConvert.INSTANCE.convert(reqVO);
        userMapper.updateById(updateObj);
    }

    @Override
    public void updateUserProfile(Long id, SysUserProfileUpdateReqVO reqVO) {
        // 校验正确性
        this.checkUserExists(id);
        this.checkEmailUnique(id, reqVO.getEmail());
        this.checkMobileUnique(id, reqVO.getMobile());
        // 执行更新
        SysUserDO convert = SysUserConvert.INSTANCE.convert(reqVO);
        convert.setId(id);
        userMapper.updateById(convert);
    }

    @Override
    public void updateUserPassword(Long id, SysUserProfileUpdatePasswordReqVO reqVO) {
        // 校验旧密码密码
        this.checkOldPassword(id, reqVO.getOldPassword());
        // 执行更新
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setId(id);

        sysUserDO.setPassword(passwordEncoder.encode(reqVO.getNewPassword())); // 加密密码
        userMapper.updateById(sysUserDO);
    }

    @Override
    public void updateUserAvatar(Long id, InputStream avatarFile) {
        this.checkUserExists(id);
        // 存储文件
//        String avatar = fileService.createFile(IdUtil.fastUUID(), IoUtil.readBytes(avatarFile));
        //转base64存储图片
        String avatar= FileUtils.FileToBase64(avatarFile);
        // 更新路径
        avatar="data:image/png;base64,"+avatar;
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setId(id);
        sysUserDO.setAvatar(avatar);
        userMapper.updateById(sysUserDO);
    }

    @Override
    public void updateUserPassword(Long id, String password) {
        // 校验用户存在
        this.checkUserExists(id);
        // 更新密码
        SysUserDO updateObj = new SysUserDO();
        updateObj.setId(id);
        updateObj.setPassword(passwordEncoder.encode(password)); // 加密密码
        userMapper.updateById(updateObj);
    }

    @Override
    public void updateUserStatus(Long id, Integer status) {
        // 校验用户存在
        this.checkUserExists(id);
        // 更新状态
        SysUserDO updateObj = new SysUserDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        userMapper.updateById(updateObj);
        // 删除用户关联数据
        permissionService.processUserDeleted(id);
    }

    @Override
    public void deleteUser(Long id) {
        // 校验用户存在
        this.checkUserExists(id);
        // 删除用户
        userMapper.deleteById(id);
    }

    @Override
    public SysUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public SysUserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<SysUserDO> getUserPage(SysUserPageReqVO reqVO) {
        return userMapper.selectPage(reqVO, this.getDeptCondition(reqVO.getDeptId()));
    }

    @Override
    public List<SysUserDO> getUsers(SysUserExportReqVO reqVO) {
        return userMapper.selectList(reqVO, this.getDeptCondition(reqVO.getDeptId()));
    }

    @Override
    public List<SysUserDO> getUsers(Collection<Long> ids) {
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public List<SysUserDO> getUsersByNickname(String nickname) {
        return userMapper.selectListByNickname(nickname);
    }

    @Override
    public List<SysUserDO> getUsersByUsername(String username) {
        return userMapper.selectListByUsername(username);
    }

    /**
     * 获得学院条件：查询指定学院的子学院编号们，包括自身
     *
     * @param deptId 学院编号
     * @return 学院编号集合
     */
    private Set<Long> getDeptCondition(Long deptId) {
        if (deptId == null) {
            return Collections.emptySet();
        }
        Set<Long> deptIds = CollectionUtils.convertSet(deptService.getDeptsByParentIdFromCache(
              deptId, true), SysDeptDO::getId);
        deptIds.add(deptId); // 包括自身
        return deptIds;
    }

    private void checkCreateOrUpdate(Long id, String username, String mobile, String email,
                                     Long deptId, Set<Long> postIds) {
        // 校验用户存在
        this.checkUserExists(id);
        // 校验用户名唯一
        this.checkUsernameUnique(id, username);
        // 校验手机号唯一
        this.checkMobileUnique(id, mobile);
        // 校验邮箱唯一
        this.checkEmailUnique(id, email);
        // 校验学院处于开启状态
        this.checkDeptEnable(deptId);

    }

    private void checkUserExists(Long id) {
        if (id == null) {
            return;
        }
        SysUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_NOT_EXISTS);
        }
    }

    private void checkUsernameUnique(Long id, String username) {
        if (StrUtil.isNotBlank(username)) {
            return;
        }
        SysUserDO user = userMapper.selectByUsername(username);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_USERNAME_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_USERNAME_EXISTS);
        }
    }

    private void checkEmailUnique(Long id, String email) {
        if (StrUtil.isNotBlank(email)) {
            return;
        }
        if(!StringUtils.isEmpty(email)){
            SysUserDO user = userMapper.selectByEmail(email);
            if (user == null) {
                return;
            }
            // 如果 id 为空，说明不用比较是否为相同 id 的用户
            if (id == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_EMAIL_EXISTS);
            }
            if (!user.getId().equals(id)) {
                throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_EMAIL_EXISTS);
            }
        }

    }

    private void checkMobileUnique(Long id, String email) {
        if (StrUtil.isNotBlank(email)) {
            return;
        }
        SysUserDO user = userMapper.selectByMobile(email);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_MOBILE_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_MOBILE_EXISTS);
        }
    }

    private void checkDeptEnable(Long deptId) {
        if (deptId == null) { // 允许不选择
            return;
        }
        SysDeptDO dept = deptService.getDept(deptId);
        if (dept == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_NOT_FOUND);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(dept.getStatus())) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_NOT_ENABLE);
        }
    }

    private void checkPostEnable(Set<Long> postIds) {
        if (CollUtil.isEmpty(postIds)) { // 允许不选择
            return;
        }
        List<SysPostDO> posts = postService.getPosts(postIds, null);
        if (CollUtil.isEmpty(posts)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.POST_NOT_FOUND);
        }
        Map<Long, SysPostDO> postMap = CollectionUtils.convertMap(posts, SysPostDO::getId);
        postIds.forEach(postId -> {
            SysPostDO post = postMap.get(postId);
            if (post == null) {
                throw ServiceExceptionUtil.exception(SysErrorCodeConstants.POST_NOT_FOUND);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(post.getStatus())) {
                throw ServiceExceptionUtil.exception(SysErrorCodeConstants.POST_NOT_ENABLE, post.getName());
            }
        });
    }

    /**
     * 校验旧密码
     *
     * @param id          用户 id
     * @param oldPassword 旧密码
     */
    private void checkOldPassword(Long id, String oldPassword) {
        SysUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_NOT_EXISTS);
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_PASSWORD_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 添加事务，异常则回滚所有导入
    public SysUserImportRespVO importUsers(List<SysUserImportExcelVO> importUsers, boolean isUpdateSupport) {
        if (CollUtil.isEmpty(importUsers)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.USER_IMPORT_LIST_IS_EMPTY);
        }
        SysUserImportRespVO respVO = SysUserImportRespVO.builder().createUsernames(new ArrayList<>())
              .updateUsernames(new ArrayList<>()).failureUsernames(new LinkedHashMap<>()).build();
        importUsers.forEach(importUser -> {
            // 校验，判断是否有不符合的原因
            try {
                checkCreateOrUpdate(null, null, importUser.getMobile(), importUser.getEmail(),
                      importUser.getDeptId(), null);
            } catch (ServiceException ex) {
                respVO.getFailureUsernames().put(importUser.getUsername(), ex.getMessage());
                return;
            }
            // 判断如果不存在，在进行插入
            SysUserDO existUser = userMapper.selectByUsername(importUser.getUsername());
            if (existUser == null) {
                // TODO ：初始密码
                userMapper.insert(SysUserConvert.INSTANCE.convert(importUser));
                respVO.getCreateUsernames().add(importUser.getUsername());
                return;
            }
            // 如果存在，判断是否允许更新
            if (!isUpdateSupport) {
                respVO.getFailureUsernames().put(importUser.getUsername(), SysErrorCodeConstants.USER_USERNAME_EXISTS.getMessage());
                return;
            }
            SysUserDO updateUser = SysUserConvert.INSTANCE.convert(importUser);
            updateUser.setId(existUser.getId());
            userMapper.updateById(updateUser);
            respVO.getUpdateUsernames().add(importUser.getUsername());
        });
        return respVO;
    }

    @Override
    public Long registerUser(SysUserCreateReqVO reqVO) {
        SysUserDO user = SysUserConvert.INSTANCE.convert(reqVO);
        user.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 默认开启
        user.setPassword(passwordEncoder.encode(reqVO.getPassword())); // 加密密码

        userMapper.insert(user);
        //插入用户角色
        SysUserRoleDO userRoleDO=new SysUserRoleDO();
        userRoleDO.setRoleId(Long.valueOf(reqVO.getRoleId()));
        userRoleDO.setUserId(user.getId());
        sysUserRoleMapper.insert(userRoleDO);
        return user.getId();
    }

    @Override
    public List<SysUserPageReqVO> getUserPagesByUserName(SysUserPageReqVO vo) {
        PageResult<SysUserDO> sysUserDOPageResult = userMapper.selectPage(vo);
        List<SysUserDO> list = sysUserDOPageResult.getList();
        List<SysUserPageReqVO>  result=new ArrayList<>();
        list.forEach(each->{
            SysUserPageReqVO neweach= new SysUserPageReqVO();
            BeanUtils.copyProperties(each,neweach);
            result.add(neweach);
        });
        return  result;
    }

    public static void main(String[] args) {

    }


}
