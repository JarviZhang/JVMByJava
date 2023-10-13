package classFile.attribute;

import classFile.ClassReader;
import classFile.ConstantPool;

public class SourceFileAttribute extends AttributeInfo{
    //sourcefile_index是常量池索引，指向CONSTANT_Utf8_info常量，其常量值是源码文件的文件名
    int sourceFileIndex;
    ConstantPool constantPool;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        sourceFileIndex = reader.readUint16();
    }

    public String getFileName() {
        return constantPool.getUtf8(sourceFileIndex);
    }
}
