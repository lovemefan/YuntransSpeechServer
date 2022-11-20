package com.yuntrans.common.utils;
import java.util.Base64;

public class StringUtils {
    public static String base64ToHex(String base64String) {
        byte[] md5 = Base64.getDecoder().decode(base64String);
        StringBuilder builder = new StringBuilder();
        for (byte b : md5) {
            builder.append(String.format("%02x", new Object[] { Byte.valueOf(b) }));
        }
        return builder.toString();
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", new Object[] { Byte.valueOf(b) }));
        }
        return builder.toString();
    }

    public static boolean isStringOnlyAlphabet(String str) {
        return str.replaceAll("\\s+", "").matches("^[\\p{P}a-zA-Z]*$");
    }

    public static boolean nullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static boolean isBlank(String str) {
        return (str == null || str.trim().isEmpty());
    }

    public static boolean isNotBlank(String str) {
        return (str != null && !str.trim().isEmpty());
    }

    public static boolean checkWordLength(String word, int maxLength) {
        return checkWordLength(word, maxLength, 1);
    }

    public static boolean checkWordLength(String word, int maxLength, int ratio) {
        String text = (word == null) ? "" : word.trim();
        byte[] bytes = text.getBytes();
        int byteLength = bytes.length;
        int strLength = text.length();
        if (byteLength == strLength) {
            String[] array = word.split(" ");
            return (array.length <= maxLength / ratio);
        }
        return (strLength <= maxLength);
    }
}
