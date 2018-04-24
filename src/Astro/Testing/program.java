package Astro.Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Astro.API.AstroException;
import Astro.API.Employee;
import Astro.API.Schedule;
import Astro.API.day;
import Astro.Filters.*;


public class program {

		//This Filter Contains these rules:
		// 1- Each one from engineers
		//		* should not work two shifts in one, 
		//		* should not work two shifts in Two Consecutive Days
 
	public static void TestSimpleRules()
	{
		Schedule schedule = new Schedule();
        List<String> EmployeeNames = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        for (int i = 1; i <= 10; i++)
        {
            Employee emp = new Employee(i);
            emp.setEmployeeName(EmployeeNames.get(i - 1));
            emp.AddFilter(new TwoShiftInTwoConsecutiveDaysFilter());
            emp.AddFilter(new TwoShiftsInOneDayFilter());
            schedule.AddEmployee(emp);
        }               
        int doneAfter=1;
        try
        {
            List<day> results = schedule.Run(50, doneAfter);
            System.out.println(String.format("The solution has been found after %1$d Experiments", doneAfter));
            System.out.println("*******************************************************************************");
            for(day d : results)
            {
            	System.out.println(String.format("Day Number:%1$d=> FirstShift:%2$s   and   SecondShift:%3$s", d.getId(), d.getFirstShift().getEmp().getEmployeeName(), d.getSecondShift().getEmp().getEmployeeName()));
            }

        }
        catch (AstroException astroEx)
        {
        	System.out.println(astroEx.getMessage());
        	
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }


	}
	//This Filter Contains these rules:
		// 1- Each one from engineers
		//		* should not work two shifts in one, 
		//		* should not work two shifts in Two Consecutive Days
		//		* Should work just one shift per week
	public static void TestMainRulesWithWorkingInDifferentWeeksFilter()
	{
		Schedule schedule = new Schedule();
        List<String> EmployeeNames = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        for (int i = 1; i <=10; i++)
        {
            Employee emp = new Employee(i);
            emp.setEmployeeName(EmployeeNames.get(i - 1));
            emp.AddFilter(new TwoShiftInTwoConsecutiveDaysFilter());
            emp.AddFilter(new TwoShiftsInOneDayFilter());
            emp.AddFilter(new WorkingInDifferentWeeksFilter());
            schedule.AddEmployee(emp);
        }               
        int doneAfter=1;
        try
        {
            List<day> results = schedule.Run(50, doneAfter);
            System.out.println(String.format("The solution has been found after %1$d Experiments", doneAfter));
            System.out.println("*******************************************************************************");
            for(day d : results)
            {
            	System.out.println(String.format("Day Number:%1$d=> FirstShift:%2$s   and   SecondShift:%3$s", d.getId(), d.getFirstShift().getEmp().getEmployeeName(), d.getSecondShift().getEmp().getEmployeeName()));
            }

        }
        catch (AstroException astroEx)
        {
        	System.out.println(astroEx.getMessage());
        	
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }


	}
	//This Filter Contains these rules:
	// 1- Each one from engineers
	//		* should not work two shifts in one, 
	//		* should not work two shifts in Two Consecutive Days
	//		* Should work just one shift per week
	// 2- Engineers A and J should work just in (1st,3rd,8th,9th) day 
	// 3- Engineer A should work just in the first Shift(day shift)
	// 4- Engineer J should work just in the second Shift(night shift)   
	public static void TestComplexRules()
	{
		Schedule schedule = new Schedule();
        List<String> EmployeeNames = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        for (int i = 2; i <10; i++)
        {
            Employee emp = new Employee(i);
            emp.setEmployeeName(EmployeeNames.get(i - 1));
            emp.AddFilter(new TwoShiftInTwoConsecutiveDaysFilter());
            emp.AddFilter(new TwoShiftsInOneDayFilter());
            emp.AddFilter(new WorkingInDifferentWeeksFilter());
            schedule.AddEmployee(emp);
        }
        Integer[] daysNumbers= new Integer[]{1,8,9,3};
        Employee emp1 = new Employee(1);
        emp1.setEmployeeName("A");
        emp1.AddFilter(new TwoShiftInTwoConsecutiveDaysFilter());
        emp1.AddFilter(new TwoShiftsInOneDayFilter());
        emp1.AddFilter(new WorkingInDifferentWeeksFilter());
        emp1.AddFilter(new JustWorkFirstShiftsFilter());
        emp1.AddFilter(new WorkinginSpecificDayFilter(daysNumbers));
        schedule.AddEmployee(emp1);

        Employee emp10 = new Employee(10);
        emp10.setEmployeeName("J");        
        emp10.AddFilter(new WorkinginSpecificDayFilter(daysNumbers));
        emp10.AddFilter(new TwoShiftInTwoConsecutiveDaysFilter());
        emp10.AddFilter(new TwoShiftsInOneDayFilter());
        emp10.AddFilter(new WorkingInDifferentWeeksFilter());
        emp10.AddFilter(new JustWorkSecondShiftsFilter());
        
        schedule.AddEmployee(emp10);
        
        int doneAfter=1;
        try
        {
            List<day> results = schedule.Run(50, doneAfter);
            System.out.println(String.format("The solution has been found after %1$d Experiments", doneAfter));
            System.out.println("*******************************************************************************");
            for(day d : results)
            {
            	System.out.println(String.format("Day Number:%1$d=> FirstShift:%2$s   and   SecondShift:%3$s", d.getId(), d.getFirstShift().getEmp().getEmployeeName(), d.getSecondShift().getEmp().getEmployeeName()));
            }

        }
        catch (AstroException astroEx)
        {
        	System.out.println(astroEx.getMessage());
        	
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }

	}
	

	public static void main(String[] args) {
		//TestSimpleRules();
		//TestMainRulesWithWorkingInDifferentWeeksFilter();
		TestComplexRules();

	}

}
