/** CeeloPlayer
*  Models a player playing Ceelo
*  @author Aryan Soni
*/

import java.lang.Math;

public class CeeloPlayer {
  
  // define instance variables that outline key attributes pertinent to a CeeloPlayer object
  private double balance;
  private double bet;
  private int[] roll;
  private final int[] dice = {1, 2, 3, 4, 5, 6};
  
  /** Constructs a CeeloPlayer object */
  public CeeloPlayer() {
    
    // set up starting values for attributes
    this.balance = 500;
    this.bet = 0;
    this.roll = new int[3];

  }
  
  /** When a CeeloPlayer wins a round, this method increases their balance
    * @param bet Amount bet by CeeloPlayer
    */
  public void increaseBalance(double bet) {
    this.balance += (2 * bet);
  }
  
  /** When there is a draw, this method restores the amount of the bet to the Player's balance
    * @param bet Amount bet by CeeloPlayer
    */
  public void restoreBalance(double bet) {
    this.balance += bet;
  }
  
  /** This method will allow a CeeloPlayer to bet a certain amount
    * @param bet Amount to bet
    */
  public void betAmount(double bet) {
    this.balance -= bet; // remove the bet from the player's balance
    this.bet = bet;
  }
  
  /** This method will simulate the rolling of 3 dices
    * @return roll The 3 values that were rolled
    */
  public int[] roll() {
    
    int[] valuesRolled = new int[3];
    
    // loop thrice
    for(int i = 0; i < 3; i++){
      
      int randomIndex = (int) (Math.random() * 6); // get a random val from 0 to 5
      valuesRolled[i] = this.dice[randomIndex]; // isolate index from dice array; val will be random
      this.roll[i] = valuesRolled[i];
      
    } // end of for loop
    
    return valuesRolled;
    
  }
  
  /** This method will enable other Classes to access the CeeloPlayer's balance
    * @return balance The CeeloPlayer's balance
    */
  public double getBalance() {
    return this.balance;
  }
  
  /** This method will enable other Classes to access the CeeloPlayer's bet attribute
    * @return bet The CeeloPlayer's bet
    */
  public double getBet() {
    return this.bet;
  }
  
  /** This method will enable other Classes to check whether any given bet is valid within the context of the game
    * @param betToCheck The bet to check the validity of
    * @return isValid Whether the bet passed is valid
    */
  public boolean isBetValid(double betToCheck) {
     boolean isValid = (betToCheck <= this.balance) && (betToCheck > 0);
     return isValid;
  }
  
  /** Determines whether a player has a balance of 0
    * @return noBalanceRemaining Whether the player has no balance remaining
    */
  public boolean noBalance() {
    
    boolean noBalanceRemaining = false;
    
    if(this.balance <= 0){
      noBalanceRemaining = true;
    }
    
    return noBalanceRemaining;
    
  }
  
  /** This method will enable other Classes to access the CeeloPlayer's roll attribute
    * @return roll The CeeloPlayer's roll
    */
  public int[] getRoll() {
    return this.roll;
  }
  
  /** This method will enable other Classes to access the CeeloPlayer's roll as a formatted message
    * @return formattedRoll The roll formatted as a String
    */
  public String getRollFormatted() {
    
    // if the roll is ready to be displayed
    if(this.roll[0] > 0){
        String formattedRoll = this.roll[0] + ", " + this.roll[1] + ", " + this.roll[2];
        return formattedRoll;
    }
    else {
      return "";
    }
   
  }
  
  /** This method will enable the CeeloGUI class to reset the player's attributes
    */
  public void resetPlayer() {
    
    this.balance = 500;
    this.balance = 500;
    
    // loop thrice
    for(int i = 0; i < 3; i++){
      this.roll[i] = 0;
    }
   
  }
   
}