package org.ace.socketserver.constant;

import io.netty.util.AttributeKey;
import org.ace.socketserver.bo.ChannelProperty;
import org.ace.socketserver.bo.ChannelPropertyCommon;

/**
 * @author xiepufeng
 */
public class AttributeKeyConst
{
    public static final String CHANNEL_PROPERTY_KEY_NAME = "channel.property.key";

    public static final String CHANNEL_PROPERTY_COMMON_KEY_NAME = "channel.property.common.key";

    public static final AttributeKey<ChannelProperty> CHANNEL_PROPERTY_KEY = AttributeKey.valueOf(CHANNEL_PROPERTY_KEY_NAME);

    // 所有通道共有
    public static final AttributeKey<ChannelPropertyCommon> CHANNEL_PROPERTY_COMMON_KEY = AttributeKey.valueOf(CHANNEL_PROPERTY_COMMON_KEY_NAME);
}
