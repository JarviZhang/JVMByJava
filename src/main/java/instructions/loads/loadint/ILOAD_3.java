package instructions.loads.loadint;

import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import runtimeData.Zframe;

public class ILOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.iload(frame, 3);
    }
}
