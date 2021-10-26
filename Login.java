package emailprj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Email id");
		String email= sc.nextLine();
		System.out.println("Enter password");
		
		String pass= sc.nextLine();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/persons", "root", "");
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("select * from details where emailid=? and password=?");
			pst.setString(1,email);
			pst.setString(2,pass);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				System.out.println("\n\nlogged in\n\n");
				 while(true){
				
				System.out.println("now press\n1 for:compose mail \n2 to:Watch your draft messages\n3 to:Watch your sent messages \n4 to:watch your received messages\n5 to:delete\n6 to:watch your trash messages\n7 to:Log out");
				int check=sc.nextInt();
				if(check==1) {
					System.out.println("write your message");
					sc.nextLine();
					String msg= sc.nextLine();
					System.out.println("press\n 1:save to draft\n2:send to another user");
					int composechk= sc.nextInt();
					if(composechk==1) {
						Draft obj= new Draft();
						obj.function_to_save_msg(email, msg);
						
					}
					else if(composechk==2) {
						System.out.println("please enter the email id of another registered user to whom you want to send the message ");
						sc.nextLine();
						String recvr_email= sc.nextLine();
	
						PreparedStatement pstt=(PreparedStatement) con.prepareStatement("select * from details where emailid=? ");
						pstt.setString(1,recvr_email);
						ResultSet rss = pstt.executeQuery();
						
						if(rss.next()) {
							SendAnother obj1= new SendAnother();
							obj1.receiver_sender_fn(email, recvr_email, msg);
						}
						else {
							System.out.println("This is not registered email id.");
						}
					}
						
					}
				else if(check==2) {
					//show draft messages
					String sql="select * from draft where emailid=?";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, email);
					ResultSet rss = statement.executeQuery();
					System.out.println("id        emaild           message");
					while (rss.next())
						System.out.println(rss.getInt(1)+"  "+rss.getString(2) + "     " + rss.getString(3));

				}
				else if(check==3) {
					//show sent messages
					String sql="select * from send where senderemailid=?";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, email);
					ResultSet rss = statement.executeQuery();
					System.out.println("id     sender_emaild               message                          sent_to ");
					while (rss.next())
						System.out.println(rss.getInt(1) + "     " + rss.getString(2)+ "             " + rss.getString(3)+ "             " + rss.getString(4));

				}
				else if(check==4) {
					//show received messages
					
					String sql="select * from receive where receiveremailid=?";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, email);
					ResultSet rss = statement.executeQuery();
					System.out.println("id    receiver_emaild                 message                  received_from");
					while (rss.next())
						System.out.println(rss.getInt(1) + "      " + rss.getString(2)+ "         " + rss.getString(3)+ "         " + rss.getString(4));
				}
				else if(check==5) {
					Delete.delete(email);
				}
				else if(check==6) {
					String sql="select * from trash where emailid=?";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, email);
					ResultSet rss = statement.executeQuery();
					System.out.println("id    emaild                 message");
					while (rss.next())
						System.out.println(rss.getInt(1) + "      " + rss.getString(2)+ "         " + rss.getString(3));
				}
				else if(check==7){
					con.close();
					break;
				}
				else {
					System.out.println("Enter right choice\n");
				}

			}
				}
			else {
				System.out.println("wrong id or password");
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		sc.close();
	}

}