//This file opens the window on which the title screen is located

package casinopack;

import javax.swing.JFrame;

public class CasinoPack 
{
    public static void main(String[] args) 
    {
        titleScreen screen = new titleScreen();
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setSize(1150, 700); //Size of the window
        screen.setVisible(true);
    }
    
}
