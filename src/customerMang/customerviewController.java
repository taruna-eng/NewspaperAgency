package customerMang;

import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class customerviewController {

	
	  Connection con;
	    PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> availarea;

    @FXML
    private TextField caddress;

    @FXML
    private TextField cemail;

    @FXML
    private TextField corrhawker;
    
   
    
    @FXML
    private DatePicker dos;
    
    @FXML
    private Label info;

    @FXML
    private ListView<String> lstavapaper;

    @FXML
    private ListView<String> lstprice;

    @FXML
    private ListView<String> lstselpaper;

    @FXML
    private ListView<String> lstselprice;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtname;

    
    //===========================================================================================
    @FXML
    void dodelete(ActionEvent event) 
    {
      
    	try{
        	String mobb= txtmobile.getText();
        	pst=con.prepareStatement("delete from customer where mobile=?");
        	pst.setString(1, mobb);
        		int count=pst.executeUpdate();
        	if(count!=0)
        		info.setText(count+ " Records Deleted");
        	else
        		info.setText("Invalid ID");
        	}
        	catch(Exception exp)
        	{
        		System.out.println(exp.toString());
        	}
    	
    }
    
    
    @FXML
    void docorrhawker(ActionEvent event) 
    {
    	String area=availarea.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select hname from hawkers where alloareas like ?");
			pst.setString(1,"%"+area+"%");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				corrhawker.setText(table.getString(1));
			}
				

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void dosave(ActionEvent event)
    {
    	
    	
    	String sitems="";
    	String sprice="";
    	
    	
    	if (lstselpaper.getItems().size() >= 1) {
    	    sitems+=lstselpaper.getItems().get(0);
    	}

    	// note that i starts at 1, since we already printed the element at index 0
    	for (int i = 1; i < lstselpaper.getItems().size();i++) { 
    	     //System.out.print(", " + arrayListWords[i]);
    		sitems+=",";
    	     sitems+=lstselpaper.getItems().get(i);
    	}
    	
    	//========================================================================
    	if (lstselprice.getItems().size() >= 1) {
    	    sprice+=lstselprice.getItems().get(0);
    	}

    	// note that i starts at 1, since we already printed the element at index 0
    	for (int i = 1; i < lstselprice.getItems().size();i++) { 
    	     //System.out.print(", " + arrayListWords[i]);
    		sprice+=",";
    	     sprice+=lstselprice.getItems().get(i);
    	}
    	System.out.println(sprice);
    	
    	
    	
    	
    	
    	LocalDate ld= dos.getValue();
    	java.sql.Date dt=java.sql.Date.valueOf(ld);
    															//in parameters
    	try {
				pst=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");
				pst.setString(1,txtmobile.getText());
				pst.setString(2,txtname.getText());
				//pst.setArray(3, (Array) sitems);
				pst.setString(3,sitems);
				pst.setString(4,sprice);
				pst.setString(5,availarea.getSelectionModel().getSelectedItem() );
				pst.setString(6,corrhawker.getText());
				pst.setString(7,cemail.getText() );
				pst.setString(8, caddress.getText());
				pst.setDate(9, dt);
				pst.executeUpdate();
				info.setText("Saved........");
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}
    	
    }

    @FXML
    void dosearch(ActionEvent event) 
    {

    	
    	try{
			pst=con.prepareStatement("select * from customer where mobile=?");
			String mobb=txtmobile.getText();
			pst.setString(1,mobb);
			
			
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String namee=table.getString("name");
			String selectedpapers=table.getString("spapers");
			String selectedprice=table.getString("sprices");
			//String areaa=table.getString("area");
			String hawkerr=table.getString("hawker");
			String maill=table.getString("email");
			String addrr=table.getString("address");
			java.sql.Date dobb= table.getDate("dos");
			String []spapers=selectedpapers.split(",");
			for(int i=0;i<spapers.length;i++)
			{
				lstselpaper.getItems().add(spapers[i]);
			}
			String []sprices=selectedprice.split(",");
			for(int i=0;i<sprices.length;i++)
			{
				lstselprice.getItems().add(sprices[i]);
			}
			txtname.setText(namee);
			//lstselpaper.getItems().addAll(selectedpapers);
			//lstselprice.getItems().addAll(selectedprice);
			corrhawker.setText(hawkerr);
			txtname.setText(namee);
			cemail.setText(maill);
			caddress.setText(addrr);
			
						
			dos.setValue(dobb.toLocalDate());
			
			
			
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    	
    	
    	
    }
//=========================================================================================
    @FXML
    void doupdate(ActionEvent event) 
    {

    	String sitems="";
    	String sprice="";
    	
    	
    	if (lstselpaper.getItems().size() >= 1) {
    	    sitems+=lstselpaper.getItems().get(0);
    	}

    	// note that i starts at 1, since we already printed the element at index 0
    	for (int i = 1; i < lstselpaper.getItems().size();i++) { 
    	     //System.out.print(", " + arrayListWords[i]);
    		sitems+=",";
    	     sitems+=lstselpaper.getItems().get(i);
    	}
    	
    	//========================================================================
    	if (lstselprice.getItems().size() >= 1) {
    	    sprice+=lstselprice.getItems().get(0);
    	}

    	// note that i starts at 1, since we already printed the element at index 0
    	for (int i = 1; i < lstselprice.getItems().size();i++) { 
    	     //System.out.print(", " + arrayListWords[i]);
    		sprice+=",";
    	     sprice+=lstselprice.getItems().get(i);
    	}
    	
    
    	LocalDate ld= dos.getValue();
    	java.sql.Date dt=java.sql.Date.valueOf(ld);
    															//in parameters
    	try {
				pst=con.prepareStatement("update customer set name=?,spapers=?,sprices=?,area=?,hawker=?,email=?,address=?,dos=? where mobile=?");
				pst.setString(9,txtmobile.getText());
				pst.setString(1,txtname.getText());
				//pst.setArray(3, (Array) sitems);
				pst.setString(2,sitems);
				pst.setString(3,sprice);
				pst.setString(4,availarea.getSelectionModel().getSelectedItem() );
				pst.setString(5,corrhawker.getText());
				pst.setString(6,cemail.getText() );
				pst.setString(7, caddress.getText());
				pst.setDate(8, dt);
				pst.executeUpdate();
				info.setText(" record updated.......");
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}
    	
    	
    	
    }
    
    //==================================================================================
    @FXML
    void dochoose(MouseEvent event)
    {


    	String selected = null;
        	if(event.getClickCount()==2)
        	{
        		selected=lstavapaper.getSelectionModel().getSelectedItem();
        		lstselpaper.getItems().addAll(selected);
        		
        		int i=lstavapaper.getSelectionModel().getSelectedIndex();
        		lstselprice.getItems().add(lstprice.getItems().get(i));
        	}
    	}
    	
//=================================================================================================
    @FXML
    void initialize() {
    	
    	
    	 
       
    	con=mysqlconnector.doConnect();
    	if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected");

    	
    	ArrayList<String> items=new ArrayList<String>(Arrays.asList("Select","ghore wala chownk","Bibi wala road","Ajit road","shahid bhagat singh road","Power house road"));
	    availarea.getItems().addAll(items);
    	
    	try{
			pst=con.prepareStatement("select * from paperlist");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			
			String papers=table.getString("paper");
			String prices=table.getString("price");
			
			lstavapaper.getItems().addAll(papers);
			lstprice.getItems().addAll(prices);
			
			
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    	

    }
    
    
   

}
