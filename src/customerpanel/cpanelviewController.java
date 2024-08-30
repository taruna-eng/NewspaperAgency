package customerpanel;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class cpanelviewController {

	 Connection con;
	    PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private ComboBox<String> combopapers;

    @FXML
    private TableView<CustomerBean> tabledata;

    @FXML
    void doFilter(ActionEvent event)
    {
    	tabledata.getColumns().clear();
         
    	TableColumn<CustomerBean, String> mob=new TableColumn<CustomerBean, String>("Mobile");//any thing
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile")); //name of column  in database
    	//name.setMinWidth(150);
    	
    	TableColumn<CustomerBean, String> naam=new TableColumn<CustomerBean, String>("Name");//any thing
    	naam.setCellValueFactory(new PropertyValueFactory<>("name"));
    	naam.setMinWidth(50);
    	
    	TableColumn<CustomerBean, String> paper=new TableColumn<CustomerBean, String>("papers");//any thing
    	paper.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	paper.setMinWidth(50);
    	
    	TableColumn<CustomerBean, String> area=new TableColumn<CustomerBean, String>("area");//any thing
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));
    	area.setMinWidth(50);
    	

    	TableColumn<CustomerBean, String> addr=new TableColumn<CustomerBean, String>("Address");//any thing
    	addr.setCellValueFactory(new PropertyValueFactory<>("address"));
    	addr.setMinWidth(50);
    	

    	TableColumn<CustomerBean, String> doj=new TableColumn<CustomerBean, String>("Date of start");//any thing
    	doj.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	doj.setMinWidth(50);
    	
    	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(mob, naam, paper, area,addr,doj)));
    	tabledata.setItems(FetchAllCustomers());
    	
    	//lblResp.setText("Records Fetched Successfully...");
    	
    	
    }

    @FXML
    void dofetch(ActionEvent event) 
    {
    	tabledata.getColumns().clear();
    	TableColumn<CustomerBean, String> mob=new TableColumn<CustomerBean, String>("Mobile");//any thing
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile")); //name of column  in database
    	//name.setMinWidth(150);
    	
    	TableColumn<CustomerBean, String> naam=new TableColumn<CustomerBean, String>("Name");//any thing
    	naam.setCellValueFactory(new PropertyValueFactory<>("name"));
    	naam.setMinWidth(50);
    	
    	TableColumn<CustomerBean, String> paper=new TableColumn<CustomerBean, String>("papers");//any thing
    	paper.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	paper.setMinWidth(50);
    	
    	TableColumn<CustomerBean, String> area=new TableColumn<CustomerBean, String>("area");//any thing
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));
    	area.setMinWidth(50);
    	

    	TableColumn<CustomerBean, String> addr=new TableColumn<CustomerBean, String>("Address");//any thing
    	addr.setCellValueFactory(new PropertyValueFactory<>("address"));
    	addr.setMinWidth(50);
    	

    	TableColumn<CustomerBean, String> doj=new TableColumn<CustomerBean, String>("Date of start");//any thing
    	doj.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	doj.setMinWidth(50);
    	
    	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(mob, naam, paper, area,addr,doj)));
    	tabledata.setItems(FetchCustomer());
    	
    	//lblResp.setText("Records Fetched Successfully...");
    	
    }
    
    void doFillRolls()
    {
    	try{
			pst=con.prepareStatement("select distinct paper from paperlist");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String paper=table.getString("paper");//use table wale col ka name
			
			combopapers.getItems().add(paper);
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }
    
    
    
    
    
    ObservableList<CustomerBean> FetchAllCustomers() 
    {
    	ObservableList<CustomerBean>	ary=FXCollections.observableArrayList();
    	try {
    	   	
    		pst = con.prepareStatement("select * from customer where area=?");
    		String areaa=comboarea.getSelectionModel().getSelectedItem();
    		pst.setString(1, areaa);
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("name");
	    		String DOJ = String.valueOf(table.getDate("dos").toLocalDate());
	    		String areas=table.getString("area");
	    		String add=table.getString("address");
	    		String papers=table.getString("spapers");
	    		
	    		CustomerBean ref=new CustomerBean(mno, name,papers,areas,add , DOJ);
	    		ary.add(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
    
    
    
    ObservableList<CustomerBean> FetchCustomer() 
    {
    	ObservableList<CustomerBean>	ary=FXCollections.observableArrayList();
    	try {
    	   	
    		pst = con.prepareStatement("select * from customer where spapers like ?");
    		String paperr=combopapers.getSelectionModel().getSelectedItem();
    		pst.setString(1,"%"+paperr+"%");
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("name");
	    		String DOJ = String.valueOf(table.getDate("dos").toLocalDate());
	    		String areas=table.getString("area");
	    		String add=table.getString("address");
	    		String papers=table.getString("spapers");
	    		
	    		CustomerBean ref=new CustomerBean(mno, name,papers,areas,add , DOJ);
	    		ary.add(ref);
	    		
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
    
    
    
    

    @FXML
    void initialize() {
    	con=papermaster.mysqlConnector.doConnect();
    	if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected");
    	
    	ArrayList<String> items=new ArrayList<String>(Arrays.asList("Select","ghore wala chownk","Bibi wala road","Ajit road","shahid bhagat singh road","Power house road"));
        comboarea.getItems().addAll(items);
        
        doFillRolls();
    }

}
