package qa.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class InterviewTests {

	WebDriver driver;

	@Test
	public void windowsHandler() {
		driver = new ChromeDriver();
		String parentWindow = driver.getWindowHandle();
		/*
		 * after clicking many tabs
		 */
		List<String> allWindows = new ArrayList(driver.getWindowHandles());
		/*
		 * use SET for windows handles
		 */

		Set<String> trueWindows = driver.getWindowHandles();

		Map<String, String> keys = new HashMap<>();

		for (String x : trueWindows) {
			driver.switchTo().window(x);
			keys.put(x, driver.getTitle());
		}

		while (true) {

		}

	}

	@Test
	public void datePicker(String selectDate) {
		String[] date = selectDate.split("-");
		;
		String addDay = date[0];
		String month = date[1];
		String year = date[2];

		driver.findElement(By.id("date picker")).click();

		while (!driver.findElement(By.id("datepicker ui")).getText().equals(month)) {
			driver.findElement(By.id("datepicker ui")).click();

		}

		while (!driver.findElement(By.id("datepicker ui")).getText().equals(year)) {
			driver.findElement(By.id("datepicker ui")).click();

		}

		List<WebElement> daysList = driver.findElements(By.id("table//selector//td/2"));
		for (WebElement x : daysList) {
			x.getText().equals(addDay);
			x.click();
			break;
		}

	}

	@Test
	public void duplicate() {
		String test = "hello";
		Map<Character, Integer> check = new HashMap<>();

		for (char c : test.toCharArray()) {
			check.put(c, check.getOrDefault(c, 0) + 1);
		}
		for (Map.Entry<Character, Integer> x : check.entrySet()) {
			if (x.getValue() > 1) {
				System.out.println(x);
			}

			System.out.println("\n--------------------------------------------\n");

		}
		System.out.println(check.entrySet());
	}

	@Test
	public void fibanocci() {
		int a = 0;
		int b = 1;
		int temp;

		for (int i = 0; i < 10; i++) {
			System.out.print(a + ", ");
			temp = a + b;
			a = b;
			b = temp;

		}
	}

	@Test
	public void reverseString() {
		/*
		 * manual way
		 */

		String input = "Man thats crazy";

		char[] arr = input.toCharArray();
		// String reverse = "";
		int start = 0;
		char[] revArr = new char[arr.length];
		for (int i = input.length() - 1; i >= 0; i--) {
			// reverse = reverse+arr[i];
			revArr[start] = arr[i];
			start++;
		}
		// System.out.println(reverse);
		String changearr = new String(revArr);
		System.out.println(changearr);
	}

	@Test
	public void palindromeChecker() {
		String input = "mam";
		input = input.toLowerCase();
		char[] arr = input.toLowerCase().toCharArray();

		char[] revArr = new char[arr.length];
		int loopCount = 0;
		for (int i = input.length() - 1; i >= 0; i--) {
			revArr[loopCount] = arr[i];

			loopCount++;
		}
		String reverse = new String(revArr);
		if (input.equals(reverse)) {
			System.out.println("yesy");
		}

	}

	@Test
	public void duplicateChecker() {
		String input = "wel that is the same of etc";
		char[] in = input.toCharArray();
		boolean[] visited = new boolean[in.length];

		for (int i = 0; i < in.length; i++) {
			if (visited[i]) {
				continue;
			}

			int count = 1;

			for (int j = i + 1; j < in.length; j++) {
				if (in[i] == in[j]) {
					count++;
					visited[j] = true;
				}
			}

			System.out.println("Character '" + in[i] + "' has " + count + " occurrences.");

			visited[i] = true;
		}
	}

	@Test
	public void stringSort() {
		{
			String input = " hello";
			input = input.trim();
			char[] arr = input.toCharArray();
			Arrays.sort(arr);
			;
			input = new String(arr);
			System.out.println(new String(arr));

		}

		{

			String input = "fellow";

			char[] arr = input.toCharArray();

			for (int i = 0; i < input.length(); i++) {
				// compare the one char to another loop

				for (int j = 0; j < input.length() - 1; j++) {
					if (arr[i] > arr[j + 1]) {
						char temp = arr[i];
						arr[i] = arr[j + 1];
						arr[j + 1] = temp;

					}
				}

			}
			String rev = new String(arr);
			System.out.println(arr);
		}
	}

	@Test
	public void arrayToList() {
		Integer[] arr = { 1, 2, 4, 5, 5 };
		Set<Integer> cc = new HashSet<>(Arrays.asList(arr));
		String[] array = { "apple", "banana", "apple", "orange" };
		Set<String> set = new HashSet<>(Arrays.asList(array));
	}

	@Test
	public void streamTest() {
		List<Integer> arr = Arrays.asList(1, 2, 34, 5, 90, 90, 6);
		List<Integer> even = arr.stream().distinct().collect(Collectors.toList());
		for (int x : even) {
			System.out.println(x);
		}
	}

	@Test
	public void stringStream() {
		List<String> arr = Arrays.asList("hello", "Zello", "mellow");
		List<String> st = arr.stream().sorted().collect(Collectors.toList()).reversed();
		for (String x : st) {
			System.out.println(x);
		}
	}

	@Test
	public void stringStreamPrint() {
		List<String> arr = Arrays.asList("hellob", "helloa", "mellow");
		arr.stream()
		.sorted()
		.forEach(x -> System.out.println(x));
		record x(String one, int xo) {
		};

		x c = new x("hello", 1);
		System.out.println(c.one + "  " + c.xo);

	}
	
	@Test
	public void jdbcTest() {
		 String url = "jdbc:mysql://2233121948:3306/dgmsexmjun23";
	        String user = "root";
	        String pass = "";
	        
	        try {
	            Connection conn = DriverManager.getConnection(url, user, pass);
	            System.out.println("✅ Connected to DB!");
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	}

}
