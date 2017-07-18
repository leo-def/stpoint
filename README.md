# stpoint
Application for languages practing , this application teaches the main expressions used in certain places and situations

Tecnologies:
	Frontend: AngularJS 1;
	Backend: Spring Boot;
	Automation Tool: Maven;
	Production Deploy: Wildfly / Embedded Apache Tomcat;
	Session Persistence: Redis;
	Files Storage:  S3 / S3Ninja;
	Email: spring-boot-starter-mail / JNDI;
	Data Persistence: JNDI(MariaDB) / hsqldb;
	Hosting: AWS EC2 / Localhost
	
Configuration (It's recomended execute the application in some VM with necessary environment variables configured)
	DEV:
		1- Execute Redis in path 'softwares'
		2- Execute S3NINJA in path 'softwares'
		3- Create a S3Ninja bucket 'stpointapp'
		4- set the following environment variables
				DEVamazonAccessKey
				DEVamazonSecretkey
				DEVamazonEndpoint
				DEVgmailUsername
				DEVgmailPassword
				superuserName
				superuserUsername
				superuserPassword
				
	PROD:
		1- Execute Redis in server computer
		2- Create a AWS S3 bucket stpointapp
		3- Install and run MariaDB
		4- Install and run Wildfly
		5- Configure the following JNDI in Wildfly
			java:jboss/mail/STPoint
			java:jboss/datasources/STPointDS (MariaDB)
		6- set the following environment variables
				PRODamazonAccessKey
				PRODamazonSecretkey
				PRODamazonEndpoint
				PRODamazonHost
				PRODwildflyHostname
                PRODwildflyPort
                PRODwildflyusername
                PRODwildflyPassword
				superuserName
				superuserUsername
				superuserPassword