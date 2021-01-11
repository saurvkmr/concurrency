import java.io.BufferedReader;
import java.io.FileReader;

public class MyThreadClass {
    public void readFromFile() {
        Thread th = new Thread(() -> {
           try(BufferedReader bis = new BufferedReader(new FileReader("/Users/s0k02c9/workspace/personal/git/concurrency/src/resources/txtDump.txt"))) {
               bis.lines().forEach(line -> System.out.println(String.join(" -", Thread.currentThread().getName(), line)));
           } catch (Exception ex) {
               System.err.println(ex);
           }
        });
        Thread th1 = new Thread(() -> {
            try(BufferedReader bis = new BufferedReader(new FileReader("/Users/s0k02c9/workspace/personal/git/concurrency/src/resources/txtDump.txt"))) {
                bis.lines().forEach(line -> System.out.println(String.join(" -", Thread.currentThread().getName(), line)));
            } catch (Exception ex) {
                System.err.println(ex);
            }
        });
        Thread th2 = new Thread(() -> {
            try(BufferedReader bis = new BufferedReader(new FileReader("/Users/s0k02c9/workspace/personal/git/concurrency/src/resources/txtDump.txt"))) {
                bis.lines().forEach(line -> System.out.println(String.join(" -", Thread.currentThread().getName(), line)));
            } catch (Exception ex) {
                System.err.println(ex);
            }
        });
        try {
            th.start();
            th1.start();
            th2.start();

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        new MyThreadClass().readFromFile();
    }
}
