package classFile.classConstant;

import classFile.ClassReader;
import utils.ByteUtils;

public class ConstantLongInfo extends ConstantInfo{
    long val;

    public ConstantLongInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
       /* String hexData = ByteUtils.bytesToHexString(data);
        val = Long.parseLong(hexData, 16);*/
        val = ByteUtils.byteToLong64(data);
    }

    public long getVal() {
        return val;
    }

}
