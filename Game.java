
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;


public class Game {
   List<Card> cards = new ArrayList<Card>();
   List<Player> players = new ArrayList<Player>();
   int NumberOfPlayers;
   Scanner kb = new Scanner(System.in);
    
   public void shuffleCards() {
      Collections.shuffle(cards);
   }
    
   public void dealCards() {
      
   }
    
   public void promptNextPlayerToPlay() {
    
   }
   
   public Card chooseCard (String cardStr)
   {
      Card card = null;
      while (card == null) {
         System.out.print("\nChoose "+cardStr+" card=");
         int aCard = kb.nextInt();
         card = chooseCard(aCard);
         if (card == null) {
            System.out.println("\nThe card is not available choose another number");
         }
      }
      return card;
   } 
   public boolean playATurn(Player currentPlayer) {
   
      System.out.println("\n"+currentPlayer.getId()+"'s Turn\n");
      
      // The player chooses the first card
      Card card1 = chooseCard("First");
      System.out.println("Card1 is "+ card1.getCardValue());
      
      // The player chooses the 2nd card
      Card card2 =chooseCard("Second");
      System.out.println("Card2 is "+ card2.getCardValue());
               
      // Compare the cards
      if (card1.equalTo(card2))
      {
         removeCardsFromPlay(card1, card2);
         currentPlayer.scoresPoint();
         System.out.println("Great! - You got it. Play more ");
         return true;
      }
      else
      {
         returnCardsToPlay(card1, card2);
         System.out.println("Sorry! no match");
      }
      return false;
   }
   
   public Card chooseCard(int index) {
      Card aCard = cards.get(index-1);
      if (aCard.isChosen() || aCard.isRemoved())
         return null;
      else
         aCard.setChosen(true);
         
      return aCard;
   }
   
   public void removeCardsFromPlay(Card c1, Card c2)
   {
      c1.markItRemoved();
      c2.markItRemoved();
   }
   
   public void returnCardsToPlay(Card c1, Card c2) {
      c1.setChosen(false);
      c2.setChosen(false);
   }
    
   public void initializeGame() {
      
      // Create the cards
      createCards();
      
      // Get Number of Players
      System.out.println("Enter number of Players :");
      NumberOfPlayers = kb.nextInt();
      
      // Create the playing board
      GameBoard myBoard = new GameBoard();
      
      // Create the players
      for (int i = 0; i < NumberOfPlayers; i++)
      {
         Player aPlayer = new Player();
         System.out.println("Enter name of the "+ (i+1) +" Player :");
         aPlayer.setId(kb.next());
         players.add(aPlayer);
      }
      
   }
    
   public void createCards() {
      for (int suit=0; suit < 4; suit++)
      {
         for (int i=1; i <=13; i++) {
            Card aCard = new Card(i);
            cards.add(aCard);
         }
      }
   }
    
   public void playGame() {
      // Shuffle the cards
      shuffleCards();
      // Deal the card
      
      // Prompt the next player to play
     // int numberOfPlayers = 2;
      
      int numberOfCardsLeft = cards.size();
      int currentPlayer = 1;
      while (numberOfCardsLeft > 0)
      {
        Player aPlayer = players.get(currentPlayer-1);
        boolean winningPlay = playATurn(aPlayer);
         if (!winningPlay)
            currentPlayer = getNextPlayer(currentPlayer);
         
         numberOfCardsLeft = cards.size();
      }
      
    
   }
    
   public int getNextPlayer(int ctPlayer) {
      int nextPlayer=ctPlayer+1;
      if (ctPlayer == NumberOfPlayers)
      {
         nextPlayer = 1;
      }
      return nextPlayer;
   }
    
   public static void main (String [] args){
    
      Game game = new Game();
      game.initializeGame();
      game.playGame();
   }
    
}
