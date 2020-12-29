/** Ceelo Class
 *  Organizes and runs a game of Ceelo by leveraging Object-Oriented-Programming
 *  @author Aryan Soni
 */

import javax.swing.*;
import java.awt.*;

public class Ceelo {
    /** Instantiates the relevant objects, and creates a JFrame to display the game
     * @param args
     */
    public static void main(String[] args) {

        CeeloGame newGame = new CeeloGame(); // instantiate a Ceelo Game
        CeeloGUI view = new CeeloGUI(newGame); // instantitate the GUI and passes newGame as an argument

        // set up the JFrame 
        JFrame frame = new JFrame("Ceelo");
        view.setBackground(new Color(255, 140, 0)); // orange background
        frame.pack();
        frame.setLocation(400, 20);
        frame.setSize(675, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view); // set CeeloGUI object as the frame's content pane
        frame.setVisible(true);

    }

}