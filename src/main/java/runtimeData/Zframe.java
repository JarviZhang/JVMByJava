package runtimeData;
/*
* 栈帧实体类
* */
public class Zframe {
    //当前栈帧的前一个栈帧
    Zframe lower;
    //局部变量表的引用
    LocalVars localVars;
    //操作数栈的引用
    OperandStack operandStack;
    //当前栈帧所在的线程
    Zthread thread;
    //TODO: Zmethod method;
    //frame中并不改变PC的值,其PC值是由ByteReader读取字节码不断改变的
    int nextPC;

    public Zframe(Zthread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }
    //TODO:WHAT ?
//    public Zframe(Zthread thread, Zmethod method) {
//        this.thread = thread;
//        this.method = method;
//        localVars = new LocalVars(method.getMaxLocals());
//        operandStack = new OperandStack(method.getMaxStack());
//    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Zthread getThread() {
        return thread;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }
    //TODO:WHAT?
//    public Zmethod getMethod() {
//        return method;
//    }
//
//    public void setMethod(Zmethod method) {
//        this.method = method;
//    }

    //TODO: WHAT?
    //用在new，getStatic，invokeStatic 等指令中，判断clinit 方法是否执行，如果执行，则需要保存当前thread 的 pc
    //eg：当前执行的是 new 指令，那么 thead 的 pc 指向的是 new，
    //再 push 一个新栈去执行<clinit>，等<clinit>直接结束后，在回到当前 frame，拿到 pc，此时的 pc 指向的还是 new
    //重新执行一遍 new
    public void revertNextPC() {
        this.nextPC = thread.getPc();
    }
}
