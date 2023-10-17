package instructions.math.add;

import instructions.base.NoOperandsInstruction;
import runtimeData.OperandStack;
import runtimeData.Zframe;
/*
* 栈中取出两个值，相加后push进栈
* */
public class IADD extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        int val2 = stack.popInt();
        int res = val1 + val2;
        stack.pushInt(res);
    }
}
