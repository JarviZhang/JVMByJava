package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;

public class LCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushLong(0);
    }
}
