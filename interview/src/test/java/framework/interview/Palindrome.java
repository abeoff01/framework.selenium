package framework.interview;

public class Palindrome {
	public void check(String input) {
		
		input = input.replaceAll("\\s","").toLowerCase();
		
		char []arr1 = input.toCharArray();
		char []arr2 = new char[arr1.length];
		
		int loopCount = 0;
		for(int i =arr1.length-1;i>=0;i--) {
			arr2[loopCount]=arr1[i];
			loopCount++;
			
		}
		
		String one = new String(arr2);
		
		if(one.equals(input)) {
			System.out.println("The string of \""+input+"\" is palindrome");
		}
		else {
			System.out.println("The string of \""+input+"\" is not a palindrome");
		}
	}
	
	
	
	public void noNewArray(String input) {
	input = input.replaceAll("\\s", "").toLowerCase();
	
	int left = 0;
	int right = input.length()-1;
	
	boolean palindrome = true;
	
	while(left<right) {
		if(input.charAt(left)!=input.charAt(right)) {
			
			palindrome = false;
			break;
		}
		
		left++;
		right--;
	}
	
	
	if(palindrome) {
		System.out.println(input+ " is palindrome");
	}
	else {
		System.out.println(input+ " is not a palindrome");
	}
	
	
	
	
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		String [] list = {"hello","done", "sin", "mom"
				
		};
		
		Palindrome pp = new Palindrome();
		for(String x:list) {
		pp.noNewArray(x);
		}
	}

}
