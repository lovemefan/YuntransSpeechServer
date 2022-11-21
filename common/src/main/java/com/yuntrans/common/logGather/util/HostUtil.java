/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:41
 */
package com.yuntrans.common.logGather.util;


import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostUtil {
    static String hostName = null;

    public static String getHostName() {
        try {
            if (StringUtils.isEmpty(hostName)) {
                InetAddress addr = InetAddress.getLocalHost();
                hostName = addr.getHostName();
            }
            return hostName;
        } catch (UnknownHostException unknownHostException) {
            return "<NO_HOST_NAME>";
        }
    }
}
