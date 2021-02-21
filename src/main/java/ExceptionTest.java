public class ExceptionTest {

    public static void main(String[] args) {
        try {
            System.out.println(returnTest());
            throwTest();
        } catch (Exception e) {
            System.out.println("outer block");
            System.out.println(e.hashCode());
        }
    }

    public static int returnTest() {
        try {
            System.out.println("try block");
            return 1;
//            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("catch block");
            return 2;
        } finally {
            System.out.println("final block");
            return 3;
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
        } finally {
            System.out.println("final block");
            RuntimeException exception = new RuntimeException();
            System.out.println(exception.hashCode());
            throw exception;
        }
    }
}