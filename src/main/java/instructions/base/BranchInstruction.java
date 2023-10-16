package instructions.base;
/*
* 跳转指令的抽象类
* */
public abstract class BranchInstruction implements Instruction{
    public int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt16();
    }
}
