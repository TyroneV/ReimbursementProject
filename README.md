# Reimbursement Project
Reimbursement Web Application made using Tomcat/Java and Html/JavaScript for the front end pages

## User Stories
 <h5>As a Employee User</h5>
  <ul>
    <li>Register and login</li>
    <li>I can see my past reimbursements</li>
    <li>Create and Submit a new reimbursement</li>
  </ul>
 <h5>As a Finance Management User</h5>
  <ul>
    <li>Register and login</li>
    <li>Accept or Deny Reimbursements</li>
    <li>See all Reimbursements</li>
  </ul>
  
## Installation

<ol>
  <li>Java</li>
  <li>Java IDE (Either Intellij,Spring tools or Eclipse)</li>
    <li>Import the project in to your IDE</li>
  <li>
         <details>
           <summary>Setup a tomcat server in the IDE</summary>
           <p>For IntelliJ you need Ultimate version or just install Smart Tomcat https://plugins.jetbrains.com/plugin/9492-smart-tomcat</p>
            <p>For Springtools https://crunchify.com/step-by-step-guide-to-setup-and-install-apache-tomcat-server-in-eclipse-development-environment-ide/</p>
       <p>"DB_PASSWORD" password of your database</p>
       </details>
  </li>
  <li>PostgreSQL (Database can be on an RDS instance or Local instance)</li>
   <li>
     <details>
           <summary>Create these Environment Variable in your PC. It is necessary to connect to the database</summary>
           <p>"DB_URL" for your Database url eg. jdbc:postgresql://[HOST]/[DATABASE]</p>
            <p>"DB_USERNAME" username of your database</p>
       <p>"DB_PASSWORD" password of your database</p>
       </details>
     <li>
       Run the sql script stored in the resouces folder in your database for initial setup.
    </li>
    <li>
      Once all are setup you can run the application. Enjoy
  </li>
</ol>

## Usage
Run the application and go to the homepage URL.
Create an account for both employee and financemanager
  
  #### Registration page
  <img src="https://raw.githubusercontent.com/TyroneV/ReimbursementProject/master/src/main/resources/registerpage.png"/>
  
Login to either account and you are able to do what each type of account can do base on the user story provided at the top of the readme
  
  #### Login page
   <img src="https://raw.githubusercontent.com/TyroneV/ReimbursementProject/master/src/main/resources/loginpage.png"/>
   
  #### User page
  <img src="https://raw.githubusercontent.com/TyroneV/ReimbursementProject/master/src/main/resources/userpage.png"/>
  
  #### Admin page
  <img src="https://raw.githubusercontent.com/TyroneV/ReimbursementProject/master/src/main/resources/adminpage.png"/>

