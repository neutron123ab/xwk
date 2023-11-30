package cn.code51.dashboard.modules.system.mq.consumer.mail;

import cn.code51.dashboard.framework.redis.core.stream.AbstractStreamMessageListener;
import cn.code51.dashboard.modules.system.mq.message.mail.SysMailSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SysMailSendConsumer extends AbstractStreamMessageListener<SysMailSendMessage> {

    @Override
    public void onMessage(SysMailSendMessage message) {
        log.info("[onMessage][消息内容({})]", message);
    }

}
