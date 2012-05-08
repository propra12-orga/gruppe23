import javax.swing.*;                                                                                                             
import java.awt.*;                                                                                                                
//Event brauchen wir fr das Ereigniss, wenn ein Button geklickt wird                                                              
import java.awt.event.*;                                                                                                          
                                                                                                                                  
public class Starter extends JFrame                                                                                               
{                                                                                                                                 
                                                                                                                                  
    private JPanel panelButton;                                                                                                   
    private JLabel oben;                                                                                                          
    private JLabel anzeige;                                                                                                       
                                                                                                                                  
    public Starter()                                                                                                              
    {                                                                                                                             
        super("BomberHulk 1.0");                                                                                                  
        setLocation(200,300);                                                                                                     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                                           
        getContentPane().setLayout(new BorderLayout(5,5));                                                                        
                                                                                                                                  
        //Buttons erzeugen                                                                                                        
        JButton[] button = new JButton[6];                                                                                        
        button[0]= new JButton("Start");                                                                                          
        button[1] = new JButton("Anleitung");                                                                                     
        button[2] = new JButton("Spielmodus");                                                                                    
        button[3] = new JButton("Editor");                                                                                        
        button[4] = new JButton("Highscore");                                                                                     
        button[5] = new JButton("Ende");                                                                                          
                                                                                                                                  
        //Panels erzeugen                                                                                                         
        panelButton = new JPanel(new GridLayout(3,1));                                                                            
                                                                                                                                  
        //Auf Panel Buttons packen                                                                                                
       for(int i = 0; i <6; i ++)                                                                                                 
        panelButton.add(button[i]);                                                                                               
                                                                                                                                  
        //Listener fÃ¼r Buttons                                                                                                   
		for(int i = 0; i <6; i ++)                                                                                                
        addButtonListener(button[i]);                                                                                             
		                                                                                                                          
        //Labels erzeugen                                                                                                         
        oben = new JLabel("Gruppe 23");                                                                                           
        //Label zentrieren                                                                                                        
        oben.setHorizontalAlignment(JLabel.CENTER);                                                                               
        anzeige = new JLabel("Bitte auswählen");                                                                                 
                                                                                                                                  
        //Labels auf Frame packen                                                                                                 
        getContentPane().add(BorderLayout.NORTH, oben);                                                                           
        getContentPane().add(anzeige);                                                                                            
                                                                                                                                  
        //Panels auf Frame packen                                                                                                 
        getContentPane().add(BorderLayout.WEST, panelButton);                                                                     
                                                                                                                                  
        pack();                                                                                                                   
        setVisible(true);                                                                                                         
                                                                                                                                  
    }                                                                                                                            
                                                                                                                                  
    public static void main(String[] args)                                                                                        
    {                                                                                                                             
        Starter g = new Starter();
        
    }                                                                                                                             
                                                                                                                                  
    private void addButtonListener(JButton b)                                                                                     
    {                                                                                                                             
        b.addActionListener(new ActionListener()                                                                                  
        {                                                                                                                         
            public void actionPerformed(ActionEvent ae)                                                                           
            {                                                                                                                     
                eingabe(ae.getActionCommand());                                                                                   
            }                                                                                                                     
        });                                                                                                                       
    }                                                                                                                             
                                                                                                                                  
    private void eingabe(String a)                                                                                                
    {                                                                                                                             
        anzeige.setText(a);                                                                                                       
    }                                                                                                                             
}                                                                                                                                 
                                                                                                                                  