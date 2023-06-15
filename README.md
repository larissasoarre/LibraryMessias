# Library Messias
This project is a Java application developed as part of a mentorship program by Palo Alto Networks in collaboration with the university FIAP. The application aims to provide a comprehensive solution for efficient book management in a library setting.

The application encompasses fundamental CRUD operations (Create, Read, Update, Delete) to seamlessly interact with book records. 

The software includes a Sign-Up page with password encryption to protect user accounts and personal information to ensure data security. Access to the application is granted through a Login page, ensuring authorized usage. The application stores all data in a database, facilitating data integrity and enabling efficient information retrieval.


## ✨ Features
The application contains the following features:

* CRUD Operations: Users can effortlessly add new books to the system, modify existing book information, review the complete inventory of books, and remove specific books from the system when necessary.
* Sign-in Page: Offers a secure authentication system, allowing users to create accounts with encrypted passwords using the BCrypt algorithm. That ensures that user information remains protected.
* Login Page: Provides a secure gateway for registered users to access their accounts. It employs the BCrypt algorithm to compare and verify passwords, enhancing the overall security of the login process.


## 👥 User Stories
During the creation of this project, the following user stories were identified:

1. As a librarian, I want to easily add new books to the library by providing their relevant details, including ISBN, title, author, genre, publisher, price, the number of books available, and edition.
2. As a librarian, I want the ability to update book records whenever necessary.
3. As a librarian, I need a clear and intuitive visualization of all available books in the inventory.
4. As a librarian, I want to be able to remove books from the library's software that are no longer available or are considered inappropriate.
5. As a librarian, I want to create an account by signing up with my credentials to access the library management features.
6. As the library's owner, I prioritize the secure storage of employees' passwords within the system, utilizing robust cryptographic algorithms to safeguard sensitive information.
7. As a librarian, I expect a reliable and user-friendly login mechanism to access the library management system.


## 📝 Requirements
To run this software, please ensure that the following requirements are met:

* Java Version: The software is compatible with Java version 20 or later.
* BCrypt Version: The software relies on BCrypt version 0.4.3, which can be found in the lib folder. Please ensure that you have this version installed.
* Database Connection: If you plan to connect to an Oracle database from Java, be sure to have the ojdbc.jar dependency in place to guarantee a successful connection.
* Database Configuration: The software requires a configured database. While Oracle SQL Developer was used for this project, you have the flexibility to choose a database that best suits your needs.

By meeting these requirements, you will be able to successfully run the software.


## ⚙️ Installation
1. Clone the repository:
    Run the command bellow to clone this repository.
    
    ```
    git clone <repository_url>
    ```
    
2. Set up the development environment:
3. Choose your preferred IDE and import the project.
4. Create the required database tables:
    Execute the provided SQL script here to create the necessary tables in the database.
    Note: If you decide to rename the tables or attributes, ensure that you update the corresponding SQL script in the BookSaleDAO.java and UserLoginDAO.java files located in the dao package within the src folder. This ensures that the application reflects the renamed information accurately.
5. Configure the database connection details:
    Open the DBManager.java file located in the dao package within the src folder.
    Replace the existing code with your own database connection details. 
    
    ```
    connection = DriverManager.getConnection("url", "username", "password");
    ```

By following these steps, you can successfully install and configure the application for your specific environment and database.


## 📜 SQL Script
To ensure the smooth operation of the application, please execute the following SQL script to create the necessary tables and attributes in your database.

```sql
-- Table: T_PBL_USERLOGIN
CREATE TABLE T_PBL_USERLOGIN ( 
ID_USERLOGIN NUMBER(10) NOT NULL, 
NM_USERNAME VARCHAR2(20) NOT NULL, 
DS_PASSWORD VARCHAR2(100) NOT NULL, 
CONSTRAINT PK_YOUR_TABLE_NAME PRIMARY KEY (ID_USERLOGIN), 
CONSTRAINT UK_YOUR_TABLE_NAME UNIQUE (NM_USERNAME)
);

-- Table: T_PBL_ORDER_BOOK
CREATE TABLE T_PBL_ORDER_BOOK (
ID_ORDER_BK NUMBER(10) NOT NULL,
CD_ORDER_ISBN NUMBER(13,0) NOT NULL,
NM_ORDER_TITLE VARCHAR2(50) NOT NULL,
DS_GENRE VARCHAR2(30) NOT NULL,
DS_PUBLISHER VARCHAR2(30) NOT NULL,
VL_SALE NUMBER(5,2) NOT NULL,
QT_ORDER_AVAILABLE NUMBER(3,0) NOT NULL,
NM_AUTHOR VARCHAR2(50) NOT NULL,
DS_EDITION NUMBER(2,0),
CONSTRAINT PK_YOUR_TABLE_NAME PRIMARY KEY (ID_ORDER_BK)
);
```

## 🌐 Usage
Once you have completed the installation and configuration process, you can start using the application. To get started, follow these steps:

1. Export the project as a runnable JAR file and designate the `LoginPage` as the launch page.
2. Open your command-line interpreter and execute the following code to launch the application:

    ```bash
    java -jar FileName.jar
    ```

    Note: Make sure to navigate to the directory where your file is saved and replace `"FileName"` with the actual name of your file.
3. If you are a new user, sign up using your credentials to create an account. If you have already created an account, use your login credentials to sign in and access the library management features. 
4. Once you have gained access to the system, you can perform various inventory management operations.


## 🔍 Troubleshooting
If you encounter issues with the application, please check the following:
* Ensure that the database connection settings are correctly configured.
* Verify that the required libraries and dependencies are installed with the correct versions.
* Double-check that the provided SQL script was executed successfully and the required tables exist in the database.


## ❓ FAQ
**Q:** How can I reset my password if I forget it?

**A:** Unfortunately, password recovery functionality is not currently implemented in the application. It is recommended to create a new account in such cases.


## 🧾 License
This project is licensed under the [Specify the license].


## 🥰 Acknowledgements
I'd like to thank **FIAP University** for their support throughout the project. Their educational resources and guidance have been invaluable in our journey.

Additionally, I'd like to extend my sincere gratitude to **Palo Alto Networks** for providing mentorship during the development of this project. Their expertise and guidance have played a significant role in the cybersecurity matters of this project.

