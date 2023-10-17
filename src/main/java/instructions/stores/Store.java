package instructions.stores;

import runtimeData.Zframe;
import runtimeData.heap.Zobject;

/*
* 存储指令工具类(从操作数栈运到本地变量表)
* 针对引用,int,long,double,float五种类型;
* */
public class Store {
    public static void astore(Zframe frame, int index) {
        Zobject ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, ref);
    }

    public static void istore(Zframe frame, int index) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }

    public static void lstore(Zframe frame, int index) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }

    public static void fstore(Zframe frame, int index) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }

    public static void dstote(Zframe frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }

    //用在 store 数组元素时，检测数组是否为 null
    public static void checkNotNull(Zobject arrRef) {
        if (arrRef == null) {
            throw new NullPointerException();
        }
    }

    public static void checkIndex(int count, int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("index: " + index + " array's count: " + count);
        }
    }
}
