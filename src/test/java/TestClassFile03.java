import classFile.ClassFile;
import classFile.MemberInfo;
import classFile.attribute.AttributeInfo;
import classpath.ClassPath;
import org.junit.Test;
import utils.Cmd;

import java.io.FileNotFoundException;

public class TestClassFile03 {
    /*
    * 启动类路径、扩展类路径使用JAVAHOME,设置用户类路径为D:\importantThing\mycode\project\JVMByJava\target\test-classes
    *
    * */
    @Test
    public void test1(){
        String cmd = "java -cp D:\\importantThing\\mycode\\project\\JVMByJava\\target\\test-classes ClassFileTest";
        getInsert(cmd.split(" "));
    }

    private void getInsert(String[] cmds){
        Cmd cmd = new Cmd(cmds);
        ClassPath classPath = new ClassPath(cmd.getXjreOption(), cmd.getCpOption());
        try {
            byte[] classData = classPath.readClass(cmd.getClazz());
            ClassFile classFile = new ClassFile(classData);
            System.out.println("classFile.getMajorVersion: " + classFile.getMajorVersion());
            System.out.println("classFile.getMinorVersion: " + classFile.getMinorVersion());
            System.out.println("classFile.getAccessFlags: " + classFile.getAccessFlags());
            System.out.println("classFile.getClassName: " + classFile.getClassName());
            System.out.println("classFile.getSuperClassName: " + classFile.getSuperClassName());
            System.out.println("interface names:");
            for (String name : classFile.getInterfaceNames()) {
                System.out.println(name);
            }
            System.out.println("---------------------");
            System.out.println("field count: " + classFile.getFields().length);
            for (MemberInfo name : classFile.getFields()) {
                System.out.println(name.getName());
            }
            System.out.println("---------------------");
            System.out.println("method count: " + classFile.getMethods().length);
            for (MemberInfo name : classFile.getMethods()) {
                System.out.println(name.getName() + ":" + name.getDescriptor());
            }
            System.out.println("---------------------");
            System.out.println("constantPool count: "+classFile.getConstantPool().getConstantPoolCount());
            System.out.println("---------------------");
            System.out.println("attribute count:"+classFile.getAttributes().length);
            for (AttributeInfo attribute:classFile.getAttributes()){
                System.out.println(attribute.getClass());
            }
        } catch (FileNotFoundException e){
            System.out.println("can not find class file");
        }

    }
}
