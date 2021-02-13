public class ThreadCompare {
    public static void main(String[] args) {
        Thread th = new Thread();
        Thread tc = new ThCompare();
        System.out.println(th.getClass());
        System.out.println(tc.getClass());
        System.out.println(th.getClass() == tc.getClass());
    }
}

class ThCompare extends Thread {

}