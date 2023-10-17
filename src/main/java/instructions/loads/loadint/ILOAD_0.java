package instructions.loads.loadint;

import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import runtimeData.Zframe;
/*
* 从本地变量表的第零个位置获取int变量，放入操作数栈中
* */
public class ILOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.iload(frame,0);
    }
}
