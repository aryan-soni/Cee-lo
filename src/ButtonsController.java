/** ButtonsController Class
*  Controls and validates user input received through JButtons
*  @author Aryan Soni
*/

import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class ButtonsController implements ActionListener {
  
  // declare instance variables
  private CeeloGame game;
  private JTextField bet;
  private JLabel gameStatus;
  
  /** Constructs ButtonsController class by linking it to the relevant Model (CeeloGame), JTextField, and JLabel
    * @param game
    * @param bet
    * @param gameStatus
    */
  public ButtonsController(CeeloGame game, JTextField bet, JLabel gameStatus){
    
    this.game = game;
    this.bet = bet;
    this.gameStatus = gameStatus;
    
  }
  
  /** Handles input from buttons
    * @param e Event triggered when the button is clicked
    */
  public void actionPerformed(ActionEvent e){
    
    NumberFormat money = NumberFormat.getCurrencyInstance(); // will be used to format output
    
    // if the game can continue
    if(this.gameCanContinue(e)) {
      
      // if the user wishes to roll
      if(e.getActionCommand() == "Roll"){
        
        try {
          
          double userBet = Double.parseDouble(this.bet.getText()); // determine how much user wishes to bet
          
          // if the bet is valid
          if(this.game.user.isBetValid(userBet) && this.game.computer.isBetValid(userBet)){
            // orchestrate the round
            this.game.user.betAmount(userBet);
            this.game.computer.betAmount(userBet);
            this.game.startRound();
            this.gameStatus.setText("Game Status: Balance Updated!");
          }
          // else (bet isn't valid)
          else {
            this.bet.setText("Invalid bet!");
          }
        }
        catch (NumberFormatException ex) {
          this.bet.setText("ERROR! Please enter a number!");
        }
        
      }
    
    }
    
    // at this point, check if the user wishes to start a new game
    if(e.getActionCommand() == "End Current Game and Start New Game"){
      
      // show results from the end of the current game
      JOptionPane.showMessageDialog(this.game.getView(), "Game Status: GAME OVER!\n" + "User has " 
         + money.format(this.game.user.getBalance()) + ", Computer has " +  
         money.format(this.game.computer.getBalance()) + ".\n" + this.game.getWinner() 
         + "!\nResults written to output.txt.");
      
      this.gameStatus.setText("");
      this.bet.setText("");
      
      this.game.gameOver(); // writes final results to output file
      this.game.resetGame(); // reset the game
      
    }
    // else if the user wishes to end the game
    else if(e.getActionCommand() == "End Game"){
      
      // output results of the game; gameOver() method returns game results and writes results to text file
      JOptionPane.showMessageDialog(this.game.getView(), this.game.gameOver() +"\nResults written to output.txt");
      
      System.exit(1); // exit
      
    }
    // else if the game can no longer continue, tell user that the game is over
    else if(!this.gameCanContinue(e)){

      // output final game results
      this.gameStatus.setText(
         "<html> Game Status: GAME OVER!<br>" + "User has " + money.format(this.game.user.getBalance()) +
         ", Computer has " +  money.format(this.game.computer.getBalance()) + ".<br>" + this.game.getWinner() 
         + "!<br>Results written to output.txt.</html>"
      );
      
      this.game.gameOver(); // writes final results to output file
      
    }
    
  }
  
  /** Determines whether the game can continue
    * @param e Event triggered when the button is clicked
    * @return canContinue Whether the game can continue
    */
  private boolean gameCanContinue(ActionEvent e){
    
    boolean canContinue; 
    
    canContinue = (e.getActionCommand() != "End Game" && e.getActionCommand() != "End Current Game and Start New Game" 
                  && !this.game.user.noBalance() && !this.game.computer.noBalance() && !this.game.roundLimitExceeded()
                  );
    
    return canContinue;
  
  }

}