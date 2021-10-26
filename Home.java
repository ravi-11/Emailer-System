package emailprj;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Home {
	public static void main(String[] args) {
		System.out.print("Enter \n1: SignUP\n2: Login\n3: Exit");
		Scanner sc= new Scanner(System.in);
		int c= sc.nextInt();
		if(c==1) {
			Signup.main(args);
	}	
		else if(c==2) {
			Login.main(args);
		}
	sc.close();
}
}