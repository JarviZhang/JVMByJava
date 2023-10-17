package instructions.loads.loadref;

import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import runtimeData.Zframe;

public class ALOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.aload(frame,1);
    }
}
