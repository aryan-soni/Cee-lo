/** CeeloGame
*  Models a game of Ceelo
*  Last Modified: November 3, 2020
*  @author Aryan Soni
*/

import java.util.Arrays;
import java.text.NumberFormat;
import java.io.*;

public class CeeloGame {
  
  // Define instance variables that outline key attributes of a CeeloGame object:
  
  public final CeeloPlayer user; // user
  public final CeeloPlayer computer; // computer that will play against the player
  
  private int roundNum; // # of rounds elapsed
  private int roundOutcome; // whether the user won the round, the computer won the round, or it's a draw
  private String fullGameResults = ""; // will store the results from each round and the final result
  private NumberFormat money = NumberFormat.getCurrencyInstance(); // will be used to format the balance
  
  private CeeloGUI view;
  
  /** Constructs a CeeloGame object */
  public CeeloGame() {
    
    // instantiate CeeloPlayer objects
    this.user = new CeeloPlayer();
    this.computer = new CeeloPlayer();
    
    this.roundNum = 0; // 0 rounds completed by default
    this.roundOutcome = -1; // set outcome to arbitrary value

  }
  
  /** Sets the view for the CeeloGame
    * @param currentGUI GUI to set as the view
    */
  public void setGUI(CeeloGUI currentGUI) {
    this.view = currentGUI;
  }
  
  /** Places bets for both CeeloPlayer objects
    * @param bet Bet to place
    */
  public void setBets(double bet) {
    this.user.betAmount(bet);
    this.computer.betAmount(bet);
  }
  
  /** Rolls for both user and computer
    * @return sortedRolls[][] Contains both players' rolls with 3 values each sorted in ascending order 
    */
  public int[][] roll() {
    
    int[][] sortedRolls = new int[2][3]; // initialize 2D array
    
    this.user.roll();
    this.computer.roll();
    
    // populate arrays by looping thrice
    for(int i = 0; i < 3; i++) {
        sortedRolls[0][i] = this.user.getRoll()[i];
        sortedRolls[1][i] = this.computer.getRoll()[i];
    } // end of for loop
    
    // sort in ascending order
    Arrays.sort(sortedRolls[0]);
    Arrays.sort(sortedRolls[1]);
    
    return sortedRolls;
    
  }

  /** Organizes the sequential flow of the round and outcome (W, L, D); while updating the program's state */
  public void startRound() {
    
    int[][] sortedArray = this.roll(); // get the sorted 2D array
    
    // create variables to store rolls, the value of the rolls, and the outcome
    int[] userRoll = new int[3];
    int[] computerRoll = new int[3];
    int userRollValue, computerRollValue;
    int outcome = -1; // default to -1
    
    // loop thrice
    for(int i = 0; i < 3; i++) {
        userRoll[i] = sortedArray[0][i];
        computerRoll[i] = sortedArray[1][i];
    } // end of for loop
    
    // get the value for each roll
    userRollValue = this.getRollValue(userRoll);
    computerRollValue = this.getRollValue(computerRoll);
    
    // if the user wins
    if(userRollValue > computerRollValue){
      this.user.increaseBalance(this.user.getBet()); // increase the user's balance
      outcome = 0; // outcome of 0 means that the user won
    }
    // else if the computer wins
    else if(computerRollValue > userRollValue){
      this.computer.increaseBalance(this.user.getBet()); // increase the computer's balance
      outcome = 1; // outcome of 1 means that the computer won
    }
    // else (it's a draw)
    else {
      this.user.restoreBalance(this.user.getBet()); // restore the user's balance
      this.computer.restoreBalance(this.user.getBet()); // restore the computer's balance
      outcome = 2; // outcome of 2 means it's a draw
    }
    
    this.roundNum++; // increment the round number
    
    this.roundOutcome = outcome; // set round outcome
    
    // add outcome of round to attribute that stores the outcome of all rounds
    this.fullGameResults = this.fullGameResults + this.getRoundOutcomeFormatted() + 
      "\nUser Balance: " + this.money.format(this.user.getBalance()) + "\nComputer Balance: " + 
       this.money.format(this.computer.getBalance()) +"\n";
    
    this.updateView();

  }
  
  /** Determine the value of a Roll
    * @param rollToCheck[] The roll to examine
    * @return value The value of the roll
    */
  private int getRollValue(int[] rollToCheck) {
    
    // default value to 3, as code will not check for situation where there's 2 identical nums (ex. 5, 5, 6).
    // the situation described would have a value of 3
    int value = 3;
    
    int[] ceelo = {4, 5, 6};
    int[] firstThreeDigits = {1, 2, 3};
    
    // if the roll is equal to 4, 5, 6
    if(this.areArraysEqual(rollToCheck, ceelo)){
      value = 5;
    }
    // else if a triple is rolled
    else if(this.areAllEqual(rollToCheck)){
      value = 4;
    }
    // else if a 1, 2, 3 is rolled
    else if(this.areArraysEqual(rollToCheck, firstThreeDigits)) {
      value = 2;
    }
    // else if all the values are distinct
    else if(this.areAllDistinct(rollToCheck)){
      value = 1;
    }
    
    return value;
    
  }
  
  /** Determines whether two arrays with the same length are equal
    * @param array1[] The first array to examine
    * @param array2[] The second array to examine
    * @return areEqual Whether the arrays are equal
    */
  private boolean areArraysEqual(int[] array1, int[] array2) {
    
    int length = array1.length; // isolate length of array
    boolean areEqual = true;
    
    // loop through array's length
    for(int i = 0; i < length; i++){
    
      if(array1[i] != array2[i]){
        areEqual = false;
      }
      
    } // end of for loop
    
    return areEqual;
    
  }
  
  /** Determine whether the values in an array are distinct
    * @param array1[] The array to examine
    * @return areValuesDistinct Whether all the values in the array are distinct
    */
  private boolean areAllDistinct(int[] array1) {
    
    int length = array1.length; // isolate length of array
    boolean areValuesDistinct = true;
    
    // loop through array's length
    for(int i = 0; i < length; i++){
    
      int tempNum = array1[i];
      
      // loop through array's length again
      for(int j = i + 1; j < length; j++) {
        
        if(array1[j] == tempNum) {
          areValuesDistinct = false;
        }
      
      } // end of for loop
      
    } // end of for loop
    
    return areValuesDistinct;
    
  }
  
  /** Determines whether all the values in an array are the same
    * @param array1[] The array to examine
    * @return areValuesEqual Whether all the values in the array are the same
    */
  private boolean areAllEqual(int[] array1) {
    
    int length = array1.length; // isolate length of array
    boolean areValuesEqual = true;
    
    // loop through array's length
    for(int i = 0; i < length; i++){
   
      if(array1[i] != array1[0]) {
        areValuesEqual = false;
      }
      
    } // end of for loop
    
    return areValuesEqual;
    
  }
  
  /** Determines whether 10 rounds have been reached
    * @return overTenRounds Whether 10 rounds have been reached
    */
  public boolean roundLimitExceeded() {
    
    boolean overTenRounds = false;
    
    // once 10 rounds is reached, set overTenRounds to true
    if(this.roundNum == 10){
      overTenRounds = true;
    }
    
    return overTenRounds;
    
  }
  
  /** Allows other classes to access the round number
    * @return roundNum The round number
    */
  public int getRoundNum() {
    return this.roundNum;
  }
  
  /** Allows other classes to access the round outcome formatted as a message
    * @return roundOutcomeFormatted The round outcome formatted as a String
    */
  public String getRoundOutcomeFormatted() {
    
    String roundOutcomeFormatted = "";
    
    // if user wins
    if(this.roundOutcome == 0)
    {
      roundOutcomeFormatted = "Outcome: User Wins!";
    }
    // else if computer wins
    else if(this.roundOutcome == 1)
    {
      roundOutcomeFormatted = "Outcome: Computer Wins!";
    }
    // else if it's a draw
    else if(this.roundOutcome == 2)
    {
      roundOutcomeFormatted = "Outcome: Draw!";
    }
    // else
    else {
      roundOutcomeFormatted = "Outcome: ";
    }
    
    return roundOutcomeFormatted;
    
  }
  
  /** Concludes game by writing results to output.txt and returning a shortened version of the game results
    * @return results The results of the game
    */
  public String gameOver() {
    
    // define variables
    double userBalance = this.user.getBalance();
    double computerBalance = this.computer.getBalance();
    String results = "User: " + this.money.format(userBalance) + "\r\nComputer: " +  this.money.format(computerBalance);
    
     // add the winner to the results String, so results displays both players' final balances and the final outcome
    results += "\r\n" + this.getWinner() + "!";

    // try outputting entire game's results to output.txt
    try {
      
      PrintWriter outputFile = new PrintWriter("output.txt");
      outputFile.println(this.fullGameResults + "\n" + results); // output result of each round and final results
      outputFile.close();
      
    }
    catch(FileNotFoundException ex) {
      
      System.out.println(ex.getMessage() + " in " + System.getProperty("user.dir")); // print error
      System.exit(1);
      
    }
    
    return results;

  }
  
 /** Returns the game winner
    * @return winner The game winner
    */
  public String getWinner() {
    
    String winner = "";
    
     // if the user won
    if(this.user.getBalance() > this.computer.getBalance()) {
      winner = "User Wins"; 
    }
    // else if the computer won
    else if(this.computer.getBalance() > this.user.getBalance()) {
      winner = "Computer Wins"; 
    }
    // else if it's a draw
    else if(this.user.getBalance() == this.computer.getBalance()) {
      winner = "Draw"; 
    }
    
    return winner;

  }
  
  /** Resets the game state */
  public void resetGame() {
    
    // reset instance variables to state found in the beginning of the game
    this.roundNum = 0;
    this.roundOutcome = -1;
    this.user.resetPlayer();
    this.computer.resetPlayer();
    this.fullGameResults = "";
    this.updateView();
    
  }
  
  /** Will enable ButtonsController to access the view
    * @return view The game's view
    */
  public CeeloGUI getView() {
    return this.view;
  }
  
  /** Updates the view */
  public void updateView() {
    this.view.update();
  }
   
}