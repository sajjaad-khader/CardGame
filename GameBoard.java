
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameBoard extends JFrame implements ActionListener {

   JPanel gamePanel;
   List<Card> cards = new ArrayList<Card>();
   
   
   public void createCards() {
      for (int suit=0; suit < 4; suit++)
      {
         for (int i=1; i <=13; i++) {
            Card aCard = new Card(i);
            cards.add(aCard);
         }
      }
   }
      
   public void shuffleCards() {
      Collections.shuffle(cards);
   }

   public GameBoard() {
      super("Game Board");
      createCards();
      shuffleCards();
      //initialize();
   }
   public void makeUi() {
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      gamePanel = new JPanel(new GridLayout(4,13));
      gamePanel.setPreferredSize(new Dimension(500, 300));
      gamePanel.setVisible(true);
      
      fillCards(gamePanel,4,13);
      
      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(gamePanel,BorderLayout.CENTER);
      this.pack();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }
   
   public void fillCards (JPanel panel, int rows, int cols)
   {
      int position=0;
      for (int i=0; i< rows; i++)
         for (int j=0; j < cols; j++)
         {
            CardButton cb = new CardButton(cards.get(position), position++);
            cb.addActionListener(this);
            panel.add(cb);
         }
   }
   CardButton card1=null;
   CardButton card2=null;
   
   public void actionPerformed(ActionEvent e) {
      CardButton cb = (CardButton) e.getSource();
      synchronized(cb) {
      
         if (card1 == null)
         {
            card1 = cb;
            cb.showCardValue();
            cb.setEnabled(false);
         }
         else if (card2 == null)
         {
            card2=cb;
            cb.showCardValue();
            cb.setEnabled(false);
         }
      }
      if (card1 != null && card2 !=null) {
         if (card1.equalTo(card2)) {
            // Win
            card1.won();
            card2.won();
            card1=null;
            card2=null;
         }
         else {
            Runnable r = 
               new Runnable() {
                  public void run(){ 
                     if (card1 != null){
                        synchronized(card1)
                        { 
                           if (card2 != null){ 
                              synchronized(card2)
                              {  
                                 //System.out.println("thread is running...");
                                 try { 
                                    Thread.sleep(500);
                                 }
                                 catch (Exception e)
                                 {
                                    e.printStackTrace();
                                 }  
                              
                                 card1.restore();
                              
                                 card2.restore();
                                 card1=null;
                                 card2=null;
                              }
                           }
                        }
                     }
                  }  
               };
            new Thread(r).start();
         
         }
      }
   }
 
   public static void main (String [] args){
      javax.swing.SwingUtilities.invokeLater(
         new Runnable() {
            public void run() {
               GameBoard myBoard = new GameBoard();
               myBoard.makeUi();
            }
         }); 
      
   }

}

