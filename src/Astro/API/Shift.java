package Astro.API;

// Shift Object
public class Shift extends baseClass {
	 public Shift(day d, int order)
     {
         day = d;
         shiftOrder = order;
         // shift Id can be specified by the day number and shift order
         Id = ((day.Id - 1) * 2) + shiftOrder;
     }
	 // Shift order can be (1 or 2) means (day Shift or night shift)
     private int shiftOrder ;
     public int getShiftOrder() {
		return shiftOrder;
	}
	public void setShiftOrder(int shiftOrder) {
		this.shiftOrder = shiftOrder;
	}
	// Day that this Shift belong to
	private day day ;
	public day getDay() {
		return day;
	}
	public void setDay(day day) {
		this.day = day;
	}
	
	// the employee who this shift is assigned to
	private Employee emp ;
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
     
}
