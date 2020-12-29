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

    private Font defaultFont = new Font("Futura", Font.PLAIN, 16);
    private Font subHeadingFont = new Font("Futura", Font.PLAIN, 18);
    private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    private Color orange = new Color(255, 140, 0);

    // title row
    private JPanel titlePanel = new JPanel();
    private Font titleFont = new Font("Futura", Font.BOLD, 27);
    private JLabel lblTitle = new JLabel("Cee-Lo");
    private JLabel lblDie1 = new JLabel(new ImageIcon("../imgs/die.png"));
    private JLabel lblDie2 = new JLabel(new ImageIcon("../imgs/die.png"));
    private BoxLayout titleLayout = new BoxLayout(titlePanel, BoxLayout.X_AXIS);

    // round num
    private JLabel lblRoundNum = new JLabel("");
    private BoxLayout roundLayout = new BoxLayout(lblRoundNum, BoxLayout.X_AXIS);

    // balance row
    private JPanel balancePanel = new JPanel();
    private JLabel lblUserBalNum = new JLabel(" ");
    private JLabel lblCompBalNum = new JLabel(" ");
    private JLabel lblUserBalImg = new JLabel(new ImageIcon("../imgs/user.png"));
    private JLabel lblCompBalImg = new JLabel(new ImageIcon("../imgs/robot.png"));
    private BoxLayout balanceLayout = new BoxLayout(balancePanel, BoxLayout.X_AXIS);

    // bet row
    private JPanel betPanel = new JPanel();
    private JLabel lblEnterBet = new JLabel("Enter Bet: ");
    private JTextField txtBet = new JTextField(12);
    private JLabel lblBetImg = new JLabel(new ImageIcon("../imgs/dollar.png"));
    private BoxLayout betLayout = new BoxLayout(betPanel, BoxLayout.X_AXIS);

    // roll button row
    JPanel rollPanel = new JPanel();
    private JButton btnRoll = new JButton("Roll", new ImageIcon("../imgs/dice.png"));
    private BoxLayout rollLayout = new BoxLayout(rollPanel, BoxLayout.X_AXIS);

    // user and computer roll row
    private JPanel gameRollsPanel = new JPanel();
    private JLabel lblUserRollNum = new JLabel(" ");
    private JLabel lblCompRollNum = new JLabel(" ");
    private BoxLayout gameRollsLayout = new BoxLayout(gameRollsPanel, BoxLayout.X_AXIS);

    // outcome row
    private JPanel outcomePanel = new JPanel();
    private JLabel lblOutcome = new JLabel("");
    private JLabel lblOutcomeImg = new JLabel(new ImageIcon("../imgs/outcome.png"));
    private BoxLayout outcomeLayout = new BoxLayout(outcomePanel, BoxLayout.X_AXIS);

    // game status row
    private JPanel gameStatusPanel = new JPanel();
    private JLabel lblGameStatus = new JLabel("Game Status: ");
    private BoxLayout gameStatusLayout = new BoxLayout(gameStatusPanel, BoxLayout.X_AXIS);

    // buttons row
    private JPanel buttonsPanel = new JPanel();
    private JButton btnRestart = new JButton("Restart Game", new ImageIcon("../imgs/restart.png"));
    private JButton btnEndGame = new JButton("End Game", new ImageIcon("../imgs/end.png"));
    private BoxLayout buttonsLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);

    /** Constructs CeeloGUI
     * @param newGame
     */
    public CeeloGUI(CeeloGame newGame) {
        super();
        this.newGame = newGame;
        this.newGame.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
    }

    /* Lays out the GUI */
    private void layoutView() {

        // title row:

        lblTitle.setFont(titleFont);
        lblTitle.setForeground(Color.WHITE);

        titlePanel.add(Box.createRigidArea(new Dimension(100, 0)));
        titlePanel.add(lblDie1);
        titlePanel.add(Box.createRigidArea(new Dimension(15, 125)));
        titlePanel.add(lblTitle);
        titlePanel.add(Box.createRigidArea(new Dimension(15, 125)));
        titlePanel.add(lblDie2);
        titlePanel.add(Box.createRigidArea(new Dimension(100, 0)));

        titlePanel.setBackground(new Color(255, 140, 0));
        this.add(titlePanel);

        // round number row:

        lblRoundNum.setFont(subHeadingFont);
        lblRoundNum.setForeground(Color.WHITE);

        this.add(lblRoundNum);

        // user and computer balance row:

        lblUserBalNum.setFont(defaultFont);
        lblUserBalNum.setForeground(Color.WHITE);

        lblCompBalNum.setFont(defaultFont);
        lblCompBalNum.setForeground(Color.WHITE);

        balancePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        balancePanel.add(lblUserBalImg);
        balancePanel.add(Box.createRigidArea(new Dimension(15, 100)));
        balancePanel.add(lblUserBalNum);
        balancePanel.add(Box.createRigidArea(new Dimension(75, 100)));
        balancePanel.add(lblCompBalNum);
        balancePanel.add(Box.createRigidArea(new Dimension(15, 100)));
        balancePanel.add(lblCompBalImg);
        balancePanel.add(Box.createRigidArea(new Dimension(20, 0)));

        balancePanel.setBackground(orange);
        this.add(balancePanel);

        // bet row:

        JPanel panelBet = new JPanel();
        lblEnterBet.setFont(defaultFont);
        lblEnterBet.setForeground(Color.WHITE);

        betPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        betPanel.add(lblBetImg);
        betPanel.add(Box.createRigidArea(new Dimension(15, 50)));
        betPanel.add(lblEnterBet);
        betPanel.add(txtBet);
        betPanel.add(Box.createRigidArea(new Dimension(100, 0)));

        betPanel.setBackground(orange);
        this.add(betPanel);

        // roll button row:

        btnRoll.setForeground(new Color(128, 128, 128));
        btnRoll.setFont(subHeadingFont);

        rollPanel.add(Box.createRigidArea(new Dimension(200, 75)));
        rollPanel.add(btnRoll);
        rollPanel.add(Box.createRigidArea(new Dimension(200, 75)));

        rollPanel.setBackground(orange);
        this.add(rollPanel);

        // user and computer roll row:

        lblUserRollNum.setFont(defaultFont);
        lblUserRollNum.setForeground(Color.WHITE);

        lblCompRollNum.setFont(defaultFont);
        lblCompRollNum.setForeground(Color.WHITE);

        gameRollsPanel.add(Box.createRigidArea(new Dimension(75, 0)));
        gameRollsPanel.add(lblUserRollNum);
        gameRollsPanel.add(Box.createRigidArea(new Dimension(150, 60)));
        gameRollsPanel.add(lblCompRollNum);
        gameRollsPanel.add(Box.createRigidArea(new Dimension(75, 0)));

        gameRollsPanel.setBackground(orange);
        this.add(gameRollsPanel);

        // outcome row:

        lblOutcome.setFont(defaultFont);
        lblOutcome.setForeground(Color.WHITE);

        outcomePanel.add(Box.createRigidArea(new Dimension(75, 0)));
        outcomePanel.add(lblOutcomeImg);
        outcomePanel.add(Box.createRigidArea(new Dimension(30, 25)));
        outcomePanel.add(lblOutcome);
        outcomePanel.add(Box.createRigidArea(new Dimension(75, 0)));

        outcomePanel.setBackground(orange);
        this.add(outcomePanel);

        // game status row
        lblGameStatus.setFont(defaultFont);
        lblGameStatus.setForeground(Color.WHITE);

        gameStatusPanel.add(Box.createRigidArea(new Dimension(200, 50)));
        gameStatusPanel.add(lblGameStatus);
        gameStatusPanel.add(Box.createRigidArea(new Dimension(200, 50)));

        gameStatusPanel.setBackground(orange);
        this.add(gameStatusPanel);

        // end row:

        btnRestart.setForeground(new Color(128, 128, 128));
        btnRestart.setFont(subHeadingFont);

        btnEndGame.setForeground(new Color(128, 128, 128));
        btnEndGame.setFont(subHeadingFont);

        buttonsPanel.add(btnRestart);
        buttonsPanel.add(Box.createRigidArea(new Dimension(25, 10)));
        buttonsPanel.add(btnEndGame);

        buttonsPanel.setBackground(orange);
        this.add(buttonsPanel);

    }

    /* Registers the controllers that will handle the user input */
    private void registerControllers() {

        ButtonsController controller = new ButtonsController(this.newGame, this.txtBet, this.lblGameStatus);
        this.btnRoll.addActionListener(controller);
        this.btnEndGame.addActionListener(controller);
        this.btnRestart.addActionListener(controller);

    }

    /* Redraws the GUI based on updated data */
    public void update() {

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

        this.lblOutcome.setText(this.newGame.getRoundOutcomeFormatted());

    }

}