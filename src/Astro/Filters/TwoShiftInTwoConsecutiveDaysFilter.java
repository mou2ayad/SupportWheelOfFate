package Astro.Filters;

import Astro.API.Employee;
import Astro.API.IFilter;

//This Filter to make sure that the employee should not work two shifts in two Consecutive Days
public class TwoShiftInTwoConsecutiveDaysFilter implements IFilter {

	@Override
	public Boolean isValidOptions(Employee emp) {
		if (emp.getFirstShift() != null && emp.getSecondShift() != null)
            if (Math.abs(emp.getFirstShift().getDay().getId() - emp.getSecondShift().getDay().getId()) == 1)
            {
                return false;
            }
        return true;
	}

}
