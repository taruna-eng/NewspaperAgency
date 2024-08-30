package billgen;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class billviewController {

	
	 Connection con;
	    PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomobile;

    @FXML
    private TextField fillpapers;

    @FXML
    private TextField fillprices;

    @FXML
    private TextField filltotalprice;

    @FXML
    private DatePicker lastdate;

    @FXML
    private Label lblbill;
    
    @FXML
    private Label info;

    @FXML
    private TextField missdate;

    @FXML
    private DatePicker tillndate;

    @FXML
    void dogenbill(ActionEvent event) 
    {
    	LocalDate frstdate=lastdate.getValue();
		LocalDate secddate=tillndate.getValue();
		long daysBtwn=ChronoUnit.DAYS.between(frstdate, secddate);
		System.out.println(daysBtwn);
	
		LocalDate ld=lastdate.getValue();
		java.sql.Date dt=java.sql.Date.valueOf(ld);
		LocalDate A=tillndate.getValue();
		java.sql.Date B=java.sql.Date.valueOf(A);
		String tp= filltotalprice.getText();
		float sum=Float.parseFloat(tp)*daysBtwn;
		float t=sum-Integer.parseInt(missdate.getText());
		lblbill.setText(Float.toString(t));
		try {
		pst=con.prepareStatement("insert into bills values(?,?,?,?,?)");
		pst.setString(1,combomobile.getSelectionModel().getSelectedItem());
		pst.setDate(2,dt);
		pst.setDate(3, B);
		pst.setString(4,lblbill.getText());
		pst.setInt(5, 0);
		pst.executeUpdate();
		System.out.println("Record saved....");
		info.setText("record saved");
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
	}
    	
    	
    @FXML
    void dofindstartdate(ActionEvent event) throws SQLException 
    {
    	pst=con.prepareStatement("select *from customer where mobile=?");
    	String mobb=(combomobile.getSelectionModel().getSelectedItem());
		pst.setString(1, mobb);
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
			java.sql.Date dt=table.getDate("dos");
			LocalDate ld=dt.toLocalDate();
			lastdate.setValue(ld);
		}
    	
    }

    
    @FXML
    void dofindenddate(ActionEvent event) throws SQLException 
    {
    	pst=con.prepareStatement("select *from bills where mobile=?");
    	String mobb=(combomobile.getSelectionModel().getSelectedItem());
		pst.setString(1, mobb);
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
			java.sql.Date dt=table.getDate("dateto");
			LocalDate ld=dt.toLocalDate();
			lastdate.setValue(ld);
		}
    }

    

    @FXML
    void dolastbill(ActionEvent event)
    {

    	try{
			pst=con.prepareStatement("select spapers from customer where mobile=?");
		 //array of objects
		String mobb=(combomobile.getSelectionModel().getSelectedItem());
		pst.setString(1, mobb);
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
			String sspaper=table.getString("spapers");//use table wale col ka name
			System.out.println(sspaper);
			fillpapers.setText(sspaper);
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    	
    	
    	
    	try{
			pst=con.prepareStatement("select sprices from customer where mobile=?");
		 //array of objects
		String mobb=(combomobile.getSelectionModel().getSelectedItem());
		pst.setString(1, mobb);
		ResultSet table=pst.executeQuery();
		int bill=0;
		while(table.next())
		{
			String ssprice=table.getString("sprices");//use table wale col ka name
			System.out.println(ssprice);
			fillprices.setText(ssprice);
			
			float totalprice = 0;
    		String[] priceary=ssprice.split(",");
    		
    		for(int i=0;i<priceary.length;i++)
    		{
    			totalprice+=Float.parseFloat(priceary[i]);
    		}
    		filltotalprice.setText(String.valueOf(totalprice));
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    	
    	
    	
    	
    }
    
    @FXML
    void doshowmobiles(ActionEvent event) {

    }
  
    
    void doFillRolls()
    {
    	try{
			pst=con.prepareStatement("select mobile from customer");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String mobilee=table.getString("mobile");//use table wale col ka name
			
			combomobile.getItems().add(String.valueOf(mobilee));
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
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
    	
    	doFillRolls();
    	
    	

    }

}
