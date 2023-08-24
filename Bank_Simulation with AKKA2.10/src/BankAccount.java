import akka.actor.AbstractActor;

public class BankAccount extends AbstractActor {
    private double balance = 100.0;

    public static class Deposit {
        private final double amount;

        public Deposit(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }
    }

    public static class Withdrawal {
        private final double amount;

        public Withdrawal(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Deposit.class, deposit -> {
                    balance += deposit.getAmount();
                    System.out.println("Deposit: " + deposit.getAmount() + ", Balance: " + balance);
                })
                .match(Withdrawal.class, withdrawal -> {
                    balance -= withdrawal.getAmount();
                    System.out.println("Withdrawal: " + withdrawal.getAmount() + ", Balance: " + balance);
                })
                .matchAny(o -> System.out.println("Invalid Operation"))
                .build();
    }
}
