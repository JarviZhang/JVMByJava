package classFile.classConstant;

import classFile.ClassReader;
import utils.ByteUtils;

public class ConstantDoubleInfo extends ConstantInfo{
    double val;

    public ConstantDoubleInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
        val = ByteUtils.byte2Double64(data);
    }

    public double getVal() {
        return val;
    }
}
