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
import ma.fstt.model.ProduitTransaction;
import ma.fstt.model.RayonTransaction;

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

public class Rayon extends JPanel   {

	JTable table,pTable;
    DefaultTableModel model,pModel;
	JScrollPane pane,pane2;
	JButton delButton;
	JButton editButton;
	private JTextField numero;
	
	private JTextField nbr_etage ,type ;
	
	private JButton AddButton;
	


	/**
	 * Launch the application.
	 */
 	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Rayon() throws SQLException {
		
		this.setBackground(Color.decode("#639991"));
		this.setBounds(0,200,1270,450);
		this.setLayout(null);
		JLabel Rayon = new JLabel("Rayons");
		Rayon.setFont(new Font("Sans Serif",Font.PLAIN,30));
		Rayon.setBounds(1000, -20, 300, 100);
		JLabel listproduit = new JLabel("Liste des produits de rayon selectionne ");
		listproduit.setFont(new Font("Sans Serif",Font.PLAIN,16));
		listproduit.setBounds(460, -20, 500, 100);
        this.add(listproduit);
		JLabel Numero = new JLabel("Numero");
		Numero.setFont(new Font("Sans Serif",Font.PLAIN,18));
		Numero.setBounds(100, 50, 200, 100);
		Border border = new LineBorder(Color.BLUE, 1, true);
		 numero = new JTextField();
	     numero.setBounds(100, 120, 300, 30);
         numero.setFont(new Font("Sans Serif",Font.PLAIN,18));
         numero.setBorder(border);
		

		JLabel Nbr_Etage = new JLabel("Numero d'etage");
		Nbr_Etage.setFont(new Font("Sans Serif",Font.PLAIN,18));
	    Nbr_Etage.setBounds(100, 120, 200, 100);
		

		 nbr_etage = new JTextField();
		
         nbr_etage.setFont(new Font("Sans Serif",Font.PLAIN,18));
         nbr_etage.setBorder(border);
	
         nbr_etage.setBounds(100,190,300,30);


		JLabel Type = new JLabel("Type");
		Type.setFont(new Font("Sans Serif",Font.PLAIN,18));
		Type.setBounds(100, 190, 200, 100);

		this.add(numero);
		this.add(Numero);
		this.add(nbr_etage);
        this.add(Nbr_Etage);
		this.add(Type);
		this.add(Rayon);

		

		type = new JTextField();
      

       
		
        type.setFont(new Font("Sans Serif",Font.PLAIN,18));
	
		
		type.setBounds(100, 260, 300, 30);
		

       this.add( type );


	   AddButton = new JButton("Add");
	  
	   AddButton.setBackground(Color.decode("#EBAE3D"));
	  AddButton.setBounds(100,320,100,30);
       this.add(AddButton);

		


		delButton= new JButton("Delete");
		delButton.setBackground(Color.decode("#EBAE3D"));
		delButton.setBounds(310,320,100,30);
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
		pTable=new JTable();
	
		

		model = new DefaultTableModel();
		pModel=new DefaultTableModel();
	
		 Object[] columnNames = {"ID","Numero","Numero d'etage", "Type"}  ;
		 Object[] PcolumnNames = {"ID","Libelle","Pull",}  ;
		 
		  
		 model.setColumnIdentifiers(columnNames);
		 for ( ma.fstt.model.Rayon rayon2 : new RayonTransaction().getAll()) {
			 Object [] rows =  {rayon2.getId_rayon(),rayon2.getNumero(),rayon2.getNmbr_etage(),rayon2.getType()};
			 model.addRow(rows);
		 }

	 
		 pModel.setColumnIdentifiers(PcolumnNames);

   
		 table.setModel(model);
		 table.setBackground(Color.decode("#EBAE3D"));
		
		 pane.setViewportView(table);
		 pTable.setModel(pModel);
		 pTable.setBackground(Color.decode("#EBAE3D"));
		 pane2.setViewportView(pTable);
	
		 this.add(pane2);
		 this.add(pane);
		 delButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow =  table.getSelectedRow();
			   
				ma.fstt.model.Rayon rayon = new RayonTransaction().getAll().get(selectedRow);

				RayonTransaction rayonTransaction= new RayonTransaction();

				rayonTransaction.delete(rayon);

				 model.removeRow(selectedRow);

				 numero.setText("");
				 nbr_etage.setText("");
				 type.setText("");
				
				


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		} );

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
			 
				int i = table.getSelectedRow();
				numero.setText(model.getValueAt(i, 1).toString());
				nbr_etage.setText(model.getValueAt(i, 2).toString());
				type.setText(model.getValueAt(i,3).toString());
			   
			
			   
				
				try {
                    List<ma.fstt.model.Rayon> lRayons =  new RayonTransaction().getAll() ;
                    List<ma.fstt.model.Produit> lProduits =  new ProduitTransaction().getAll() ;

					pModel.setRowCount(0);
					for (ma.fstt.model.Produit produit : new ProduitTransaction().getAll() ) {
						
						
							if (lRayons.get(i).getId_rayon()==produit.getrayon().getId_rayon()  ) {
								Object [] rows =  {produit.getId_Produit() ,produit.getLibelle(),produit.getPull() };
								
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
			   
				ma.fstt.model.Rayon rayon = new RayonTransaction().getAll().get(selectedRow);
                rayon.setId_rayon( Integer.parseInt(model.getValueAt(selectedRow, 0).toString()) );
				rayon.setNumero( Integer.parseInt(numero.getText()));
				rayon.setNmbr_etage(Integer.parseInt(nbr_etage.getText()) );
				rayon.setType(Integer.parseInt(type.getText()));
				RayonTransaction rayonTransaction = new RayonTransaction();
				rayonTransaction.update(rayon);
				model.setValueAt(numero.getText(), selectedRow, 1);
				model.setValueAt(nbr_etage.getText(), selectedRow, 2);
				model.setValueAt(type.getText().toString(), selectedRow, 3);

				model.fireTableDataChanged();

				numero.setText("");
				nbr_etage.setText("");
				type.setText("");

				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		AddButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
			   
				ma.fstt.model.Rayon rayon = new ma.fstt.model.Rayon() ;
				rayon.setNumero(Integer.parseInt(numero.getText()) );
				rayon.setNmbr_etage(Integer.parseInt(nbr_etage.getText()));
                rayon.setType(Integer.parseInt(type.getText()));
				RayonTransaction rayonTransaction = new RayonTransaction();
				rayonTransaction.save(rayon);
				ma.fstt.model.Rayon tempRayon= new ma.fstt.model.Rayon();
				 List<ma.fstt.model.Rayon> raylits= new RayonTransaction().getAll() ;

                 tempRayon = raylits.get(raylits.size()-1);
				
				 Object [] row =  {tempRayon.getId_rayon(),rayon.getNumero(),rayon.getNmbr_etage(),rayon.getType()};
				 model.addRow(row);
				 model.fireTableDataChanged();
                 
				 numero.setText("");
				 nbr_etage.setText("");
				 type.setText("");


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		
	}


 
  
}


		