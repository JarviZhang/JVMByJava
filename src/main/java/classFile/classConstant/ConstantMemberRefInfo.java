package classFile.classConstant;

import classFile.ClassReader;
import classFile.ConstantPool;

public class ConstantMemberRefInfo extends ConstantInfo{
    ConstantPool constantPool;
    int classIndex;
    int nameAndTypeIndex;

    //    该构造方法是供子类调用的,虽然有三个子类,但是并没有使用过该子类,因为当前类(父类)已经满足需求了;
//    public ConstantMemberRefInfo(ConstantPool constantPool) {
//        this.constantPool = constantPool;
//    }

    //    该构造方法是供外部调用的;
    public ConstantMemberRefInfo(ConstantPool constantPool, int type) {
        this.constantPool = constantPool;
        this.type = type; //因为接口,方法,字段通用这一个类,所以在构造方法中传入 i 来区分不同的类型;
    }


    @Override
    void readInfo(ClassReader reader) {
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

    public String getClassName() {
        return constantPool.getClassName(classIndex);
    }

    public String[] getNameAndDescriptor() {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }


    //下面两个方法是将上面的单独分开拿出来的,
    public String getName() {
        return constantPool.getName(nameAndTypeIndex);
    }

    public String getDescriptor() {
        return constantPool.getType(nameAndTypeIndex);
    }
}
