import org.junit.Test;
import runtimeData.LocalVars;
import runtimeData.OperandStack;

import java.time.LocalDate;

/*
* 运行时数据区-线程相关
* */
public class TestThread04 {
    //测试局部变量表
    @Test
    public void testLocalVars(){
        LocalVars localVars = new LocalVars(10);
        localVars.setInt(0,100);
        localVars.setInt(1,-100);
        localVars.setLong(2,2997934580L);
        localVars.setLong(4,-2997934580L);
        localVars.setFloat(6,3.1415926f);
        localVars.setDouble(7,2.141592678912);
        System.out.println(localVars.getInt(0));
        System.out.println(localVars.getInt(1));
        System.out.println(localVars.getLong(2));
        System.out.println(localVars.getLong(4));
        System.out.println(localVars.getFloat(6));
        System.out.println(localVars.getDouble(7));
    }
    //测试操作数栈
    @Test
    public void testOperandStack(){
        OperandStack stack = new OperandStack(10);
        stack.pushInt(100);
        stack.pushInt(-100);
        stack.pushLong(2997934580L);
        stack.pushLong(-2997934580L);
        stack.pushFloat(3.1415925f);
        stack.pushDouble(2.141592678912);

        System.out.println(stack.popDouble());
        System.out.println(stack.popFloat());
        System.out.println(stack.popLong());
        System.out.println(stack.popLong());
        System.out.println(stack.popInt());
        System.out.println(stack.popInt());
    }
}
