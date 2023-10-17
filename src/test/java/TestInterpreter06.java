import classFile.ClassFile;
import classFile.MemberInfo;
import classFile.attribute.CodeAttribute;
import classpath.ClassPath;
import instructions.InstructionFactory;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import org.junit.Test;
import runtimeData.Zframe;
import runtimeData.Zthread;
import utils.Cmd;

import java.io.FileNotFoundException;

/*
* 指令集相关的测试
* */
public class TestInterpreter06 {
    //使用自己实现的指令集来测试test方法生成的指令
    @Test
    public void test1(){
        String cmdLine = "java -cp D:\\importantThing\\mycode\\project\\JVMByJava\\target\\test-classes TestInterpreter06";
        String[] cmds = cmdLine.split(" ");
        Cmd cmd = new Cmd(cmds);
        ClassPath classPath = new ClassPath(cmd.getXjreOption(), cmd.getCpOption());
        try {
            byte[] classData = classPath.readClass(cmd.getClazz());
            ClassFile classFile = new ClassFile(classData);
            MemberInfo[] methods = classFile.getMethods();
            MemberInfo targetMethod = null;
            for (MemberInfo method : methods) {
                if (method.getName().equals("test") && method.getDescriptor().equals("()I")) {
                    targetMethod = method;
                    break;
                }
            }
            if (targetMethod != null) {
                CodeAttribute codeAttribute = targetMethod.getCodeAttribute();
                int maxLocals = codeAttribute.getMaxLocals();
                int maxStack = codeAttribute.getMaxStack();
                byte[] byteCode = codeAttribute.getCode();
                Zthread thread = new Zthread();
                Zframe frame = thread.createFrame(maxLocals, maxStack);
                BytecodeReader reader = new BytecodeReader();
                while (true) {
                    int pc = frame.getNextPC();
                    thread.setPc(pc);
                    //将Code的二进制内容和pc保存到reader中,以方便后续使用
                    reader.reset(byteCode, pc);
                    //读取pc对象的指令,reader的pc+=1
                    int opCode = reader.readInt8() & 0xFF;
                    try {
                        Instruction instruction = InstructionFactory.createInstruction(opCode);
                        instruction.fetchOperands(reader);
                        frame.setNextPC(reader.getPc());
                        instruction.execute(frame);
                    } catch (Exception e){
                        System.out.println("return: ");
                        System.out.println(frame.getOperandStack().popInt());
                        return;
                    }
                }
            }else {
                System.out.println("can not find method");
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    @Test
    public void test2(){
        System.out.println(test());
    }

    //要测试的方法
    public static int test() {
        int sum = 0;
        for (int i = 0; i < 100; i++){
            sum += i;
        }
        return sum;
    }
}
