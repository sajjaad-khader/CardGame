

public class Player {
   String Id;
   int score;


   public void scoresPoint(){
      score++;
      System.out.println("Your Score is "+ score);
   
   }
   
   public String getId() {
      return Id;
   }
   
   public void setId(String inId) {
      Id = inId;
   }
}
