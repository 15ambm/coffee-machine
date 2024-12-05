package machine;

import java.util.HashMap;
import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner scanner = new Scanner(System.in);

    private int water = 0;
    private int milk = 0;
    private int beans = 0;
    private int cups = 0;
    private int money = 0;
    private int cupsMade = 0;

    private static final HashMap<Integer, CoffeeType> coffeeIngredientMap;

    static {
        coffeeIngredientMap = new HashMap<>();
        coffeeIngredientMap.put(1, CoffeeType.ESPRESSO);
        coffeeIngredientMap.put(2, CoffeeType.LATTE);
        coffeeIngredientMap.put(3, CoffeeType.CAPPUCCINO);
    }
    
    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    public void buy() {
        if (cupsMade >= 10) {
            System.out.println("I need cleaning!");
            return;
        }
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
        String input = scanner.nextLine();
        if (input.equals("back")) return;
        try {
            CoffeeType coffeeType = coffeeIngredientMap.get(Integer.parseInt(input));
            if (this.water - coffeeType.water < 0) System.out.println("Sorry, not enough water!");
            else if (this.milk - coffeeType.milk < 0) System.out.println("Sorry, not enough milk!");
            else if (this.beans - coffeeType.beans < 0) System.out.println("Sorry, not enough beans!");
            else if (this.cups <= 0) System.out.println("Sorry, not enough cups!");
            else {
                System.out.println("I have enough resources, making you a coffee!");
                this.water -= coffeeType.water;
                this.milk -= coffeeType.milk;
                this.beans -= coffeeType.beans;
                this.money += coffeeType.cost;
                this.cups--;
                this.cupsMade++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid coffee type. Choose one of 1 - espresso, 2 - latte, 3 - cappuccino");
        }
    }
    public void fill() {
        System.out.println("Write how many ml of water you want to add:");
        String water = scanner.nextLine();
        System.out.println("Write how many ml of milk you want to add:");
        String milk = scanner.nextLine();
        System.out.println("Write how many grams of coffee beans you want to add:");
        String beans = scanner.nextLine();
        System.out.println("Write how many disposable cups you want to add:");
        String cups = scanner.nextLine();

        try {
            this.water += Integer.parseInt(water);
            this.milk += Integer.parseInt(milk);
            this.beans += Integer.parseInt(beans);
            this.cups += Integer.parseInt(cups);
        } catch(NumberFormatException e) {
            System.out.println("Invalid fill amount(s)");
        }
    }
    public void take() {
        System.out.printf("I gave you %s\n", this.money);
        this.money = 0;
    }

    public void clean() {
        System.out.println("I have been cleaned!\n");
        this.cupsMade = 0;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nThe coffee machine has:\n");
        builder.append(String.format("%s ml of water\n", this.water));
        builder.append(String.format("%s ml of milk\n", this.milk));
        builder.append(String.format("%s g of coffee beans\n", this.beans));
        builder.append(String.format("%s disposable cups\n", this.cups));
        builder.append(String.format("$%s of money", this.money));
        return builder.toString();
    }

    private enum CoffeeType {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        final int water, milk, beans, cost;

        CoffeeType(int water, int milk, int beans, int cost) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cost = cost;
        }
    }
}
