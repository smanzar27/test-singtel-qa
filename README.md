# test-singtel-qa

Prerequisite:
1. Install JDK 1.8
2. Install Maven 3 Version
3. Set JAVA_HOME and MAVEN_HOME path
4. Install Chrome Browser

QA Framework Technology: 
1. Java
2. Maven
3. GIT  
4. Selenium
5. Rest Assured
6. Cucumber 
7. TestNG
8. Maven Cucumber Reporting 


GIT Source		: 	https://github.com/smanzar27/test-singtel-qa.git
Take CheckOut	: 	git clone https://github.com/smanzar27/test-singtel-qa.git


Goto CheckOut Folder:
1. Compile Source Code:		mvn clean install -Dmaven.test.skip=true -Dskip-maven-cucumber-reporting=true

Goto CheckOut Folder:
1. Run TestCode:			mvn clean verify -Dtest=CucumberRunner  -Dcucumber.filter.tags="@QATest"


Cucumber Extent Report: genarates when execute via mvn or run as cucumber runner in intelji
Path: <project_dir>/Report/<QA_Report> -> contains cucumber reports having test results in both html and pdf form
 