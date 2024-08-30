package papermaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class masterviewController {

	  
    Connection con;
    PreparedStatement pst;

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> papern;
    
    @FXML
    private Label info;

    @FXML
    private TextField price;
  
    @FXML
    void dodelete(ActionEvent event) {
    	String paper=papern.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("delete from paperlist where paper=?");
			
			pst.setString(1,paper);
    		int count=pst.executeUpdate();
    	if(count!=0)
    		info.setText(count+ " Records Deleted");
    	else
    		info.setText("Invalid ID");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }

    
    
    void doFillpapers()
    {
    	try{
			pst=con.prepareStatement("select distinct paper from paperlist");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String paperr=table.getString("paper");
					System.out.println(paperr);
			papern.getItems().add(String.valueOf(paperr));
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }
    @FXML
    void dosave(ActionEvent event) {

    
    String paper=papern.getSelectionModel().getSelectedItem();
    float pricee=Float.parseFloat(price.getText());
    	
	try {
		pst=con.prepareStatement("insert into paperlist values(?,?)");
		pst.setString(1, paper);
		pst.setFloat(2,pricee);
		
		pst.executeUpdate();
		info.setText("Record Saved........");
		
	} 
catch (SQLException e) 
	{
	  e.printStackTrace();
	}
    	
    }

    @FXML
    void dosearch(ActionEvent event) {

    	try{
			pst=con.prepareStatement("select * from paperlist where paper=?");
			String paper=papern.getSelectionModel().getSelectedItem();
			pst.setString(1, paper);
			
			
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			float pp=table.getFloat("price");
			price.setText(String.valueOf(pp));
			
			
						
			
			//System.out.println(papern+"\t"+price+"\t");
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }

    	
    	
    

    @FXML
    void doupdate(ActionEvent event) {

    	 
        String paper=papern.getSelectionModel().getSelectedItem();
        float pricee=Float.parseFloat(price.getText());
        	
    	try {
    		pst=con.prepareStatement("update paperlist set price=? where paper=?");
    		pst.setString(2, paper);
    		pst.setFloat(1,pricee);
    		
    		pst.executeUpdate();
    		//info.setText("Record updated........");
    		int count=pst.executeUpdate();
			
			info.setText(count+" Records Updated........");
			
    		
    	} 
    catch (SQLException e) 
    	{
    	  e.printStackTrace();
    	}
    	
    	
    }

    @FXML
    void initialize() {
    	con=mysqlConnector.doConnect();
    	if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected.....");
    	
    	doFillpapers();

    	
    	
    }

}
