package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;

public class FCONST_2 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushFloat(2.0f);
    }
}
