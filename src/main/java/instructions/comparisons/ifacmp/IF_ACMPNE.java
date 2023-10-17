package instructions.comparisons.ifacmp;

import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;
import runtimeData.Zframe;
/*
* 如果操作数栈的前两个变量不相等,则pc += offset
* */
public class IF_ACMPNE extends BranchInstruction {
    @Override
    public void execute(Zframe frame) {
        if (!IfAcmp._acmp(frame)) {
            BranchLogic.branch(frame, offset);
        }
    }
}
