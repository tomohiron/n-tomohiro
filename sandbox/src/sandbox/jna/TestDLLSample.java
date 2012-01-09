package sandbox.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class TestDLLSample {

    static {
        System.setProperty("jna.library.path",
                "C:\\Users\\tomohiro\\Documents\\Visual Studio 2008\\Projects\\test\\Debug");
        Native.register("test");
    }

    public static native Pointer doubleArray();

    public static native int func(int a, int b);

//    public static native void test();

    public static void main(String[] args) {
        int size = 10;
        Pointer p = doubleArray();
        double[] array = p.getDoubleArray(0, size);
        for (int i = 0; i < size; ++i) {
            System.out.println(array[i]);
        }

        int x = func(1, 2);
        System.out.println(x);

//        test();
    }
}
