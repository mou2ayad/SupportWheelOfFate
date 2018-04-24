package Astro.Filters;

import Astro.API.Employee;
import Astro.API.IFilter;


//this Filter to make sure that the engineer should work just on the fist shift(day shift)
public class JustWorkFirstShiftsFilter implements IFilter {

	@Override
	public Boolean isValidOptions(Employee emp) {
		if ((emp.getFirstShift() != null && emp.getFirstShift().getShiftOrder() == 2) || (emp.getSecondShift() != null && emp.getSecondShift().getShiftOrder() == 2))
            return false;
        return true;
	}

}
