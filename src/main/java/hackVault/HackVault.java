package hackVault;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackVault {
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(9999));
        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingVaultHacker("AscendingVaultHacker", vault));
        threads.add(new DescendingVaultHacker("DescendingVaultHacker", vault));
        threads.add(new RandomVaultHacker("RandomVaultHacker_1", vault));
        threads.add(new RandomVaultHacker("RandomVaultHacker_2", vault));
        threads.add(new Police());

        for (Thread th : threads)
            th.start();
    }

    private static class Vault {
        int MAX_PASSWORD = 9999;
        private int password;
        private int guessTime = 5;

        public Vault(int password) {
            this.password = password;
            System.out.println(password);
        }

        public boolean isCorrectPassword(int guess) throws InterruptedException {
            Thread.sleep(guessTime);
            return this.password == guess;
        }
    }

    private static abstract class VaultHackers extends Thread {
        String threadName;
        Vault vault;

        public VaultHackers(String threadName, Vault vault) {
            this.threadName = threadName;
            this.vault = vault;
        }

        @Override
        public void run() {
            guessPassword();
        }

        public abstract void guessPassword();
    }

    private static class AscendingVaultHacker extends VaultHackers {

        public AscendingVaultHacker(String threadName, Vault vault) {
            super(threadName, vault);
        }

        @Override
        public void guessPassword() {
            for (int i = 0; i < vault.MAX_PASSWORD; i++) {
                try {
                    if (vault.isCorrectPassword(i)) {
                        System.out.println("Vault hacked by AscendingHacker. Password is " + i);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class DescendingVaultHacker extends VaultHackers {

        public DescendingVaultHacker(String threadName, Vault vault) {
            super(threadName, vault);
        }

        @Override
        public void guessPassword() {
            for (int i = vault.MAX_PASSWORD; i > 0; i--) {
                try {
                    if (vault.isCorrectPassword(i)) {
                        System.out.println("Vault hacked by DescendingHacker. Password is " + i);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class RandomVaultHacker extends VaultHackers {

        public RandomVaultHacker(String threadName, Vault vault) {
            super(threadName, vault);
        }

        @Override
        public void guessPassword() {
            Random random = new Random();
            int count = 0;
            while (count < vault.MAX_PASSWORD) {
                try {
                    int guess = random.nextInt(vault.MAX_PASSWORD);
                    if (vault.isCorrectPassword(guess)) {
                        System.out.println("Vault hacked by Random Hacker. Password is " + guess);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Police extends Thread {
        @Override
        public void run() {
            try {
                int count = 10;
                while(count != 0) {
                    System.out.println("Police coming in " + count);
                    Thread.sleep(1000);
                    count--;
                }
                System.out.println("Police arrived. Hackers are caught");
                System.exit(0);
            }catch (Exception e) {}
        }
    }
}
