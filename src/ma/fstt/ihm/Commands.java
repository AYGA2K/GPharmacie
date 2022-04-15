package ma.fstt.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;



 import java.awt.event.MouseEvent;

import java.awt.event.MouseAdapter;
import java.awt.Color;
import ma.fstt.model.ClientTransaction;
import ma.fstt.model.CommandeTransaction;
import ma.fstt.model.Lign_cmd;
import ma.fstt.model.Lign_cmdTransaction;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitTransaction;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Commands extends JPanel   {

	JTable table,pTable;
    DefaultTableModel model,pModel;
	JScrollPane pane,pane2;
	JButton delButton;
	JButton editButton;
	private JTextField numero;
	
	private JTextField date;
	
	private JButton AddButton;
	
	JComboBox<String> idClient;
	
	 
	/**
	 * Launch the application.
	 */
 	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Commands() throws SQLException {
		
	 

		this.setBackground(Color.decode("#639991"));
		this.setBounds(0,200,1270,450);
		this.setLayout(null);
		JLabel Command = new JLabel("Commands");
		Command.setFont(new Font("Sans Serif",Font.PLAIN,24));
		Command. setBounds(1000, -20, 300, 100);
	
		JLabel listproduits = new JLabel("Liste des produits de la commande selectionne");
		listproduits.setFont(new Font("Sans Serif",Font.PLAIN,16));
		listproduits.setBounds(450, -20, 500, 100);
		this.add(listproduits);
		JLabel Numero = new JLabel("Numero");
		Numero.setFont(new Font("Sans Serif",Font.PLAIN,18));
		Numero.setBounds(50, 50, 200, 100);
		Border border = new LineBorder(Color.BLUE, 1, true);
		numero = new JTextField();
	    numero.setBounds(50, 120, 300, 30);
		numero.setFont(new Font("Sans Serif",Font.PLAIN,18));
		numero.setBorder(border);
		

		JLabel Date = new JLabel("Date");
		Date.setFont(new Font("Sans Serif",Font.PLAIN,18));
	    Date.setBounds(50, 120, 200, 100);
		

		 date = new JTextField();
		
		date.setFont(new Font("Sans Serif",Font.PLAIN,18));
		date.setBorder(border);
		
		date.setBounds(50,190,300,30);


		JLabel IdClient = new JLabel("Nom de Client");
		IdClient.setFont(new Font("Sans Serif",Font.PLAIN,18));
		IdClient.setBounds(50, 190, 200, 100);

		this.add(numero);
		this.add(Date);
		this.add(Command);
        this.add(Numero);
		this.add(date);
		this.add(IdClient);

		

		idClient = new JComboBox<String>();
      

		 List <ma.fstt.model.Client> lClients = new ClientTransaction().getAll();
		 for (ma.fstt.model.Client client2 : lClients) {
			 idClient.addItem(client2.getNom());
		 }
        
		
        idClient.setFont(new Font("Sans Serif",Font.PLAIN,18));
		
	    
       
		
		idClient.setBounds(50, 260, 300, 30);
		

       this.add( idClient );


	   AddButton = new JButton("Add");
	  
	   AddButton.setBackground(Color.decode("#EBAE3D"));
	  AddButton.setBounds(50,320,100,30);
       this.add(AddButton);

		


		delButton= new JButton("Delete");
		delButton.setBackground(Color.decode("#EBAE3D"));
		delButton.setBounds(260,320,100,30);
		editButton=new JButton("Edit");
		editButton.setBackground(Color.decode("#EBAE3D"));
		editButton.setBounds(155, 320, 100, 30);
		this.add(editButton);
		this.add(delButton);




		pane = new JScrollPane();
		pane.setBounds(850, 60, 400, 350);
		pane2 =  new JScrollPane();
		pane2.setBounds(450,60,350,200);
	
		table = new JTable( );
		pTable=new JTable();
		
	
		

		model = new DefaultTableModel();
	    pModel = new DefaultTableModel();
		
		 Object[] columnNames = {"ID","Numero","Date", "Nom de client"}  ;
		 Object[] PcolumnNames = {"ID","Libelle","Pull", "Id_Rayon"}  ;
		
		 
		 model.setColumnIdentifiers(columnNames);
		 for ( ma.fstt.model.Commande commande  : new  CommandeTransaction().getAll()) {
			 Object [] rows =  { commande.getId_commande(),commande.getNumero(),commande.getDate(),commande.getClient().getNom() };
			 model.addRow(rows);
		 }

		pModel.setColumnIdentifiers(PcolumnNames);
		

		 

		 table.setModel(model);
		 table.setBackground(Color.decode("#EBAE3D"));
		
		 
	
		 pane.setViewportView(table);
		
		 pTable.setModel(pModel);
		 pTable.setBackground(Color.decode("#EBAE3D"));
		 pane2.setViewportView(pTable);
		 
		 this.add(pane);
		 this.add(pane2);
		

		 
		 delButton.addActionListener(new ActionListener ()  {
             
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow =  table.getSelectedRow();
			   
				ma.fstt.model.Commande commande = new CommandeTransaction().getAll().get(selectedRow);

				CommandeTransaction commandeTransaction = new CommandeTransaction();

				commandeTransaction.delete(commande);
				 model.removeRow(selectedRow);
				 numero.setText("");
				 date.setText("");
				
				
				


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		} );
		

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
			 
				int i = table.getSelectedRow();
				numero.setText(model.getValueAt(i, 1).toString());
				date.setText(model.getValueAt(i, 2).toString());
				idClient.setSelectedItem(model.getValueAt(i, 3).toString());
              
				
				try {

					List<ma.fstt.model.Commande>lCommandes=new CommandeTransaction().getAll();
					List<ma.fstt.model.Produit>lProduits=new ProduitTransaction().getAll();

					pModel.setRowCount(0);
					for (ma.fstt.model.Lign_cmd lign_cmd : listLign_cmd() ) {
						
						
							if (lCommandes.get(i).getId_commande()==lign_cmd.getCmd().getId_commande()  ) {
								Object [] rows =  {lign_cmd.getPr().getId_Produit() ,lign_cmd.getPr().getLibelle(),lign_cmd.getPr().getPull(),lign_cmd.getPr().getrayon().getId_rayon() };
								pModel.addRow(rows);
							}
							
							
								
							
							
					}

					
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			
			   
				

			}
		  });
		  

		  editButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow =  table.getSelectedRow();
			   
				   ma.fstt.model.Commande commande = new CommandeTransaction().getAll().get(selectedRow) ;
				   commande.setNumero(numero.getText());
				   commande.setDate(date.getText());
				   List<ma.fstt.model.Client> clients = new ClientTransaction().getAll();
			        for (ma.fstt.model.Client object : clients) {
						if (object.getNom().equals(idClient.getSelectedItem().toString())) {
							commande.setClient(object);
						}
					}
				   CommandeTransaction commandeTransaction = new CommandeTransaction();
				   commandeTransaction.update(commande);
				   model.setValueAt(numero.getText(), selectedRow, 1);
				   model.setValueAt(date.getText(), selectedRow, 2);
				   model.setValueAt(idClient.getSelectedItem().toString(), selectedRow, 3);
				   numero.setText("");
				   date.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		
		AddButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
			   
				ma.fstt.model.Commande commande= new ma.fstt.model.Commande() ;
				commande.setNumero(numero.getText());
				commande.setDate(date.getText());
				List<ma.fstt.model.Client> clients = new ClientTransaction().getAll();
			        for (ma.fstt.model.Client object : clients) {
						if (object.getNom().equals(idClient.getSelectedItem().toString())) {
							commande.setClient(object);
						}
					}

				CommandeTransaction commandeTransaction = new CommandeTransaction();
			    commandeTransaction.save(commande);
				ma.fstt.model.Commande tempCommande= new ma.fstt.model.Commande();
				for (ma.fstt.model.Commande com: new CommandeTransaction().getAll()) {
					if (commande.getNumero().equals(com.getNumero() )) {
						tempCommande.setId_commande(com.getId_commande());
					}
				}
				 Object [] row =  {tempCommande.getId_commande(),commande.getNumero(),commande.getDate(),commande.getClient().getNom()};
				 model.addRow(row);
				numero.setText("");
				date.setText("");
				


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		
	}


	public  List<ma.fstt.model.Produit>  listProduit  () throws SQLException{
		List<ma.fstt.model.Produit> lProduits = new ProduitTransaction().getAll();

				return lProduits;
	}
 
	public  List<ma.fstt.model.Lign_cmd>  listLign_cmd  () throws SQLException{
	
				List<Lign_cmd> lign_cmdTransaction =  new Lign_cmdTransaction().getAll();
				return lign_cmdTransaction;
	}
  
}


		