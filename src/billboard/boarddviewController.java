package billboard;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class boarddviewController {

	 
    Connection con;
    PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton radpaid;

    @FXML
    private RadioButton radpending;

    @FXML
    private TableView<BillBean> tabledata;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txttotal;

    @FXML
    private ToggleGroup yahu;

    @FXML
    void dobillhistory(ActionEvent event) 
    {
          
    	
    	tabledata.getColumns().clear();
		TableColumn<BillBean, String> mob=new TableColumn<BillBean, String>("Mobile");//any thing
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile")); //name of column 
    	//name.setMinWidth(150);
    	
    	TableColumn<BillBean, String> dof=new TableColumn<BillBean, String>("Date from");//any thing
    	dof.setCellValueFactory(new PropertyValueFactory<>("datefrom"));
    	dof.setMinWidth(50);
    	
    	TableColumn<BillBean, String> dto=new TableColumn<BillBean, String>("Date To");//any thing
    	dto.setCellValueFactory(new PropertyValueFactory<>("dateto"));
    	dto.setMinWidth(50);
    	
    	TableColumn<BillBean, String> bill=new TableColumn<BillBean, String>("Bill");//any thing
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
    	bill.setMinWidth(50);
    	

    	TableColumn<BillBean, String> bstatus=new TableColumn<BillBean, String>("Billstatus");//any thing
    	bstatus.setCellValueFactory(new PropertyValueFactory<>("billstatus"));
    	bstatus.setMinWidth(50);
    	
    	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(mob, dof, dto, bill,bstatus)));
    	tabledata.setItems(FetchAllCustome());
    	
    	
    	
    	
    	
    	
    }

    @FXML
    void doexport(ActionEvent event) 
    {

    }
    
    
    @FXML
    void dosearch(ActionEvent event)
    {

    	if(radpending.isSelected()==true)
    	{
    		tabledata.getColumns().clear();
    		TableColumn<BillBean, String> mob=new TableColumn<BillBean, String>("Mobile");//any thing
        	mob.setCellValueFactory(new PropertyValueFactory<>("mobile")); //name of column 
        	//name.setMinWidth(150);
        	
        	TableColumn<BillBean, String> dof=new TableColumn<BillBean, String>("Date from");//any thing
        	dof.setCellValueFactory(new PropertyValueFactory<>("datefrom"));
        	dof.setMinWidth(50);
        	
        	TableColumn<BillBean, String> dto=new TableColumn<BillBean, String>("Date To");//any thing
        	dto.setCellValueFactory(new PropertyValueFactory<>("dateto"));
        	dto.setMinWidth(50);
        	
        	TableColumn<BillBean, String> bill=new TableColumn<BillBean, String>("Bill");//any thing
        	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
        	bill.setMinWidth(50);
        	

        	TableColumn<BillBean, String> bstatus=new TableColumn<BillBean, String>("Billstatus");//any thing
        	bstatus.setCellValueFactory(new PropertyValueFactory<>("billstatus"));
        	bstatus.setMinWidth(50);
        	
        	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(mob, dof, dto, bill,bstatus)));
        	tabledata.setItems(FetchAllCustomers());
        	
        	//lblResp.setText("Records Fetched Successfully...");
    	}
        	else if(radpaid.isSelected()==true)
        	{
        		tabledata.getColumns().clear();
        		TableColumn<BillBean, String> mob=new TableColumn<BillBean, String>("Mobile");//any thing
            	mob.setCellValueFactory(new PropertyValueFactory<>("mobile")); //name of column 
            	//name.setMinWidth(150);
            	
            	TableColumn<BillBean, String> dof=new TableColumn<BillBean, String>("Date from");//any thing
            	dof.setCellValueFactory(new PropertyValueFactory<>("datefrom"));
            	dof.setMinWidth(50);
            	
            	TableColumn<BillBean, String> dto=new TableColumn<BillBean, String>("Date To");//any thing
            	dto.setCellValueFactory(new PropertyValueFactory<>("dateto"));
            	dto.setMinWidth(50);
            	
            	TableColumn<BillBean, String> bill=new TableColumn<BillBean, String>("Bill");//any thing
            	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
            	bill.setMinWidth(50);
            	

            	TableColumn<BillBean, String> bstatus=new TableColumn<BillBean, String>("Billstatus");//any thing
            	bstatus.setCellValueFactory(new PropertyValueFactory<>("billstatus"));
            	bstatus.setMinWidth(50);
            	
            	tabledata.getColumns().addAll(new ArrayList<>(Arrays.asList(mob, dof, dto, bill,bstatus)));
            	tabledata.setItems(FetchAllCustomer());
            	
        	
    		
    	}
    	
    	
    	
    }
    
    
    

	ObservableList<BillBean> FetchAllCustomers() 
    {
    	ObservableList<BillBean>ary = FXCollections.observableArrayList();
    	Float total=(float)0.0;
    	try {
    		
    	   	
    		pst = con.prepareStatement("select * from bills where billstatus=0");
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String DOF = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String DTO = String.valueOf(table.getDate("dateto").toLocalDate());
	    		String bill=table.getString("bill");
	    		total+=Float.parseFloat(bill);
	    		
	    		String billStatus=table.getString("billstatus");
	    		
	    		BillBean ref=new BillBean(mno, DOF, DTO, bill,billStatus);
	    		ary.add(ref);
    		}
    		txttotal.setText(String.valueOf(total));
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
	
	
	
	
	
	ObservableList<BillBean> FetchAllCustomer() 
    {
    	ObservableList<BillBean>ary = FXCollections.observableArrayList();
    	Float total=(float)0.0;
    	try {
    	   	
    		pst = con.prepareStatement("select * from bills where billstatus=1");
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String DOF = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String DTO = String.valueOf(table.getDate("dateto").toLocalDate());
	    		String bill=table.getString("bill");
	    		total+=Float.parseFloat(bill);
	    		String billStatus=table.getString("billstatus");
	    		
	    		BillBean ref=new BillBean(mno, DOF, DTO, bill,billStatus);
	    		ary.add(ref);
    		}
    		txttotal.setText(String.valueOf(total));
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
	
	

	ObservableList<BillBean> FetchAllCustome() 
    {
    	ObservableList<BillBean>ary = FXCollections.observableArrayList();
    	Float total=(float)0.0;
    	try {
    	   	
    		pst = con.prepareStatement("select * from bills where mobile like ?");
    		pst.setString(1,txtmobile.getText());
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String DOF = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String DTO = String.valueOf(table.getDate("dateto").toLocalDate());
	    		String bill=table.getString("bill");
	    		total+=Float.parseFloat(bill);
	    		String billStatus=table.getString("billstatus");
	    		
	    		BillBean ref=new BillBean(mno, DOF, DTO, bill,billStatus);
	    		ary.add(ref);
    		}
    		txttotal.setText(String.valueOf(total));
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
	
	

    @FXML
    void initialize() 
    {
    	con = papermaster.mysqlConnector.doConnect();
    	if(con==null) { System.out.println("Invalid Connection"); }
    	else { System.out.println("Connected");	}

    }

}
