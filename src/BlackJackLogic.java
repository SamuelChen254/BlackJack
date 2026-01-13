import java.sql.SQLOutput;
import java.util.Map;
import java.util.Scanner;


public class BlackJackLogic {
    private Scanner scan;
    private Player player;
    private Cards deck;
    private int total = 0;
    private int dealerTotal = 0;


    public BlackJackLogic() {
        scan = new Scanner(System.in);
        player = new Player();
        deck = new Cards();
    }


    public void play() {
        System.out.println("Welcome to Black Jack!");
        while(!broke()) {
            playRound();
        }
    }


    private void playRound()  {
        Map<String, Integer> cards = Cards.createDeckOfCards();

        System.out.println("---------------------");
        System.out.println("Cash Amount: " + player.getCash());
        System.out.println("---------------------");
        System.out.println("How much do you want to bet?");
        System.out.println("---------------------");
        System.out.print("Bet Amount: ");
        int bet = scan.nextInt();
        scan.nextLine();
        player.bet(bet);
        System.out.println("---------------------");
        System.out.println("Cash Amount: " + player.getCash());
        System.out.println("---------------------");
        String card1 = deck.getDeck();
        deck.remove(card1);
        String card2 = deck.getDeck();
        deck.remove(card2);
        System.out.println("You received a " + card1 + " and a " + card2);
        total += cards.get(card1) + cards.get(card2);
        System.out.println("total: "+ total);
        //Dealer's Turn
        System.out.println("----------------------");
        String dealerCard1 = deck.getDeck();
        deck.remove(dealerCard1);
        String dealerCard2 = deck.getDeck();
        deck.remove(dealerCard2);
        System.out.println("The dealer has a " + dealerCard1 + " and one other");
        dealerTotal += cards.get(dealerCard1) + cards.get(dealerCard2);
        System.out.println("----------------------");
        //Back to user
        System.out.println("Do you still want to draw?" + "\n" + "yes(x) || no(y)");
        String choice = scan.nextLine();
        while (choice.equals("X") || choice.equals("x")) {
            player.hitMe();
            String card3 = deck.getDeck();
            deck.remove(card3);
            System.out.println("You received a " + card3);
            total += cards.get(card3);
            System.out.println("total: " + total);
            System.out.println("Do you still want to draw?" + "\n" + "yes(x) || no(y)");
            choice = scan.nextLine();
        }
        if (choice.equals("Y") || choice.equals("y")) {
            player.stand();
            while (dealerTotal <= 16) {
                String dealerCard3 = deck.getDeck();
                deck.remove(dealerCard3);
                dealerTotal += cards.get(dealerCard3);
            }
            System.out.println("----------------------");
            System.out.println("Dealer: " + player.stand());
            if (total <= 21 && total > dealerTotal || dealerTotal > 21 && total < dealerTotal) {
                System.out.println("Your total: " + total + "\n" + "Dealer total: " + dealerTotal);
                System.out.println("YOU WIN!");
                player.addCash(bet * 2);
            }else if (dealerTotal <= 21 && dealerTotal > total || total > 21 && dealerTotal < total ){
                System.out.println("Your total: " + total + "\n" + "Dealer total: " + dealerTotal);
                System.out.println("BUSTED! HOUSE WINS!");
                total = 0;
            }else {
                System.out.println("Your total: " + total + "\n" + "Dealer total: " + dealerTotal);
                System.out.println("DRAW!");
            }
        }
    }

    public boolean broke() {
        return player.getCash() <= 0;
    }
}

