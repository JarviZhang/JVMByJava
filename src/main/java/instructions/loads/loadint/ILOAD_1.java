package instructions.loads.loadint;

import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import runtimeData.Zframe;
/*
* 获取本地变量表的第一个变量，并压入操作数栈
* */
public class ILOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.iload(frame,1);
    }
}
