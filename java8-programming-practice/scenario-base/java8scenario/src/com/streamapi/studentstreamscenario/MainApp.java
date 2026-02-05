package com.streamapi.studentstreamscenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<>();
		
		// Adding 25 students
		students.add(new Student("Rahul Sharma", 101, "Biotech", 30, "Male", "Delhi", 1,
		        Arrays.asList("9876543210", "8602959597")));

		students.add(new Student("Priya Singh", 102, "Mechanical Engineering", 39, "Female", "Mumbai", 2,
		        Arrays.asList("9876543211")));

		students.add(new Student("Amit Kumar", 103, "Computer Science", 31, "Male", "Bangalore", 2,
		        Arrays.asList("9876543212")));

		students.add(new Student("Sneha Gupta", 104, "Electronics", 30, "Female", "Delhi", 4,
		        Arrays.asList("9876543213")));

		students.add(new Student("Vikram Patel", 105, "Civil Engineering", 32, "Male", "Mumbai", 5,
		        Arrays.asList("9876543214")));

		students.add(new Student("Anjali Verma", 106, "Computer Science", 39, "Female", "Pune", 3,
		        Arrays.asList("9876543215")));

		students.add(new Student("Rohan Mehta", 107, "Electrical Engineering", 31, "Male", "Delhi", 7,
		        Arrays.asList("9876543216")));

		students.add(new Student("Neha Reddy", 108, "Mechanical Engineering", 30, "Female", "Hyderabad", 8,
		        Arrays.asList("9876543217")));

		students.add(new Student("Karan Joshi", 109, "Computer Science", 32, "Male", "Bangalore", 9,
		        Arrays.asList("9876543218")));

		students.add(new Student("Pooja Iyer", 110, "Electronics", 39, "Female", "Karnataka", 10,
		        Arrays.asList("9876543219")));

		students.add(new Student("Arjun Nair", 111, "Civil Engineering", 31, "Male", "Kolkata", 11,
		        Arrays.asList("9876543220")));

		students.add(new Student("Divya Shah", 112, "Computer Science", 20, "Female", "Mumbai", 12,
		        Arrays.asList("9876543221")));

		students.add(new Student("Siddharth Roy", 113, "Mechanical Engineering", 22, "Male", "Delhi", 13,
		        Arrays.asList("9876543222")));

		students.add(new Student("Riya Desai", 114, "Electronics", 19, "Female", "Ahmedabad", 14,
		        Arrays.asList("9876543223")));

		students.add(new Student("Aditya Kapoor", 115, "Computer Science", 21, "Male", "Pune", 15,
		        Arrays.asList("9876543224")));

		students.add(new Student("Aditya Kapoor", 116, "Electrical Engineering", 20, "Female", "Kochi", 16,
		        Arrays.asList("9876543225")));

		students.add(new Student("Dev Sharma", 117, "Civil Engineering", 23, "Male", "Jaipur", 17,
		        Arrays.asList("9876543226")));

		students.add(new Student("Ishita Bansal", 118, "Computer Science", 19, "Female", "Delhi", 18,
		        Arrays.asList("9876543227")));

		students.add(new Student("Ayush Verma", 119, "Mechanical Engineering", 21, "Male", "Lucknow", 19,
		        Arrays.asList("9876543228")));

		students.add(new Student("Tanvi Agarwal", 120, "Electronics", 20, "Female", "Indore", 20,
		        Arrays.asList("9876543229")));

		students.add(new Student("Harsh Malhotra", 121, "Computer Science", 22, "Male", "Chandigarh", 21,
		        Arrays.asList("9876543230")));

		students.add(new Student("Sakshi Rao", 122, "Electrical Engineering", 19, "Female", "Bangalore", 22,
		        Arrays.asList("9876543231")));

		students.add(new Student("Nikhil Pandey", 123, "Civil Engineering", 21, "Male", "Patna", 23,
		        Arrays.asList("9876543232")));

		students.add(new Student("Ananya Chopra", 124, "Computer Science", 20, "Female", "Mumbai", 35,
		        Arrays.asList("9876543233")));

		students.add(new Student("Vishal Thakur", 125, "Mechanical Engineering", 22, "Male", "Surat", 35,
		        Arrays.asList("9876543234")));

        
        //  =========== Basic Filtering & Mapping ==================
        System.out.println("=========== Basic Filtering & Mapping ==================\n\n");
        
        // Question 1.Print all students whose rank is less than 50.
        System.out.println("Question 1.Print all students whose rank is less than 50.");
        List<Student> stdnamesLess50 =
        		students.stream()
        		.filter(x -> x.getRank()<50)
        		.toList();
        
        System.out.printf("%-20s%-5s\n\n","Name","Rank");
        stdnamesLess50.forEach((x) -> System.out.println(String.format("%-20s%-5d", x.getName(),x.getRank())));
        
        // Question 2.Print all students whose age is greater than 25.
        System.out.println("\n_______________________________________________________");
        System.out.println("Question 2.Print all students whose age is greater than 25.");
        
        List<Student> stdnamesAgeGreater25 =
        		students.stream()
        		.filter(x -> x.getAge()>25)
        		.collect(Collectors.toList());
        
        System.out.printf("%-20s%-5s\n\n","Name","Age");
        stdnamesAgeGreater25.forEach((x) -> System.out.println(String.format("%-20s%-5d", x.getName(),x.getAge())));
        
        
        // Question 3.Print the names of all students only.
        System.out.println("\n_______________________________________________________");
        System.out.println("Question 3.Print the names of all students only.");
        
        List<String> stdNames =
        		students.stream()
        		.map(Student::getName)
        		.toList();
        
        
        stdNames.forEach(System.out::println);
     
        
        // Question 4.Print all students belonging to Mechanical Engineering.
        System.out.println("\n_______________________________________________________");
        System.out.println("Question 4.Print all students belonging to Mechanical Engineering.");
        
        List<String> mechDeptName =
        		students.stream()
        		.filter(x -> x.getDepartment() == "Mechanical Engineering")
        		.map(Student::getName)
        		.toList();
        
        
        mechDeptName.forEach(System.out::println);
        
        
        // Question 5.Print all students not from Mumbai.
        System.out.println("\n_______________________________________________________");
        System.out.println("Question 5.Print all students not from Mumbai");
        
        List<Student> stdNotMumbai =
        		students.stream()
        		.filter(x -> x.getCity() != "Mumbai")
        		.toList();
        
        System.out.printf("%-20s%-5s\n\n","Name","City");
       stdNotMumbai.forEach((x) -> System.out.printf("%-20s%-10s\n", x.getName(),x.getCity()));
        
       
       // --------------------------------------
       
       //  =========== Sorting & Limiting ==================
       
       System.out.println("\n=========== Sorting & Limiting ==================\n");
       
       
       // Question 1.Sort students by rank (ascending).
       System.out.println("\n_______________________________________________________");
       System.out.println("Question 1.Sort students by rank (ascending).");
       
       
       List<Student> sortByRank = students.stream()
    		    .sorted(Comparator.comparingInt(Student::getRank))  
    		    .toList();
	
       System.out.printf("%-20s%-5s\n\n","Name","Rank");
       sortByRank.forEach((x) -> System.out.printf("%-20s%-5d\n", x.getName(), x.getRank()));
	
	
       // Question 2.Sort students by age (descending).
       System.out.println("\n_______________________________________________________");
       System.out.println("Question 2.Sort students by age (descending).\n");
       
       List<Student> sortByAge = students.stream()
   		    .sorted(Comparator.comparingInt(Student::getAge).reversed())  
   		    .toList();
	
      System.out.printf("%-20s%-5s\n\n","Name","Age");
      sortByAge.forEach((x) -> System.out.printf("%-20s%-5d\n", x.getName(), x.getAge()));
      
   // Question 3.Print top 5 students based on rank.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question 3. Print top 5 students based on rank.\n");
      
      
      students.stream()
      .sorted(Comparator.comparingInt(Student::getRank))
      .limit(5)
      .forEach((x) -> System.out.printf("%-20s%-5d\n", x.getName(),x.getRank()));
      
      
   // Question 4.Print last 3 students based on rank.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question 3. Print last 3 students based on rank.\n");
      
      students.stream()
      .sorted(Comparator.comparingInt(Student::getRank))
      .skip(students.size()-3)
      .forEach((x) -> System.out.printf("%-20s%-5d\n", x.getName(),x.getRank()));
      
      
      // Question 5.Print students sorted by name and then rank.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question  5.Print students sorted by name and then rank.\n");
      
      students.stream()
      .sorted(Comparator.comparing(Student::getName).thenComparing(Student::getRank))
      .forEach((x) -> System.out.printf("%-20s%-5d\n", x.getName(),x.getRank()));
     
      
      // ==============Grouping & Aggregation======================
      System.out.println("\n\n ==============Grouping & Aggregation======================");
      
      
      // Question 1.Count the number of students in each department.
      System.out.println("Question  1.Count the number of students in each department.\n");
      
      
      Map<String, Long> countDeptStudent =
    		  students.stream()
    		  .collect(Collectors.groupingBy(
    				  Student::getDepartment,
    				  Collectors.counting()));
      
      
      System.out.printf("%-25s%-5s\n\n","Department", "Students Count");
      
      countDeptStudent.forEach(
    		  (dept, count) ->
    		  System.out.printf("%-25s%-5d\n",dept,count));
      
      
      
      // Question 2.Count the number of students in each city.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question  2.Count the number of students in each city.\n");
      
      
      Map<String, Long> countCityStudent =
    		  students.stream()
    		  .collect(Collectors.groupingBy(
    				  Student::getCity,
    				  Collectors.counting()));
      
      System.out.printf("%-20s%-5s\n\n","City", "Students Count");
      countCityStudent.forEach(
    		  (dept, count) ->
    		  System.out.printf("%-20s%-5d\n",dept,count));
      
      
      // Question 3.Find the average age of students in each department.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question  3.Find the average age of students in each department.\n");
      
      Map<String, Double> avgAgeByDept =
    		  students.stream()
    		  .collect(Collectors.groupingBy(
    				  Student::getDepartment,
    				  Collectors.averagingDouble(Student::getAge)));
      
      System.out.printf("%-25s%-5s\n\n","Department", "Avg Age(in years)");
      avgAgeByDept.forEach(
    		  (dept, avgAge) -> 
    		  System.out.printf("%-25s%.2f\n", dept, avgAge));
      
      
      
      // Question 4.Find the average rank of students by gender.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question  4.Find the average rank of students by gender.\n");
      
      
      Map<String, Double> avgRankByGender =
    		  students.stream()
    		  .collect(Collectors.groupingBy(
    				  Student::getGender,
    				  Collectors.averagingDouble(Student::getRank)));
      
      System.out.printf("%-25s%-5s\n\n","Gender", "Avg rank");
      avgRankByGender.forEach(
    		  (gender, avgRank) -> 
    		  System.out.printf("%-25s%.2f\n", gender, avgRank));
      
      
      // Question 5.Find the total number of students in the organization.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question  5.Find the total number of students in the organization.\n");
      
      System.out.println(students.size());
      
      
      
      // Question 6.Find the department with the highest average rank.
      System.out.println("\n_______________________________________________________");
      System.out.println("Question  6.Find the department with the highest average rank.\n");
      
      
      Map<String,Double> highAvgDeptByRank =
    		  students.stream()
    		  .collect(Collectors.groupingBy(Student::getDepartment,
    		  	Collectors.averagingDouble(Student::getRank)));
      
      Optional<Map.Entry<String, Double>> highestDept =
    	        highAvgDeptByRank.entrySet()
    	                .stream()
    	                .max(Map.Entry.comparingByValue());

    		highestDept.ifPresent(entry ->
    	        System.out.println(
    	                entry.getKey() + " -> " + entry.getValue()));

      
      
      
      // ======================== Min / Max / Ranking Problems =====================
    		System.out.println("======================== Min / Max / Ranking Problems =====================");

      // Question 1.Find the student with the best (lowest) rank.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 1.Find the student with the best (lowest) rank.");
    		
    		
    		Student topper = students.stream()
    		        .filter(s -> s.getRank() == 1)
    		        .findFirst()
    		        .orElse(null);

    		System.out.println(topper.getName());

    		// Question 2.Find the student with the worst (highest) rank.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 2.Find the student with the worst (highest) rank.\n");
    				
    		Student worstRank =
    				students.stream()
    				.reduce((s1,s2) ->
    				s1.getRank()>s2.getRank() ? s1 : s2)
    				.orElse(null);
    		
    		System.out.println(worstRank.getName());
    		
      
    		// Question 3.Find the second best rank holder.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 3.Find the second best rank holder.\n ");
    		
      
    		Student secondRank = students.stream()
    		        .map(Student::getRank)
    		        .distinct()
    		        .sorted()
    		        .skip(1)
    		        .findFirst()
    		        .flatMap(rank -> students.stream()
    		                .filter(s -> s.getRank() == rank)
    		                .findFirst())
    		        .orElse(null);

    		System.out.println(secondRank.getName());

    				
    				
    		// Question 3.Find the second best rank holder.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 3.Find the second best rank holder.\n ");
    		
    				
    		
    		Student thirdRank = students.stream()
    		        .map(Student::getRank)
    		        .distinct()
    		        .sorted()
    		        .skip(2)
    		        .findFirst()
    		        .flatMap(rank -> students.stream()
    		                .filter(s -> s.getRank() == rank)
    		                .findFirst())
    		        .orElse(null);

    		System.out.println(thirdRank.getName());
      
    		
    		// Question 5.Find the youngest student.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 5.Find the youngest student.\n");
    		
    		
    		Student youngest =
    				students.stream()
    				.reduce((s1,s2) -> 
    				s1.getAge()<s2.getAge() ? s1 : s2)
    				.orElse(null);
    		
    		System.out.println(youngest);
    		
    		
    		// Question 5.Find the oldest student.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 5.Find the youngest student.\n");
    		
    		
    		Student oldest =
    				students.stream()
    				.reduce((s1,s2) -> 
    				s1.getAge()>s2.getAge() ? s1 : s2)
    				.orElse(null);
    		
    		System.out.println(oldest);
    		
    		
    		
    		// ======================FlatMap & Collections=====================
    		System.out.println("======================FlatMap & Collections=====================");
    		// Question 1.Print all contact numbers of all students.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 1.Print all contact numbers of all students.\n");

    		students.stream()
            .forEach(s ->
                    System.out.printf("%-20s%-20s\n",s.getName(), s.getContacts()));

      
    		
    		
    		
    		// Question 2.Count the total number of contact numbers.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 2.Count the total number of contact numbers \n");

    		long totalContacts = students.stream()
    		        .map(Student::getContacts)
    		        .filter(Objects::nonNull)
    		        .count();

    		System.out.println("Total contact numbers: " + totalContacts);

    		
    		
    		
    		// Question 3.Find students who have more than one contact number..
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 3.Find students who have more than one contact number. \n");
    		
    		
    		students.stream()
            .filter(s -> s.getContacts() != null)
            .flatMap(s -> 
                    s.getContacts().size() > 1
                            ? Stream.of(s)
                            : Stream.empty())
            .forEach(s ->
                    System.out.println(s.getName() + " -> " + s.getContacts()));
    		
    		
    		
    		// Question 4.Print unique contact numbers.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question  4.Print unique contact numbers. \n");
    		
    		students.stream()
            .flatMap(student ->
                    student.getContacts().stream()
                            .map(contact -> Map.entry(student.getName(), contact)))
            .distinct()
            .forEach(entry ->
                    System.out.printf("%-20s %-15s%n",
                            entry.getKey(),
                            entry.getValue()));
    		
    		
    		// ==============================Conditional & Matching============================
    		System.out.println("\n\n=============================Conditional & Matching==============================");
    		
    		
    		
    		// Question 1.Check if any student belongs to Biotech Engineering.
    		System.out.println("\n\nQuestion 1.Check if any student belongs to Biotech Engineering."); 
    		
    		List<Student> biotech =
    				students.stream()
    				.filter(s -> s.getDepartment() == "Biotech")
    				.toList();
    		
    		if(biotech.isEmpty()) {
    			System.out.println("No One is in Bio Tech");
    		}
    		else {
    			biotech.forEach(System.out::println);
    		}
    		
    		
    		
    		// Question 2.Check if all students are above age 20.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 2.Check if all students are above age 20.\n");
    		
    		boolean allAbove20 = students.stream()
    		        .allMatch(student -> student.getAge() > 20);

    		System.out.println("Are all students above age 20? " + allAbove20);
    		
    		
    		// Question 3.Check if no student belongs to Civil Engineering.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 3.Check if no student belongs to Civil Engineering.\n");
    		
    		boolean isCivil = students.stream()
    		        .allMatch(student -> student.getDepartment() != "Civil Engineering");

    		System.out.println("Is no student belongs to Civil Engineering? " + isCivil);
    		
    		
    		
    		
    		
    		
    		// ==============================Gender-Based Analysis============================
    		System.out.println("\n\n=============================Gender-Based Analysis==============================");
    		
    		
    		
    		// Question 1.Count male and female students.
    		System.out.println("\n\nQuestion 1.Count male and female students."); 
    		Map<String, Long> countGender =
    				students.stream()
    				.collect(Collectors.groupingBy(Student::getGender,
    						Collectors.counting()));
    		
    		
    		countGender.forEach((gender, count) ->
    					System.out.println(gender + " = " + count)
    			);
    		
    		
    		
    		// Question 2.Find the average age of male students.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 2.Find the average age of male students.\n");
    				
    				
    				
    		Double avgAgeMale =
    				students.stream()
    				.filter(x -> x.getGender() == "Male")
    				.collect(Collectors.averagingDouble(Student::getAge));
    		
    		System.out.println(String.format("%.2f",avgAgeMale));
    		
    		
    		// Question 3.Find the average age of male students.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 3.Find the average age of Female students.\n");
    		
    		
    		
    		Double avgAgeFemale =
    				students.stream()
    				.filter(x -> x.getGender() == "Female")
    				.collect(Collectors.averagingDouble(Student::getAge));
    		
    		System.out.println(String.format("%.2f",avgAgeFemale));
    		
    		
    		// Question 4.Find the highest ranked male student.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 4.Find the highest ranked male student.\n");
    		
    		Student highestMale =
    				students.stream()
    				.filter(s -> s.getGender() == "Male")
    				.reduce((s1,s2) ->
    						s1.getRank()>s2.getRank() ? s1:s2)
    				.orElse(null);
    		
    		if(highestMale != null) {
    			System.out.println(highestMale);
    		}
    		else {
    			System.out.println("No such male");
    		}
    				
    		
    		
    		// Question 4.Find the highest ranked Female student.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 4.Find the highest ranked Female student.\n");
    		
    		Student highestFeMale =
    				students.stream()
    				.filter(s -> s.getGender() == "Female")
    				.reduce((s1,s2) ->
    				s1.getRank()>s2.getRank() ? s1:s2)
    				.orElse(null);
    		
    		if(highestFeMale != null) {
    			System.out.println(highestFeMale);
    		}
    		else {
    			System.out.println("No such Female");
    		}
    		
    		
    		// ==============================Department-Specific Queries============================
    		System.out.println("\n\n=============================Department-Specific Queries==============================");
    		
    		// Question 1.List students department-wise with names only.
    		System.out.println("\n\nQuestion 1.List students department-wise with names only."); 
    		Map<String, List<String>> stdDeptWise =
    				students.stream()
    				.collect(Collectors.groupingBy(Student::getDepartment, 
    						Collectors.mapping(Student::getName, Collectors.toList())));
    		
    		
    		stdDeptWise.forEach((dept, names) -> {
    		    System.out.println("\n" + dept + ":\n");
    		    names.forEach(name -> System.out.println("  " + name));
    		});
    		
    		
    		// Question 2.Find the youngest student in Computer Engineering.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 2.Find the youngest student in Computer Engineering.\n");
    		
    		Student cseYoungest =
    				students.stream()
    				.filter(s -> s.getDepartment() == "Computer Science")
    				.reduce((s1, s2) ->
    				s1.getAge()< s2.getAge() ? s1:s2)
    				.orElse(null);
    		
    		
    		if(cseYoungest != null) {
    			System.out.println(cseYoungest);
    		}
    		else {
    			System.out.println("No such Youngest student.");
    		}
    		
    		
    		
    		
    		// Question 3.Find the top-ranked student in Mechanical Engineering.
    		System.out.println("\n_______________________________________________________");
    		System.out.println("Question 3.Find the top-ranked student in Mechanical Engineering");
    		
    		Student topRanked = 
    				students.stream()
    				.filter(s -> s.getDepartment() == "Mechanical Engineering")
    				.reduce((s1,s2) ->
    				s1.getRank()<s2.getRank() ? s1:s2)
    				.orElse(null);
    		
    		if(topRanked != null) {
    			System.out.println(topRanked);
    		}
    		else {
    			System.out.println("No Top Ranked Student");
    		}
    		
    		
    		// Question 4.Count students in Electronics Engineering from Karnataka.
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 4.Count students in Electronics Engineering from Karnataka.\n");
    		
    		
    		Long countStd =
    				students.stream()
    				.filter(s -> s.getDepartment() == "Electronics" && s.getCity() == "Karnataka")
    				.count();
    		
    		System.out.println(countStd);
    		
    		
    		// ============================== Advanced Level============================
    		System.out.println("\n\n============================= Advanced Level==============================");
    		
    		// Question 1.List students department-wise with names only.
    		System.out.println("\n\nQuestion 1.Convert the student list into a Map<Id, Name>..\n"); 
    		
    		Map<Integer, String> mapStd = students.stream()
    		        .collect(Collectors.toMap(
    		                Student::getId,      // Key = Id
    		                Student::getName     // Value = Name
    		        ));
    		
    		System.out.printf("%-7s%-10s\n", "ID","Name");
    		
    		mapStd.forEach((id, name) ->
            System.out.printf("%-7s %-20s%n", id, name));

 
    		
    		// Question 2. Convert the student list into a Map<Department, List<Student>>
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 2. Convert the student list into a Map<Department, List<Student>>.\n");

    		Map<String, List<Student>> departmentMap = 
    		    students.stream()
    		    .collect(Collectors.groupingBy(Student::getDepartment));

    		System.out.println(departmentMap);

    		// Question 3. Find students whose name starts with 'S'
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 3. Find students whose name starts with 'S'.\n");

    		List<Student> studentsStartingWithS = 
    		    students.stream()
    		    .filter(s -> s.getName().startsWith("S"))
    		    .collect(Collectors.toList());

    		System.out.println(studentsStartingWithS);

    		// Question 4. Find students whose name length is greater than 5
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 4. Find students whose name length is greater than 5.\n");

    		List<Student> studentsNameLengthGreaterThan5 = 
    		    students.stream()
    		    .filter(s -> s.getName().length() > 5)
    		    .collect(Collectors.toList());

    		System.out.println(studentsNameLengthGreaterThan5);

    		// Question 5. Find students whose rank is even
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 5. Find students whose rank is even.\n");

    		List<Student> studentsWithEvenRank = 
    		    students.stream()
    		    .filter(s -> s.getRank() % 2 == 0)
    		    .collect(Collectors.toList());

    		System.out.println(studentsWithEvenRank);

    		// Question 6. Find students whose rank is odd
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 6. Find students whose rank is odd.\n");

    		List<Student> studentsWithOddRank = 
    		    students.stream()
    		    .filter(s -> s.getRank() % 2 != 0)
    		    .collect(Collectors.toList());

    		System.out.println(studentsWithOddRank);
    		
    		
    		System.out.println("===================Bonus Challenges====================");
    		// Question 1. Find the top 3 youngest students
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 1. Find the top 3 youngest students.\n");

    		List<Student> top3Youngest = 
    		    students.stream()
    		    .sorted(Comparator.comparing(Student::getAge))
    		    .limit(3)
    		    .collect(Collectors.toList());

    		System.out.println(top3Youngest);

    		// Question 2. Find the top 3 oldest students
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 2. Find the top 3 oldest students.\n");

    		List<Student> top3Oldest = 
    		    students.stream()
    		    .sorted(Comparator.comparing(Student::getAge).reversed())
    		    .limit(3)
    		    .collect(Collectors.toList());

    		System.out.println(top3Oldest);

    		// Question 3. Partition students based on age > 25
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 3. Partition students based on age > 25.\n");

    		Map<Boolean, List<Student>> partitionByAge = 
    		    students.stream()
    		    .collect(Collectors.partitioningBy(s -> s.getAge() > 25));

    		System.out.println(partitionByAge);

    		// Question 4. Partition students based on rank < 100
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 4. Partition students based on rank < 100.\n");

    		Map<Boolean, List<Student>> partitionByRank = 
    		    students.stream()
    		    .collect(Collectors.partitioningBy(s -> s.getRank() < 100));

    		System.out.println(partitionByRank);

    		// Question 5. Find the city having maximum students
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 5. Find the city having maximum students.\n");

    		String cityWithMaxStudents = 
    		    students.stream()
    		    .collect(Collectors.groupingBy(Student::getCity, Collectors.counting()))
    		    .entrySet().stream()
    		    .max(Map.Entry.comparingByValue())
    		    .map(Map.Entry::getKey)
    		    .orElse(null);

    		System.out.println(cityWithMaxStudents);

    		// Question 6. Find the department having minimum students
    		System.out.println("_______________________________________________________");
    		System.out.println("Question 6. Find the department having minimum students.\n");

    		String departmentWithMinStudents = 
    		    students.stream()
    		    .collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()))
    		    .entrySet().stream()
    		    .min(Map.Entry.comparingByValue())
    		    .map(Map.Entry::getKey)
    		    .orElse(null);

    		System.out.println(departmentWithMinStudents);
    		
	}
}
