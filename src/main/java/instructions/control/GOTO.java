package instructions.control;

import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;
import runtimeData.Zframe;
/*
* GOTO指令
* */
public class GOTO extends BranchInstruction {
    //pc = pc + offset,并保存在frame的nextPC中
    @Override
    public void execute(Zframe frame) {
        BranchLogic.branch(frame, offset);
    }
}
