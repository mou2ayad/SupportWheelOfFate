package Astro.Filters;

import Astro.API.Employee;
import Astro.API.IFilter;

// This Filter to make sure that the employee should not work two shifts in one day
public class TwoShiftsInOneDayFilter implements IFilter {

	@Override
	public Boolean isValidOptions(Employee emp) {
        if (emp.getFirstShift() != null && emp.getSecondShift() != null)
            if (emp.getFirstShift().getDay().getId() == emp.getSecondShift().getDay().getId())
                return false;
        return true;
	}

}

