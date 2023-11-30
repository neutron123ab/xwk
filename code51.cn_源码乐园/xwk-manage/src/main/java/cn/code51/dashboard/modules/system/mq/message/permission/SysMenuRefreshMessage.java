package cn.code51.dashboard.modules.system.mq.message.permission;

import cn.code51.dashboard.framework.redis.core.pubsub.ChannelMessage;
import lombok.Data;

/**
 * 菜单数据刷新 Message
 */
@Data
public class SysMenuRefreshMessage implements ChannelMessage {

    @Override
    public String getChannel() {
        return "system.menu.refresh";
    }

}
