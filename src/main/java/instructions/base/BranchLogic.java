package instructions.base;

import runtimeData.Zframe;

/*
* 真正的跳转指令
* */
public class BranchLogic {
    //pc = pc + offset,并保存在frame的nextPC中
    public static void branch(Zframe frame, int offset) {
        int pc = frame.getThread().getPc();
        int nextPC = pc + offset;
        frame.setNextPC(nextPC);
    }
}
