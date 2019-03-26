//Caution: Direct download of this application will not work without the associated image files.

//This slot machine contains two multiplier symbols (2x and 3x) that multiply the payout.
//All symbols must be on the 'payline' (the middle of the reel)

package casinopack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class Slots extends JFrame
{
    private JButton spin, bet1, betMax, payTable;
    private int credits = 500, cost = 5;
    private JLabel frame = new JLabel(), creditDisplay = new JLabel("");
    private JLabel creditsDisplay = new JLabel("Credits: " + credits);
    private JLabel winningsDisplay = new JLabel("Welcome");
    private JLabel light = new JLabel();
    private JLabel[] place;
    private ImageIcon[] symbol, spinAnim;
    private ImageIcon lightOff, lightOn;
    
    JLabel version = new JLabel("Java Slots - Version 1.0.1"); //Displayed at the upper left corner of the screen
    
    private JLabel payline = new JLabel();   
    
    ImageIcon payTableImage = new ImageIcon(getClass().getResource("PayTable.PNG"));
    
    Timer spinner, spinReel1, spinReel2, spinReel3;
    Random spinner = new Random();
    
    private int[] paylineSymbol = new int[3];
    
    public Slots (int credits, int cost)
    {
        super("Java Slots - 3 Reel 1 Line");
        JPanel p = new JPanel();
        this.credits = credits;
        this.cost = cost;

        p.setLayout(null); //Allows for manual placement of each element
        getContentPane().add(p); //Adds the panel to the window (all things are installed on the panel)

        version.setBounds(5, 0, 150, 30);
        p.add(version);
        
        place = new JLabel[9]; //These are the 'places' on the slot machine - three per reel
        
        symbol = new ImageIcon[20];
        symbol[0] = new ImageIcon(getClass().getResource("Blank.PNG"));
        symbol[1] = new ImageIcon(getClass().getResource("1Bar.PNG"));
        symbol[2] = new ImageIcon(getClass().getResource("2Bar.PNG"));
        symbol[3] = new ImageIcon(getClass().getResource("3Bar.PNG"));
        symbol[4] = new ImageIcon(getClass().getResource("7Bar.PNG"));
        symbol[5] = new ImageIcon(getClass().getResource("Blue7.PNG"));
        symbol[6] = new ImageIcon(getClass().getResource("Red7.PNG"));
        symbol[7] = new ImageIcon(getClass().getResource("Gold7.PNG"));
        symbol[8] = new ImageIcon(getClass().getResource("2X.PNG"));
        symbol[9] = new ImageIcon(getClass().getResource("3X.PNG"));
        
        //The 'off' represents the symbol which is at the top or bottom of the reel (so is half the size)
        symbol[10] = new ImageIcon(getClass().getResource("BlankOff.PNG"));
        symbol[11] = new ImageIcon(getClass().getResource("1BarOff.PNG"));
        symbol[12] = new ImageIcon(getClass().getResource("2BarOff.PNG"));
        symbol[13] = new ImageIcon(getClass().getResource("3BarOff.PNG"));
        symbol[14] = new ImageIcon(getClass().getResource("7BarOff.PNG"));
        symbol[15] = new ImageIcon(getClass().getResource("Blue7Off.PNG"));
        symbol[16] = new ImageIcon(getClass().getResource("Red7Off.PNG"));
        symbol[17] = new ImageIcon(getClass().getResource("Gold7Off.PNG"));
        symbol[18] = new ImageIcon(getClass().getResource("2XOff.PNG"));
        symbol[19] = new ImageIcon(getClass().getResource("3XOff.PNG"));
        
        //These are the 'spinning animations' so to speak
        spinAnim = new ImageIcon[9];
        spinAnim[0] = new ImageIcon(getClass().getResource("BlueSpin.PNG"));
        spinAnim[1] = new ImageIcon(getClass().getResource("RedSpin.PNG"));
        spinAnim[2] = new ImageIcon(getClass().getResource("GoldSpin.PNG"));
        spinAnim[3] = new ImageIcon(getClass().getResource("BlueSpinOff.PNG"));
        spinAnim[4] = new ImageIcon(getClass().getResource("RedSpinOff.PNG"));
        spinAnim[5] = new ImageIcon(getClass().getResource("GoldSpinOff.PNG"));
        spinAnim[6] = new ImageIcon(getClass().getResource("BlueSpinOff.PNG"));
        spinAnim[7] = new ImageIcon(getClass().getResource("RedSpinOff.PNG"));
        spinAnim[8] = new ImageIcon(getClass().getResource("GoldSpinOff.PNG"));
        
        //The light at the top of the slot machine. If a win happens, it lights up
        lightOff = new ImageIcon(getClass().getResource("LightOff.PNG"));
        lightOn = new ImageIcon(getClass().getResource("LightOn.PNG"));
        
        light.setIcon(lightOff);
        light.setBounds(200, 15, 732, 51);
        p.add(light);
        
        frame.setIcon(new ImageIcon(getClass().getResource("Frame.PNG")));
        frame.setBounds(100, 80, 911, 435);
        p.add(frame);
        
        for (int i = 0; i < place.length; i++)
        {
            place[i] = new JLabel();
        }
        
        //Subtract 100 and 80
        place[0].setBounds(50, 119, 232, 198);
        place[1].setBounds(340, 119, 232, 198);
        place[2].setBounds(627, 119, 232, 198);
        place[3].setBounds(50, 20, 232, 99);
        place[4].setBounds(340, 20, 232, 99);
        place[5].setBounds(627, 20, 232, 99);
        place[6].setBounds(50, 307, 232, 99);
        place[7].setBounds(340, 307, 232, 99);
        place[8].setBounds(627, 307, 232, 99);
        
        for (int i = 0; i < place.length; i++)
        {
            frame.add(place[i]);
        }
        
        //The payline was meant to go on top of the icons - but this never worked properly
//        payline.setIcon(new ImageIcon(getClass().getResource("Payline.PNG")));
//        payline.setBounds(46, 216, 855, 3);
//        frame.add(payline);

        spin = new JButton("Spin");
        bet1 = new JButton("Bet 5");
        betMax = new JButton("Bet Max");
        payTable = new JButton("See Pays");
        
        spin.setBounds(830, 540, 200, 100);
        p.add(spin);
        
        bet1.setBounds(730, 540, 80, 50);
        p.add(bet1);
        
        betMax.setBounds(730, 600, 80, 50);
        p.add(betMax);
        
        payTable.setBounds(230, 540, 200, 100);
        p.add(payTable);
        
        creditsDisplay.setBounds(450, 540, 200, 100);
        p.add(creditsDisplay);
        
        winningsDisplay.setBounds(450, 500, 200, 100);
        p.add(winningsDisplay);
        
        HandlerClass handler = new HandlerClass(); //Allows for events on button presses
        spin.addActionListener(handler);
        bet1.addActionListener(handler);
        betMax.addActionListener(handler);
        payTable.addActionListener(handler);                    
    }
    
    private class HandlerClass implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {       
           if (e.getSource() == spin) //Spin button was pressed
           {
               if (getCredits() < getCost())
               {
                   setCredits(getCredits() + 500);
               }
               
               setCredits(getCredits() - getCost());
               creditsDisplay.setText("Credits: " + getCredits());
               winningsDisplay.setText("");
               
               light.setIcon(lightOff);
               
               spin.setEnabled(false); //Buttons disabled while a spin is in progress
               bet1.setEnabled(false);
               betMax.setEnabled(false);
               payTable.setEnabled(false);
               
               spin();
           }
           if (e.getSource() == bet1) //Elevates the bet by 5 credits (or sets to 5 if at max)
           {
               if (getCost() == 15)
               {
                   setCost(5);
               }
               else
               {
                   setCost(getCost() + 5);
               }
               bet1.setText("Bet " + getCost());
           }
           if (e.getSource() == betMax) //Sets the bet to the maximum (15 credits) and instantly spins
           {   
               if (getCost() != 15)
               {
                   setCost(15);
               }
               bet1.setText("Bet " + getCost());
               
               if (getCredits() < getCost())
               {
                   setCredits(getCredits() + 500);
               }
               
               setCredits(getCredits() - getCost());
               creditsDisplay.setText("Credits: " + getCredits());
               winningsDisplay.setText("");
               
               light.setIcon(lightOff);
               
               spin.setEnabled(false); //Buttons disabled while a spin is in progress
               bet1.setEnabled(false);
               betMax.setEnabled(false);
               payTable.setEnabled(false);
               
               spin();
           }
           if (e.getSource() == payTable) //Opens the pay table in a small window
           {
               JOptionPane.showMessageDialog(null, "", "Pay Table", JOptionPane.INFORMATION_MESSAGE, payTableImage);
           }
        }
    }
    
    //Unnecessary as this is all in one class
    public void setCost(int newCost)
    {
        cost = newCost;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public void setCredits(int newCredits)
    {
        credits = newCredits;
    }
    
    public int getCredits()
    {
        return credits;
    }
    
    public void spin() //Sets the reels spinning
    {
        spinner = new Timer();
        spinner.schedule(new spinClass(), 1000, 1 * 1000); //1 second delay, then runs at a 1 second interval

        spinReel1 = new Timer();
        spinReel1.schedule(new spinReel1Class(), 0, 1 * 50); //Runs at a 50 millisecond interval
        
        spinReel2 = new Timer();
        spinReel2.schedule(new spinReel2Class(), 0, 1 * 50);
        
        spinReel3 = new Timer();
        spinReel3.schedule(new spinReel3Class(), 0, 1 * 50);
    }
    
    public int getSymbol(int choice) //This decides how often a given symbol appears
    {
        if (choice <= 400)
        {
            return 0;
        }
        else if (choice <= 700)
        {
            return 1;
        }
        else if (choice <= 950)
        {
            return 2;
        }
        else if (choice <= 1200)
        {
            return 3;
        }
        else if (choice <= 1400)
        {
            return 4;
        }
        else if (choice <= 1550)
        {
            return 5;
        }
        else if (choice <= 1680)
        {
            return 6;
        }
        else if (choice <= 1800)
        {
            return 7;
        }
        else if (choice <= 1910)
        {
            return 8;
        }
        else // choice 1911-2000
        {
            return 9;
        }
    }
    
    public void setSymbol(int i, int decision) //This puts the symbol on the reel in the location selected
    {
        if (i <= 2)
        {
            place[i].setIcon(symbol[decision]);
            paylineSymbol[i] = decision;
            if (decision >= 8) //If the symbol is a 2x or 3x, and is on the payline (places 0 through 2) a beep occurs
            {
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
        }
        else //The top and bottom of the reels are half the height of the center - so these use different graphics
        {
            place[i].setIcon(symbol[decision + 10]);
        }
    }
    
    private class spinClass extends TimerTask
    {
        int[] decision = new int[9];
        int reel = 0;
        
        public void run() //Runs at the interval described above (1000 ms/1 second)
        {
            switch (reel)
            {
                case 0:
                {
                    spinReel1.cancel(); //Stops the reel spinning
                    
                    place[0].setIcon(null); //Blanks the reel so that no spinning animations are seen
                    place[3].setIcon(null);
                    place[6].setIcon(null);
                    
                    break; //Exits the switch
                }
                case 1:
                {
                    spinReel2.cancel(); 
                    
                    place[1].setIcon(null);
                    place[4].setIcon(null);
                    place[7].setIcon(null);
                    
                    break;
                }
                case 2:
                {
                    spinReel3.cancel(); 
                    
                    place[2].setIcon(null);
                    place[5].setIcon(null);
                    place[8].setIcon(null);
                    
                    break;
                }
            }
 
            for(int i = 0; i < decision.length; i++)
            {
                if (i % 3 == reel)
                {
                    if (i % 3 == 0)
                    {
                        int choice = spinner.nextInt(1800); //Instead of 2000; this prevents a multiplier from being on the first reel
                        decision[i] = getSymbol(choice);
                        setSymbol(i, decision[i]); //Sets the place to the selected symbol
                    }
                    else
                    {
                        int choice = spinner.nextInt(2000); //Multipliers can appear on either of the other two reels
                        decision[i] = getSymbol(choice);
                        setSymbol(i, decision[i]);
                    } 
                }
            }
            
            reel++;
            
            if (reel > 3) //Reels have been spun, end of the 'hand'
            {
                spinner.cancel();
                calculate();
                
                spin.setEnabled(true); //Buttons re-enabled for the next spin
                bet1.setEnabled(true);
                betMax.setEnabled(true);
                payTable.setEnabled(true);
            }
        }        
    }
    
    private class spinReel1Class extends TimerTask
    {
        int spinColor = 0;
        public void run() //At 50 ms
        {
            place[0].setIcon(spinAnim[spinColor]); //This is the 'spinning animation'
            place[3].setIcon(spinAnim[spinColor + 3]);
            place[6].setIcon(spinAnim[spinColor + 3]);
            
            if (spinColor == 2) //Resets the spin color
            {
                spinColor = 0;
            }
            else
            {
                spinColor++;
            }
        }        
    }
    
    private class spinReel2Class extends TimerTask
    {
        int spinColor = 0;
        public void run() 
        {
            place[1].setIcon(spinAnim[spinColor]);
            place[4].setIcon(spinAnim[spinColor + 3]);
            place[7].setIcon(spinAnim[spinColor + 3]);
            
            if (spinColor == 2)
            {
                spinColor = 0;
            }
            else
            {
                spinColor++;
            }
        }        
    }
    
    private class spinReel3Class extends TimerTask
    {
        int spinColor = 0;
        public void run() 
        {
            place[2].setIcon(spinAnim[spinColor]);
            place[5].setIcon(spinAnim[spinColor + 3]);
            place[8].setIcon(spinAnim[spinColor + 3]);
            
            if (spinColor == 2)
            {
                spinColor = 0;
            }
            else
            {
                spinColor++;
            }
        }        
    }
    
    public void calculate()
    {
        int doubles = 0;
        int triples = 0;
        
        int payout = 0;
        boolean jackpot = false;
        boolean wildsOnly = true;
        
        for (int i = 0; i < paylineSymbol.length; i++) //Calculates the number of 2x or 3x symbols there are
        {
            if (paylineSymbol[i] == 8)
            {
                doubles++;
            }         
            if (paylineSymbol[i] == 9)
            {
                triples++;
            }
        }
        
        //System.out.println(paylineSymbol[0] + " " + paylineSymbol[1] + " " + paylineSymbol[2]);
        payout = getPayout(doubles, triples);
        wildsOnly = checkWildsOnly();
        
        if (payout == 15000) //Only happens if a Gold 7 + 3x + 3x
        {
            jackpot = true;
        }

        if (wildsOnly) //Multiplers exist, but no other winning combination exists
        {
        //A 2x symbol is worth 6 credits * bet. 3x is worth 9 credits * bet
            switch (doubles)
            {
                case 0: //No 2x symbols
                {
                    switch(triples)
                    {
                        case 1: //One 3x symbol
                        {
                            payout = 9;
                            break;
                        }
                        case 2: //Two 3x symbols
                        {
                            payout = 18;
                            break;
                        }
                    }
                    break;
                }
                case 1: //One 2x symbol
                {
                    switch(triples)
                    {
                        case 0: //No 3x symbols 
                        {
                            payout = 6;
                            break;
                        }
                        case 1: //One 3x symbol
                        {
                            payout = 15;
                            break;
                        }                   
                    }
                    break;
                }
                case 2: //Two 2x symbols
                {
                    payout = 12;
                    break;
                }
            }
        }     

        if (doubles > 0 && ! jackpot && ! wildsOnly) //Multiplies the payout by 2 for each 2x symbol as long as a jackpot does not exist
        {
            payout = payout * 2 * doubles;
        }
        
        if (triples > 0 && ! jackpot && ! wildsOnly) //Multiplies the payout by 3 for each 3x symbol as long as a jackpot does not exist
        {
            if (triples == 1)
            {
                payout = payout * 3;
            }
            else
            {
                payout = payout * 9;
            }
        }
        
        //Displays a message depending on the payout
        if (payout == 0)
        {
            winningsDisplay.setText("Nothing here! Try again");
        }
        else if (payout < 100)
        {
            winningsDisplay.setText("Won " + payout + " credits");
        }
        else if (payout < 300)
        {
            winningsDisplay.setText("Won " + payout + " credits!");
        }
        else if (payout < 1000)
        {
            winningsDisplay.setText("Won " + payout + " credits!!!");
        }
        else if (payout < 15000)
        {
            winningsDisplay.setText("WON " + payout + " CREDITS!!!");
        }
        else
        {
            winningsDisplay.setText("JACKPOT!!! " + payout + " CREDITS!!!");
        }
        
        if (payout > 0)
        {
            light.setIcon(lightOn);
        }
        
        setCredits(getCredits() + payout);
        creditsDisplay.setText("Credits: " + credits);
    }
    
    public int getPayout(int doubles, int triples)
    {
        switch (doubles + triples) //Basically there are three primary cases - no wilds exist, one wild exists, or two wilds exist
        {
            case 0:
            {
                if (paylineSymbol[0] == paylineSymbol[1] && paylineSymbol[1] == paylineSymbol[2]) //All three symbols are equal
                {
                    switch (paylineSymbol[0])
                    {
                        case 0: //All blank, nothing won
                        {
                            break;
                        }
                        case 1: //Three 1 Bars
                        {
                            return 3 * cost;
                        }
                        case 2: //Three 2 Bars
                        {
                            return 4 * cost;
                        }
                        case 3: //Three 3 Bars
                        {
                            return 6 * cost;
                        }
                        case 4: //Three 7 Bars
                        {
                            return 10 * cost;
                        }
                        case 5: //Three Blue 7s
                        {
                            return 16 * cost;
                        }
                        case 6: //Three Red 7s
                        {
                            return 30 * cost;
                        }
                        case 7: //Three Gold 7s
                        {
                            if (cost == 3)
                            {
                                return 1500;
                            }
                            else
                            {
                                return 60 * cost;
                            }                           
                        }
                    }
                }
                else if (paylineSymbol[0] != 0 && paylineSymbol[1] != 0 && paylineSymbol[2] != 0) //Basically there is something in all three spaces
                {
                    if (paylineSymbol[0] >= 4 && paylineSymbol[1] >= 4 && paylineSymbol[2] >= 4) //This means they are all some combination of 7s (but not the same)
                    {
                        return 2 * cost;
                    }
                    else if (paylineSymbol[0] <= 4 && paylineSymbol[1] <= 4 && paylineSymbol[2] <= 4) //This means they are all some combination of bars (but not the same)
                    {
                        return 1 * cost;
                    }
                }
                break; //None of the above true, so no payout
            }
            case 1: //There is one double or triple somewhere
            {
                if (paylineSymbol[0] == paylineSymbol[2] || paylineSymbol[0] == paylineSymbol[1]) //The other two symbols are the same
                {
                    switch (paylineSymbol[0]) //The multiplers will be dealt with on return
                    {
                        case 0: //All blank, nothing won except for the wilds
                        {
                            return (doubles * 3) + (triples * 3);
                        }
                        case 1: //Three 1 Bars
                        {
                            return 3 * cost;
                        }
                        case 2: //Three 2 Bars
                        {
                            return 4 * cost;
                        }
                        case 3: //Three 3 Bars
                        {
                            return 6 * cost;
                        }
                        case 4: //Three 7 Bars
                        {
                            return 10 * cost;
                        }
                        case 5: //Three Blue 7s
                        {
                            return 16 * cost;
                        }
                        case 6: //Three Red 7s
                        {
                            return 30 * cost;
                        }
                        case 7: //Three Gold 7s
                        {
                            if (cost == 3)
                            {
                                return 1500;
                            }
                            else
                            {
                                return 60 * cost;
                            }                           
                        }
                    }    
                }
                else if (paylineSymbol[0] != 0 && paylineSymbol[1] != 0 && paylineSymbol[2] != 0) //Symbols in all three positions
                {
                    if ((paylineSymbol[2] >= 8 && paylineSymbol[0] >= 4 && paylineSymbol[1] >= 4) || (paylineSymbol[1] >= 8 && paylineSymbol[0] >= 4 && paylineSymbol[2] >= 4)) //7s in the other two areas
                    {
                        return 2 * cost;
                    }
                    else if ((paylineSymbol[0] <= 4 && paylineSymbol[1] <= 4) || (paylineSymbol[0] <= 4 && paylineSymbol[2] <= 4)) //Bars in the other two areas
                    {
                        return 1 * cost;
                    }
                }
                return (doubles * 3) + (triples * 3); //Missing symbols elsewhere - only wilds win            
            }
            case 2: //Two wilds - only what's in the first position matters
            {
                switch (paylineSymbol[0]) //The multiplers will be dealt with on return
                {
                    case 0: //Nothing won except for the wilds
                    {
                        return (doubles * 3) + (triples * 3);
                    }
                    case 1: //Three 1 Bars
                    {
                        return 3 * cost;
                    }
                    case 2: //Three 2 Bars
                    {
                        return 4 * cost;
                    }
                    case 3: //Three 3 Bars
                    {
                        return 6 * cost;
                    }
                    case 4: //Three 7 Bars
                    {
                        return 10 * cost;
                    }
                    case 5: //Three Blue 7s
                    {
                        return 16 * cost;
                    }
                    case 6: //Three Red 7s
                    {
                        return 30 * cost;
                    }
                    case 7: //Three Gold 7s
                    {
                        if (cost == 3)
                        {
                            if (paylineSymbol[1] == paylineSymbol[2] && paylineSymbol[1] == 9) //Both are 3x
                            {
                                return 15000;
                            }
                            return 1500; //If the above is not true
                        }
                        else
                        {
                            return 60 * cost;
                        }                           
                    }
                }    
            }
        }
        return 0;
    }
    
    //Checks if a multipler exists. If it does, then if the first symbol is blank, or no other winning combination exists, returns true. Otherwise returns false.
    public boolean checkWildsOnly()
    {
        if (paylineSymbol[1] >= 8 || paylineSymbol[2] >= 8)
        {
            if (paylineSymbol[0] == 0)
            {
                return true;
            }
            else if (paylineSymbol[0] == paylineSymbol[1] || paylineSymbol[0] == paylineSymbol[2])
            {
                return false;
            }
            else if (paylineSymbol[1] >= 8 && paylineSymbol[2] >= 8)
            {
                return false;
            }
            else if (paylineSymbol[0] <= 4 && (paylineSymbol[1] <= 4 || paylineSymbol[2] <= 4))
            {
                return false;
            }
            else if (paylineSymbol[0] <= 7 && ((paylineSymbol[1] <= 7 && paylineSymbol[1] >= 4)) || ((paylineSymbol[2] <= 7 && paylineSymbol[2] >= 4)))
            {
                return false;
            }
            return true;
        }
        return false;
    }
}
