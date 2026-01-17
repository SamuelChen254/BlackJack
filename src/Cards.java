import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cards {
    public Cards() {}

    String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    List<String> list = new ArrayList<>(Arrays.asList(cards));

    public static Map<String, Integer> createDeckOfCards() {
        Map <String, Integer> deckOfCards = new HashMap<>();

        //Num Cards
        for (int i = 2; i < 11; i++) {
            deckOfCards.put(String.valueOf(i), i);
        }

        //Face Cards
        deckOfCards.put("Jack", 10);
        deckOfCards.put("Queen", 10);
        deckOfCards.put("King", 10);
        deckOfCards.put("Ace", 1);

        return deckOfCards;
    }

    public String getDeck() {
        int index = (int) (Math.random() * list.size() - 1);
        return list.get(index);
    }

    public void remove(String word) {
        list.remove(word);
    }

    public void reset() {
        list = new ArrayList<>(Arrays.asList(cards));
    }
}