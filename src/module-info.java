module newspaperagency {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens papermaster to javafx.graphics, javafx.fxml;
	opens hawkermanage to javafx.graphics, javafx.fxml;
	opens customerMang to javafx.graphics, javafx.fxml;
	opens billgen to javafx.graphics, javafx.fxml;
	opens billcoll to javafx.graphics, javafx.fxml;
	opens billboard to javafx.graphics, javafx.fxml,javafx.base;
	
	opens table to javafx.graphics, javafx.fxml,javafx.base;
	opens customerpanel to javafx.graphics, javafx.fxml,javafx.base;
	opens adminlogin to javafx.graphics, javafx.fxml;
	opens admindesk to javafx.graphics, javafx.fxml;
	opens meetdev to javafx.graphics, javafx.fxml;
	
}
