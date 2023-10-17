package instructions.loads.loadfloat;

import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import runtimeData.Zframe;

public class FLOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.fload(frame,2);
    }
}
