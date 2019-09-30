
import java.awt.*;
import javax.swing.*;

public class CardButton extends JButton {
   int cardPosition;
   Card card;
   String DEFAULT_VALUE="X";
   boolean done = false;
   
   public CardButton(Card inCard, int position)
   {
      super.setText(DEFAULT_VALUE);
      card = inCard;
      this.cardPosition = position;
      setFont(new Font("Arial", Font.PLAIN, 20));
   }
   
   public void won() {
      done = true;
      setBackground(Color.gray);
      setEnabled(false);
   }
   
   public int getCardPosition() {
      return cardPosition;
   }
   
   public void showCardValue() {
      if (!done)
         super.setText(card.getCardValue());
   }   
   public void restore() {
      if (!done){
         super.setText(DEFAULT_VALUE);
         setEnabled(true);
      }
   }
   
   public boolean equalTo(CardButton cb)
   {
      return card.equalTo(cb.getCard());
   }
   
   public Card getCard() {
      return card;
   }
}

