package iterrupt;

import java.math.BigInteger;

public class LongComputationInterrupt {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputation(new BigInteger("200000"), new BigInteger("1000000")));
        thread.start();
        thread.interrupt();
    }

    private static class LongComputation implements Runnable {
        BigInteger base;
        BigInteger power;

        public LongComputation(BigInteger number, BigInteger power) {
            this.base = number;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base+ " ^ " + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger number, BigInteger pow) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(pow) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread got interrupted. Exiting out");
                    return BigInteger.ZERO;
                }
                result = result.multiply(number);
            }
            return result;
        }
    }
}
