package classFile.attribute;

import classFile.ClassReader;
import classFile.ConstantPool;
import utils.ByteUtils;

/*
* 属性表
* 属性结构
* u2 attribute_name_index 1
* u4 attribute_length 1
* u1 info attribute_length
* */
public abstract class AttributeInfo {
    //抽象方法,由各属性读取自己的属性信息
    abstract void readInfo(ClassReader reader);

    //从class中向后读取一个属性
    private static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool){
        int attrNameIndex = reader.readUint16();
        String attrName = constantPool.getUtf8(attrNameIndex);
        int attrLen = ByteUtils.byteToInt32(reader.readUint32());
        AttributeInfo attrInfo = create(attrName, attrLen, constantPool);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    /*
    * 首先从class中读取需要读取的属性数量,然后读取改数量的属性
    * JVM中三处用到:
    * 1. 由 class 文件转为 ClassFile 对象时，读取 Class 的属性
    * 2. 为 class 中定义的 Field 和 Method 读取属性
    * 3. 为 Method 中的字节码读取属性(本地变量表大小，操作数大小，字节码，异常表)
    * */
    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool){
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributeInfos = new AttributeInfo[attributesCount];
        for (int i =0; i < attributesCount; i++){
            attributeInfos[i] = readAttribute(reader, constantPool);
        }
        return attributeInfos;
    }

    private static AttributeInfo create(String attrName, int attrLen, ConstantPool constantPool){
        if ("Code".equals(attrName)) {
            return new CodeAttribute(constantPool);
        } else if ("ConstantValue".equals(attrName)) {
            return new ConstantValueAttribute();
        } else if ("Deprecated".equals(attrName)) {
            return new DeprecatedAttribute();
        } else if ("Exceptions".equals(attrName)) {
            return new ExceptionsAttribute();
        } else if ("LineNumberTable".equals(attrName)) {
            return new LineNumberTableAttribute();
        } else if ("LocalVariableTable".equals(attrName)) {
            return new LocalVariableTableAttribute();
        } else if ("SourceFile".equals(attrName)) {
            return new SourceFileAttribute(constantPool);
        } else if ("Synthetic".equals(attrName)) {
            return new SyntheticAttribute();
        } else {
            return new UnparsedAttribute(attrName, attrLen);
        }
    }
}
