package classFile.classConstant;

import classFile.ClassReader;

public class ConstantNameAndTypeInfo extends ConstantInfo{

    //指向方法或字段名称常量项的索引
    private int nameIndex;
    //指向方法或字段描述符常量项的索引
    private int descriptorIndex;

    public ConstantNameAndTypeInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
