package com.streamapi.employeestreamscenario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

public class MainApp {
	public static void main(String[] args) {
		
		ArrayList<Employee> employees = new ArrayList<>();
		
		employees.add(new Employee("E001", "Rohan Sharma", 28, "Male", "Sales",
		        LocalDate.of(2018, 5, 10), 75000));

		employees.add(new Employee("E002", "Anjali Verma", 32, "Female", "HR",
		        LocalDate.of(2016, 3, 15), 68000));

		employees.add(new Employee("E003", "Amit Kumar", 26, "Male", "Finance",
		        LocalDate.of(2020, 7, 20), 68000));

		employees.add(new Employee("E004", "Sneha Patel", 30, "Female", "Marketing",
		        LocalDate.of(2017, 11, 5), 70000));

		employees.add(new Employee("E005", "Rahul Mehta", 35, "Male", "IT",
		        LocalDate.of(2014, 9, 25), 90000));

		employees.add(new Employee("E006", "Priya Singh", 29, "Female", "Sales",
		        LocalDate.of(2019, 1, 18), 65000.054345));

		employees.add(new Employee("E007", "Vikas Gupta", 31, "Male", "Finance",
		        LocalDate.of(2015, 6, 30), 85000));

		employees.add(new Employee("E008", "Neha Kapoor", 27, "Female", "IT",
		        LocalDate.of(2021, 4, 12), 55000));

		employees.add(new Employee("E009", "Arjun Rao", 33, "Male", "Operations",
		        LocalDate.of(2013, 8, 8), 88000));

		employees.add(new Employee("E010", "Kavita Nair", 24, "Female", "HR",
		        LocalDate.of(2022, 2, 14), 55000));

		employees.add(new Employee("E011", "Manish Yadav", 36, "Male", "Sales",
		        LocalDate.of(2012, 12, 1), 95000));

		employees.add(new Employee("E012", "Pooja Das", 28, "Female", "Marketing",
		        LocalDate.of(2019, 10, 19), 67000));

		employees.add(new Employee("E013", "Sandeep Reddy", 34, "Male", "Operations",
		        LocalDate.of(2016, 5, 22), 82000));

		employees.add(new Employee("E014", "Meera Iyer", 29, "Female", "Finance",
		        LocalDate.of(2018, 7, 9), 71000));

		employees.add(new Employee("E015", "Karan Malhotra", 27, "Male", "IT",
		        LocalDate.of(2020, 3, 3), 73000));

		
		
		
		
		// Question 1. How many male and female employees are there in the organization ?
		System.out.println("Question 1. How many male and female employees are there in the organization ?");
		Map<String, Long> employeesGender = 
				employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		
		employeesGender.forEach((gender, count) ->
        System.out.println(gender + " = " + count + " People"));
		
		
		
		
		// Question 2. Print the name of all departments in the organization ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 2. Print the name of all departments in the organization ?");
		List<String> departments =
		        employees.stream()
		                 .map(Employee::getDepartment)
		                 .distinct()
		                 .collect(Collectors.toList());

		departments.forEach(System.out::println);

		
		// Question 3. What is the average age of male and female employees ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 3. What is the average age of male and female employees ?");
		Map<String, Double> avgAge = 
				employees.stream().
				collect(Collectors.groupingBy
				(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
		avgAge.forEach((gender, avgage) -> System.out.format("%s = %.2f years\n",gender,avgage));
		
						
		
		// Question 4. Get the details of highest paid employee in the organization ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 4. Get the details of highest paid employee in the organization ?");
		Employee highestPaid = employees.stream()
		        .reduce((e1, e2) ->
		                e1.getSalary() > e2.getSalary() ? e1 : e2)
		        .orElse(null);

		System.out.println(highestPaid);

		
		// Question 5. Get the names of all employees who have joined after 2015 ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 5. Get the names of all employees who have joined after 2015 ?");
		List<String> employeesAfter2015 =
		        employees.stream()
		                .filter(e -> e.getYearOfJoining().getYear() > 2015)
		                .map(Employee::getName)
		                .toList();

		employeesAfter2015.forEach(System.out::println);
		
		
		
		// Question 6. Count the number of employees in each department ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 6. Count the number of employees in each department ?");
		Map<String, Long> countDeptEmployees =
				employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		
		countDeptEmployees.forEach((dept, count) -> System.out.println(dept + " = " + count));
		
		
		// Question 7. What is the average salary of each department ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 7. What is the average salary of each department ?");
		Map<String, Double> avgSalary =
				employees.stream().
				collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.averagingDouble(Employee::getSalary)));
		
		avgSalary.forEach((dept, salary) ->
        System.out.println(dept + " = " + String.format("%.2f", salary)));
	
		// Question 8. Get the details of youngest male employee in the product development department ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 8. Get the details of youngest male employee in the product development department ?");
		Employee youngEmp = 
				employees.stream()
				.reduce((e1, e2) ->
                e1.getAge() < e2.getAge() ? e1 : e2)
				.orElse(null);
	
		System.out.println(youngEmp);
		
		
		// Question 9. Who has the most working experience in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 9. Who has the most working experience in the organization?");
		LocalDate today = LocalDate.now();

		Employee experiencedEmployee =
		    employees.stream()
		             .reduce((e1, e2) ->
		                 ChronoUnit.DAYS.between(e1.getYearOfJoining(), today) >
		                 ChronoUnit.DAYS.between(e2.getYearOfJoining(), today) ? e1 : e2)
		             .orElse(null);

		
		System.out.println(experiencedEmployee);
		
		
		// Question 10. How many male and female employees are there in the Sales team?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 10. How many male and female employees are there in the Sales team?");
		Map<String, Long> countInSale =
				employees.stream()
				.filter(e -> e.getDepartment() == "Sales")
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		
		countInSale.forEach((gender, count) -> System.out.println(gender + " = " + count));
		
		
		// Question 11. What is the average salary of male and female employees ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 11. What is the average salary of male and female employees ?");
		Map<String, Double> avgGenderSalary =
				employees.stream()
				.collect(Collectors.groupingBy(e -> 
			    e.getGender() == null ? "Unknown" : e.getGender(),
			    Collectors.averagingDouble(Employee::getSalary)));

		avgGenderSalary.forEach(
				(gender, salary) ->
				System.out.println(gender + " = " + String.format("%.2f",salary)));
		
		
		// Question 12. List down the names of all employees in each department ?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 12. List down the names of all employees in each department ?");
		Map<String,List<String>> namesByDept =
				employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, 
						Collectors.mapping(Employee:: getName, Collectors.toList())));
		
		
		namesByDept.forEach((dept,name) ->System.out.println(dept + " : " + name ));
		
		
		// Question 13. What is the average salary and total salary of the whole organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 13. What is the average salary and total salary of the whole organization?");
		
		// Average salary
		Double avgSalary1 = employees.stream()
		                            .collect(Collectors.averagingDouble(Employee::getSalary));

		// Total salary
		Double totalSalary = employees.stream()
		                              .mapToDouble(Employee::getSalary)  // get salary as double
		                              .sum();                             // sum them

		System.out.println("Average Salary = " + String.format("%.2f", avgSalary1));
		System.out.println("Total Salary   = " + String.format("%.2f", totalSalary));
		
		
		
		
		// Question 14. Get the employee whose salary are more than 25k.
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 14. Get the employee whose salary are more than 25k.");
		
		List<String> upper25 =
				employees.stream()
				.filter(x -> x.getSalary()>25000)
				.collect(Collectors.mapping(Employee::getName, Collectors.toList()));
		
		upper25.forEach(System.out::println);

		
		// Question 15.Who is the highest paid employee in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 15.Who is the highest paid employee in the organization?");
		
		Employee highestPaid1 =
				employees.stream()
				.reduce((e1,e2) -> 
				e1.getSalary()>e2.getSalary() ? e1 : e2)
				.orElse(null);
		
		System.out.println(highestPaid1.getName());
		
		
		// Question 16.Who is the second highest paid employee in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 16.Who is the second highest paid employee in the organization?");
		
		Employee secondHighest =
		        employees.stream()
		                 .map(Employee::getSalary)               // get salaries
		                 .distinct()                             // remove duplicates
		                 .sorted(Comparator.reverseOrder())      // descending
		                 .skip(1)                                // skip highest
		                 .findFirst()                            // get second highest salary
		                 .flatMap(sal ->                         // find employee with that salary
		                         employees.stream()
		                                  .filter(e -> e.getSalary() == sal)
		                                  .findFirst())
		                 .orElse(null);

		System.out.println(secondHighest.getName());


			
		
		// Question 17. Who is the third highest paid employee in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 17. Who is the third highest paid employee in the organization?");

		Employee thirdHighest = employees.stream()
		                                 .map(Employee::getSalary)
		                                 .distinct()
		                                 .sorted(Comparator.reverseOrder())
		                                 .skip(2)
		                                 .findFirst()
		                                 .flatMap(sal ->
		                                     employees.stream()
		                                              .filter(e -> e.getSalary() == sal)
		                                              .findFirst())
		                                 .orElse(null);

		if (thirdHighest != null) {
		    System.out.println(thirdHighest.getName());
		} else {
		    long distinctSalaries = employees.stream()
		                                     .map(Employee::getSalary)
		                                     .distinct()
		                                     .count();
		    if (employees.isEmpty()) {
		        System.out.println("No employees in the organization");
		    } else if (distinctSalaries < 3) {
		        System.out.println("Not enough distinct salary levels. Found: " + distinctSalaries + 
		                          " (need at least 3)");
		    } else {
		        System.out.println("Third highest paid employee not found");
		    }
		}


		// Question 18. Who is the lowest paid employee in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 18. Who is the lowest paid employee in the organization?");

		Employee lowestPaid = employees.stream()
		                               .min(Comparator.comparingDouble(Employee::getSalary))
		                               .orElse(null);

		if (lowestPaid != null) {
		    System.out.println(lowestPaid.getName());
		    
		    // Check if there are multiple employees with the same lowest salary
		    double lowestSalary = lowestPaid.getSalary();
		    List<Employee> allLowestPaid = employees.stream()
		                                            .filter(e -> e.getSalary() == lowestSalary)
		                                            .collect(Collectors.toList());
		    
		    if (allLowestPaid.size() > 1) {
		        System.out.println("Note: " + allLowestPaid.size() + 
		                          " employees share the lowest salary of " + lowestSalary);
		        System.out.println("All lowest paid employees: " + 
		                          allLowestPaid.stream()
		                                       .map(Employee::getName)
		                                       .collect(Collectors.joining(", ")));
		    }
		} else {
		    System.out.println("No employees in the organization");
		}

		// Question 19. Who is the second lowest paid employee in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 19. Who is the second lowest paid employee in the organization?");

		Employee secondLowest = employees.stream()
		                                 .map(Employee::getSalary)
		                                 .distinct()
		                                 .sorted(Comparator.naturalOrder())
		                                 .skip(1)
		                                 .findFirst()
		                                 .flatMap(sal ->
		                                     employees.stream()
		                                              .filter(e -> e.getSalary() == sal)
		                                              .findFirst())
		                                 .orElse(null);

		if (secondLowest != null) {
		    System.out.println(secondLowest.getName());
		} else {
		    long distinctSalaries = employees.stream()
		                                     .map(Employee::getSalary)
		                                     .distinct()
		                                     .count();
		    if (employees.isEmpty()) {
		        System.out.println("No employees in the organization");
		    } else if (distinctSalaries < 2) {
		        System.out.println("Not enough distinct salary levels. Found: " + distinctSalaries + 
		                          " (need at least 2)");
		    } else {
		        System.out.println("Second lowest paid employee not found");
		    }
		}
		
		
		
		// Question 20. Get the first five lowest paid employees in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 20. Get the first five lowest paid employees in the organization?");

		List<Employee> firstFiveLowestPaid = employees.stream()
		                                              .sorted(Comparator.comparingDouble(Employee::getSalary))
		                                              .limit(5)
		                                              .collect(Collectors.toList());

		if (!firstFiveLowestPaid.isEmpty()) {
		    System.out.println("First " + firstFiveLowestPaid.size() + " lowest paid employee(s):");
		    firstFiveLowestPaid.forEach(emp -> 
		        System.out.println(emp.getName() + " - Salary: "+ " - $" + emp.getSalary())
		    );
		    
		    if (firstFiveLowestPaid.size() < 5) {
		        System.out.println("Note: Only " + firstFiveLowestPaid.size() + 
		                          " employee(s) found (less than 5 total employees)");
		    }
		} else {
		    System.out.println("No employees in the organization");
		}
		
		// Question 21. Get the first five highest paid employees in the organization?
		System.out.println("\n_______________________________________________________________________________________________");
		System.out.println("Question 21. Get the first five highest paid employees in the organization?");

		List<Employee> firstFiveHighestPaid = employees.stream()
		                                               .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
		                                               .limit(5)
		                                               .collect(Collectors.toList());

		if (firstFiveHighestPaid.isEmpty()) {
		    System.out.println("No employees in the organization");
		} else {
		    System.out.println("First " + firstFiveHighestPaid.size() + " highest paid employee(s):");
		    int rank = 1;
		    for (Employee emp : firstFiveHighestPaid) {
		        System.out.println(rank++ + ". " + emp.getName() + " - $" + emp.getSalary());
		    }
		}
	}
}
