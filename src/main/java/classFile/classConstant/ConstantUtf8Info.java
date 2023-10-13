package classFile.classConstant;

import classFile.ClassReader;

import java.io.IOException;
import java.io.UTFDataFormatException;

/*
* CONSTANT_Utf8_info类型的产量
* 结构:
* u1 tag 1
* u2 length 1
* u1 bytes length
*
* */
public class ConstantUtf8Info extends ConstantInfo{
    //代表的字符串
    public String val;

    public ConstantUtf8Info(int i){
        type = i;
    }

    @Override
    void readInfo(ClassReader reader) {
        //读取u2 length
        int len = reader.readUint16();
        byte[] data = reader.readBytes(len);
        try {
            val = decodeMUTF8(data);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //将UTF-8缩略编码转化为UTF-8编码,根据java.io.DataInputStream.readUTF()
    private static String decodeMUTF8(byte[] bytearr) throws IOException {
        int utflen = bytearr.length;
        char[] chararr = new char[utflen];
        int c, char2, char3;
        int count = 0;
        int chararr_count = 0;
        while (count < utflen){
            //使用二进制保存byte数据
            c = (int) bytearr[count] & 0xff;
            if (c > 127){
                break;
            }
            count++;
            chararr[chararr_count++] = (char) c;
        }

        while (count < utflen){
            c = (int) bytearr[count] & 0xff;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    /* 0xxxxxxx*/
                    count++;
                    chararr[chararr_count++] = (char) c;
                    break;
                case 12:
                case 13:
                    /* 110x xxxx   10xx xxxx*/
                    count += 2;
                    if (count > utflen) {
                        throw new UTFDataFormatException("malformed input: partial character at end");
                    }
                    char2 = (int) bytearr[count - 1];
                    if ((char2 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException("malformed input around byte " + count);
                    }
                    chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
                            (char2 & 0x3F));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen) {
                        throw new UTFDataFormatException(
                                "malformed input: partial character at end");
                    }
                    char2 = (int) bytearr[count - 2];
                    char3 = (int) bytearr[count - 1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
                        throw new UTFDataFormatException(
                                "malformed input around byte " + (count - 1));
                    }
                    chararr[chararr_count++] = (char) (((c & 0x0F) << 12) |
                            ((char2 & 0x3F) << 6) |
                            ((char3 & 0x3F) << 0));
                    break;
                default:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDataFormatException(
                            "malformed input around byte " + count);
            }
        }
        return new String(chararr, 0, chararr_count);
    }

    public String getVal() {
        return val;
    }

}
