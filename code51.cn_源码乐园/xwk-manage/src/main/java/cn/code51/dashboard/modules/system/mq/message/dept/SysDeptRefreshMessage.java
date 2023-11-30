package cn.code51.dashboard.modules.system.mq.message.dept;

import cn.code51.dashboard.framework.redis.core.pubsub.ChannelMessage;
import lombok.Data;

/**
 * 学院数据刷新 Message
 */
@Data
public class SysDeptRefreshMessage implements ChannelMessage {

    @Override
    public String getChannel() {
        return "system.dept.refresh";
    }

}
