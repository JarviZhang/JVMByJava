package runtimeData;

import runtimeData.heap.Zobject;

//TODO: git上是继承Slot
public class LocalVars {
    //具体局部变量表
    private Slot[] localVars;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            localVars = new Slot[maxLocals];
        } else {
            throw new NullPointerException("maxLocals < 0");
        }
    }

    //提供了对 int,float,long,double,引用的存取,这里要注意的是 long 和 double 是占用 8 字节的,所以使用了局部变量表中的两个槽位分别存储前四字节和后四字节
    public void setInt(int index, int val) {
        Slot slot = new Slot();
        slot.num = val;
        localVars[index] = slot;
    }

    public int getInt(int index) {
        return localVars[index].num;
    }

    public void setFloat(int index, float val) {
        Slot slot = new Slot();
        slot.num = Float.floatToIntBits(val);
        localVars[index] = slot;
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(localVars[index].num);
    }

    public void setLong(int index, long val) {
        //先存低 32 位
        Slot slot1 = new Slot();
        slot1.num = (int) (val);
        localVars[index] = slot1;
        //再存高 32 位
        Slot slot2 = new Slot();
        slot2.num = (int) (val >> 32);
        localVars[index + 1] = slot2;
    }

    public long getLong(int index) {
        int low = localVars[index].num;
        long high = localVars[index + 1].num;
        return ((high & 0x000000ffffffffL) << 32) | (low & 0x00000000ffffffffL);
    }

    public void setDouble(int index, double val) {
        long bits = Double.doubleToLongBits(val);
        setLong(index, bits);
    }

    public double getDouble(int index) {
        long bits = getLong(index);
        return Double.longBitsToDouble(bits);
    }

    public void setRef(int index, Zobject ref) {
        Slot slot = new Slot();
        slot.ref = ref;
        localVars[index] = slot;
    }

    public Zobject getRef(int index) {
        return localVars[index].ref;
    }
}
