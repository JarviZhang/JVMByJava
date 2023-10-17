package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;

public class FCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushFloat(0.0f);
    }
}
