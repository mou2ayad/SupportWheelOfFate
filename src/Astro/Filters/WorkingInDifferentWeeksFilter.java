package Astro.Filters;

import Astro.API.Employee;
import Astro.API.IFilter;
// this Filter to make sure that the engineer should work just one shift per week
// Ex: engineer X should not work in day number 2 and 5 because both days belong to the first week,
//		but can work in day number 5 and 6 because each day belong to a different week
public class WorkingInDifferentWeeksFilter implements IFilter {

	@Override
	public Boolean isValidOptions(Employee emp) {
		if (emp.getFirstShift() != null && emp.getSecondShift() != null)
            if (!((emp.getFirstShift().getDay().getId() <= 5 && emp.getSecondShift().getDay().getId() > 5) || (emp.getFirstShift().getDay().getId() > 5 && emp.getSecondShift().getDay().getId() <= 5)))
            {
                return false;
            }
        return true;
	}

}
