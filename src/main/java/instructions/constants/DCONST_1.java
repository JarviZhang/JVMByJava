package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;

public class DCONST_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushDouble(1.0);
    }
}
