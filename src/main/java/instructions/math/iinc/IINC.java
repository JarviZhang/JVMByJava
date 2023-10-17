package instructions.math.iinc;

import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import runtimeData.LocalVars;
import runtimeData.Zframe;
/*
* iinc指令给局部变量表中的int变量增加常量值，局部变量表索引和常量值都由指令的操作数提供。
* */
public class IINC implements Instruction {
    private int index;
    private int offset;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readInt8();
        offset = reader.readInt8();
    }

    @Override
    public void execute(Zframe frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(index);
        val += offset;
        localVars.setInt(index,val);
    }
}
