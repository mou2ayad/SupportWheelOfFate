package Astro.API;

import java.util.ArrayList;

public class Employee extends baseClass {
	public Employee(int empId)
    {
        this.Id = empId;
        filters = new ArrayList<IFilter>();
    }
	// list of the filter on the employee
    private ArrayList<IFilter> filters;
    
    // add filter to employee
    public void AddFilter(IFilter filter)
    {
        filters.add(filter);
    }
    // invoke isValidOptions from added filters, if any filter return false,
    // then the shift is not valid for this employee
    boolean isValidShifts()
    {
        for (IFilter iFilter : filters) {
        	if (iFilter.isValidOptions(this) == false)
                return false;
		}
        return true;
    }
    private String EmployeeName ;
    public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	public Shift getFirstShift() {
		return FirstShift;
	}
	void setFirstShift(Shift firstShift) {
		FirstShift = firstShift;
	}
	public Shift getSecondShift() {
		return SecondShift;
	}
	void setSecondShift(Shift secondShift) {
		SecondShift = secondShift;
	}
	private Shift FirstShift ;
    private Shift SecondShift ;
}
