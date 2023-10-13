package classFile;

import classFile.classConstant.*;

/*
* clas中的常量池类
* */
public class ConstantPool {

    //保存常量池中的所有常量
    ConstantInfo[] infos;

    public ConstantInfo[] getInfos(){
        return infos;
    }

    //常量池长度,这个长度包含0,但是0是空的
    private int constantPoolCount;
    //真正的常量池长度
    private int realConstantPoolCount;

    //从reader中获取常量池信息，并保存在实体类中
    public ConstantPool(ClassReader reader){
        constantPoolCount = reader.readUint16();
        infos = new ConstantInfo[constantPoolCount];
        for (int i = 1; i < constantPoolCount; i++){
            infos[i] = ConstantInfo.readConstantInfo(reader, this);
            realConstantPoolCount++;
            if ((infos[i] instanceof ConstantLongInfo) || (infos[i] instanceof ConstantDoubleInfo)) {
                i++;
            }
        }
    }

    //按索引查找常量,如果没有则直接抛出异常
    private ConstantInfo getConstantInfo(int index){
        if (0 < index && index < constantPoolCount) {
            ConstantInfo info = infos[index];
            if (info != null){
                return info;
            }
        }
        throw new NullPointerException("Invalid constant pool index");
    }

    //获取index对应的utf8字符串
    public String getUtf8(int index){
        return ((ConstantUtf8Info) getConstantInfo(index)).val;
    }

    //获取ConstantClassInfo类型变量的类名
    public String getClassName(int index){
        ConstantClassInfo info = (ConstantClassInfo) getConstantInfo(index);
        return getUtf8(info.getNameIndex());
    }

    //获取ConstantNameAndTypeInfo类型常量的名称和描述符
    public String[] getNameAndType(int index){
        String[] str = new String[2];
        str[0] = getName(index);
        str[1] = getType(index);
        return str;
    }

    //获取ConstantNameAndTypeInfo类型常量的名称
    public String getName(int index){
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        return getUtf8(info.getNameIndex());
    }

    //获取ConstantNameAndTypeInfo类型常量的名称
    public String getType(int index){
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        return getUtf8(info.getDescriptorIndex());
    }

    //测试方法，正式版本去除
    public int getConstantPoolCount() {
        return realConstantPoolCount;
    }
}
