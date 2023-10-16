package instructions.base;

import runtimeData.Zframe;

/*
* 指令的抽象接口
* */
public interface Instruction {
    //提取操作数
    void fetchOperands(BytecodeReader reader);

    //执行指令逻辑
    void execute(Zframe frame);
}
