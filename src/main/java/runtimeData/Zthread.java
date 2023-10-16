package runtimeData;
/*
* 线程实体类
* */
public class Zthread {
    //pc计数器
    private int pc;
    //虚拟机栈帧
    private Zstack stack;

    public Zthread(){
        //默认栈的大小为1024,也就是说可以装1024个栈帧
        stack = new Zstack(1024);
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Zframe frame){
        stack.push(frame);
    }

    public Zframe popFrame(){
        return stack.pop();
    }

    public Zframe getCurrentFrame() {
        return stack.top();
    }

    public Zframe createFrame(int maxLocals, int maxStack){
        return new Zframe(this, maxLocals, maxStack);
    }

    public boolean isStackEmpty(){
        return stack.size == 0;
    }

    public void clearStack() {
        while (!isStackEmpty()) {
            stack.pop();
        }
    }

    public Zframe[] getFrames(){
        return stack.getFrames();
    }
}
