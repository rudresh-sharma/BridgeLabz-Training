package com.streamapi.logicalquestions1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.function.Function;
public class LogicalApp {
	public static void main(String[]args) {
		
		
		// Question 1. Count vowels in a string using Stream
		// Input: "programming"
		// Output: 3

		System.out.println("Question 1. Count vowels in a string using Stream. ");
		String input1 = "Programming";
		System.out.println(input1);
		long countVowel = input1.chars().filter(x -> "aeiouAEIOU".indexOf(x) != -1).count();
		
		System.out.println(countVowel);
		
		
		
		// Question 2. Count number of words in a sentence
		// Input: "Java is very powerful"
		//	Output: 4
		System.out.println("\nQuestion 2. Count number of words in a sentence");
		String input2 = "Java is very powerful";
		System.out.println(input2);
		long noOfWords = Arrays.stream(input2.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .count();
		
		System.out.println(noOfWords);
		
		
		
		// Question 3. Find even numbers from list
		// Input: [2,5,7,8,10,13]
		//	Output: [2,8,10]
		
		
		System.out.println("\nQuestion 3. Find even numbers from list");
		List<Integer> list = Arrays.asList(2,5,7,8,10,13);
		System.out.println("Input = " + list);
		List<Integer> evenNo = list.stream().filter(x -> x%2 == 0).toList();
		
		System.out.println("Output = " + evenNo);
		
		
		// 4. Convert list of strings to uppercase
		// Input: ["java","spring","boot"]
		// Output: ["JAVA","SPRING","BOOT"]
		
		System.out.println("\nQuestion 4. Convert list of strings to uppercase");
		
		List<String> words = Arrays.asList("java", "spring", "boot");
		System.out.println("Input = " + words);
		
		List<String> upperWord = words.stream().map(w -> w.toUpperCase()).toList();
		
		System.out.println("Output = " + upperWord);
	
		
		//5. Find string length list
		// 	Input: ["cat","elephant","dog"]
		//	Output: [3,8,3]

		
		System.out.println("\nQuestion 5. Find string length list");
		List<String> wordList = Arrays.asList("cat", "elephant", "dog");	
		System.out.println("Input = " + wordList);
		List<Integer> lengthOfWord = wordList.stream().map(x -> x.length()).toList();
		System.out.println("Output = " + lengthOfWord);
	
	
		
		// 6. Count strings starting with ‘a’
		// Input: ["apple","banana","ant","car"]
		// Output: 2
		
		System.out.println("\nQuestion 6. Count strings starting with ‘a’");
		List<String> startWord = Arrays.asList("apple", "Abanana", "ant", "car");
		System.out.println("Input = " + startWord);
		Long countA = startWord.stream().filter(x -> x.toLowerCase().startsWith("a")).count();
		System.out.println("Output = " + countA);
		
		
		// 7. Remove empty strings
		// Input: ["java","","spring","","boot"]
		// Output: ["java","spring","boot"]
		
		
		System.out.println("\nQuestion 7. Remove empty strings");
		List<String> emptyWords = Arrays.asList("java", "","spring", "","boot");
		System.out.println("Input = " + emptyWords);
		List<String> noEmptyWords = emptyWords.stream().filter(x -> !x.isEmpty()).toList();
		System.out.println("Output = " + noEmptyWords);
		
		
		
		// 8. Sum of all numbers using stream
		// Input: [1,2,3,4,5]
		// Output: 15
		
		
		System.out.println("\nQuestion 8. Sum of all numbers using stream ");
		List<Integer> numbers = Arrays.asList(1,-2,3,4,5);
		System.out.println("Input = " + numbers);
		Long sum = numbers.stream()
                .map(x -> (long) x)
                .reduce(0L, (accumulator, element) -> accumulator + element);
		System.out.println("Output = " + sum);
		
		
		
		// 9. Find max number
		// Input: [10,25,3,99,45]
		// Output: 99
		
		System.out.println("\nQuestion  9. Find max number");
		List<Integer> maxNumbers = Arrays.asList(-10,-25,-3,-99,-450);
		System.out.println("Input = " + maxNumbers);
		Integer max = maxNumbers.stream().
					  reduce((n1,n2) -> n1>n2 ? n1 : n2)
					  .orElse(0);	
		System.out.println("Output = " + max);
		
		
		
		// 10. Reverse each string in list
		// Input: ["java","api"]
		// Output: ["avaj","ipa"]
		
		
		System.out.println("\nQuestion 10. Reverse each string in list");
		List<String> givenStrings = Arrays.asList("-java", "api");
		System.out.println("Input = " + givenStrings);
		List<String> reverseString =
		        givenStrings.stream()
		                .map(s -> new StringBuilder(s).reverse().toString())
		                .collect(Collectors.toList());

		System.out.println("Output = " + reverseString);
				
		
		// 11. Remove duplicate elements from list
		// Input: [1,2,2,3,4,4,5]
		// Output: [1,2,3,4,5]

		System.out.println("\nQuestion 11. Remove duplicate elements from list");
		List<Integer> numbers1 = Arrays.asList(1,2,2,3,4,4,5);
		System.out.println("Input = " + numbers1);
		List<Integer> uniqueNumbers =
		        numbers1.stream()
		               .distinct()
		               .collect(Collectors.toList());
		System.out.println("Output = " + uniqueNumbers);
		
		
		
		// 12. Sort list in descending order
		// Input: [5,1,9,3]
		// Output: [9,5,3,1]
		
		System.out.println("\nQuestion 12. Sort list in descending order");
		List<Integer> dupElements = Arrays.asList(5,1,9,3);
		System.out.println("Input = " + dupElements);
		List<Integer> withoutDup = dupElements.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
		
		System.out.println("Output = " + withoutDup);
		
		
		// 13. Find second highest number
		// Input: [10,40,30,20]
		// Output: 30

		
		System.out.println("\nQuestion 13. Find second highest number");
		List<Integer> highNum = Arrays.asList(10,40,40,30,20);
		System.out.println("Input = " + highNum);
		Integer secMax =
				highNum.stream()
				.distinct()
				.sorted(Comparator.reverseOrder())
				.skip(1)
				.findFirst()
				.orElse(0);
		
		System.out.println("Output = " + secMax);
		
		
		// 14. Join strings with comma
		// Input: ["Java","Spring","Boot"]
		// Output: "Java,Spring,Boot"

		System.out.println("\n Question 14. Join strings with comma");
		List<String> tech = Arrays.asList("Java","Spring","Boot");
		System.out.println("Input = " + tech);
		String result =
		        tech.stream()
		            .collect(Collectors.joining(","));
		
		System.out.println("Output = \"" + result + "\"");

		
		
		System.out.println("\n Question 15. Frequency of each character");
		String input = "baeada";
		System.out.println("Input = " + input);
		Map<Character, Long> frequency = input.chars()
		                                      .mapToObj(c -> (char) c)
		                                      .collect(Collectors.groupingBy(
		                                          Function.identity(),
		                                          Collectors.counting()
		                                      ));
		System.out.println("Output = " + frequency);
		
		
		
		// 16. Find numbers greater than 50
		// Input: [10,55,60,23,90]
		//  Output: [55,60,90]
		
		
		System.out.println("\nQuestion 16. Find numbers greater than 50");
		List<Integer> nums2 = List.of(10,55,60,23,90);
		System.out.println("Input = " + nums2);
		List<Integer> greaterThan50 =
				nums2.stream()
				.filter(x -> x>50)
				.toList();
		
		System.out.println("Output = " + greaterThan50);
		
		
		
		
		//17. Group strings by length
		// Input: ["a","bb","ccc","dd"]
		// Output: {1=[a], 2=[bb,dd], 3=[ccc]}
		
		
		System.out.println("\n 17. Group strings by length");
		List<String> strByLen = List.of("a","bbb", "ccc", "dd");
		System.out.println("Input = " + strByLen);
		Map<Integer, List<String>> groupByLen =
				strByLen.stream()
				.collect(Collectors.groupingBy(String::length));
		System.out.println("Output = " + groupByLen);
		
		
		// 18. Find first non-repeated character
		// Input: "stress"
		// Output: t
		System.out.println("\n Question 18. Find first non-repeated character");
		String input3 = "stress";
		System.out.println("Input = " + input3);

		Character firstNonRepeated = input3.chars()
		                                  .mapToObj(c -> (char) c)
		                                  .filter(c -> input3.indexOf(c) == input3.lastIndexOf(c))
		                                  .findFirst()
		                                  .orElse(null);

		System.out.println("Output = " + firstNonRepeated); // Output: t
		
		
		
		// 19. Convert List<Integer> to List<String>
		// Input: [1,2,3]
		// Output: ["1","2","3"]
		
		System.out.println("\n Question 19. Convert List<Integer> to List<String>");
		List<Integer> intList = List.of(1,2,3);
		System.out.println("Input = " + intList);
		List<String> strList =
				intList.stream()
				.map(n -> String.valueOf(n))
				.toList();
		
		System.out.println("Output = " + strList);
		
		
		// 20. Count occurrences of each word
		// Input: "java is java and java is fast"
		// Output: {java=3, is=2, and=1, fast=1}
		
		System.out.println("\nQuestion 20. Count occurrences of each word");

		String str = "java is java and java is fast";
		System.out.println("Input = " + str);

		Map<String, Long> occOfWord =
		        Arrays.stream(str.split("\\s+"))
		              .collect(Collectors.groupingBy(
		                      word -> word,
		                      Collectors.counting()
		              ));

		System.out.println("Output = " + occOfWord);

		
		// 21. Partition numbers into even and odd
		// Input: [1,2,3,4,5,6]
		// Output: {even=[2,4,6], odd=[1,3,5]}
		
		
		System.out.println("\nQuestion 21. Partition numbers into even and odd");
		List<Integer> numsToSep = List.of(1,2,3,4,5,6);
		System.out.println("Input = " + numsToSep);
		Map<String, List<Integer>> sepNums =
		        numsToSep.stream()
		                 .collect(Collectors.groupingBy(
		                         n -> n % 2 == 0 ? "Even" : "Odd"
		                 ));

		System.out.println("Output = " + sepNums);
		
		// * 22. Find duplicate elements only
		// Input: [1,2,3,2,4,5,1]
		// Output: [1,2]
		
		System.out.println("\nQuestion 22. Find duplicate elements only");
		List<Integer> dupList = List.of(1,2,3,2,4,5,1);
		System.out.println("Input = " + dupList);
		List<Integer> onlyDup =
				dupList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
				.stream().filter(x-> x.getValue()>1).map(x-> x.getKey()).toList();
		
		System.out.println("Output = " + onlyDup);
		
		
		// 23. Find longest string
		// Input: ["java","microservices","api"]
		// Output: "microservices"
		
		
		System.out.println("Question 23. Find longest string");
		List<String> longWords = List.of("java", "microservices", "api");
		System.out.println("Input = " + longWords);
		String longestWord =
				longWords.stream()
				.reduce((s1,s2) -> 
				s1.length()>=s2.length() ? s1 : s2)
				.orElse(null);
		
		System.out.println("Output = " + longestWord);
				
		
		
		// 24. Find top 3 highest numbers
		// Input: [10,90,30,70,50]
		// Output: [90,70,50]
				
		
		System.out.println("Question 24. Find top 3 highest numbers"); 
		List<Integer> highNums = List.of(10,90,90,70,50);
		System.out.println("Input = " + highNums);
		List<Integer> top3 =
				highNums.stream()
				.distinct()
				.sorted(Comparator.reverseOrder())
				.limit(3)
				.toList();
		
		System.out.println("Output =  " + top3);
		
		
		
		// 25. Flatten list of lists
		// Input: [[1,2],[3,4],[5]]
		// Output: [1,2,3,4,5]

		System.out.println("\n Question 25. Flatten list of lists");
		List<List<Integer>> listOfLists = 
		        List.of(
		                List.of(1,2),
		                List.of(3,4),
		                List.of(5)
		        );

		System.out.println("Input = " + listOfLists);
		List<Integer> flattenedList =
		        listOfLists.stream()
		                   .flatMap(List::stream)
		                   .toList();

		System.out.println("Output = " + flattenedList);

		
		
		// * 26. Find sum of squares of even numbers
		// Input: [1,2,3,4,5]
		// Output: 20 (2² + 4²)
		
		System.out.println("\nQuestion 26. Find sum of squares of even numbers");
		List<Integer> squareNum = List.of(1,2,3,4,5);
		System.out.println("Input = " + squareNum);
		
		int squareSum = 
				squareNum.stream()
				.filter(x -> x%2==0)
				.mapToInt(x -> x*x)
				.sum();
		
		System.out.println("Output = " + squareSum);
		
		
		//27. Sort map by value
		// Input: {A=3, B=1, C=2}
		// Output: {B=1, C=2, A=3}
		
		
		System.out.println("\nQuestion 27. Sort map by value");
		Map<String, Integer> input31 = new HashMap<>();
		input31.put("A", 3);
		input31.put("B", 1);
		input31.put("C", 2);

		System.out.println("Input = " + input31);

		Map<String, Integer> sortedMap = input31.entrySet()
		                                      .stream()
		                                      .sorted(Map.Entry.comparingByValue())
		                                      .collect(Collectors.toMap(
		                                          Map.Entry::getKey,
		                                          Map.Entry::getValue,
		                                          (e1, e2) -> e1,
		                                          LinkedHashMap::new
		                                      ));

		System.out.println("Output = " + sortedMap); // Output: {B=1, C=2, A=3}
		
		
		// 28. Find employee with highest salary (using stream)
		// Input: List of Employees
		// Output: Employee object with max salary
		System.out.println("\nQuestion 28. Find employee with highest salary");

		// Employee class
		class Employee {
		    String name;
		    double salary;
		    
		    public Employee(String name, double salary) {
		        this.name = name;
		        this.salary = salary;
		    }
		    
		    public double getSalary() {
		        return salary;
		    }
		    
		    @Override
		    public String toString() {
		        return "Employee{name='" + name + "', salary=" + salary + "}";
		    }
		}

		List<Employee> employees = Arrays.asList(
		    new Employee("John", 50000),
		    new Employee("Alice", 75000),
		    new Employee("Bob", 60000),
		    new Employee("Sarah", 90000),
		    new Employee("Mike", 55000)
		);

		System.out.println("Input = " + employees);

		Employee highestPaid = employees.stream()
		                                .max(Comparator.comparingDouble(Employee::getSalary))
		                                .orElse(null);

		System.out.println("Output = " + highestPaid);
		
		
		
		
		// 29. Find common elements between two lists
		// Input: [1,2,3,4] and [3,4,5,6]
		// Output: [3,4]
		
		System.out.println("\n Question 29. Find common elements between two lists");

		List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
		List<Integer> list2 = Arrays.asList(3, 4, 5, 6);

		System.out.println("Input: list1 = " + list1);
		System.out.println("Input: list2 = " + list2);

		List<Integer> commonElements = list1.stream()
		                                    .filter(list2::contains)
		                                    .collect(Collectors.toList());

		System.out.println("Output = " + commonElements); 
		
		
		//30. Find kth smallest element
		//Input: [9,1,5,3,7], k=2
		//Output: 3
		
		System.out.println("\n Question 30. Find kth smallest element");

		List<Integer> numbers11 = Arrays.asList(9, 1, 5, 3, 7);
		int k = 2;

		System.out.println("Input = " + numbers11);
		System.out.println("k = " + k);

		Integer kthSmallest = numbers11.stream()
		                             .sorted()
		                             .skip(k - 1)
		                             .findFirst()
		                             .orElse(null);

		System.out.println("Output = " + kthSmallest); // Output: 3
		
		
		
		
		
		
		
		
		
		
	}	
}
