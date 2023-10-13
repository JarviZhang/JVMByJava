package utilsTest;

import org.junit.Test;

public class ByteUtilsTest {
    @Test
    public void test1(){
        byte i = -12;
        int a = (int) i;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(i);
        System.out.println(i & 0xFF);
        System.out.println(Integer.toHexString(i));
        System.out.println(Integer.toHexString(i & 0xFF));
    }

    @Test
    public void test2(){
        byte a = 12;
        int ai = a & 0xFF;
        System.out.println(ai);
        System.out.println(Integer.toBinaryString(ai));
        byte b = -12;
        int bi = b & 0xFF;
        System.out.println(bi);
        System.out.println(Integer.toBinaryString(bi));
    }

    @Test
    public void test3(){
        byte a = 12;  //00001100
        int ai = (a +256) % 256;
        System.out.println(ai);
        System.out.println(Integer.toBinaryString(ai));
        byte b = -12;  //11110100
        int bi = (b +256) % 256;
        System.out.println(bi);
        System.out.println(Integer.toBinaryString(bi));
        int c = ai * 256 + bi;
        System.out.println(Integer.toBinaryString(c));
    }
}
