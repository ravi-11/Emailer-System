package emailprj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
   public static void delete(String email) throws SQLException, ClassNotFoundException {
	   System.out.println("1: Delete from draft box \n2:Sent box\n3:Received box");
	   Scanner sc= new Scanner(System.in);
	   int d=sc.nextInt();
	   if(d==1) {
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
		   String sql="select * from draft where emailid=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rss = statement.executeQuery();
			System.out.println("id    emailid         message");
			while (rss.next())
				System.out.println(rss.getInt(1) + "   " + rss.getString(2)+"       "+ rss.getString(3));
			    
			System.out.println("Which id no do you want to delete");
			int idno=sc.nextInt();
			String sqql="select * from draft where id=?";
			PreparedStatement sm = con.prepareStatement(sqql);
			sm.setInt(1, idno);		
			ResultSet r = sm.executeQuery();
			r.next();
			String message=r.getString(3);
			System.out.println("1: Delete forever \n2:Move to trash");
			int i= sc.nextInt();
			if(i==1) {
		   PreparedStatement smt = con.prepareStatement("delete from draft where id=?");
			smt.setInt(1, idno);
			smt.executeUpdate();
			System.out.println("Message deleted successfully");
			}
			else if(i==2) {
				
				String sqll="INSERT INTO trash (id,emailid,msg)"+ "VALUES (?,?,?)";
				PreparedStatement smtt = con.prepareStatement(sqll);
				ResultSet rs = smtt.executeQuery("select * from trash");
				int count=0;
				while(rs.next())
					count=rs.getInt(1);
				smtt.setInt(1,count+1);
				smtt.setString(2,email);
				smtt.setString(3, message);
				smtt.executeUpdate();	
				PreparedStatement smt = con.prepareStatement("delete from draft where id=?");
				smt.setInt(1, idno);
				smt.executeUpdate();
				System.out.println("Message moved to trash");
			}
	   }
	   else if(d==2) {
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
		   String sql="select * from send where senderemailid=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rss = statement.executeQuery();
			System.out.println("id   sender_emailid         messages              sent_to");
			while (rss.next())
				System.out.println(rss.getInt(1) + "   " + rss.getString(2)+"         "+ rss.getString(3)+"         "+ rss.getString(4));
			System.out.println("Which id no do you want to delete");
			int idno=sc.nextInt();
			String sqql="select * from send where id=?";
			PreparedStatement sm = con.prepareStatement(sqql);
			sm.setInt(1, idno);		
			ResultSet r = sm.executeQuery();
			r.next();
			String message=r.getString(3);
			System.out.println("1: Delete forever \n2:Move to trash");
			int i= sc.nextInt();
			if(i==1)
			{
		   PreparedStatement smt = con.prepareStatement("delete from send where id=?");
			smt.setInt(1, idno);
			smt.executeUpdate();
			System.out.println("Message deleted successfully");
			}
            else if(i==2) {
				
				String sqll="INSERT INTO trash (id,emailid,msg)"+ "VALUES (?,?,?)";
				PreparedStatement smtt = con.prepareStatement(sqll);
				ResultSet rs = smtt.executeQuery("select * from trash");
				int count=0;
				while(rs.next())
					count=rs.getInt(1);
				smtt.setInt(1,count+1);
				smtt.setString(2,email);
				smtt.setString(3, message);
				smtt.executeUpdate();	
				PreparedStatement smt = con.prepareStatement("delete from send where id=?");
				smt.setInt(1, idno);
				smt.executeUpdate();
				System.out.println("Message moved to trash");
			}
	   }
	   else {
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
		   String sql="select * from receive where receiveremailid=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rss = statement.executeQuery();
			System.out.println("id    receiver_emailid         message             received_from");
			while (rss.next())
				System.out.println(rss.getInt(1) + "   " + rss.getString(2)+"         "+ rss.getString(3)+"         "+ rss.getString(4));
			System.out.println("Which id no do you want to delete");
			int idno=sc.nextInt();
			String sqql="select * from receive where id=?";
			PreparedStatement sm = con.prepareStatement(sqql);
			sm.setInt(1, idno);		
			ResultSet r = sm.executeQuery();
			r.next();
			String message=r.getString(3);
			System.out.println("1: Delete forever \n2:Move to trash");
			int i= sc.nextInt();
			if(i==1) {
		   PreparedStatement smt = con.prepareStatement("delete from receive where id=?");
			smt.setInt(1, idno);
			smt.executeUpdate();
			System.out.println("Message deleted successfully");
			}
			else if(i==2) {
				String sqll="INSERT INTO trash (id,emailid,msg)"+ "VALUES (?,?,?)";
				PreparedStatement smtt = con.prepareStatement(sqll);
				ResultSet rs = smtt.executeQuery("select * from trash");
				int count=0;
				while(rs.next())
					count=rs.getInt(1);
				smtt.setInt(1,count+1);
				smtt.setString(2,email);
				smtt.setString(3, message);
				smtt.executeUpdate();	
				PreparedStatement smt = con.prepareStatement("delete from receive where id=?");
				smt.setInt(1, idno);
				smt.executeUpdate();
				System.out.println("Message moved to trash");
			}
	   }
	   
   }
}