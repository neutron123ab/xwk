package cn.code51.dashboard.modules.system.mq.consumer.dept;

import cn.code51.dashboard.framework.redis.core.pubsub.AbstractChannelMessageListener;
import cn.code51.dashboard.modules.system.mq.message.dept.SysDeptRefreshMessage;
import cn.code51.dashboard.modules.system.service.dept.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SysDeptRefreshMessage} 的消费者
 *
 * @author 教研源码
 */
@Component
@Slf4j
public class SysDeptRefreshConsumer extends AbstractChannelMessageListener<SysDeptRefreshMessage> {

    @Resource
    private SysDeptService deptService;

    @Override
    public void onMessage(SysDeptRefreshMessage message) {
        log.info("[onMessage][收到 Dept 刷新消息]");
        deptService.initLocalCache();
    }

}
