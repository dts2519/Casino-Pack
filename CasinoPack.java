package casinopack;

import javax.swing.JFrame;

public class CasinoPack 
{
    public static void main(String[] args) 
    {
        titleScreen moira = new titleScreen();
        moira.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        moira.setSize(1150, 700); //Size of the window
        moira.setVisible(true);
    }
    
}
