package emailprj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Signup {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your first name");		
		String fname= sc.nextLine();
		if(fname.length()==0) {
			System.out.println("WRONG!! You have not entered anything");
			Signup.main(args);
		}
		System.out.println("Enter your last name");
		String lname= sc.nextLine();
		System.out.println("Enter your email-ID");
		String email= sc.nextLine();
		if(email.length()==0) {
			System.out.println("WRONG!! You have not entered anything");
			Signup.main(args);
		}
		System.out.println("Enter your password");
		String pass= sc.nextLine();
		if(pass.length()==0) {
			System.out.println("WRONG!! You have not entered anything");
			Signup.main(args);
		}
		System.out.println("Enter your phone number");
		long no= sc.nextLong();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
			String sql = "INSERT INTO details (emailid,fname,lname,no,password) "
					+ "VALUES (?, ?, ?, ?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, fname);
			statement.setString(3, lname);
			statement.setLong(4, no);
			statement.setString(5, pass);
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Registered Successfully");
				System.out.println("Press 1 for Login");
				int l= sc.nextInt();
				if(l==1)
				Login.main(args);
			}
		} 
		catch(SQLException ee) {
			System.out.println("This email id already exists Please enter another email id\n");
			Signup.main(args);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		sc.close();
	}
}
