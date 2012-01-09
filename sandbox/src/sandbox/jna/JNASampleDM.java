package sandbox.jna;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class JNASampleDM {

    static {
        Native.register(Platform.isWindows() ? "msvcrt" : "m");
    }

    public static native double cos(double x);

    public static native double sin(double x);

    public static void main(String[] args) {
        System.out.println("cos(0)=" + cos(0));
        System.out.println("sin(0)=" + sin(0));
    }
}
