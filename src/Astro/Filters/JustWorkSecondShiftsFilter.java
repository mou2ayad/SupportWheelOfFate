package Astro.Filters;

import Astro.API.Employee;
import Astro.API.IFilter;

// this Filter to make sure that the engineer should work just on the second shift(night shift)
public class JustWorkSecondShiftsFilter implements IFilter {

	@Override
	public Boolean isValidOptions(Employee emp) {
		
	            if ((emp.getFirstShift() != null && emp.getFirstShift().getShiftOrder() == 1) || (emp.getSecondShift() != null && emp.getSecondShift().getShiftOrder() == 1))
	                return false;
	            return true;
	        
	}

}
