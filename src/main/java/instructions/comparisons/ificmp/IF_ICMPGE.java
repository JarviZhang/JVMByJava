package instructions.comparisons.ificmp;

import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;
import runtimeData.Zframe;
/*
* 如果操作数栈变量一大于变量二则pc += offset
* */
public class IF_ICMPGE extends BranchInstruction {
    @Override
    public void execute(Zframe frame) {
        int[] res = IfIcmp._icmpPop(frame);
        int val1 = res[0];
        int val2 = res[1];
        if (val1 >= val2) {
            BranchLogic.branch(frame, offset);
        }
    }
}
