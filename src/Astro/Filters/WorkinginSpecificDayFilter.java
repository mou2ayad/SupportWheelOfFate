package Astro.Filters;
import java.util.ArrayList;
import java.util.List;

import Astro.API.Employee;
import Astro.API.IFilter;
import Astro.API.Shift;

// This Filter to make sure that the engineer should work just in some specific days
// Ex: Engineer X should work just in 2nd or 5th day
public class WorkinginSpecificDayFilter implements IFilter {

	List<Integer> daysnumbers;
	
	public WorkinginSpecificDayFilter()
	{
		daysnumbers=new ArrayList<Integer>();
	}
	public WorkinginSpecificDayFilter(Integer ... daysNumbers){
		daysnumbers=new ArrayList<Integer>();
		for(int n : daysNumbers)
		{
			this.daysnumbers.add(n);
		}
	}	
	@Override
	public Boolean isValidOptions(Employee emp) {
		return (checkShiftday(emp.getFirstShift()) && checkShiftday(emp.getSecondShift()));
	}
	private boolean checkShiftday(Shift shift)
	{
		if(shift!=null)
		{
			if(!daysnumbers.contains(shift.getDay().getId()))
			{
				return false;
			}
		}
		return true;
	}

}
