package table;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class tableViewController {
	
	Connection con;
    PreparedStatement pst;

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Label info;

    @FXML
    private URL location;

    @FXML
    private TableView<HawkerBean> tabledata;

    @FXML
    void dofetch(ActionEvent event) 
    {
    	TableColumn<HawkerBean, String> name=new TableColumn<HawkerBean, String>("Hawker Name");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("hname")); //name of column 
    	//name.setMinWidth(150);
    	
    	TableColumn<HawkerBean, String> mobile=new TableColumn<HawkerBean, String>("Hawker Mobile No");//any thing
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> alloarea=new TableColumn<HawkerBean, String>("Allocated Areas");//any thing
    	alloarea.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	alloarea.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> doj=new TableColumn<HawkerBean, String>("Date of joining");//any thing
    	doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	doj.setMinWidth(50);
    	
    	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(name,mobile,alloarea,doj)));
    	tabledata.setItems(FetchAllHawkers());
    	
    	info.setText("Records Fetched Successfully...");
    	
    }

    
    ObservableList<HawkerBean> FetchAllHawkers()
    {
    	ObservableList<HawkerBean> ary=FXCollections.observableArrayList();
    	
    	try{
			pst=con.prepareStatement("select * from hawkers");
			ResultSet table=pst.executeQuery();
			
			
		
		while(table.next())
		{
			String mno=table.getString("mobile");
    		String name = table.getString("hname");
    		String DOJ = String.valueOf(table.getDate("doj").toLocalDate());
    		String alloarea=table.getString("alloareas");
    		HawkerBean ref=new HawkerBean(name, mno, alloarea, DOJ);
    		ary.add(ref);
    		
    		
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    	
    	
    	return ary;
    	
    }
    @FXML
    void initialize() {
    	con = papermaster.mysqlConnector.doConnect();
    	if(con==null) { System.out.println("Invalid Connection"); }
    	else { System.out.println("Connected");	}

    }

}
