import org.junit.Test;

public class cmdTest {
    @Test
    public void Test1(){
        JvmApplication jvmApplication = new JvmApplication();
        String[] args = "java -version".split(" ");
        jvmApplication.getInsert(args);
        args = "java -?".split(" ");
        jvmApplication.getInsert(args);
        args = "java -help".split(" ");
        jvmApplication.getInsert(args);
    }

    @Test
    public void Test2(){
        JvmApplication jvmApplication = new JvmApplication();
        String[] args = "java -cp D:\\importantThing\\mycode myApplication.class Tom Jerry".split(" ");
        jvmApplication.getInsert(args);
        args = "java -classpath D:\\importantThing\\mycode myApplication.class Tom Jerry".split(" ");
        jvmApplication.getInsert(args);
    }
}
