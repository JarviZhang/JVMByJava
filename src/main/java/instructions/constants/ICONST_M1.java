package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;
/*
* 向操作数栈中加入-1
* */
public class ICONST_M1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushInt(-1);
    }
}
