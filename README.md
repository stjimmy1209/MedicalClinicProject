# MedicalClinicProject
Database class project

		Project Breakdown and timeline
First deliverable: Each group will develop an E-R model for the application and upload it as any type of image format due by March 28.
The second deliverable: The E-R model will be translated into a relational schema implemented by an SQL script. The script should run on a PostgreSQL database (or a comparable database). In addition to defining the tables and constraints the script should create indexes where appropriate due by April 10.
Last deliverable is an application that uses the relational schema defined in the first two deliverables. Due by April 27.
Submittal
Each individual will submit the part they worked on. If the group decided to submit one final version, each team member’s contribution has to be stated. The project should be submitted via blackboard.
Project Description
The objective of the project is to create a medical clinic application, allowing scheduling appointments, tracking medical record,  seeing doctor calendar, running reports.
Database Requirements
Login
Information about the users of the system must be maintained. There will be 4 types of users with different privileges (admin, scheduler, medicalStaff, patient). Each user will have the following fields:
•	User ID
•	Privilege 
•	LoginTime
•	LogoutTime
Diagnostic
The diagnostic is identified by:
•	Id
•	Cost
•	Category Type (Enum):
o	1: Lab
o	2: MRI		
o	3: Xray
o	Office Visit
Patient
The patient information includes:
•	First Name 
•	Last Name
•	Address
•	Patient ID
EmployeeFirst Name 
•	Last Name
•	Staff ID
•	Job type 
o	1: Medical Staff
o	2: Admin
o	3 Scheduler
Order
All purchase transaction will be logged with the following information
•	Order ID
•	Customer ID
•	StaffID
•	DiagnosticID
•	Results
Application requirements
Login
The application must provide a login portal for user to login. The application will validate the user credentials and privileges. For this project you are only required to provide the implementation of managers.
Privileges
1-	Users with admin privileges can do the following:
a.	Schedule an appointment
b.	Create new patient
c.	Create a new user account
d.	Access the business reporting 

2-	Users with Doctor/Staff privileges can do the following:
a.	View and update patient record
b.	Create an Order
c.	View calendar and schedule appointment with medical staff

3-	Users with Patient privileges can do the following:
a.	View the orders
b.	View bills

4-	Users with Scheduler privileges can do the following:
a.	View the orders
b.	View bills
c.	View calendar and schedule appointment with medical staff


Order transaction
Doctors can order diagnostics for a patient, once the diagnostics are completed and results are available. The system gets updated by medical staff.
Reporting and analytics
The admin will have the capability of running business analytics reports that will help them monitor business operations.
•	Total revenue from all diagnostic 
•	Total revenue from each doctor visit
•	Allow patient to view their bills, appointment and medical record


