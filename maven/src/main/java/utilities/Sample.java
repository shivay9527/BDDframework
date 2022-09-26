package utilities;

public class Sample extends SeleniumUtility {

	public static void main(String[] args) {
		SeleniumUtility obj = new SeleniumUtility();
//		WebDriver driver = obj.setUp("chrome", "https://www.google.com");
		String username = obj.readPropFile("username");
		System.out.println(username);
	}

}