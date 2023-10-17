package instructions.loads.loadlong;

import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import runtimeData.Zframe;

public class LLOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.lload(frame,1);
    }
}
