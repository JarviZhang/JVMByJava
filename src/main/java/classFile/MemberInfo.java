package classFile;

import classFile.attribute.AttributeInfo;
import classFile.attribute.CodeAttribute;
import classFile.attribute.ConstantValueAttribute;
import classFile.attribute.ExceptionsAttribute;

/*
* 字段表和方法表共用该类
* */
public class MemberInfo {
    ConstantPool constantPool;
    int accessFlags;
    int nameIndex;
    int descriptorIndex;
    AttributeInfo[] attributes;

    public MemberInfo(ClassReader reader, ConstantPool constantPool){
        this.constantPool = constantPool;
        accessFlags = reader.readUint16();
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
        attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    //从class文件中先读取字段(方法)表的数量,然后读取对应数量的字段表或者方法表
    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int memberCount = reader.readUint16();
        MemberInfo[] members = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            members[i] = new MemberInfo(reader, constantPool);
        }
        return members;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }

    public String getDescriptor() {
        return constantPool.getUtf8(descriptorIndex);
    }

    public CodeAttribute getCodeAttribute() {
        for (AttributeInfo info : attributes) {
            if (info instanceof CodeAttribute) {
                return (CodeAttribute) info;
            }
        }
        return null;
    }

    //并非每个成员变量都有ConstantValueAttribute属性,该属性只针对于static final 基础类型变量或者String类型变量;
    public ConstantValueAttribute getConstantValueAttribute() {
        for (AttributeInfo info : attributes) {
            if (info instanceof ConstantValueAttribute) {
                return (ConstantValueAttribute) info;
            }
        }
        return null;
    }


    public ExceptionsAttribute getExceptionsAttribute() {
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i] instanceof ExceptionsAttribute) {
                return (ExceptionsAttribute) attributes[i];
            }
        }
        return null;
    }

}
