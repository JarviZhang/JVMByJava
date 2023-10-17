package instructions.stores.storeint;

import instructions.base.NoOperandsInstruction;
import instructions.stores.Store;
import runtimeData.Zframe;
/*
 * 从操作数栈顶pop一个int，然后放入本地变量表的第1个位置
 * */
public class ISTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Store.istore(frame,1);
    }
}
