package org.ace.websocket.constant;

import io.netty.util.AttributeKey;
import org.ace.websocket.bo.ChannelProperty;
import org.ace.websocket.bo.ChannelPropertyCommon;

/**
 * Channel对应的属性key值定义
 * @author xiepufeng
 */
public class AttributeKeyConst
{
    public static final String CHANNEL_PROPERTY_KEY_NAME = "channel.property.key";

    public static final String CHANNEL_PROPERTY_COMMON_KEY_NAME = "channel.property.common.key";

    public static final AttributeKey<ChannelProperty> CHANNEL_PROPERTY_KEY = AttributeKey.valueOf(CHANNEL_PROPERTY_KEY_NAME);

    public static final AttributeKey<ChannelPropertyCommon> CHANNEL_PROPERTY_COMMON_KEY = AttributeKey.valueOf(CHANNEL_PROPERTY_COMMON_KEY_NAME);
}
