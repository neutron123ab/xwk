package cn.code51.dashboard.framework.security.core.handler;

import cn.code51.dashboard.framework.security.core.service.SecurityAuthFrameworkService;
import cn.code51.dashboard.framework.security.core.util.SecurityFrameworkUtils;
import cn.hutool.core.util.StrUtil;
import cn.code51.dashboard.common.pojo.CommonResult;
import cn.code51.dashboard.framework.security.config.SecurityProperties;
import cn.code51.dashboard.util.servlet.ServletUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义退出处理器
 *
 *
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private SecurityAuthFrameworkService securityFrameworkService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 执行退出
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            securityFrameworkService.logout(token);
        }
        // 返回成功
        ServletUtils.writeJSON(response, CommonResult.success(null));
    }
}
