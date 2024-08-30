package customerpanel;

public class CustomerBean 
{
  String mobile;
  String name;
  String spapers;
  String area;
  String address;
  String dos;
	
  
  public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSpapers() {
	return spapers;
}
public void setSpapers(String spapers) {
	this.spapers = spapers;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getDos() {
	return dos;
}
public void setDos(String dos) {
	this.dos = dos;
}
public CustomerBean(String mobile, String name, String spapers, String area, String address, String dos) {
	super();
	this.mobile = mobile;
	this.name = name;
	this.spapers = spapers;
	this.area = area;
	this.address = address;
	this.dos = dos;
}

	
	
}
