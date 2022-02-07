# WIF3007-UMLibraryManagementSystem

## Introduction

This is the repository for our team submission for WIF3006 Component-based Software Engineering group assignment for Semester 1, 2021/2022 session. 

Group Members:
1. Muhammad Rahiman bin Abdulmanab
2. Nur Faidz Hazirah binti Nor'Azman
3. Nur Hazirah binti Abdul Razak
4. Ajwad bin Alias
5. Mohd Syahruddin bin Syahrir
6. Farhan Sadiq

## Implementation

Due to the adoption of the Spring component model, our application consists of two separate projects: LMSSpringBeans and LMSSpringClient. We primarily used NetBeans IDE throughout the development of the project since it is a popular IDE for Java-based projects.

Since this project is graphical user interface (GUI) based and utilises the JavaFX external library, we opted to develop the application using Maven, as it allows us to easily configure JavaFX by simply specifying the modules and the versions you want in the file known as pom.xml, and the build system will download the required modules, including the native libraries for the application itself.

Another implication of using Maven is that, unlike what was taught in class as well as the examples given in the lab practice for Spring component model, the installation of Spring using Maven is different in that instead of downloading the .jar files from Spring’s website (https://repo.spring.io/ui/native/release/org/springframework/spring/) and manually adding them to each project, we only need to specify the dependencies inside Maven’s pom.xml file by referring to the Spring module names and version inside Maven Repository (https://mvnrepository.com/artifact/org.springframework).

## Screenshots

### A. Sign In

![image](https://user-images.githubusercontent.com/50654608/152884621-585010bc-ba3a-4067-b4cc-417d1a958e03.png)

### B. Librarian Main Menu

![image](https://user-images.githubusercontent.com/50654608/152884629-cd052b9d-6495-475c-8a95-4d53978b3cd9.png)

### C. Librarian Manage Books

![image](https://user-images.githubusercontent.com/50654608/152884640-72422360-bb55-4e64-bf63-da90b50a3c44.png)

### D. Librarian Manage Book Reservations

![image](https://user-images.githubusercontent.com/50654608/152884651-77f8f519-fe15-460e-af48-3e1ae6613867.png)

### E. Librarian View Issued Books

![image](https://user-images.githubusercontent.com/50654608/152884662-5e3b73b4-90a4-49af-99cc-154c04e17b74.png)

### F. Member Main Menu

![image](https://user-images.githubusercontent.com/50654608/152884677-67f3cecd-6311-4f8e-b126-f1a65a46f0b3.png)

### G. Member Borrow Books

![image](https://user-images.githubusercontent.com/50654608/152884690-bcac52c1-2f98-4054-86b5-5c1b05cda786.png)

### H. Member Manage Book Reservations

![image](https://user-images.githubusercontent.com/50654608/152884699-17996bdc-3825-468c-9627-6d26d9527ebd.png)

### I. Member Manage Issued Books

![image](https://user-images.githubusercontent.com/50654608/152884710-24b1de2c-a50f-4cee-bf1f-20b669b02281.png)

### J. About Us

![image](https://user-images.githubusercontent.com/50654608/152884722-ba15d17c-ae63-475b-81ad-96226941ddf7.png)
