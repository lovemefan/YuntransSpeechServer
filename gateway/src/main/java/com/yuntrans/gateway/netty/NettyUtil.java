/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:50
 */
package com.yuntrans.gateway.netty;


import io.netty.channel.ChannelId;

public class NettyUtil {
    public static String genConnId(ChannelId channelId) {
        String longConnId = channelId.asLongText();
        if (longConnId.length() < 52)
            return channelId.asShortText();
        return longConnId.substring(52, 60) + longConnId.substring(35, 51) + longConnId.substring(26, 34);
    }
}
