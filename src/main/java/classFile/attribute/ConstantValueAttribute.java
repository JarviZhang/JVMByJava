package classFile.attribute;

import classFile.ClassReader;

public class ConstantValueAttribute extends AttributeInfo{
    int constantValueIndex;
    @Override
    void readInfo(ClassReader reader) {
        constantValueIndex = reader.readUint16();
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
