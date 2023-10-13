package classFile;

import classFile.attribute.AttributeInfo;
import classFile.attribute.SourceFileAttribute;
import utils.ByteUtils;

/*
* 保存class信息
* */
public class ClassFile {
    //小版本号
    private int minorVersion;
    //大版本号
    private int majorVersion;
    //常量池
    public ConstantPool constantPool;
    //访问标志
    private int accessFlags;
    //类索引
    private int thisClass;
    //父类索引
    private int superClass;
    //接口索引
    private int[] interfaces;
    //属性表
    private MemberInfo[] fields;
    //方法表
    private MemberInfo[] methods;
    //属性表
    private AttributeInfo[] attributes;

    public ClassFile(byte[] classData){
        ClassReader reader = new ClassReader(classData);
        read(reader);
    }

    /*
    *   读取class文件并解析
    * */
    private void read(ClassReader reader){
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = new ConstantPool(reader);
        accessFlags = reader.readUint16();
        thisClass = reader.readUint16();
        superClass = reader.readUint16();
        interfaces = reader.readUint16s();
        fields = MemberInfo.readMembers(reader, constantPool);
        methods = MemberInfo.readMembers(reader, constantPool);
        attributes = AttributeInfo.readAttributes(reader, constantPool);

    }

    //检查魔数
    private void readAndCheckMagic(ClassReader reader){
        String magic = ByteUtils.bytesToHexString(reader.readUint32());
        if (!magic.equals("CAFEBABE")) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
    }

    //读取、检查、保存版本号
    private void readAndCheckVersion(ClassReader reader){
        minorVersion = reader.readUint16();
        majorVersion = reader.readUint16();
        if (majorVersion == 45){
            return;
        }
        if (minorVersion == 0 && majorVersion >= 46 && majorVersion <= 52){
            return;
        }
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    //获取本类的类名
    public String getClassName(){
        return constantPool.getClassName(thisClass);
    }

    //获取父类类名
    public String getSuperClassName() {
        if (superClass > 0) {
            return constantPool.getClassName(superClass);
        } else {
            return "";
        }
    }

    public String[] getInterfaceNames() {
        String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;
    }

    public String getSourceFile() {
        for (AttributeInfo info : attributes) {
            if (info instanceof SourceFileAttribute) {
                return ((SourceFileAttribute) info).getFileName();
            }
        }
        return "unknow";
    }
}
