package hawkermanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class hawkerviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboareas;

    @FXML
    private ComboBox<String> comboname;

    @FXML
    private Label picpathh;

    @FXML
    private ImageView picprev;

    @FXML
    private TextField txtaddr;

    @FXML
    private TextField txtalloareas;
    
    @FXML
    private Label info;

    @FXML
    private TextField txtmobile;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doclear(ActionEvent event) throws FileNotFoundException {

      
    	
    	
    	txtmobile.setText("");
    	picpathh.setText("");
		txtaddr.setText("");
		txtalloareas.setText("");
		picprev.setImage(new Image(new FileInputStream("@../../../project/public/pics.1/user1.jpg")));
    	
    	
    	
    }
    
    @FXML
    void dosearch(ActionEvent event) {

    	try{
			pst=con.prepareStatement("select * from hawkers where hname=?");
			String sname=comboname.getSelectionModel().getSelectedItem();
			pst.setString(1,sname);
			
			
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String mobilee=table.getString("mobile");  //type table wali values!!!
			String addd=table.getString("address"); 
			String aloo=table.getString("alloareas"); 
			String picPath= table.getString("picpath");
			
			txtmobile.setText(mobilee);
			txtaddr.setText(addd);
			txtalloareas.setText(aloo);
			
			picpathh.setText(picPath);
			picprev.setImage(new Image(new FileInputStream(picPath)));
						
			
			System.out.println(sname+"\t"+mobilee+"\t"+addd+"\t"+aloo +"\t"+picPath);
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    	
    	
    	
    	
    }

    @FXML
    void dodelete(ActionEvent event) {


    	try{
    		String sname=comboname.getSelectionModel().getSelectedItem();
    	pst=con.prepareStatement("delete from hawkers where hname=?");
    	pst.setString(1,sname);
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
    void dosave(ActionEvent event) {

    	String sname=comboname.getSelectionModel().getSelectedItem();
    	String mob=txtmobile.getText();
    	String addr=txtaddr.getText();
    	//String availareas=comboareas.getSelectionModel().getSelectedItem();
    	String allocated=txtalloareas.getText();
    	String path=picpathh.getText();
    	
    															
    	try {
				pst=con.prepareStatement("insert into hawkers values(?,?,?,?,?,CURRENT_DATE)");
				pst.setString(1,sname);
				pst.setString(2,mob);
				pst.setString(3,addr);
				pst.setString(4,allocated);
				pst.setString(5,path);
				pst.executeUpdate();
				info.setText("Record Saved........");
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}
    		
    }
    	
    void doFillnames()
    {
    	try{
			pst=con.prepareStatement("select distinct hname from hawkers");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String hhname=table.getString("hname");//use table wale col ka name
			System.out.println(hhname);
			comboname.getItems().add(String.valueOf(hhname));
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }
  	
  
    @FXML
    void doselpic(ActionEvent event) throws FileNotFoundException {

    	
    	
    	FileChooser fileChooser = new FileChooser();
   	 fileChooser.setTitle("Open Resource File");
   	 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.JFIF"));
   	 File selectedFile = fileChooser.showOpenDialog(null);
   	 
   	 if (selectedFile != null) {
   	    picpathh.setText(selectedFile.getPath());
   	    Image img=new Image(selectedFile.toURI().toString());
   	    System.out.println(selectedFile.toURI().toString());
   	    picprev.setImage(new Image(new FileInputStream(selectedFile)));
   	    //picPrev.setImage(img);
   	 }
   	 else
   	 {
   		 picpathh.setText("nopic.jpg");
   	 }
    }

    @FXML
    void doupdate(ActionEvent event) {

    	

    	String sname=comboname.getSelectionModel().getSelectedItem();
    	String mob=txtmobile.getText();
    	String addr=txtaddr.getText();
    	//String availareas=comboareas.getSelectionModel().getSelectedItem();
    
        
    	String allocated=txtalloareas.getText();
    	String path=picpathh.getText();
    	
    															
    	try {
				pst=con.prepareStatement("update hawkers set mobile=?,address=?,alloareas=?,picpath=? where hname=?");
				pst.setString(5,sname);
				pst.setString(1,mob);
				pst.setString(2,addr);
				pst.setString(3,allocated);
				pst.setString(4,path);
				pst.executeUpdate();
				//info.setText("Record updated.......");
                  int count=pst.executeUpdate();
				
				info.setText(count+" Records Updated........");
				
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}
    	
    }
    
    
    @FXML
    void doshowareas(ActionEvent event) 
    {
    	String area=comboareas.getSelectionModel().getSelectedItem()+",";
        
    	if(comboareas.getSelectionModel().getSelectedItem()!=null)
    	{	
    		String concated=area.concat(txtalloareas.getText());
    		if(concated.endsWith(","))
    		{
    		concated=concated.substring(0, concated.length()-1);
    		System.out.println(concated);
    		}
    		txtalloareas.setText(concated);
    	}

    	
    	
    }


    @FXML
    void initialize() {
       
    	ArrayList<String> items=new ArrayList<String>(Arrays.asList("Select","ghore wala chownk","Bibi wala road","Ajit road","shahid bhagat singh road","Power house road"));
        comboareas.getItems().addAll(items);
//     String assignareas=   comboareas.getSelectionModel().getSelectedItem();
//     txtalloareas.setText(assignareas);
        
    	
    	picpathh.setText("nopic.jpg");
    	con=mysqlconnector.doConnect();
    	if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected");
    	
    	doFillnames();

    }

}
