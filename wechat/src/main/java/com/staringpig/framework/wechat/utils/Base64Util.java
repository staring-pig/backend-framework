package com.staringpig.framework.wechat.utils;

/**
 * Base64编解码工具类。
 */
public final class Base64Util {
    /**
     * Base64解码。
     *
     * @param code 用Base64编码的ASCII字符串。
     * @return 解码后的字节数据
     */
    public static byte[] decode(String code) {
        // 检查参数合法性
        if (code == null || code.isEmpty()) {
            return null;
        }

        code = code.replace("\n", "").replace(" ", "+");

        int len = code.length();
        if (len % NUMBER_FOUR != 0) {
            throw new IllegalArgumentException("Base64 string length must be 4*n");
        }

        // 统计填充的等号个数
        int pad = 0;
        if (code.charAt(len - 1) == '=') {
            pad++;
        }
        if (code.charAt(len - NUMBER_TWO) == '=') {
            pad++;
        }

        // 根据填充等号的个数来计算实际数据长度
        int retLen = len / NUMBER_FOUR * NUMBER_THREE - pad;

        // 分配字节数组空间
        byte[] ret = new byte[retLen];

        // 查表解码
        char ch1, ch2, ch3, ch4;
        int i;
        for (i = 0; i < len; i += NUMBER_FOUR) {
            int j = i / NUMBER_FOUR * NUMBER_THREE;
            ch1 = code.charAt(i);
            ch2 = code.charAt(i + 1);
            ch3 = code.charAt(i + NUMBER_TWO);
            ch4 = code.charAt(i + NUMBER_THREE);
            int tmp = (BASE64DECODE[ch1] << NUMBER_EIGHTEEN) | (BASE64DECODE[ch2] << NUMBER_TWELVE)
                    | (BASE64DECODE[ch3] << NUMBER_SIX) | (BASE64DECODE[ch4]);
            ret[j] = (byte) ((tmp & HEX_16711680) >> NUMBER_SIXTEEN);
            if (i < len - NUMBER_FOUR) {
                ret[j + 1] = (byte) ((tmp & HEX_65280) >> NUMBER_EIGHT);
                ret[j + NUMBER_TWO] = (byte) ((tmp & HEX_255));
            } else {
                if (j + 1 < retLen) {
                    ret[j + 1] = (byte) ((tmp & HEX_65280) >> NUMBER_EIGHT);
                }
                if (j + NUMBER_TWO < retLen) {
                    ret[j + NUMBER_TWO] = (byte) ((tmp & HEX_255));
                }
            }
        }
        return ret;
    }

    //Base64解码表
    private static final byte[] BASE64DECODE
            = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1,
            -1,
            -1, // 注意两个63，为兼容SMP，
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 63,
            -1,
            63, // “/”和“-”都翻译成63。
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13,
            14, // 注意两个0：
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1,
            -1, // “A”和“=”都翻译成0。
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
            -1, -1, -1, -1, -1,};

    private static final int HEX_255 = 0x0000ff;
    private static final int HEX_16711680 = 0xff0000;
    private static final int HEX_65280 = 0x00ff00;
    private static final int NUMBER_TWO = 2;
    private static final int NUMBER_THREE = 3;
    private static final int NUMBER_FOUR = 4;
    private static final int NUMBER_SIX = 6;
    private static final int NUMBER_EIGHT = 8;
    private static final int NUMBER_TWELVE = 12;
    private static final int NUMBER_SIXTEEN = 16;
    private static final int NUMBER_EIGHTEEN = 18;
}
