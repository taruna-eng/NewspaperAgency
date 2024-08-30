package billcoll;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class billcollviewController {

	 Connection con;
	    PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datefrom;

    @FXML
    private DatePicker dateto;

    @FXML
    private Label lblmess;

    @FXML
    private TextField txtamount;

    @FXML
    private TextField txtmobile;

    @FXML
    void dobilldetail(ActionEvent event) 
    {

    	try {
			pst=con.prepareStatement("select * from bills where mobile=?");
			String mobb=txtmobile.getText();
			pst.setString(1, mobb);
			
			
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String amount=table.getString("bill");
			
			java.sql.Date dof= table.getDate("datefrom");
			java.sql.Date doto= table.getDate("dateto");
			
			
			txtamount.setText(amount);
			
			datefrom.setValue(dof.toLocalDate());
			dateto.setValue(doto.toLocalDate());
			
			
			
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		
    }

    @FXML
    void dopayment(ActionEvent event)
    {


    														//in parameters
    	try {
				pst=con.prepareStatement("update bills  set billstatus=1 where mobile=?");
				String mobb=txtmobile.getText();
				pst.setString(1, mobb);
				
				
				int count=pst.executeUpdate();
				
				lblmess.setText(count+" bill paid");
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}
    	
    }

    @FXML
    void initialize() 
    {
    	con=mysqlconnector.doConnect();
    	if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected");


    }

}
