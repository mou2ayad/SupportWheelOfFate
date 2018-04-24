package Astro.API;

import java.util.ArrayList;
import java.util.List;

// Schedule object containing all required data to generate “Support Wheel of Fate”
public class Schedule {	
	 private List<day> days;
	 
	 // this box contains all available shifts to be assigned,shifts are randomly grabbed from this box
	 //the captured option would be removed from the box 
     private Box<Shift> AvailableOptions;
     
     
     //this box contains all available employee to assign shifts to them,employees are randomly grabbed from this box
     //the captured option would be removed from the box 
     private Box<Employee> EmployeesOptions;
     
     
     // List of all employees 
     private List<Employee> AllEmployees;
     
     // the list contains employees who have already assigned shifts to them
     List<Employee> finishedEmployees = new ArrayList<Employee>();
     
     // The Constructor prepares the days list  as well as the available shifts
     // adding all shifts to Shifts box and adding Employees to Employees box 
     public Schedule()
     {
         days = new ArrayList<day>();
         EmployeesOptions = new Box<Employee>(10);
         AllEmployees = new ArrayList<Employee>(10);
         AvailableOptions = new Box<Shift>(20);
         for (int i = 0; i < 10; i++)
         {
             day d = new day(i + 1);
             if (i > 0)
             {
                 d.setPreDay(days.get(i - 1));
                 days.get(i - 1).setNextDay(d);
             }
             d.setFirstShift(new Shift(d, 1));
             d.setSecondShift(new Shift(d, 2));
             AvailableOptions.AddOption(d.getFirstShift());
             AvailableOptions.AddOption(d.getSecondShift());
             days.add(d);
         }
     }
     
     // reset all lists and boxes in order to restart assigning operation again 
     private void reset()
     {
         finishedEmployees.clear();
         EmployeesOptions.Clear();
         AvailableOptions.Clear();
         for (day d : days)
         {
        	 // remove employees from the shifts
             d.getFirstShift().setEmp(null);
             d.getSecondShift().setEmp(null);
        	 // re-adding the shifts to the available options
             AvailableOptions.AddOption(d.getFirstShift());
             AvailableOptions.AddOption(d.getSecondShift());
         }
         for (Employee emp : AllEmployees)
         {
        	 // remove shifts from the employees
             emp.setFirstShift(null);
             emp.setSecondShift(null);
        	 // re-adding the employees to the available options
             EmployeesOptions.AddOption(emp);
         }

     }
     // to add employee to Employees box 
     public void AddEmployee(Employee emp)
     {
    	 // if employees count reaches 10, the method will not accept more inputs
         if (EmployeesOptions.GetOptionsCount() < 10)
         {
             EmployeesOptions.AddOption(emp);
             AllEmployees.add(emp);
         }
     }
     
     // swap two shifts between two engineers
     // the inputs are the two employees and the Shifts orders to be swapped among these two employees
     private void SwapShifts(Employee firstEmployee, int FirstShiftOrder, Employee SecondEmployee, int SecondShiftOrder)
     {

         Shift firstShift = FirstShiftOrder == 1 ? firstEmployee.getFirstShift() : firstEmployee.getSecondShift();
         Shift SecondShift = SecondShiftOrder == 1 ? SecondEmployee.getFirstShift() : SecondEmployee.getSecondShift();
         firstShift.setEmp(SecondEmployee);
         SecondShift.setEmp(firstEmployee);
         if (FirstShiftOrder == 1)
         {
             firstEmployee.setFirstShift(SecondShift);
         }
         else
         {
             firstEmployee.setSecondShift(SecondShift);
         }
         if (SecondShiftOrder == 1)
         {
             SecondEmployee.setFirstShift(firstShift);
         }
         else
         {
             SecondEmployee.setSecondShift( firstShift);
         }

     }
     // trying to replace specific shift from one employee with another employee in order to find a solution meets all filters 
     private Shift ReplaceShiftWithOthers(Employee emp, int ShiftOrder)
     {
    	 // all rest available options for this employee
         Box<Shift> employeeChoices = AvailableOptions.Clone();
         Shift sh;
         while (employeeChoices.GetOptionsCount() > 0)
         {
        	 // grab shift 
             sh = employeeChoices.GrabOption();
             employeeChoices.RemoveOption(sh);
             if (ShiftOrder == 1)
                 emp.setFirstShift(sh);
             else
                 emp.setSecondShift(sh);
             sh.setEmp(emp);
             
             // loop to check if any employee can replace his shift with this employee shift
             for (Employee otheremp : finishedEmployees)
             {
            	 // swap employee shift with the first shifts from the another employee
            	 // and validate the filters for both employees
                 SwapShifts(emp, ShiftOrder, otheremp, 1);
                 if (otheremp.isValidShifts() && emp.isValidShifts())
                 {
                     return sh;
                 }
                 // if one shift or two shifts are not valid for the employees,
                 // swap-back the shifts again to bring shifts back to the original employees
                 SwapShifts(emp, ShiftOrder, otheremp, 1);

                 // swap employee shift with the second shifts from the another employee
            	 // and validate the filters for both employees
                 SwapShifts(emp, ShiftOrder, otheremp, 2);
                 if (otheremp.isValidShifts() && emp.isValidShifts())
                 {
                     return sh;
                 }
                 // if one shift or two shifts are not valid for the employees,
                 // swap-back the shifts again to bring shifts back to the original employees
                 SwapShifts(emp, ShiftOrder, otheremp, 2);
             }
         }
         return null;
     }
     // grab one valid shift for a specific employee
     private boolean ChooseEmployeeShift(Employee emp, int shiftOrder)
     {
         Box<Shift> employeeChoices = AvailableOptions.Clone();
         Shift sh;
         do
         {
        	 // grab shift from available shifts
             sh = employeeChoices.GrabOption();
             if (shiftOrder == 1)
                 emp.setFirstShift(sh);
             else
                 emp.setSecondShift(sh);
             
             if (sh != null)             
                 sh.setEmp(emp);             
             else
                 break;
             employeeChoices.RemoveOption(sh);

             // validate the grabbed shift by applying the employee filters
         } while (!emp.isValidShifts());

         // if the algorithm could not find a proper shift option for this employee,
         // then try to replace the shift with another employee
         if (sh == null)
        	 sh = ReplaceShiftWithOthers(emp, shiftOrder);
         
         // if the algorithm could not find a proper shift option for this employee,
         // after calling ReplaceShiftWithOthers method then stop this experiment and give up
         if (sh == null)
             return false;
         AvailableOptions.RemoveOption(sh);
         return true;
     }

     // a public method, can be called from the clients to start the choosing process 
     public List<day> Run(int GiveUpAfter,Integer DoneAfter) throws AstroException
     {
         if (EmployeesOptions.GetOptionsCount() != 10)
         {
             throw new AstroException("Engineers count should be 10");
         }
         int ExperimentNumber = 0;
         boolean isFinished = false;
         do
         {
             ExperimentNumber++;
             // loop all employees to grab shifts for each one
             while (EmployeesOptions.ContainsOptions())
             {
            	 // grab the first and second shift, if can't find proper options this repeat the process for all employees again 
                 Employee emp = EmployeesOptions.GrabOption();
                 if (!ChooseEmployeeShift(emp, 1))
                     break;
                 if (!ChooseEmployeeShift(emp, 2))
                     break;
                 EmployeesOptions.RemoveOption(emp);
                 finishedEmployees.add(emp);
             }
             // if the any employee with out 2 shift the clear the list and fill shifts box
             // and employee box in order to restart the experiment again  
             if (EmployeesOptions.ContainsOptions())
             {
                 reset();
                 continue;
             }
             isFinished = true;
             DoneAfter = ExperimentNumber;
             return this.days;

         } while ((ExperimentNumber < GiveUpAfter) && !isFinished);
         // if can't find a solution after all experiments, throw an exception and give up :(
         throw new AstroException(String.format("No Choice available after %1$d Experiments", ExperimentNumber));
     }

}
