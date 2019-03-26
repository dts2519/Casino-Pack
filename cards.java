package casinopack;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*; //Used: Timer, TimerTask, Random, Arrays


public class cards extends JFrame{
    
    private JButton cardDeal, card1Hold, card2Hold, card3Hold, card4Hold, card5Hold, payTable, changeBet, betMax;
    private int creditsActual = 100; 
    private int cost = 1;
    
    private Boolean cardsDealt = false, card1Held = false, card2Held = false, card3Held = false, card4Held = false, card5Held = false, cardClickFlag = false;
    
    private int decision1 = 52, decision2 = 52, decision3 = 52, decision4 = 52, decision5 = 52, decision6 = 52, decision7 = 52, decision8 = 52, decision9 = 52, decision10 = 52; //Technical null value. 
    
    JLabel version = new JLabel("Java Poker - Version 1.0");
    
    JLabel card1 = new JLabel(); //Represent the card icons on the screen
    JLabel card2 = new JLabel();
    JLabel card3 = new JLabel();
    JLabel card4 = new JLabel();
    JLabel card5 = new JLabel();
   
    JLabel held1 = new JLabel(""); //Represent which cards are held, if any
    JLabel held2 = new JLabel("");
    JLabel held3 = new JLabel("");
    JLabel held4 = new JLabel("");
    JLabel held5 = new JLabel("");
    
    JLabel creditDisplay = new JLabel("Credits: " + creditsActual); //Displays credit balance
    
    JLabel handDisplay = new JLabel("Welcome");
    JLabel handDisplay2 = new JLabel("Press Deal for a new hand"); //Miscellaneous messages, such as the hand rank, or instructions
    
    ImageIcon back = new ImageIcon(getClass().getResource("Back.PNG")); //The 'backs' of the cards
    ImageIcon cardFront;  
    ImageIcon payTableImage = new ImageIcon(getClass().getResource("Pay Table.PNG"));
    
    private int discardCount = 0; //How many cards are discarded (calculated at second deal)
    
    Timer nelly, nallu;
    Random dealer = new Random();
    
    public cards(int credits)
    {
        super("Java Poker"); //Title of the window
        JPanel p = new JPanel();
        setCActual(credits); //This allows for various starting credits
        
        p.setLayout(null);
        getContentPane().add(p); //Adds the panel to the window (all things are installed on the panel)
        
        version.setBounds(5, 0, 150, 30);
        p.add(version);
        
        cardDeal = new JButton("Deal"); //The Deal button
        cardDeal.setBounds(904, 420, 181, 60);
        p.add(cardDeal);
        
        card1Hold = new JButton("Hold"); //The Hold buttons, one for each card
        card2Hold = new JButton("Hold");
        card3Hold = new JButton("Hold");
        card4Hold = new JButton("Hold");
        card5Hold = new JButton("Hold");
        
        payTable = new JButton("Pay Table"); //Brings up the pay table
        payTable.setBounds(90, 420, 151, 50);
        p.add(payTable);
       
        changeBet = new JButton("Bet " + getCost()); //Displays the current bet
        changeBet.setBounds(763, 400, 121, 40);
        p.add(changeBet);
        
        betMax = new JButton("Bet Max"); //5
        betMax.setBounds(763, 450, 121, 40);
        p.add(betMax);
        
        
        card1Hold.setBounds(60, 320, 181, 50);
        p.add(card1Hold);
        
        card2Hold.setBounds(271, 320, 181, 50);
        p.add(card2Hold);
        
        card3Hold.setBounds(482, 320, 181, 50);
        p.add(card3Hold);
        
        card4Hold.setBounds(693, 320, 181, 50);
        p.add(card4Hold);
        
        card5Hold.setBounds(904, 320, 181, 50);
        p.add(card5Hold);
        
        card1Hold.setEnabled(false); //Initially unclickable
        card2Hold.setEnabled(false);
        card3Hold.setEnabled(false);
        card4Hold.setEnabled(false);
        card5Hold.setEnabled(false);
        
        card1.setIcon(back); //The cards begin with 'face down'
        card2.setIcon(back);
        card3.setIcon(back);
        card4.setIcon(back);
        card5.setIcon(back);
        
        card1.setBounds(60, 60, 181, 239);
        p.add(card1);
        
        card2.setBounds(271, 60, 181, 239);
        p.add(card2);
        
        card3.setBounds(482, 60, 181, 239);
        p.add(card3);
        
        card4.setBounds(693, 60, 181, 239);
        p.add(card4);
        
        card5.setBounds(904, 60, 181, 239);
        p.add(card5);
        
        creditDisplay.setBounds(904, 370, 181, 60);
        p.add(creditDisplay);
        
        handDisplay.setBounds(300, 400, 200, 30);
        p.add(handDisplay);
        
        handDisplay2.setBounds(300, 430, 200, 30);
        p.add(handDisplay2);
        
        held1.setBounds(60, 35, 181, 30);
        p.add(held1);
        
        held2.setBounds(271, 35, 181, 30);
        p.add(held2);
        
        held3.setBounds(482, 35, 181, 30);
        p.add(held3);
        
        held4.setBounds(693, 35, 181, 30);
        p.add(held4);
        
        held5.setBounds(904, 35, 181, 30);
        p.add(held5);
        
        HandlerClass handoraa = new HandlerClass(); //Allows for events on button presses
        cardDeal.addActionListener(handoraa);
        card1Hold.addActionListener(handoraa);
        card2Hold.addActionListener(handoraa);
        card3Hold.addActionListener(handoraa);
        card4Hold.addActionListener(handoraa);
        card5Hold.addActionListener(handoraa);
        payTable.addActionListener(handoraa);
        changeBet.addActionListener(handoraa);
        betMax.addActionListener(handoraa);
        
        mouseHandlerClass handoraa2 = new mouseHandlerClass();
        card1.addMouseListener(handoraa2);
        card2.addMouseListener(handoraa2);
        card3.addMouseListener(handoraa2);
        card4.addMouseListener(handoraa2);
        card5.addMouseListener(handoraa2);
    }
    
    public void setCActual(int credits)
    {
        creditsActual = credits;
    }
    
    public int getCActual()
    {
        return creditsActual;
    }
    
    public void setCost(int temp)
    {
        cost = temp;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    private class HandlerClass implements ActionListener
    {
        public void actionPerformed(ActionEvent ebento)
        {
           if (ebento.getSource() == cardDeal) //Deal button was pressed
           {
               if (cardsDealt == false)
               {
                   cardsDealt = true;
                   firstDeal();
                   cardClickFlag = true;
               }
               else
               {
                   cardClickFlag = false;
                   cardsDealt = false;
                   secondDeal();
               }
           }
           if (ebento.getSource() == card1Hold) //The corresponding Hold button was pressed
           {
               if (cardsDealt == true)
               {
                   if (card1Held == false)
                   {
                       card1Held = true;
                       held1.setText("Held");
                   }
                   else
                   {
                       card1Held = false;
                       held1.setText("");
                   }
               }
           }
           if (ebento.getSource() == card2Hold)
           {
               if (cardsDealt == true)
               {
                   if (card2Held == false)
                   {
                       card2Held = true;
                       held2.setText("Held");
                   }
                   else
                   {
                       card2Held = false;
                       held2.setText("");
                   }
               }
           }
           if (ebento.getSource() == card3Hold)
           {
               if (cardsDealt == true)
               {
                   if (card3Held == false)
                   {
                       card3Held = true;
                       held3.setText("Held");
                   }
                   else
                   {
                       card3Held = false;
                       held3.setText("");
                   }
               }
           }
           if (ebento.getSource() == card4Hold)
           {
              if (cardsDealt == true)
               {
                   if (card4Held == false)
                   {
                       card4Held = true;
                       held4.setText("Held");
                   }
                   else
                   {
                       card4Held = false;
                       held4.setText("");
                   }
               }
           }
           if (ebento.getSource() == card5Hold)
           {
               if (cardsDealt == true)
               {
                   if (card5Held == false)
                   {
                       card5Held = true;
                       held5.setText("Held");
                   }
                   else
                   {
                       card5Held = false;
                       held5.setText("");
                   }
               }
           }
           if (ebento.getSource() == payTable)
           {
               JOptionPane.showMessageDialog(null, "", "Pay Table", JOptionPane.INFORMATION_MESSAGE, payTableImage);
           }
           if (ebento.getSource() == changeBet)
           {
               card1.setIcon(back); 
               card2.setIcon(back);
               card3.setIcon(back);
               card4.setIcon(back);
               card5.setIcon(back);
               held1.setText("");                   
               held2.setText("");              
               held3.setText("");             
               held4.setText("");              
               held5.setText("");
               if (getCost() == 5)
               {
                   setCost(1);
               }
               else
               {
                   setCost(getCost() + 1);
               }
               changeBet.setText("Bet " + getCost());
           }
           if (ebento.getSource() == betMax)
           {
               if (getCost() != 5)
               {
                   setCost(5);
               }
               changeBet.setText("Bet " + getCost());
               cardsDealt = true;
               firstDeal();
               cardClickFlag = true;
           }
        }
    }
    
    private class mouseHandlerClass implements MouseListener
    {
        
        public void mouseClicked(MouseEvent ebento) //Identical to the Hold buttons (see under HandlerClass)
        {
            //Nothing
        }

        public void mousePressed(MouseEvent ebento) 
        {
            if (ebento.getSource() == card1) //The corresponding card was pressed
            {
                if (cardsDealt == true && cardClickFlag == true)
                {
                    if (card1Held == false)
                    {
                        card1Held = true;
                        held1.setText("Held");
                    }
                    else
                    {
                        card1Held = false;
                        held1.setText("");
                    }
                }
            }
            if (ebento.getSource() == card2)
            {
                if (cardsDealt == true && cardClickFlag == true)
                {
                    if (card2Held == false)
                    {
                        card2Held = true;
                        held2.setText("Held");
                    }
                    else
                    {
                        card2Held = false;
                        held2.setText("");
                    }
                }
            }
            if (ebento.getSource() == card3)
            {
                if (cardsDealt == true && cardClickFlag == true)
                {
                    if (card3Held == false)
                    {
                        card3Held = true;
                        held3.setText("Held");
                    }
                    else
                    {
                        card3Held = false;
                        held3.setText("");
                    }
                }
            }
            if (ebento.getSource() == card4)
            {
                if (cardsDealt == true && cardClickFlag == true)
                {
                    if (card4Held == false)
                    {
                        card4Held = true;
                        held4.setText("Held");
                    }
                    else
                    {
                        card4Held = false;
                        held4.setText("");
                    }
                }
            }
            if (ebento.getSource() == card5)
            {
                if (cardsDealt == true && cardClickFlag == true)
                {
                    if (card5Held == false)
                    {
                        card5Held = true;
                        held5.setText("Held");
                    }
                    else
                    {
                        card5Held = false;
                        held5.setText("");
                    }
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
    
    public void firstDeal() //The initial hand, before discard
    {
        handDisplay.setText(""); //Removes any previous messages which now are meaningless
        handDisplay2.setText("");
        cardDeal.setEnabled(false); //Prevents overlapping deals (will soft lock the game if not present)
        card1Hold.setEnabled(false);
        card2Hold.setEnabled(false);
        card3Hold.setEnabled(false);
        card4Hold.setEnabled(false);
        card5Hold.setEnabled(false);
        changeBet.setEnabled(false);
        betMax.setEnabled(false);
        
        int cTemp = getCActual();
        if (cTemp < getCost()) 
        {
            setCActual(cTemp + (100 - getCost())); //Gives the player another 100 credits if they run out
        }
        else
        {
            setCActual(cTemp - getCost()); //Each hand costs whatever cost is
        }
        creditDisplay.setText("Credits: " + getCActual());
        
        card1Held = false; //This removes any active Held labels and resets the Booleans, which on second hand and on will all be true (see secondDealClass)
        held1.setText("");
        
        card2Held = false;
        held2.setText("");
        
        card3Held = false;
        held3.setText("");
        
        card4Held = false;
        held4.setText("");
        
        card5Held = false;
        held5.setText("");
        
        nelly = new Timer();
        nelly.schedule(new firstDealClass(), 0, 1 * 400); //Each card is presented one at a time
    }
    
    public void secondDeal() //The deal after cards are held
    {
        cardDeal.setEnabled(false); //Prevents overlapping deals (will soft lock the game if not present)
        card1Hold.setEnabled(false);
        card2Hold.setEnabled(false);
        card3Hold.setEnabled(false);
        card4Hold.setEnabled(false);
        card5Hold.setEnabled(false);
        handDisplay.setText("");
        handDisplay2.setText("");
        
        if (card1Held == false) //This checks for each card and resets the card if it was not held. Also adds 1 to discardCount.
        {
            card1.setIcon(back);
            discardCount++;
        }
        if (card2Held == false)
        {
            card2.setIcon(back);
            discardCount++;
        }
        if (card3Held == false)
        {
            card3.setIcon(back);
            discardCount++;
        }
        if (card4Held == false)
        {
            card4.setIcon(back);
            discardCount++;
        }
        if (card5Held == false)
        {
            card5.setIcon(back);
            discardCount++;
        }
        
        nelly = new Timer();
        nelly.schedule(new secondDealClass(), 400, 1 * 400);
        
        nallu = new Timer();
        nallu.schedule(new calculateClass(), 400 * discardCount, 1 * 400); //Two separate timers to avoid confusion. This starts at the conclusion of nelly
    }
    
    private class firstDealClass extends TimerTask
    {      
        int count = 0;
        public void run()
        {
            if (count == 0)
            {
                card1.setIcon(back); //Initial tick
                card2.setIcon(back);
                card3.setIcon(back);
                card4.setIcon(back);
                card5.setIcon(back);    
            }
            else if (count == 1) //Second tick, deals the first card
            {
                decision1 = dealer.nextInt(52);
                selectCard(decision1);
                card1.setIcon(cardFront); //Puts the chosen card on the label
            }
            else if (count == 2) //Third tick, second card
            {
                decision2 = dealer.nextInt(52);
                while(decision2 == decision1)
                {
                    decision2 = dealer.nextInt(52); //Checks to make sure the same card isn't dealt twice
                }
                selectCard(decision2);
                card2.setIcon(cardFront);
            }
            else if (count == 3) //Etc.
            {
                decision3 = dealer.nextInt(52);
                while(decision3 == decision1 || decision3 == decision2) //Checks to make sure the third card is different to the previouu two
                {
                    decision3 = dealer.nextInt(52);
                }
                selectCard(decision3);
                card3.setIcon(cardFront);
            }
            else if (count == 4)
            {
                decision4 = dealer.nextInt(52);
                while(decision4 == decision1 || decision4 == decision2 || decision4 == decision3) //Etc.
                {
                    decision4 = dealer.nextInt(52);
                }
                selectCard(decision4);
                card4.setIcon(cardFront);
            }
            else if (count == 5)
            {
                decision5 = dealer.nextInt(52);
                while(decision5 == decision1 || decision5 == decision2 || decision5 == decision3 || decision5 == decision4)
                {
                    decision5 = dealer.nextInt(52);
                }
                selectCard(decision5);
                card5.setIcon(cardFront);
            }  
            else //count = 6
            {
                nelly.cancel(); //Cards dealt, timer is switched off
                
                cardDeal.setEnabled(true); //Buttons activated again to allow for decision by the player
                card1Hold.setEnabled(true);
                card2Hold.setEnabled(true);
                card3Hold.setEnabled(true);
                card4Hold.setEnabled(true);
                card5Hold.setEnabled(true);
                card5Hold.setEnabled(true);
                cardClickFlag = true;
                
                calculateHand(); //Displays the current hand value as of the first deal
                handDisplay2.setText("Click Hold to keep the card above it");
            }
            count++;
        }
    }
    
    private class secondDealClass extends TimerTask
    {
        int count2 = 0;
        Boolean card1Flag = false, card2Flag = false, card3Flag = false, card4Flag = false, card5Flag = false;
        public void run()
        {
            if (count2 == 0) //This isolates the card held cases such that they do not interfere with the hand calculation.
            {
                if (card1Held == false) //I.e. not held
                {
                    discardedCard1();
                    card1Flag = true;
                }
                else if (card2Held == false)
                {
                    discardedCard2();
                    card2Flag = true;
                }
                else if (card3Held == false)
                {
                    discardedCard3();
                    card3Flag = true;
                }
                else if (card4Held == false)
                {
                    discardedCard4();
                    card4Flag = true;
                }
                else if (card5Held == false)
                {
                    discardedCard5();
                    card5Flag = true;
                }
                count2++;
            }
            else if (count2 == 1)
            {
                if (card2Held == false && card2Flag == false)
                {
                    discardedCard2();
                    card2Flag = true;
                }
                else if (card3Held == false && card3Flag == false)
                {
                    discardedCard3();
                    card3Flag = true;
                }
                else if (card4Held == false && card4Flag == false)
                {
                    discardedCard4();
                    card4Flag = true;
                }
                else if (card5Held == false && card5Flag == false)
                {
                    discardedCard5();
                    card5Flag = true;
                }
                count2++;
            }
            else if (count2 == 2)
            {
                if (card3Held == false && card3Flag == false)
                {
                    discardedCard3();
                    card3Flag = true;
                }
                else if (card4Held == false && card4Flag == false)
                {
                    discardedCard4();
                    card4Flag = true;
                }
                else if (card5Held == false && card5Flag == false)
                {
                    discardedCard5();
                    card5Flag = true;
                }
                count2++;
            }
            else if (count2 == 3)
            {
                if (card4Held == false && card4Flag == false)
                {
                    discardedCard4();
                    card4Flag = true;
                }
                else if (card5Held == false && card5Flag == false)
                {
                    discardedCard5();
                    card5Flag = true;
                }
                count2++;
            }
            else if (count2 == 4) //No else since it is not a guarantee that any of the cards will be discarded
            {
                if (card5Held == false && card5Flag == false)
                {
                    discardedCard5();
                    card5Flag = true;
                }
                count2++;
            }
            if (discardCount == 0) //It will be 0 after all cards have been re-dealt
            {
            nelly.cancel(); //Hand completed, timer is switched off
            }
        }
    }
    
    public void discardedCard1()
    {
        decision6 = dealer.nextInt(52); //The replacements should be different to all of the previous cards regardless of whether they were discarded
        while (decision6 == decision1 || decision6 == decision2 || decision6 == decision3 || decision6 == decision4 || decision6 == decision5)
        {
            decision6 = dealer.nextInt(52); 
        }
        selectCard(decision6);
        card1.setIcon(cardFront);
        discardCount--;
    }
    
    public void discardedCard2()
    {
        decision7 = dealer.nextInt(52);
        while (decision7 == decision1 || decision7 == decision2 || decision7 == decision3 || decision7 == decision4 || decision7 == decision5 || decision7 == decision6)
        {
            decision7 = dealer.nextInt(52); 
        }
        selectCard(decision7);
        card2.setIcon(cardFront);
        discardCount--;
    }
    
    public void discardedCard3()
    {
        decision8 = dealer.nextInt(52);
        while (decision8 == decision1 || decision8 == decision2 || decision8 == decision3 || decision8 == decision4 || decision8 == decision5 || decision8 == decision6 || decision8 == decision7)
        {
            decision8 = dealer.nextInt(52); 
        }
        selectCard(decision8);
        card3.setIcon(cardFront);
        discardCount--;
    }
    
    public void discardedCard4()
    {
        decision9 = dealer.nextInt(52);
        while (decision9 == decision1 || decision9 == decision2 || decision9 == decision3 || decision9 == decision4 || decision9 == decision5 || decision9 == decision6 || decision9 == decision7 || decision9 == decision8)
        {
            decision9 = dealer.nextInt(52); 
        }
        selectCard(decision9);
        card4.setIcon(cardFront);
        discardCount--;
    }
    
    public void discardedCard5()
    {
        decision10 = dealer.nextInt(52);
        while (decision10 == decision1 || decision10 == decision2 || decision10 == decision3 || decision10 == decision4 || decision10 == decision5 || decision10 == decision6 || decision10 == decision7 || decision10 == decision8 || decision10 == decision9)
        {
            decision10 = dealer.nextInt(52); 
        }
        selectCard(decision10);
        card5.setIcon(cardFront);
        discardCount--;
    }
    
    public class calculateClass extends TimerTask
    {
        int delay = 0, handValue = 0;
        public void run()
        {
            if (delay < 2) //Delay of 800ms from the last card dealt on the second deal
            {
                delay++;
            }
            else
            {
                calculateHand(); //To decide the winning value of the hand, if any
                handDisplay2.setText("Press Deal for a new hand");
                nallu.cancel(); //Delay finished, timer is switched off
                
                cardDeal.setEnabled(true); //The hold buttons do not need to come on again until the next hand
                changeBet.setEnabled(true);
                betMax.setEnabled(true);
                
                decision1 = 52; //Clears these for the next hand
                decision2 = 52;
                decision3 = 52;
                decision4 = 52;
                decision5 = 52;
                decision6 = 52;
                decision7 = 52;
                decision8 = 52;
                decision9 = 52;
                decision10 = 52;
            }
        }
    }
    
    /* Card placement:
    2s: % 13 = 0
    3s: % 13 = 1
    4s: % 13 = 2
    5s: % 13 = 3
    6s: % 13 = 4
    7s: % 13 = 5
    8s: % 13 = 6
    9s: % 13 = 7
    10s: % 13 = 8
    Js: % 13 = 9
    Qs: % 13 = 10
    Ks: % 13 = 11
    As: % 13 = 12
    
    S (Spades): / 13 = 0
    C (Clubs): / 13 = 1
    H (Hearts): / 13 = 2
    D (Diamonds): / 13 = 3
    */
    public void selectCard(int decision)
    {
        switch(decision)
        {
            case 0:
            {
                cardFront = new ImageIcon(getClass().getResource("2S.PNG"));
                break;
            }
            case 1:
            {
                cardFront = new ImageIcon(getClass().getResource("3S.PNG"));
                break;
            }
            case 2:
            {
                cardFront = new ImageIcon(getClass().getResource("4S.PNG"));
                break;
            }
            case 3:
            {
                cardFront = new ImageIcon(getClass().getResource("5S.PNG"));
                break;
            }
            case 4:
            {
                cardFront = new ImageIcon(getClass().getResource("6S.PNG"));
                break;
            }
            case 5:
            {
                cardFront = new ImageIcon(getClass().getResource("7S.PNG"));
                break;
            }
            case 6:
            {
                cardFront = new ImageIcon(getClass().getResource("8S.PNG"));
                break;
            }
            case 7:
            {
                cardFront = new ImageIcon(getClass().getResource("9S.PNG"));
                break;
            }
            case 8:
            {
                cardFront = new ImageIcon(getClass().getResource("10S.PNG"));
                break;
            }
            case 9:
            {
                cardFront = new ImageIcon(getClass().getResource("JS.PNG"));
                break;
            }
            case 10:
            {
                cardFront = new ImageIcon(getClass().getResource("QS.PNG"));
                break;
            }
            case 11:
            {
                cardFront = new ImageIcon(getClass().getResource("KS.PNG"));
                break;
            }
            case 12:
            {
                cardFront = new ImageIcon(getClass().getResource("AS.PNG"));
                break;
            }
            case 13:
            {
                cardFront = new ImageIcon(getClass().getResource("2C.PNG"));
                break;
            }
            case 14:
            {
                cardFront = new ImageIcon(getClass().getResource("3C.PNG"));
                break;
            }
            case 15:
            {
                cardFront = new ImageIcon(getClass().getResource("4C.PNG"));
                break;
            }
            case 16:
            {
                cardFront = new ImageIcon(getClass().getResource("5C.PNG"));
                break;
            }
            case 17:
            {
                cardFront = new ImageIcon(getClass().getResource("6C.PNG"));
                break;
            }
            case 18:
            {
                cardFront = new ImageIcon(getClass().getResource("7C.PNG"));
                break;
            }
            case 19:
            {
                cardFront = new ImageIcon(getClass().getResource("8C.PNG"));
                break;
            }
            case 20:
            {
                cardFront = new ImageIcon(getClass().getResource("9C.PNG"));
                break;
            }
            case 21:
            {
                cardFront = new ImageIcon(getClass().getResource("10C.PNG"));
                break;
            }
            case 22:
            {
                cardFront = new ImageIcon(getClass().getResource("JC.PNG"));
                break;
            }
            case 23:
            {
                cardFront = new ImageIcon(getClass().getResource("QC.PNG"));
                break;
            }
            case 24:
            {
                cardFront = new ImageIcon(getClass().getResource("KC.PNG"));
                break;
            }
            case 25:
            {
                cardFront = new ImageIcon(getClass().getResource("AC.PNG"));
                break;
            }
            case 26:
            {
                cardFront = new ImageIcon(getClass().getResource("2H.PNG"));
                break;
            }
            case 27:
            {
                cardFront = new ImageIcon(getClass().getResource("3H.PNG"));
                break;
            }
            case 28:
            {
                cardFront = new ImageIcon(getClass().getResource("4H.PNG"));
                break;
            }
            case 29:
            {
                cardFront = new ImageIcon(getClass().getResource("5H.PNG"));
                break;
            }
            case 30:
            {
                cardFront = new ImageIcon(getClass().getResource("6H.PNG"));
                break;
            }
            case 31:
            {
                cardFront = new ImageIcon(getClass().getResource("7H.PNG"));
                break;
            }
            case 32:
            {
                cardFront = new ImageIcon(getClass().getResource("8H.PNG"));
                break;
            }
            case 33:
            {
                cardFront = new ImageIcon(getClass().getResource("9H.PNG"));
                break;
            }
            case 34:
            {
                cardFront = new ImageIcon(getClass().getResource("10H.PNG"));
                break;
            }
            case 35:
            {
                cardFront = new ImageIcon(getClass().getResource("JH.PNG"));
                break;
            }
            case 36:
            {
                cardFront = new ImageIcon(getClass().getResource("QH.PNG"));
                break;
            }
            case 37:
            {
                cardFront = new ImageIcon(getClass().getResource("KH.PNG"));
                break;
            }
            case 38:
            {
                cardFront = new ImageIcon(getClass().getResource("AH.PNG"));
                break;
            }
            case 39:
            {
                cardFront = new ImageIcon(getClass().getResource("2D.PNG"));
                break;
            }
            case 40:
            {
                cardFront = new ImageIcon(getClass().getResource("3D.PNG"));
                break;
            }
            case 41:
            {
                cardFront = new ImageIcon(getClass().getResource("4D.PNG"));
                break;
            }
            case 42:
            {
                cardFront = new ImageIcon(getClass().getResource("5D.PNG"));
                break;
            }
            case 43:
            {
                cardFront = new ImageIcon(getClass().getResource("6D.PNG"));
                break;
            }
            case 44:
            {
                cardFront = new ImageIcon(getClass().getResource("7D.PNG"));
                break;
            }
            case 45:
            {
                cardFront = new ImageIcon(getClass().getResource("8D.PNG"));
                break;
            }
            case 46:
            {
                cardFront = new ImageIcon(getClass().getResource("9D.PNG"));
                break;
            }
            case 47:
            {
                cardFront = new ImageIcon(getClass().getResource("10D.PNG"));
                break;
            }
            case 48:
            {
                cardFront = new ImageIcon(getClass().getResource("JD.PNG"));
                break;
            }
            case 49:
            {
                cardFront = new ImageIcon(getClass().getResource("QD.PNG"));
                break;
            }
            case 50:
            {
                cardFront = new ImageIcon(getClass().getResource("KD.PNG"));
                break;
            }
            case 51:
            {
                cardFront = new ImageIcon(getClass().getResource("AD.PNG"));
                break;
            }
            case 52: //For security reasons
            {
                cardFront = new ImageIcon(getClass().getResource("Back.PNG"));
                break;
            }
        }
    }
    
    public void calculateHand() //Delivers the value of the hand, and also dispenses winnings if the hand is complete
    {
        int handValue = 0;
        if (cardsDealt == true) //First deal
        {
            handValue = getValue(decision1, decision2, decision3, decision4, decision5);
        }
        else //Second deal (after discards)
        {
            int newCard1 = decision1, newCard2 = decision2, newCard3 = decision3, newCard4 = decision4, newCard5 = decision5;
            if (card1Held == false)
            {
                newCard1 = decision6;
            }
            if (card2Held == false)
            {
                newCard2 = decision7;
            }
            if (card3Held == false)
            {
                newCard3 = decision8;
            }
            if (card4Held == false)
            {
                newCard4 = decision9;
            }
            if (card5Held == false)
            {
                newCard5 = decision10;
            }
            
            handValue = getValue(newCard1, newCard2, newCard3, newCard4, newCard5);
        }
        
        switch(handValue)
        {
            case 0:
            {
                if (cardsDealt == true) //Initial value, means nothing really, so no reaction
                {
                    handDisplay.setText("High Card");
                }
                else //Hand is over, final value
                {
                    handDisplay.setText("High Card - nothing here!");
                }
                break;
            }
            case 1:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Pair");
                }
                else
                {
                    handDisplay.setText("Pair - nothing here!");
                }
                break;
            }
            case 2:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Jacks or Better");
                }
                else
                {
                    if (getCost() == 1)
                    {
                        handDisplay.setText("Jacks or Better - won 1 credit");
                    }
                    else
                    {                                          
                        handDisplay.setText("Jacks or Better - won " + getCost() + " credits");
                    }
                    setCActual(getCActual() + getCost());
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 3:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Two Pair");
                }
                else
                {
                    handDisplay.setText("Two Pair - won " + (getCost() * 2) + " credits");
                    setCActual(getCActual() + (getCost() * 2));
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 4:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Three of a Kind");
                }
                else
                {
                    handDisplay.setText("Three of a Kind - won " + (getCost() * 3) + " credits");
                    setCActual(getCActual() + (getCost() * 3));
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 5:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Straight (Oooh)");
                }
                else
                {
                    handDisplay.setText("Straight - won " + (getCost() * 4) + " credits!");
                    setCActual(getCActual() + (getCost() * 4));
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 6:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Flush (Nice work)");
                }
                else
                {
                    handDisplay.setText("Flush - won " + (getCost() * 6) + " credits!");
                    setCActual(getCActual() + (getCost() * 6));
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 7:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Full House (Fantastic!)");
                }
                else
                {
                    handDisplay.setText("Full House - won " + (getCost() * 9) + " credits!");
                    setCActual(getCActual() + (getCost() * 9));
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 8:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Four of a Kind (Brilliant!)");
                }
                else
                {
                    if (getCost() == 5)
                    {
                        handDisplay.setText("Four of a Kind! - won " + (getCost() * 25) + " credits!");
                        setCActual(getCActual() + (getCost() * 25));
                    }
                    else
                    {
                        handDisplay.setText("Four of a Kind! - won " + (getCost() * 20) + " credits!");
                        setCActual(getCActual() + (getCost() * 20));
                    }
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 9:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("Straight Flush (How did you do this?)");
                }
                else
                {
                    if (getCost() == 5)
                    {
                        handDisplay.setText("Straight Flush!! - won " + (getCost() * 50) + " credits!!");
                        setCActual(getCActual() + (getCost() * 50));
                    }
                    else
                    {
                        handDisplay.setText("Straight Flush!! - won " + (getCost() * 40) + " credits!!");
                        setCActual(getCActual() + (getCost() * 40));
                    }
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
            case 10:
            {
                if (cardsDealt == true) 
                {
                    handDisplay.setText("ROYAL FLUSH (Ummm!?!?)");
                }
                else
                {
                    if (getCost() == 5)
                    {
                        handDisplay.setText("ROYAL FLUSH - WON " + (getCost() * 800) + " CREDITS!!!");
                        setCActual(getCActual() + (getCost() * 800));
                    }
                    else
                    {
                        handDisplay.setText("ROYAL FLUSH - WON " + (getCost() * 400) + " CREDITS!!!");
                        setCActual(getCActual() + (getCost() * 400));
                    }
                    creditDisplay.setText("Credits: " + getCActual());
                }
                break;
            }
        }
    }
    
    public int getValue(int card1, int card2, int card3, int card4, int card5)
    {
        int card1Mod = card1 % 13, card2Mod = card2 % 13, card3Mod = card3 % 13, card4Mod = card4 % 13, card5Mod = card5 % 13; //Determines rank
        int card1Div = card1 / 13, card2Div = card2 / 13, card3Div = card3 / 13, card4Div = card4 / 13, card5Div = card5 / 13; //Determines suit
        
        //Sorting the five Mods to be in rank order
        int[] cardModArray = {card1Mod, card2Mod, card3Mod, card4Mod, card5Mod};
        
        Arrays.sort(cardModArray);
        
        card1Mod = cardModArray[0];
        card2Mod = cardModArray[1];
        card3Mod = cardModArray[2];
        card4Mod = cardModArray[3];
        card5Mod = cardModArray[4];
        
        //Checking first for pair
        if (card1Mod == card2Mod || card2Mod == card3Mod || card3Mod == card4Mod || card4Mod == card5Mod) //Sorted, so reduces number of checks
        {
            //Pair succeeded, now checking for three of a kind
            if (card1Mod == card3Mod || card2Mod == card4Mod || card3Mod == card5Mod)
            {
                //Three of a kind succeeded, now checking for four of a kind
                if (card1Mod == card4Mod || card2Mod == card5Mod)
                {
                    return 8;
                }
                //Four failed, now checking for full house
                else if ((card1Mod == card3Mod && card4Mod == card5Mod) || (card1Mod == card2Mod && card3Mod == card5Mod))
                {
                    return 7;
                }
                //Both failed, must be three only
                else
                {
                    return 4;
                }
            }
            //Nothing three or higher, now checking for two pair
            else if ((card1Mod == card2Mod && card3Mod == card4Mod) || (card2Mod == card3Mod && card4Mod == card5Mod) || (card1Mod == card2Mod && card4Mod == card5Mod))
            {
                //Both Four and Full House would qualify, but they were already returned, so this won't matter
                return 3;
            }
            //Nothing higher than a pair, now checking for jacks or better
            else if ((card1Mod == card2Mod && card1Mod >= 9) || (card2Mod == card3Mod && card2Mod >= 9) || (card3Mod == card4Mod && card3Mod >= 9) || (card4Mod == card5Mod && card4Mod >= 9)) //9 = Jack
            {
                return 2;
            }
            //Pair lower than jacks
            else 
            {
                return 1;
            }
        }
        //No pairs, now checking for straight
        else if (card2Mod - card1Mod == 1 && card3Mod - card2Mod == 1 && card4Mod - card3Mod == 1 && (card5Mod - card4Mod == 1 || (card5Mod == 12 && card4Mod == 3))) //This last bit represents A-2-3-4-5
        {
            //Straight succeeded, now checking for straight flush
            if (card1Div == card2Div && card2Div == card3Div && card3Div == card4Div && card4Div == card5Div)
            {
                //Straight flush succeeded, now checking for royal flush
                if (card1Mod == 8) //I.e. card1Mod is a 10
                {
                    //Woop!
                    return 10;
                }
                else
                {
                    //Still decent
                    return 9;
                }
            }
            else
            {
                //No flush, just a straight
                return 5;
            }
        }
        //No pair or straight, now checking for flush alone
        else if (card1Div == card2Div && card2Div == card3Div && card3Div == card4Div && card4Div == card5Div)
        {
            return 6;
        }
        //Nothing
        else
        {
            return 0;
        }
    }
}