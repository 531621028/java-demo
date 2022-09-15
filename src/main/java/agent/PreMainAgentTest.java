package agent;

public class PreMainAgentTest {

    //  java -classpath D:\study\java-demo\target\classes -javaagent:C:\Users\KANG\Desktop\maven-demo-1.0-SNAPSHOT
    //  .jar agent.PreMainAgentTest 命令行启动
    public static void main(String[] args) {
        System.out.println("main start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }
}