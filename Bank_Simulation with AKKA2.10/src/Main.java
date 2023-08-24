import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("BankSystem");
        final ActorRef bankAccountActor = system.actorOf(Props.create(BankAccount.class), "bankAccountActor");

        System.out.println("Initial balance is: " + 100.0);

        final Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            double amount = random.nextDouble() * 2000 - 1000;

            if (amount > 0) {
                bankAccountActor.tell(new BankAccount.Deposit(amount), ActorRef.noSender());
            } else {
                bankAccountActor.tell(new BankAccount.Withdrawal(Math.abs(amount)), ActorRef.noSender());
            }
        }

        system.terminate();
    }
}
