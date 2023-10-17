package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;

public class ICONST_5 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushInt(5);
    }
}
