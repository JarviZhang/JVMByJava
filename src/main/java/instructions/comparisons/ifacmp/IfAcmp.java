package instructions.comparisons.ifacmp;

import runtimeData.OperandStack;
import runtimeData.Zframe;
import runtimeData.heap.Zobject;

public class IfAcmp {
    //判断操作数栈的头两个变量是否相等
    public static boolean _acmp(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        Zobject ref2 = stack.popRef();
        Zobject ref1 = stack.popRef();
        return ref1 == ref2;
    }
}
