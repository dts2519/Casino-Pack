//Caution: Direct download of this application will not work without the associated image files.

package casinopack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class titleScreen extends JFrame
{
    JLabel logo = new JLabel(); //Represents the logo
    JLabel game1 = new JLabel();
    JLabel game2 = new JLabel();
    JLabel game3 = new JLabel();
    
    JLabel version = new JLabel("Java Casino - Version 1.0"); //This appears in the upper-left corner of the screen
    
    public boolean game1Open = false;
    public boolean game2Open = false;
    public boolean game3Open = false;
    
    public titleScreen()
    {
        super("Java Casino");
        JPanel p = new JPanel();
        
        p.setLayout(null); //This allows manual placement of each element
        getContentPane().add(p);
        
        version.setBounds(5, 0, 150, 30);
        p.add(version);
        
        logo.setIcon(new ImageIcon (getClass().getResource("Logo.PNG")));
        game1.setIcon(new ImageIcon (getClass().getResource("SlotsLogo.PNG")));
        game2.setIcon(new ImageIcon (getClass().getResource("PokerLogo.PNG")));
        game3.setIcon(new ImageIcon (getClass().getResource("DeucesWildLogo.PNG")));
        
        logo.setBounds(230, 20, 656, 170);
        p.add(logo);
        
        game1.setBounds(50, 220, 329, 424);
        p.add(game1);
        
        game2.setBounds(400, 220, 329, 424);
        p.add(game2);
        
        game3.setBounds(750, 220, 329, 424);
        p.add(game3);
        
        mouseHandlerClass handler = new mouseHandlerClass();
        game1.addMouseListener(handler);
        game2.addMouseListener(handler);
        game3.addMouseListener(handler);
    }
    
    private class mouseHandlerClass implements MouseListener 
    {
        
        public void mouseClicked(MouseEvent ebento)
        {
            //Nothing (mandatory override)
        }

        public void mousePressed(MouseEvent ebento) 
        {            
            if (ebento.getSource() == game1) //Slots
            {
                if ( ! game1Open && ! game2Open && ! game3Open) //Basically this prevents opening multiple games
                {
                    Slots slots = new Slots(500, 5); //Starting credits and initial cost
                    slots.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    slots.setSize(1175, 700); //Size of the window
                    slots.setVisible(true);
                    game1Open = true;
                    
                    slots.addWindowListener(new java.awt.event.WindowAdapter() //This allows the user to click either Close or the X to close the window
                    {
                        public void windowClosing(java.awt.event.WindowEvent ebento)
                        {
                            setGame1Open(false);
                        }                       
                    });
                }
            }
            
            if (ebento.getSource() == game2) //Standard Poker
            {
                if ( ! game1Open && ! game2Open && ! game3Open)
                {
                    cards cardsReg = new cards(100);
                    cardsReg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    cardsReg.setSize(1175, 560); //Size of the window
                    cardsReg.setVisible(true);
                    game2Open = true;
                    
                    cardsReg.addWindowListener(new java.awt.event.WindowAdapter() //This allows the user to click either Close or the X to close the window
                    {
                        public void windowClosing(java.awt.event.WindowEvent ebento)
                        {
                            setGame2Open(false);
                        }                       
                    });
                }
            }
            
            if (ebento.getSource() == game3) //Deuces Wild
            {
                if ( ! game1Open && ! game2Open && ! game3Open)
                {
                    cardsWild deuces = new cardsWild(100);
                    deuces.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    deuces.setSize(1175, 560); //Size of the window
                    deuces.setVisible(true);
                    game3Open = true;
                    
                    deuces.addWindowListener(new java.awt.event.WindowAdapter() //This allows the user to click either Close or the X to close the window
                    {
                        public void windowClosing(java.awt.event.WindowEvent ebento)
                        {
                            setGame3Open(false);
                        }                       
                    });
                }
            }
        }

        public void mouseReleased(MouseEvent ebento) 
        {
            //Nothing (mandatory override)
        }

        public void mouseEntered(MouseEvent ebento) 
        {
            //Nothing (mandatory override)
        }

        public void mouseExited(MouseEvent ebento) 
        {
            //Nothing (mandatory override)
        }
    }
    
    //These are actually not necessary as everything is being done in the same class
    public void setGame1Open(boolean open)
    {
        game1Open = open;
    }
    
    public void setGame2Open(boolean open)
    {
        game2Open = open;
    }
    
    public void setGame3Open(boolean open)
    {
        game3Open = open;
    }
}
