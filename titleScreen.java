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
    
    JLabel version = new JLabel("Java Casino - Version 1.0");
    
    public boolean game1Open = false;
    public boolean game2Open = false;
    public boolean game3Open = false;
    
    public titleScreen()
    {
        super("Java Casino");
        JPanel p = new JPanel();
        
        p.setLayout(null);
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
        
        mouseHandlerClass handoraa = new mouseHandlerClass();
        game1.addMouseListener(handoraa);
        game2.addMouseListener(handoraa);
        game3.addMouseListener(handoraa);
    }
    
    private class mouseHandlerClass implements MouseListener 
    {
        
        public void mouseClicked(MouseEvent ebento)
        {
            //Nothing
        }

        public void mousePressed(MouseEvent ebento) 
        {            
            if (ebento.getSource() == game1) //Slots
            {
                if ( ! game1Open && ! game2Open && ! game3Open) //Basically this prevents opening multiple games
                {
                    Slots moira = new Slots(500, 5); //Starting credits and initial cost
                    moira.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    moira.setSize(1175, 700); //Size of the window
                    moira.setVisible(true);
                    game1Open = true;
                    
                    moira.addWindowListener(new java.awt.event.WindowAdapter() //This allows the user to click either Close or the X to close the window
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
                    cards moira = new cards(100);
                    moira.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    moira.setSize(1175, 560); //Size of the window
                    moira.setVisible(true);
                    game2Open = true;
                    
                    moira.addWindowListener(new java.awt.event.WindowAdapter() //This allows the user to click either Close or the X to close the window
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
                    cardsWild moira = new cardsWild(100);
                    moira.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    moira.setSize(1175, 560); //Size of the window
                    moira.setVisible(true);
                    game3Open = true;
                    
                    moira.addWindowListener(new java.awt.event.WindowAdapter() //This allows the user to click either Close or the X to close the window
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
            //Nothing
        }

        public void mouseEntered(MouseEvent ebento) 
        {
            //Nothing
        }

        public void mouseExited(MouseEvent ebento) 
        {
            //Nothing
        }
    }
    
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
