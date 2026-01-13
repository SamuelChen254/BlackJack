public class Player {
    private int cash = 2500;

    public Player() {};

    public void bet(int amount) {
        System.out.println("I bet $" + amount + "!");
        cash -= amount;
    }

    public String hitMe() {
        return "Hit Me";
    }

    public String stand() {
        return "I Stand";
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int amount) {
        cash += amount;
    }
}
