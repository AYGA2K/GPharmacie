package ma.fstt.ihm;
import ma.fstt.model.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;



public class FinalTableModel extends AbstractTableModel{
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ma.fstt.model.Client> li = new ArrayList<>();
	
	    private String[] columnNames = {"ID","Nom","EMAIL", "GENRE"}  ;
	    
	    
	    

	public FinalTableModel(List<ma.fstt.model.Client> li  ) {
			
			this.li = li;
			
		}



	@Override
	public int getColumnCount() {
		
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 ma.fstt.model.Client si = this.li.get(rowIndex);
		 
		 switch (columnIndex) {
         case 0: 
             return si.getId_client();
         case 1:
             return si.getNom();
         case 2:
             return si.getEmail();
         case 3:
             return si.getGenre();
        }
		 
		return null;
	}

	

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		if (getValueAt(0, columnIndex)!=null ) {
			return getValueAt(0, columnIndex).getClass();
		}
		else{
			return Object.class; 
		}
		  

	}



	@Override
	public int getRowCount() {
		
		return li.size();
	}

  

	


}
