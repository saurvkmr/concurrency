import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyExecutor {
    public void testExecutor() {
        Thread th = new Thread(() ->{
            try(BufferedReader bis = new BufferedReader(new FileReader("/Users/s0k02c9/workspace/personal/git/concurrency/src/resources/txtDump.txt"))) {
                bis.lines().forEach(line -> System.out.println(String.join(" -", Thread.currentThread().getName(), line)));
            } catch (Exception ex) {
                System.err.println(ex);
            }
        });

        Executor ex = Executors.newFixedThreadPool(0);
        ex.execute(th);
    }

    public static void main(String[] args) {
        new MyExecutor().testExecutor();
    }
}
