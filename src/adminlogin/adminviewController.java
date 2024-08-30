package adminlogin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class adminviewController {

	 Connection con;
	    PreparedStatement pst;
	    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtpwd;

    @FXML
    void dologin(ActionEvent event)
    {
     // System.out.println("kl");
    	
    	//String pwd="harekrishna";
    	//String password = String.valueOf(txtpwd.getPassword());
    	if(txtpwd.getText().equals("harekrishna"))
    	{
    		
    		try{
        		Parent root=FXMLLoader.load(getClass().getResource("/admindesk/admindeskview.fxml")); 
    			Scene scene = new Scene(root);
    			Stage stage=new Stage();
    			stage.setScene(scene);
    			stage.show();
        		
        		
    			//to hide the opened window
    			 
    			/*Scene scene1=(Scene)lblResp.getScene();
    			   scene1.getWindow().hide();
    			 */

    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
        	
    		
        	
    	}
    	else
		{
			System.out.println("incorrect");
		}
    
    	
    	
    }

    @FXML
    void initialize() 
    {
        
    	
    	con=papermaster.mysqlConnector.doConnect();
    	if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected");
    }

}
