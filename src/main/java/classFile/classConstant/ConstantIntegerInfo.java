package classFile.classConstant;

import classFile.ClassReader;
import utils.ByteUtils;

public class ConstantIntegerInfo extends ConstantInfo{
    int val;

    public ConstantIntegerInfo(int i) {
        type = i;
    }

    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint32();
        val = ByteUtils.byteToInt32(data);
    }

    public int getVal() {
        return val;
    }
}
