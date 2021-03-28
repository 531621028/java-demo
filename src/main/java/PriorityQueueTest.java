import java.util.PriorityQueue;

/**
 * @author kang
 * @since 2021/2/23
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<CloneTest> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new CloneTest()); //对象必须实现Comparable解耦
    }
}
