package generic;

public class GenericBase<T> {

    private T element;

    public void set(T arg) {
        element = arg;
    }

    public T get() {
        return element;
    }

    public <S> void setGeneric(S s) {

    }

    static class Derived2 extends GenericBase<String> {

        @Override
        public <S> void setGeneric(S s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        new Derived2().setGeneric(1);
        Class<Integer> cI = Integer.class;
        Class<Number> cN = Number.class;
        System.out.println(cN.isAssignableFrom(cI));
    }

}