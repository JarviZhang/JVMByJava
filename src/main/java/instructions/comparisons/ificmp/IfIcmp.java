package instructions.comparisons.ificmp;

import runtimeData.OperandStack;
import runtimeData.Zframe;

public class IfIcmp {
    //操作数栈pop两个元素并以数组方式返回
    static int[] _icmpPop(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        int[] res = new int[2];
        res[1] = stack.popInt();
        res[0] = stack.popInt();
        return res;
    }
}
