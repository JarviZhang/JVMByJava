package instructions.base;
/*
* 跳转指令的抽象类
* */
public abstract class BranchInstruction implements Instruction{
    //pc的偏移量
    public int offset;

    //获取pc偏移量
    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt16();
    }
}
