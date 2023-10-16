package runtimeData;

import java.util.EmptyStackException;


/*
* 线程的虚拟机栈
* 使用单向链表的方式实现栈帧
* */
public class Zstack {
    //虚拟机栈中栈帧的最大容量
    int maxSize;
    //虚拟机栈中当前栈帧的数量
    int size;
    //栈顶的栈帧
    private Zframe _top;

    public Zstack(int maxSize){
        this.maxSize =maxSize;
    }

    public void push(Zframe frame){
        if (size > maxSize){
            throw new StackOverflowError();
        }
        if (_top != null){
            frame.lower = _top;
        }
        _top = frame;
        size++;
    }

    public Zframe pop(){
        if (_top == null) {
            throw new EmptyStackException();
        }
        Zframe tmp = _top;
        _top = tmp.lower;
        tmp.lower = null;
        size--;
        return tmp;
    }

    public Zframe top() {
        if (_top == null){
            throw new EmptyStackException();
        }
        return _top;
    }

    public Zframe[] getFrames(){
        Zframe[] frames = new Zframe[size];
        int i = 0;
        for (Zframe frame = _top; frame != null; frame = frame.lower){
            frames[i] = frame;
            i++;
        }
        return frames;
    }
}
