package instructions.constants;

import instructions.base.NoOperandsInstruction;
import runtimeData.Zframe;
/*
* 一条什么都不做的指令
* */
public class NOP extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {

    }
}
