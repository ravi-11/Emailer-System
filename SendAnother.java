package emailprj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SendAnother {
	void receiver_sender_fn(String email, String recvr_email,String msg ) throws ClassNotFoundException {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
		String sql = "INSERT INTO send (id,senderemailid,sentmsg,sentto) "
				+ "VALUES (?, ?, ?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet rs = statement.executeQuery("select * from send");
		int count=0;
		while(rs.next())
			count=rs.getInt(1);
		statement.setInt(1, count+1);
		statement.setString(2, email);
		statement.setString(3, msg);
		statement.setString(4, recvr_email);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("message sent");
		}
	} 
	catch(SQLException e) {
		System.out.println("There is some problem");
	}
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
		String sqll = "INSERT INTO receive (id,receiveremailid,receivedmsg,receivedfrom) "
				+ "VALUES (?, ?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sqll);
		ResultSet rs = statement.executeQuery("select * from receive");
		int count=0;
		while(rs.next())
			count=rs.getInt(1);
		statement.setInt(1,count+1);
		statement.setString(2,recvr_email);
		statement.setString(3, msg);
		statement.setString(4, email);
		statement.executeUpdate();
//		int rowsInserted = statementt.executeUpdate();
//		if (rowsInserted > 0) {
//			System.out.println("msg saved to receive folder");
//		}
//		else {
//			System.out.println("msg did not saved to receive folder");
//		}
	} 
	catch(SQLException ee) {
		System.out.println("There is some problem");
	}
	}
}
