# Emailer-System
This project replicate an emailer system. Using JAVA,JDBC, MYSQL,XAMPP and ECLIPSE IDLE.

# Developed By-- Ravi Pratap

Before executing the program you have to make 6 tables in database as described below

# DATABASE:
There are following 5 tables have been used here.
1. details(emailid primary key, fname, lname,no,password) - To store the details of all the registered users.
2. draft(id primary key,emailid,savedmessages) - To store all the messages saved in draft folder.
3. send(id primary key,senderemailid,sentmsg,sentto)- To store all sent messages
4. receive(id primary key,receiveremailid,receivedmsg,receivedfrom) - To store all the received massages.
5. trash(id primary key,emailid,msg) - To store messages moved to trash folder.

After creating tables you have to run .java files using any IDLE.

# JAVA FILES:
There are total 6 '.JAVA' files within a single package.
To start the program you have to run only 'Home.java' file.
