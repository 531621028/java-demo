import java.text.MessageFormat;

/**
 * @author kang
 * @since 2020/7/22
 */
public class CloneTest implements Cloneable {

    private final String name;

    public CloneTest() {
        this(null);
    }

    public CloneTest(String name) {
        this.name = name;
        System.out.println("调用构造器了");
    }


    @Override
    protected CloneTest clone() throws CloneNotSupportedException {
        return (CloneTest) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest origin = new CloneTest("clone");
        CloneTest clone = origin.clone();
        System.out.println(origin);
        System.out.println(clone);
    }

    @Override
    public String toString() {
        return MessageFormat.format("name:{0}", this.name);
    }
}
