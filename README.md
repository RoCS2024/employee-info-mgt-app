Employee-Info-Mgt-App Setup

First is setup the Database
There are 2 database you need for Employee-info-mgt-app 
The Login Table and the other one is Employee Table

Here is the Code for Login Table 
CREATE TABLE LOGIN (
    ID         	    NUMBER(32)NOT NULL PRIMARY KEY,
    USERNAME        VARCHAR2(64),
    PASSWORD   	    VARCHAR2(64),
    ENTITY_ID       VARCHAR2(32),
    DATE_CREATED    TIMESTAMP(6),
    DATE_MODIFIED   TIMESTAMP(6)
);

Here is the Code for Employee Table
CREATE TABLE EMPLOYEE (
    LAST_NAME           VARCHAR2(64)NOT NULL,
    FIRST_NAME          VARCHAR2(64)NOT NULL,
    MIDDLE_NAME         VARCHAR2(64)NOT NULL,
    POSITION_IN_RC      VARCHAR2(64),
    DATE_EMPLOYED       TIMESTAMP(6),
    BIRTHDATE           TIMESTAMP(6),
    BIRTHPLACE          VARCHAR2(64),
    SEX                 VARCHAR2(8),
    CIVIL_STATUS        VARCHAR2(32),
    CITIZENSHIP         VARCHAR2(32),
    RELIGION            VARCHAR2(32),
    HEIGHT              NUMBER(8),
    WEIGHT              NUMBER(8),
    EMAIL               VARCHAR2(64),
    SSS_NO              VARCHAR2(16),
    TIN_NO              VARCHAR2(16),
    PAGIBIG_NO          VARCHAR2(16),	
    EMPLOYEE_NO         VARCHAR2(16),
    PRIMARY KEY (EMPLOYEE_NO));

Now that you are done with the database

You can now proceed to create jar in the Back-End of Employee Info Management System

First go the Repository Back-end named "rc-eims" (employee information management system)
Here is the  url code:
https://github.com/RoCS2024/rc-eims.git
![rc-eims repo](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/62726f56-bf7d-4104-8965-104bc2a685dc)

Get the code
![code](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/8efbb25e-bab2-4dd3-aa15-d0b630e03f68)

Create New Folder

Open Git bash
type "git clone" and paste the url code 
![backend-rc-eims](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/1c785cb4-5751-407a-9eb4-b11d1bb3bce1)

Open the rc-eims in IntelliJ
![open-rc-eims](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/ec0b97c7-2a48-41e6-b148-07e140a4d7c2)

Delete the Main Class
Right click main, select delete and then press okay
![delete-main-class](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/6f2cc74c-9ff1-48b6-a382-d090f2de0518)

After deleting the Main class

Go to ivy.xml Rename the module and then type "eims"
![ivyxml](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/649a3ce7-831a-4c26-b6fa-8df5b7a81695)

this part.
Rename the:
module="ivy-test" into module="eims"
![thispart](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/cc67a483-14a4-4dbf-9857-8c295397e8b7)

like this
![rename-eims-ivyxml](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/4da2d635-ce61-412f-8c4f-dc1571979f24)

Now you are done with ivy.xml

You can now proceed to build.xml
Go to build.xml and then go to create jar
![buildxml](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/83e88776-58cb-4d3d-9d5d-bd25abb468a2)

Rename this part:
![rename-this-part-buildxml](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/25da619f-e86a-4177-834b-fc705e5c793e)

rename the:
 <jar destfile="${build.dir}/${ant.project.name}-${version}.jar" into <jar destfile="${build.dir}/eims.jar"

 like this
 ![rename-rc-eims-buildxml](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/7cb62f10-2db4-4f4f-976e-2b8dabf27c1a)

Now that you are done with ivy.xml and build.xml

Go to Terminal and then type "ant build-jar"
![ant-build-jar](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/f099e8ac-31a4-442d-a7d5-84c9d6c73096)

It will display build-jar Successful
![build-successful](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/475b9549-0df1-402c-8571-70621942e1a7)

Check the build if the jar exist
![jar-eims-exist](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/26405d0c-09b1-4f12-a49d-d49e7c48f96a)

And now you are done creating jar in the Back-End of Employee Information Management System (eims)

You just need to repeat the steps in creating the jar for the User Management System (ums).

Now we are ready to import the eims jar and the ums jar but before that we need to set up the Front-end of the Employee Information Management System

First you go to the repo of Front-End Employee Information Management System named "employee-info-mgt-app"
Here is the url code:
https://github.com/RoCS2024/employee-info-mgt-app.git

![repo-employee-info-mgt-app](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/49deda98-2b0b-4574-bb31-3fabb43342af)

Just like we did before you need to create Folder

Clone the url code of the employee-info-mgt-app

and then open it in the IntelliJ
![open-employee-info-mgt-app-intellij](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/705c845b-dfdf-4bfd-8de2-8d3d30a24cba)

When you open the employee-info-mgt-app there are some possible error you will encounter.
1. First is the dependency
It looks like this
![possible error](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/7d8a858a-e33f-459f-b0e2-33d3f29434a2)

To fix this first check the pom.xml
![image](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/41327514-6dff-4fa2-a8fe-1dedf2cc1ed7)

Check if these dependency are in the pom.xml
![dependency](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/8f14228d-53ee-4665-b8b9-46b87c7158c5)

if not 
copy this dependency and then add it to pom.xml
 <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
            <version>23.3.0.23.09</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j2-impl</artifactId>
            <version>2.23.1</version>
        </dependency>

after that go to Terminal type mvn clean install

2. The second possible error you will encounter is this:

REMINDER THIS IS OPTIONAL WHEN YOUR JAR IS NOT WORKING ON YOU FRONT-END
You will notice that the jar name of ums is umsv2 in my project but the original name of the jar is ums not umsv2 why did i change that into umsv2? 
because when you done creating jar in Back-end and when you import it in your Front End you will notice, 
even if you type the correct name of the jar in the module-info-java and
the 2 jar are all set and when you run the Main View in the Front-end it will display "module not found ums or sims". 

To fix that you just need to delete the back-end of the jar that is not working
Create new folder
Clone it again
Repeat the steps on how to create Jar in the Back-End
Rename the jar just like i did in my project for example "umsv2"
and then it is ready to import to the Front End and i can guarantee that it will work

3. The third error you will encounter when you open the Front-end of employee-info-mgt-app is the "module-info-java"

As you can see in here the "require eims" part and "umsv2" is color red. Because we have not yet imported the jar.
![employee-module-info-java](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/68e94b59-dc3c-4d7f-a8ea-ce2b6574ba07)


Now where going to import the 2 jar of the Back-end into the Front-end
Here is the step by step:
1. Go to Project Structure
![employee-project-structure](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/ceffae8e-56fb-4bd9-9dab-12c27632e87f)
2. Go to Modules
![employee-modules](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/d0611328-7c3a-4b63-8084-01835fc98ead)
3.Click the plus button and then choose option 1. Jar or Directories
![employee-add-button](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/36251fd4-6dfb-4d35-b03c-bed038475d77)
4.After that locate the file where you build the jar. Click the Jar and press okay.
![locate-eims-jar](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/e64d1410-bdae-4a39-844d-ee25456b39e6)
5.The jar that you select will add to your dependency.
![eims-jar](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/eea9f8d4-b2bd-4503-9a1d-0165dcc8211c)
6.Repeat the steps when you want to import the jar ums
7.As you can see the 2 jar is in the dependency. Click okay and then it will resolve the error in the modules-info-java
![employee-2-jar-imported](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/c470efb5-8f24-4e4c-9695-00e3f6011a13)
8. As you can see the errors are gone
![done](https://github.com/RoCS2024/employee-info-mgt-app/assets/157860683/abe2607c-5deb-4b7a-9e0a-3d39ee573eab)

When you are done you can run the Main View of the Front End smoothly enjoy!!

    
