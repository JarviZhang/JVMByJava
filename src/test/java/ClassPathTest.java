import classpath.ClassPath;
import org.junit.Test;
import utils.Cmd;

import java.io.FileNotFoundException;

public class ClassPathTest {
    //启动类路径、扩展类路径使用JAVAHOME,设置用户类路径为D:\importantThing\mycode\project\JVMByJava\target\test-classes
    //测试HelloWorldForTest类
    @Test
    public void test1(){
        String cmd = "java -cp D:\\importantThing\\mycode\\project\\JVMByJava\\target\\test-classes HelloWorldForTest";
        getInsert(cmd.split(" "));
    }

    /*
    * 启动类路径、扩展类路径使用JAVAHOME,设置用户类路径为D:\importantThing\mycode\project\JVMByJava\target\test-classes
    * 测试一个不存在的类
    * */
    @Test
    public void test2(){
        String cmd = "java -cp D:\\importantThing\\mycode\\project\\JVMByJava\\target\\test-classes HelloWorld";
        getInsert(cmd.split(" "));
    }

    /*
    * 启动类路径、扩展类路径为D:\myFile\Java\jdk1.8.0_361，用户类路径为当前路径
    * 测试HelloWorldForTest类
    * */
    @Test
    public void test3(){
        String cmd = "java -xjre D:\\myFile\\Java\\jdk1.8.0_361\\jre HelloWorldForTest";
        getInsert(cmd.split(" "));
    }

    public void getInsert(String[] command){
        Cmd cmd = new Cmd(command);
        if (!cmd.isRightFmt()){
            //格式不正确
            System.out.println("Unrecognized command!");
            cmd.printUsage();
        } else {
            //如果是'java -version' or 'java -?'输出版本信息
            if (cmd.isVersionFlag()){
                System.out.println("java version \"1.8.0_20\"\n"
                        + "Java(TM) SE Runtime Environment (build 1.8.0_20-b26)\n"
                        + "Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23, mixed mode)");
            } else if (cmd.isHelpFlag()){
                cmd.printUsage();
            } else {
                ClassPath classPath = new ClassPath(cmd.getXjreOption(), cmd.getCpOption());
                try {
                    byte[] classData = classPath.readClass(cmd.getClazz());
                    int len = classData.length;
                    System.out.println("the length of file is: " + len);
                    for (int i = 0; i < len; i++){
                        if (i %20 == 0){
                            System.out.println();
                        }
                        System.out.printf("%04X", classData[i], 16);
                    }
                } catch (FileNotFoundException e){
                    System.out.println("can not find class file");
                }

            }
        }
    }
}
