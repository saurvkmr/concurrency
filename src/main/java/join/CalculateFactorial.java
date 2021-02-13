package join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculateFactorial {

    public static void main(String[] args) {
        List<BigInteger> nums = Arrays.asList(new BigInteger("50000"), new BigInteger("1000000"), new BigInteger("20000"), new BigInteger("80000"), new BigInteger("50000"), new BigInteger("5000"));
        List<Thread> threads = new ArrayList<>(nums.size());
        nums.forEach(num -> threads.add(new Thread(new Factorial(num))));
        threads.forEach(it -> {
            it.setDaemon(true);
            it.start();
        });

        threads.forEach(it -> {
            try {
                it.join(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    private static class Factorial implements Runnable {

        private BigInteger number;

        public Factorial(BigInteger integer) {
            this.number = integer;
        }

        @Override
        public void run() {
            System.out.println("thread - " + Thread.currentThread().getName() + "\n factorial - " + calculateFactorial(number));
        }

        private BigInteger calculateFactorial(BigInteger integer) {
            /*if (BigInteger.ONE.compareTo(integer) == 0) return BigInteger.ONE;
            return integer.multiply(calculateFactorial(integer.subtract(BigInteger.ONE)));*/
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ONE; i.compareTo(integer) != 0; i = i.add(BigInteger.ONE))
                result = result.multiply(i);
            return result;
        }
    }
}
