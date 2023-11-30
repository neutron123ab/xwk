package cn.code51.dashboard.modules.system.mq.consumer.sms;

import cn.code51.dashboard.framework.redis.core.stream.AbstractStreamMessageListener;
import cn.code51.dashboard.modules.system.mq.message.sms.SysSmsSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SysSmsSendConsumer extends AbstractStreamMessageListener<SysSmsSendMessage> {

    @Override
    public void onMessage(SysSmsSendMessage message) {
        log.info("[onMessage][消息内容({})]", message);
    }

}
