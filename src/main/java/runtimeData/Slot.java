package runtimeData;

import runtimeData.heap.Zobject;
//TODO:COM
/*
* 运行时数据区虚拟机栈栈帧中局部变量表的slot(存储具体的数据)
* 数据有两种:
* 1. 基本类型
* 2. 引用类型
* */
public class Slot {
    //如果是基本类型使用这种形式
    public int num;
    //如果是引用类型使用这种形式
    public Zobject ref;

    public Slot() {}
}
