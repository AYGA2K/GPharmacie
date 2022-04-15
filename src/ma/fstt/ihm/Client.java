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

public class Client extends JPanel   {

	JTable table,cTable;
    DefaultTableModel model,cModel;
	JScrollPane pane,pane2;
	JButton delButton;
	JButton editButton;
	private JTextField nom;
	
	private JTextField email;
	
	private JButton AddButton;
	
	JComboBox<String> genre;
	
	/**
	 * Launch the application.
	 */
 	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Client() throws SQLException {
		
	 

		this.setBackground(Color.decode("#639991"));
		this.setBounds(0,200,1270,450);
		this.setLayout(null);
		JLabel Client = new JLabel("Clients");
		Client.setFont(new Font("Sans Serif",Font.PLAIN,30));
		Client.setBounds(1000, -20, 300, 100);
		JLabel listcommand = new JLabel("Liste des commande de la client selectionne");
		listcommand.setFont(new Font("Sans Serif",Font.PLAIN,16));
		listcommand.setBounds(460, -20, 500, 100);
		this.add(listcommand);
		JLabel Nom = new JLabel("Nom");
		Nom.setFont(new Font("Sans Serif",Font.PLAIN,18));
		Nom.setBounds(100, 50, 200, 100);
		Border border = new LineBorder(Color.BLUE, 1, true);
		 nom = new JTextField();
	     nom.setBounds(100, 120, 300, 30);
		nom.setFont(new Font("Sans Serif",Font.PLAIN,18));
		nom.setBorder(border);
		

		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Sans Serif",Font.PLAIN,18));
	    Email.setBounds(100, 120, 200, 100);
		

		 email = new JTextField();
		
		email.setFont(new Font("Sans Serif",Font.PLAIN,18));
		email.setBorder(border);
	
		email.setBounds(100,190,300,30);


		JLabel Genre = new JLabel("Genre");
		Genre.setFont(new Font("Sans Serif",Font.PLAIN,18));
		Genre.setBounds(100, 190, 200, 100);

		this.add(nom);
		this.add(Email);
		this.add(Client);
        this.add(Nom);
		this.add(email);
		this.add(Genre);

		

		genre = new JComboBox<String>();
      

        genre.addItem( "M" );
        genre.addItem( "F" );
		
        genre.setFont(new Font("Sans Serif",Font.PLAIN,18));
	
		
		genre.setBounds(100, 260, 300, 30);
		

       this.add( genre );


	   AddButton = new JButton("Add");
	  
	   AddButton.setBackground(Color.decode("#EBAE3D"));
	  AddButton.setBounds(100,320,100,30);
       this.add(AddButton);

		


		delButton= new JButton("Delete");
		delButton.setBounds(310,320,100,30);
		delButton.setBackground(Color.decode("#EBAE3D"));
		editButton=new JButton("Edit");
		editButton.setBackground(Color.decode("#EBAE3D"));
		editButton.setBounds(205, 320, 100, 30);
		this.add(editButton);
		this.add(delButton);




		pane = new JScrollPane();
		pane.setBounds(860, 60, 400, 350);
		pane2 =  new JScrollPane();
		pane2.setBounds(440,60,400,200);
		table = new JTable( );
		cTable=new JTable();
	
		

		model = new DefaultTableModel();
		cModel=new DefaultTableModel();
	
		 Object[] columnNames = {"ID","Nom","EMAIL", "GENRE"}  ;
		 Object[] CcolumnNames = {"ID","Numero","Date",}  ;
		
		 model.setColumnIdentifiers(columnNames);
		 for ( ma.fstt.model.Client client  : new ClientTransaction().getAll()) {
			 Object [] rows =  {client.getId_client(),client.getNom(),client.getEmail(),client.getGenre()};
			 model.addRow(rows);
		 }

	 
		 cModel.setColumnIdentifiers(CcolumnNames);

   
		 table.setModel(model);
		 table.setBackground(Color.decode("#EBAE3D"));
		
		 pane.setViewportView(table);
		 cTable.setModel(cModel);
		 cTable.setBackground(Color.decode("#EBAE3D"));
		 pane2.setViewportView(cTable);
	
		 this.add(pane2);
		 this.add(pane);
		 delButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow =  table.getSelectedRow();
			   
				ma.fstt.model.Client client = new ClientTransaction().getAll().get(selectedRow);

				ClientTransaction clientTransaction = new ClientTransaction();

				clientTransaction.delete(client);

				 model.removeRow(selectedRow);

				 nom.setText("");
				 email.setText("");
				 genre.setSelectedItem("M");
				
				


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		} );

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
			 
				int i = table.getSelectedRow();
				nom.setText(model.getValueAt(i, 1).toString());
				email.setText(model.getValueAt(i, 2).toString());
				genre.setSelectedItem(model.getValueAt(i, 3).toString());
			   
			
			
				
			   
				
				try {
					List<ma.fstt.model.Client> lClients =  new ClientTransaction().getAll() ;
                    List<ma.fstt.model.Commande> lCommandes =  new CommandeTransaction().getAll() ;
					cModel.setRowCount(0);
					for (ma.fstt.model.Commande commande : new CommandeTransaction().getAll() ) {
						
						
							if (lClients.get(i).getId_client()==commande.getClient().getId_client()  ) {
								Object [] rows =  {commande.getId_commande() ,commande.getNumero(),commande.getDate() };
								
								cModel.addRow(rows);
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
			   
				ma.fstt.model.Client client = new ma.fstt.model.ClientTransaction().getAll().get(selectedRow);
				client.setNom(nom.getText());
				client.setEmail(email.getText());
				client.setGenre(genre.getSelectedItem().toString());
				ClientTransaction clientTransaction = new ClientTransaction();
				clientTransaction.update(client);
				model.setValueAt(nom.getText(), selectedRow, 1);
				model.setValueAt(email.getText(), selectedRow, 2);
				model.setValueAt(genre.getSelectedItem().toString(), selectedRow, 3);

				model.fireTableDataChanged();

				nom.setText("");
				email.setText("");
				genre.setSelectedItem("M");

				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		AddButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
			   
				ma.fstt.model.Client client = new ma.fstt.model.Client() ;
				client.setNom(nom.getText());
				client.setEmail(email.getText());
				client.setGenre(genre.getSelectedItem().toString());
				ClientTransaction clientTransaction = new ClientTransaction();
				clientTransaction.save(client);
				ma.fstt.model.Client tempClient= new ma.fstt.model.Client();
				for (ma.fstt.model.Client cl : new ClientTransaction().getAll()) {
					if (client.getNom().equals(cl.getNom())) {
						tempClient.setId_client(cl.getId_client());
					}
				}
				 Object [] row =  {tempClient.getId_client(),client.getNom(),client.getEmail(),client.getGenre()};
				 model.addRow(row);
				 model.fireTableDataChanged();
				 nom.setText("");
				 email.setText("");
				 genre.setSelectedItem("M");


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		
	}


 
  
}


		