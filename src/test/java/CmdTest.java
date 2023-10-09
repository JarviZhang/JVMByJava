import org.junit.Test;
import utils.Cmd;

/*
* 这里对ch01的命令行工具进行测试
* */
public class CmdTest {

    /*
    * 测试三条指令
    * java -version
    * java -?
    * java -help
    * */
    @Test
    public void Test1(){
        System.out.println("test 'java -version'");
        String[] args = "java -version".split(" ");
        System.out.println("result:");
        getInsert(args);
        System.out.println("----------------------");
        System.out.println("test 'java -?'");
        System.out.println("result:");
        args = "java -?".split(" ");
        getInsert(args);
        System.out.println("----------------------");
        System.out.println("test 'java -help'");
        System.out.println("result:");
        args = "java -help".split(" ");
        getInsert(args);
        System.out.println("----------------------");
    }

    /*
    * 测试三条指令
    * java -cp classpath ClassName arg1 arg2 ...
    * java -classpath classpath ClassName arg1 arg2 ...
    *java -xjre jrepath className arg1 arg2 ...
    * */
    @Test
    public void Test2(){
        System.out.println("test 'java -cp D:\\importantThing\\mycode myApplication Tom Jerry'");
        String[] args = "java -cp D:\\importantThing\\mycode myApplication.class Tom Jerry".split(" ");
        System.out.println("result:");
        getInsert(args);
        System.out.println("----------------------");
        System.out.println("test 'java -classpath D:\\importantThing\\mycode myApplication Tom Jerry'");
        args = "java -classpath D:\\importantThing\\mycode myApplication.class Tom Jerry".split(" ");
        System.out.println("result");
        getInsert(args);
        System.out.println("----------------------");
        System.out.println("test 'java -xjre D:\\importantThing\\mycode myApplication Tom Jerry'");
        System.out.println("result:");
        args = "java -xjre D:\\importantThing\\mycode myApplication.class Tom Jerry".split(" ");
        getInsert(args);
    }

    //使用CMD的接口
    public void getInsert(String[] args) {
        Cmd cmd = new Cmd(args);
        //判断命令格式是否正确
        if (!cmd.isRightFmt()){
            //格式不正确，输出正确格式样式
            cmd.printUsage();
        } else {
            //如果是'java -version' or 'java -?'输出版本信息
            if (cmd.isVersionFlag()){
                System.out.println("java version \"1.8.0_20\"\n"
                        + "Java(TM) SE Runtime Environment (build 1.8.0_20-b26)\n"
                        + "Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23, mixed mode)");
                //这里判断args为null，不知道为什么要判断这个，应该不会出现这种情况
                //} else if (cmd.isHelpFlag() || cmd.getArgs() == null){
                //如果是'java -help'
            } else if (cmd.isHelpFlag()){
                cmd.printUsage();
            } else {
                System.out.println("startJVM");
            }
        }
    }
}
