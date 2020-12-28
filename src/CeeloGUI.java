/** ButtonsController Class
*  Models the GUI for a game of Ceelo
*  @author Aryan Soni
*/

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class CeeloGUI extends JPanel

{
  // Outline attributes
  private CeeloGame newGame;
  
  private JPanel bet = new JPanel();
  private JPanel endMsg = new JPanel();
  private JPanel endGamePan = new JPanel();
  
  private JLabel lblTitle = new JLabel ("Cee-Lo:");
  private JLabel lblRoundNum = new JLabel ("");
  private JLabel lblUserBalNum = new JLabel (" ");
  private JLabel lblCompBalNum = new JLabel (" ");
  private JLabel lblEnterBet = new JLabel ("Enter Bet: ");
  private JLabel lblUserRollNum = new JLabel (" ");
  private JLabel lblCompRollNum = new JLabel (" ");
  private JLabel lblOutcomeNum = new JLabel ("");
  private JLabel lblGameStatus = new JLabel ("Game Status: ");
  
  private JButton roll = new JButton ("Roll");
  private JButton endGame = new JButton ("End Game");
  private JButton newGame2 = new JButton ("End Current Game and Start New Game");
  
  private JTextField betNum = new JTextField (12);
  
  /** Constructs CeeloGUI
    * @param newGame
  */  
  public CeeloGUI(CeeloGame newGame)
  {
    super();
    this.newGame = newGame;
    this.newGame.setGUI(this);
    this.layoutView();
    this.registerControllers();
    this.update();
  }
  
  /* Lays out the GUI */  
  private void layoutView()
  {
    
    Font defaultFont = new Font ("Monospaced", Font.PLAIN, 16);
    Font titleFont = new Font ("Monospaced", Font.BOLD, 25);
    
    GridLayout layout = new GridLayout(6, 2, 10, 0);
    this.setLayout(layout);
    
    lblTitle.setFont(titleFont);
    lblTitle.setForeground(Color.WHITE);
    this.add(lblTitle);
    lblTitle.setHorizontalAlignment(JLabel.CENTER);
    
    lblRoundNum.setFont(defaultFont);
    lblRoundNum.setForeground(Color.WHITE);
    this.add(lblRoundNum);
    lblRoundNum.setHorizontalAlignment(JLabel.CENTER);
    
    lblUserBalNum.setFont(defaultFont);
    lblUserBalNum.setForeground(Color.WHITE);
    this.add(lblUserBalNum);
    lblUserBalNum.setHorizontalAlignment(JLabel.CENTER);
    
    lblCompBalNum.setFont(defaultFont);
    lblCompBalNum.setForeground(Color.WHITE);
    this.add(lblCompBalNum);
    lblCompBalNum.setHorizontalAlignment(JLabel.CENTER);
    
    BoxLayout layout4 = new BoxLayout(bet, BoxLayout.X_AXIS);
    lblEnterBet.setFont(defaultFont);
    lblEnterBet.setForeground(Color.WHITE);
    bet.setBackground(Color.BLACK);
    bet.add(lblEnterBet);
    bet.add(betNum);
    this.add(bet);
    
    this.add(roll);
    
    lblUserRollNum.setFont(defaultFont);
    lblUserRollNum.setForeground(Color.WHITE);
    this.add(lblUserRollNum);
    lblUserRollNum.setHorizontalAlignment(JLabel.CENTER);
    
    lblCompRollNum.setFont(defaultFont);
    lblCompRollNum.setForeground(Color.WHITE);
    this.add(lblCompRollNum);
    lblCompRollNum.setHorizontalAlignment(JLabel.CENTER);
    
    lblOutcomeNum.setFont(defaultFont);
    lblOutcomeNum.setForeground(Color.WHITE);
    this.add(lblOutcomeNum);
    lblOutcomeNum.setHorizontalAlignment(JLabel.CENTER);
    
    lblGameStatus.setFont(defaultFont);
    lblGameStatus.setForeground(Color.WHITE);
    this.add(lblGameStatus);
    lblGameStatus.setHorizontalAlignment(JLabel.CENTER);

    this.add(newGame2);
    
    this.add(endGame);                    
                                            
  }
  
  /* Registers the controllers that will handle the user input */  
  private void registerControllers() 
  {
    
    ButtonsController controller = new ButtonsController(this.newGame, this.betNum, this.lblGameStatus);
    this.roll.addActionListener(controller);
    this.endGame.addActionListener(controller);
    this.newGame2.addActionListener(controller);
    
  }

  /* Redraws the GUI based on updated data */
  public void update() 
  {
    
    NumberFormat money = NumberFormat.getCurrencyInstance();
    
    String roundNum = Integer.toString(this.newGame.getRoundNum());
    this.lblRoundNum.setText("Rounds Completed: " + roundNum);

    String userBalance = money.format(this.newGame.user.getBalance());
    this.lblUserBalNum.setText("User Balance: " + userBalance);

    String computerBalance = money.format(this.newGame.computer.getBalance());
    this.lblCompBalNum.setText("Computer Balance: " + computerBalance);

    String userRollResults = this.newGame.user.getRollFormatted();
    this.lblUserRollNum.setText("User Roll: " + userRollResults);

    String computerRollResults = this.newGame.computer.getRollFormatted();
    this.lblCompRollNum.setText("Computer Roll: " + computerRollResults);

    this.lblOutcomeNum.setText(this.newGame.getRoundOutcomeFormatted());
    
  }
  
}