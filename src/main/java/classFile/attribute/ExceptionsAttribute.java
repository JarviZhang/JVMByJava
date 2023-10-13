package classFile.attribute;

import classFile.ClassReader;

public class ExceptionsAttribute extends AttributeInfo{
    int[] exceptionIndexTable;

    @Override
    void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readUint16s();
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
