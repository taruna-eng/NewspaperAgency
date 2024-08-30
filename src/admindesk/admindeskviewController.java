package admindesk;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class admindeskviewController {

	Connection con;
    PreparedStatement pst;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void dobillcollector(ActionEvent event) 
    {


    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billcoll/billcollview.fxml")); 
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

    @FXML
    void dobillgenerator(ActionEvent event) 
    {

    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billgen/billview.fxml")); 
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

    @FXML
    void dobillstatus(ActionEvent event)
    {


    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billboard/boarddview.fxml")); 
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

    @FXML
    void docustomergoogler(ActionEvent event) 
    {



    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customerpanel/cpanelview.fxml")); 
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

    @FXML
    void docustomermaster(ActionEvent event) 
    {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customerMang/customerview.fxml")); 
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

    @FXML
    void dodisplayhawkers(ActionEvent event) 
    {


    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/table/tableView.fxml")); 
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

    @FXML
    void dohawkermanager(ActionEvent event)
    {

    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/hawkermanage/hawkerview.fxml")); 
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

    @FXML
    void domeetdeveloper(ActionEvent event) 
    {
    	
    	

    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/meetdev/develo.fxml")); 
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

    @FXML
    void dopapermaster(ActionEvent event) 
    {


    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/papermaster/masterview.fxml")); 
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
