
public class Card {
   
   int faceValue;
   boolean chosen;
   boolean removed;
   
   public Card(int value) {
      this.faceValue = value;
   }
   
   public int getFaceValue() {
      return faceValue;
   }
   
   public boolean isChosen() {
      return chosen;
   }
   
   public void setChosen(boolean value) {
      chosen = value;
   }
   
   public void markItRemoved() {
      removed = true;
   }
   
   public boolean isRemoved() {
      return removed;
   }
   
   public String getCardValue() {
      if (faceValue==1 || faceValue > 10)
      {
         String figure=null;
         switch(faceValue) {
            case 1:
                  figure="A";
                  break;
            case 11:
                  figure="J";
                  break;
            case 12:
                  figure="Q";
                  break;
            case 13:
                  figure="K";
                  break;
                  
         }
         return figure;
      }
      else
      {
         return String.valueOf(faceValue);
      }
   }
   
   public boolean equalTo (Card inCard) {
      if (inCard != null)
      {
         int card1Value = this.getFaceValue();
         int card2Value = inCard.getFaceValue();
         if (card1Value == card2Value)
            return true;
         else 
            return false;     
      }
      return false;
   }
   
   public static void main (String[] args)
   {
      for (int i=1; i <=13; i++)
      {
         Card aCard= new Card(i);
         System.out.println(i+" Card is ="+ aCard.getCardValue());
      }
      
      Card card1 = new Card(7);
      Card card2=new Card(9) ;
      if (card1.equalTo(card2)) {
         System.out.println("The cards are equal");
      }
      else
      {
         System.out.println("The cards are not equal");
      }
   }
}

