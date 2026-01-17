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
       //Accounts for letter inputs
       while(!scan.hasNextInt()) {
           System.out.print("Please enter a numerical bet amount: ");
           scan.next();
       }
       int bet = scan.nextInt();
       //Accounts for negatives and values over cash amount
       while (bet > player.getCash() || bet < 0) {
           System.out.print("Please enter a valid bet amount: ");
           bet = scan.nextInt();
       }
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
       System.out.println("The dealer is showing a " + dealerCard1);
       dealerTotal += cards.get(dealerCard1) + cards.get(dealerCard2);
       System.out.println("----------------------");
       //Back to user
       System.out.println("""
                          Do you still want to draw?
                          yes(y) || no(x)""");
       String choice = scan.nextLine();
        if (choice.toLowerCase().equals("y")) {
            while (choice.toLowerCase().equals("y")) {
                player.hitMe();
                String card3 = deck.getDeck();
                deck.remove(card3);
                System.out.println("You received a " + card3);
                total += cards.get(card3);
                System.out.println("total: " + total);
                if (total > 21) {
                    System.out.println("BUSTED! HOUSE WINS!");
                    break;
                }
                System.out.println("""
                                    Do you still want to draw?
                                    yes(y) || no(x)""");
                choice = scan.nextLine();
            }
        }else if (choice.toLowerCase().equals("x")) {
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
               total = 0;
               deck.reset();
           }else if (dealerTotal <= 21 && dealerTotal > total || total > 21 && dealerTotal < total ){
               System.out.println("Your total: " + total + "\n" + "Dealer total: " + dealerTotal);
               System.out.println("BUSTED! HOUSE WINS!");
               total = 0;
               deck.reset();
           }else {
               System.out.println("Your total: " + total + "\n" + "Dealer total: " + dealerTotal);
               System.out.println("DRAW!");
               total = 0;
               deck.reset();
           }
       }else {
            System.out.println("---resetting---");
            //Simulate resetting effect by sleeping for 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            player.addCash(bet);
          }
        }    

   public boolean broke() {
       return player.getCash() <= 0;
   }
}

