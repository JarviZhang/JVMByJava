package classFile.classConstant;

import classFile.ClassReader;
import classFile.ConstantPool;

public class ConstantStringInfo extends ConstantInfo{
    ConstantPool constantPool;
    int stringIndex;

    public ConstantStringInfo(ConstantPool constantPool, int i) {
        this.constantPool = constantPool;
        type = i;
    }


    //读取常量池索引
    @Override
    void readInfo(ClassReader reader) {
        stringIndex = reader.readUint16();
    }

    public String getString() {
        return constantPool.getUtf8(stringIndex);
    }
}
