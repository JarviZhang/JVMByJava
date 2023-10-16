package instructions.base;
/*
* 无操作数的指令抽象类
* */
public abstract class NoOperandsInstruction implements Instruction{
    @Override
    public void fetchOperands(BytecodeReader reader){

    }
}
