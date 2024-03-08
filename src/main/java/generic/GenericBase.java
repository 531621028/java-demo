package generic;

public class GenericBase<T> {

    private T element;

    public void set(T arg) {
        element = arg;
    }

    public T get() {
        return element;
    }

    class Derived2 extends GenericBase {

    }

}