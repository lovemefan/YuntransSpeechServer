/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 21:27
 */
package com.yuntrans.common.utils;

import java.util.UUID;

public class IdUtil {
    public static final String DEFAULT_UUID = "00000000000000000000000000000000";

    private static final int UUID_LENGTH = 32;

    public static String newUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid
                .substring(9, 13) + uuid
                .substring(14, 18) + uuid
                .substring(19, 23) + uuid
                .substring(24);
    }

    public static boolean checkUuid(String id) {
        if (id == null || id.length() != 32)
            return false;
        for (int i = 0; i < id.length(); i++) {
            char ch = id.charAt(i);
            if (ch < '0' || ch > 'f')
                return false;
            if (ch > '9' && ch < 'a')
                return false;
        }
        return true;
    }
}
