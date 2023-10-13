package classFile;


import utils.ByteUtils;
/*
* 用于从class中读取规定字节数据的工具类
* */
public class ClassReader {
    //保存class文件的字节流
    private byte[] data;
    //标记当前读取位置的指针
    private int index = 0;

    //将class文件保存在data中
    public ClassReader(byte[] data){
        this.data = data;
    }

    //读u1数据
    public byte readUint8(){
        byte res = data[index++];
        return res;
    }

    //读u2数据
    public int readUint16(){
        byte[] res = new byte[2];
        res[0] = data[index++];
        res[1] = data[index++];
        return ByteUtils.bytesToU16(res);
    }
    //读u4数据
    public byte[] readUint32(){
        byte[] res = new byte[4];
        res[0] = data[index++];
        res[1] = data[index++];
        res[2] = data[index++];
        res[3] = data[index++];
        return res;
    }

    //读u8数据
    public byte[] readUint64() {
        byte[] res = new byte[8];
        res[0] = data[index++];
        res[1] = data[index++];
        res[2] = data[index++];
        res[3] = data[index++];
        res[4] = data[index++];
        res[5] = data[index++];
        res[6] = data[index++];
        res[7] = data[index++];
        return res;
    }

    /**
     * 读取连续的16bit长的数组,首先读出16bit,用来表示接下来要去读的多少个16bit
     * @return
     */
    public int[] readUint16s() {
        int n = readUint16();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = readUint16();
        }
        return data;
    }

    //读取n byte
    public byte[] readBytes(int n) {
        byte[] res = new byte[n];
        for (int i = 0; i < n; i++) {
            res[i] = data[index++];
        }
        return res;
    }
}
