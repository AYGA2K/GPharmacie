package ma.fstt.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import ma.fstt.model.ClientTransaction;

public class MainInterface   implements ActionListener   {

    public static JFrame frame;
    private JPanel navbar;
   
    private JButton Client;
    private JButton commndsButton;
    private JButton Produit,Rayon;
    
    private Produit produit;
    private Client client;
    private Commands command;
    private Rayon rayon;
    

  MainInterface() throws SQLException {



    command = new Commands();
    
    client = new Client();

    produit = new Produit();
    rayon = new Rayon();
    
    frame = new JFrame();
    frame.setLayout(null);
    
    frame.setSize(1280 ,720);
    
    frame.getContentPane().setBackground(Color.decode("#98CFC7"));
    navbar = new JPanel();
    
    navbar.setBackground(Color.decode("#639991"));
	  navbar.setBounds(250,50,800,50);
    navbar.setLayout(new GridLayout());

    

     
     Client= new JButton("Clients");
     Client.setBackground(Color.decode("#639991"));
     Client.addActionListener( new ActionListener() 
     {
         public void actionPerformed(ActionEvent e) 
         {
            try {
              
             SwingUtilities.updateComponentTreeUI(frame);

             
             client = new Client();
            
             if (command!=null) {
                frame.remove(command);
             }
            
           if (produit!=null) {
            frame.remove(produit);
         }
         if (rayon!=null) {
            frame.remove(rayon);
         }
        
           
             frame.add(client);
            
             
                
            } catch (Exception e1) {
              e1.printStackTrace();
            }
         }
     });
     Produit = new JButton("Produits");
     Produit.setBackground(Color.decode("#639991"));

     Produit.addActionListener  (
      new ActionListener() 
      {
          public void actionPerformed(ActionEvent e) 
          {
             try {
               
           SwingUtilities.updateComponentTreeUI(frame);
 
           produit=new Produit();
          
           
        
           if (client!=null) {
              frame.remove(client);
           }
           if (command!=null) {
             frame.remove(command);
           }
           if (rayon!=null) {
            frame.remove(rayon);
          }
              frame.add(produit);
              
              
                 
             } catch (Exception e1) {
               e1.printStackTrace();
             }
          }
      }
   );

     commndsButton= new JButton("Commands");
     commndsButton.setBackground(Color.decode("#639991"));
     commndsButton.addActionListener  (
        new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
               try {
                 
             SwingUtilities.updateComponentTreeUI(frame);
   
             command=new Commands();
            
             
          
             if (client!=null) {
                frame.remove(client);
             }
             if (produit!=null) {
               produit.remove(produit);
             }
             if (rayon!=null) {
               frame.remove(rayon);
             }
                frame.add(command);
                
                
                   
               } catch (Exception e1) {
                 e1.printStackTrace();
               }
            }
        }
     );
     Rayon= new JButton("Rayons");
     Rayon.setBackground(Color.decode("#639991"));
     Rayon.addActionListener  (
        new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
               try {
                 
             SwingUtilities.updateComponentTreeUI(frame);
   
             rayon=new Rayon();
            
             
          
             if (client!=null) {
                frame.remove(client);
             }
             if (produit!=null) {
               produit.remove(produit);
             }
             if (command!=null) {
               frame.remove(command);
             }
                frame.add(rayon);
                
                
                   
               } catch (Exception e1) {
                 e1.printStackTrace();
               }
            }
        }
     );
   
     navbar.add(Produit);
     navbar.add(Client);
    navbar.add(Rayon);
     navbar.add(commndsButton);
      frame.add(navbar);

      
     
      frame.setVisible(true);
      

      
      

  }


   
   



 
  

 

      @Override
       public void actionPerformed  (ActionEvent arg0)  {
       
            
           }

           public static void main(String[] args) throws SQLException {
            MainInterface mainInterface = new MainInterface();
            
        }
    }

	

