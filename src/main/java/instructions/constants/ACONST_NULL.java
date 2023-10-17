package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;
/*
*向操作数栈加入null
* */
public class ACONST_NULL extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushRef(null);
    }
}
