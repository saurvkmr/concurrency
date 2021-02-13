package iterrupt;

public class ThreadInterruptTest {

    public static void main(String[] args) {
        Thread th = new Thread(new BlockingTask());
        th.start();
        th.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(190000);
            } catch (InterruptedException e) {
                System.err.println("Exiting out");
            }
        }
    }
}
