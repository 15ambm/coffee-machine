package machine;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        String input;
        do {
            System.out.println("\nWrite action (buy, fill, take, clean, remaining, exit):");
            input = scanner.nextLine();
            switch (input) {
                case "buy" -> machine.buy();
                case "fill" -> machine.fill();
                case "take" -> machine.take();
                case "remaining" -> System.out.println(machine);
                case "clean" -> machine.clean();
                case "exit" -> {}
                default -> System.out.println("Unrecognized action, please try again");
            }
        } while(!input.equals("exit"));
    }
}
