package cn.code51.dashboard.modules.system.mq.consumer.permission;

import cn.code51.dashboard.framework.redis.core.pubsub.AbstractChannelMessageListener;
import cn.code51.dashboard.modules.system.mq.message.permission.SysMenuRefreshMessage;
import cn.code51.dashboard.modules.system.service.permission.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SysMenuRefreshMessage} 的消费者
 *
 * @author 教研源码
 */
@Component
@Slf4j
public class SysMenuRefreshConsumer extends AbstractChannelMessageListener<SysMenuRefreshMessage> {

    @Resource
    private SysMenuService menuService;

    @Override
    public void onMessage(SysMenuRefreshMessage message) {
        log.info("[onMessage][收到 Menu 刷新消息]");
        menuService.initLocalCache();
    }

}
