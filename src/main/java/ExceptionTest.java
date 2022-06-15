/**
 *
 */
public class ExceptionTest {

    /**
     * final中的返回值和抛出的异常会覆盖掉在正常代码块和catch代码中的返回值和异常，
     * 执行的流程是如果有异常或者返回值，会把异常或者返回值放到栈中的一个位置，
     * 当final或者catch执行的时候有异常或者返回值会使用新的异常和返回值替换之前的异常或者返回值
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // System.out.println(returnTest());
            throwTest();
        } catch (Exception e) {
            System.out.println("outer block");
            System.out.println(e.hashCode());
        }
    }

    public static int returnTest() {
        try {
            System.out.println("try block");
            throw new RuntimeException();
            // return 1;
        } catch (RuntimeException e) {
            System.out.println("catch block");
            return 2; // final中return的优先级会覆盖正常的返回值
        } finally {
            System.out.println("final block");
            // return 3; // final中return的优先级会覆盖正常和catch中的返回值
        }
    }

    public static void throwTest() {
        try {
            System.out.println("try block");
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("catch block");
            RuntimeException exception = new RuntimeException();
            System.out.println(exception.hashCode());
            throw exception;
        } finally { // catch中抛异常也会执行final中的代码
            System.out.println("final block");
            RuntimeException exception = new RuntimeException();
            System.out.println(exception.hashCode());
            throw exception; // final中抛出的异常会覆盖catch中的异常抛出
        }
    }
}