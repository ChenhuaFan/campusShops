package Dao;

public class Aservice {
	
	public Aservice(){}
	
	private int ServiceID;
	private int UserID;
	private String Title;
	private String Description;
	private String StartDate;
	private int Status;
	
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int Status) {
		this.Status = Status;
	}
	public int getServiceID() {
		return ServiceID;
	}
	public void setServiceID(int ServiceID) {
		this.ServiceID = ServiceID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
 
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}
 
	
}
