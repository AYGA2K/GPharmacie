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

public class Produit extends JPanel   {

	JTable table;
    DefaultTableModel model;
	JScrollPane pane;
	JButton delButton;
	JButton editButton;
	private JTextField libelle;
	List<ma.fstt.model.Produit>  Rows;
	private JTextField pull;
	
	private JButton AddButton;
	JComboBox<String> idRayon;
	
	 
	/**
	 * Launch the application.
	 */
 	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Produit() throws SQLException {
		
	 

		this.setBackground(Color.decode("#639991"));
		this.setBounds(0,200,1270,500);
		this.setLayout(null);
		JLabel Produit = new JLabel("Produits");
		Produit.setFont(new Font("Sans Serif",Font.PLAIN,30));
		Produit.setBounds(860, -20, 300, 100);
		
		JLabel Libelle = new JLabel("Libelle");
		Libelle.setFont(new Font("Sans Serif",Font.PLAIN,18));
		Libelle.setBounds(100, 50, 200, 100);
		Border border = new LineBorder(Color.BLUE, 1, true);
        libelle = new JTextField();
        libelle.setBounds(100, 120, 300, 30);
		libelle.setFont(new Font("Sans Serif",Font.PLAIN,18));
		libelle.setBorder(border);
		

		JLabel Pull = new JLabel("Pull");
		Pull.setFont(new Font("Sans Serif",Font.PLAIN,18));
	    Pull.setBounds(100, 120, 200, 100);
		

		 pull = new JTextField();
		
         pull.setFont(new Font("Sans Serif",Font.PLAIN,18));
         pull.setBorder(border);
         
         pull.setBounds(100,190,300,30);

		 JLabel IdRayon = new JLabel("Id de  Rayon ");
		 IdRayon.setFont(new Font("Sans Serif",Font.PLAIN,18));
		 IdRayon.setBounds(100, 190, 200, 100);
 
		 idRayon = new JComboBox<String>();
      
		 idRayon.setBounds(100, 260, 300, 30);
		 List <ma.fstt.model.Rayon> lRayons = new RayonTransaction().getAll();
		 for (ma.fstt.model.Rayon ray : lRayons) {
			idRayon.addItem(Integer.toString(ray.getId_rayon()));
		 }

		this.add(idRayon);
		this.add(IdRayon);
		this.add(Libelle);
		this.add(libelle);
		this.add(Produit);
        this.add(Pull);
		this.add(pull);
		

		

		
       
	   AddButton = new JButton("Add");
	  
	   AddButton.setBackground(Color.decode("#EBAE3D"));
	  AddButton.setBounds(540,200,100,30);
       this.add(AddButton);

		


		delButton= new JButton("Delete");
		delButton.setBackground(Color.decode("#EBAE3D"));
		delButton.setBounds(430,200,100,30);

		editButton=new JButton("Edit");
		editButton.setBackground(Color.decode("#EBAE3D"));
		editButton.setBounds(430, 250, 100, 30);
		this.add(editButton);
		this.add(delButton);




		pane = new JScrollPane();
		pane.setBounds(700, 60, 450, 400);
		
		table = new JTable( );
	
		

		model = new DefaultTableModel();
	
		 Object[] columnNames = {"ID","Libelle","Pull", "id_Rayon"}  ;

		  
		  
		 model.setColumnIdentifiers(columnNames);
		 for ( ma.fstt.model.Produit produit  : new ProduitTransaction().getAll()) {
			 Object [] rows =  {produit.getId_Produit() ,produit.getLibelle(),produit.getPull(),produit.getrayon().getId_rayon()};
			 model.addRow(rows);
		 }

		 

		 table.setModel(model);
		 table.setBackground(Color.decode("#EBAE3D"));
		
		 pane.setViewportView(table);
	
		 this.add(pane);
		 delButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow =  table.getSelectedRow();
			   
					
				ma.fstt.model.Produit Produit = new ma.fstt.model.ProduitTransaction().getAll().get(selectedRow);

				ProduitTransaction produitTransaction= new ProduitTransaction();

				produitTransaction.delete(Produit);

				 model.removeRow(selectedRow);
				 
				 libelle.setText("");
				 pull.setText("");
				
				
				


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		} );

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
			 
				int i = table.getSelectedRow();
				libelle.setText(model.getValueAt(i, 1).toString());
				pull.setText(model.getValueAt(i, 2).toString());
				
			   
				

			}
		  });
 
		  editButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow =  table.getSelectedRow();
			   
					ma.fstt.model.Produit produit = new ma.fstt.model.ProduitTransaction().getAll().get(selectedRow);
				produit.setLibelle(libelle.getText());
				produit.setPull( Integer.parseInt(pull.getText()) );
				for (ma.fstt.model.Rayon ray : new RayonTransaction().getAll()) {
					if (ray.getId_rayon()==Integer.parseInt(idRayon.getSelectedItem().toString())) {
						produit.setrayon(ray);
					}
				}
				ProduitTransaction produitTransaction = new ProduitTransaction();
				produitTransaction.update(produit);
				
				model.setValueAt(libelle.getText(), selectedRow, 1);
				model.setValueAt(pull.getText(), selectedRow, 2);
				model.setValueAt(idRayon.getSelectedItem(), selectedRow, 3);

				model.fireTableDataChanged();

				libelle.setText("");
				pull.setText("");
				

				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		AddButton.addActionListener(new ActionListener ()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
			   
				ma.fstt.model.Produit produit = new ma.fstt.model.Produit() ;
				produit.setLibelle(libelle.getText());
				produit.setPull(Integer.parseInt(pull.getText()));
				for (ma.fstt.model.Rayon rayon : new RayonTransaction().getAll()) {
					if (rayon.getId_rayon()== Integer.parseInt(idRayon.getSelectedItem().toString()) ) {
						produit.setrayon(rayon);
					}
				}
				ProduitTransaction produitTransaction = new ProduitTransaction();
				produitTransaction.save(produit);
				ma.fstt.model.Produit tempProduit= new ma.fstt.model.Produit();
				List<ma.fstt.model.Produit> produits= new ProduitTransaction().getAll() ;

				tempProduit = produits.get(produits.size()-1);
				 Object [] row =  {tempProduit.getId_Produit(),produit.getLibelle(),produit.getPull(),produit.getrayon().getId_rayon()};
				 model.addRow(row);
				 model.fireTableDataChanged();
				 libelle.setText("");
				 pull.setText("");
				


				} catch (Exception e) {
					e.printStackTrace();
				}
			
			

			}

		});
		
		
	}


 
  
}


	