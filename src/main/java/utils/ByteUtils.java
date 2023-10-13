package utils;

import java.util.Locale;

/*
* 对byte类型数据进行处理的工具类
* */
public class ByteUtils {
    //将比特转换为十六进制表示的字符串(二进制表示不变)
    public static String bytesToHexString(byte[] src){
        return bytesToHexString(src, src.length);
    }

    //将比特转换为十六进制表示的字符串(二进制表示不变)
    public static String bytesToHexString(byte[] src, int len){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || len <= 0){
            return null;
        }
        for (int i = 0; i < len; i++){
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2){
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    //2位byte转为16进制表示的字符串
    public static int bytesToU16(byte[] data){
        //断言机制，不符合就抛出“AssertionError”异常
        assert data.length == 2;
        return (data[0] + 256) % 256 * 256 + (data[1] + 256) % 256;
    }

    //4进制byte转化为int
    public static int byteToInt32(byte[] data){
        assert data.length == 4;
        int res = 0;
        for (int i = 0; i < data.length; i++){
            res = res << 8 | (data[i] + 256) % 256;
        }
        return res;
    }

    public static long byteToLong64(byte[] data) {
        assert data.length == 8;
        long res = 0;
        for (int i = 0; i < data.length; i++) {
            res = res << 8 | (data[i] + 256) % 256;
        }
        return res;
    }

    public static float byte2Float32(byte[] b) {
        int i = byteToInt32(b);
        return Float.intBitsToFloat(i);
    }


    public static double byte2Double64(byte[] b) {
        long l = byteToLong64(b);
        return Double.longBitsToDouble(l);
    }
}
