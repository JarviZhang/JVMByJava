package classFile.classConstant;

import classFile.ClassReader;
import classFile.ConstantPool;

public class ConstantClassInfo extends ConstantInfo{
    ConstantPool constantPool;
    private int nameIndex;

    public ConstantClassInfo(ConstantPool constantPool, int i) {
        this.constantPool = constantPool;
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
