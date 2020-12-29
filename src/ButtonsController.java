/** ButtonsController Class
*  Controls and validates user input received through JButtons
*  @author Aryan Soni
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class ButtonsController implements ActionListener {
  
  // declare instance variables
  private CeeloGame game;
  private JTextField bet;
  private JLabel gameStatus;
  private UIManager UI = new UIManager();
  
  /** Constructs ButtonsController class by linking it to the relevant Model (CeeloGame), JTextField, and JLabel. Also
    * sets up the UI Manager
    * @param game
    * @param bet
    * @param gameStatus
    */
  public ButtonsController(CeeloGame game, JTextField bet, JLabel gameStatus){
    
    this.game = game;
    this.bet = bet;
    this.gameStatus = gameStatus;
    
    this.UI.put("OptionPane.background", new Color(255, 140, 0));
    this.UI.put("Panel.background", new Color(255, 140, 0));
    UIManager.put("OptionPane.minimumSize", new Color(255, 140, 0));
    UIManager.put("OptionPane.messageFont", new Font("Futura", Font.PLAIN, 14));
    UIManager.put("OptionPane.buttonFont", new Font("Futura", Font.PLAIN, 14));

    
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
    if(e.getActionCommand() == "Restart Game"){
      
      // show results from the end of the current game
      JLabel endCurrentGameLabel = new JLabel("<html>Game Status: GAME OVER!<br>" + "User has " 
         + money.format(this.game.user.getBalance()) + ", Computer has " +  
         money.format(this.game.computer.getBalance()) + ".<br>" + this.game.getWinner() 
         + "!<br>Results written to results.txt.</html>");
      
      endCurrentGameLabel.setFont(new Font ("Futura", Font.PLAIN, 16));
      endCurrentGameLabel.setForeground(Color.white);
      
      JOptionPane.showMessageDialog(this.game.getView(), endCurrentGameLabel, "Cee-lo: Restart Game",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("../imgs/outcome.png"));
      
      this.gameStatus.setText("");
      this.bet.setText("");
      
      this.game.gameOver(); // writes final results to output file
      this.game.resetGame(); // reset the game
      
    }
    // else if the user wishes to end the game
    else if(e.getActionCommand() == "End Game"){
      
      // output results of the game
      // gameOver() method returns game results and writes results to text file
      
      JLabel endGameLabel = new JLabel("<html>" + this.game.gameOver() + "<br>Results written to results.txt <html/>");
      
      endGameLabel.setFont(new Font ("Futura", Font.PLAIN, 16));
      endGameLabel.setForeground(Color.white);
      
      JOptionPane.showMessageDialog(this.game.getView(), endGameLabel, "Cee-lo: End Game",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("../imgs/outcome.png"));
      
      System.exit(1); // exit
      
    }
    // else if the game can no longer continue, tell user that the game is over
    else if(!this.gameCanContinue(e)){

      // output final game results
      this.gameStatus.setText(
         "<html> Game Status: GAME OVER!<br>" + "User has " + money.format(this.game.user.getBalance()) +
         ", Computer has " +  money.format(this.game.computer.getBalance()) + ".<br>" + this.game.getWinner() 
         + "!<br>Results written to results.txt.</html>"
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