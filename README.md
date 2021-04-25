# user-management-system-java-mysql

This is a core java console-based program which shows how to add a new user and how to login using those user details by connecting your app to mysql database.

This project depicts how you can have two separate databases and have connectivity with them. So please create two databases with any names and create following two tables in them, one in each database:

create table `users` (
	`user_id` varchar (150),
	`user_name` varchar (150),
	`user_age` int (50),
	`user_email` varchar (150)
); 


create table `login` (
	`username` varchar (150),
	`password` varchar (150)
); 
