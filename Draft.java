package emailprj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Draft{
	public void function_to_save_msg(String email,String msg) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
			PreparedStatement statement = conn.prepareStatement("INSERT INTO draft (id,emailid,savedmsg) "
					+ "VALUES (?, ?, ?)");
			ResultSet rs = statement.executeQuery("select * from draft");
			int count=0;
			while(rs.next())
				count=rs.getInt(1);
			statement.setInt(1, count+1);
			statement.setString(2, email);
			statement.setString(3, msg);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("msg saved to draft");
			}
		} 
		catch(SQLException ee) {
			System.out.println("There is some problem");
		}
	}
}

