# musiconnect

Musiconnect is a web app built in Java Spring Boot and utilises a MySQL database to store information.  The frontend was built using HTML5 CSS and Javascript.  Testing was undertaken with Mockito, JUnit and Selenium, and Postman was used to test endpoints and database connectivity.

## Purpose and future of musiconnect

The purpose of this app was to create a musical map of bands, musicians and recordings that allowed users to see the ‘musical family tree’ of bands and musicians.  The multiple relationships between the main tables should allow the user to see who played in which bands, and were present or absent on each recording of the band.  To take an example from the initial data: John McIntyre plays in the band Tortoise with David Pajo but also in the band The Sea and Cake with Sam Prekop.  McIntyre has played on every Tortoise album, but Pajo only played on the first two.  Musiconnect would indicate this, allowing the user to follow the recordings made by each musician and the bands they played in.  An extension of the app would also track who were current or past members of the listed bands which, along with the release years of the albums would give an indication of a musician’s tenure.
The current version of the app is jus the first step of this process, but already provides most of the CRUD functionality that that would allow the it to be implemented.


## Getting Started

### Installation

Unfortunately, it has not been possible to provide a fully built app due to the severe time constraints of the project.  For this reason users should fork or clone this repository. The app can then be run by opening the project folder in eclipse IDE, performing a mvn clean operation, then running the practical-projects folder as a Spring Boot application.  

As the app uses Spring to create the database schema the MySQL part of the app needs to be set-up when run for the first time.  First run the db-setup.sql file.  Then restart the Spring Boot app in Eclipse.  This should create the database schema.  Once this is complete run the initial-data.sql file to populate the database with some introductory data.

The user should then navigate to the URL http://localhost:8081/index.html.  The instructions for using the app can be found on the webpage itself.


### Dependencies

The dependencies required to build and run the application are detailed in the 
`pom.xml` file.  
  
Additionally, users require the Java 15 JDK and MySQL installed on their local machine.

### Running the tests

The test suite includes Mockito, Junit and Selenium tests, for unit, integration and front-end testing, coverage has reached 67.5%, it has not been possible to hit the desired target of 80%, partly as there was not time to complete any repository testing.  The issues with the recording add and update functions mean that this entity has not undergone any controller tests either.

The test suite can be run by right clicking the practical-project folder and running or coverage as Junit application.
The test suite includes Mockito, Junit and Selenium tests, for unit, integration and front-end testing.  Coverage has reached 56%, due to the aforementioned time restraints it has not been possible to hit the desired target of 80%, partly as there was not time to complete any repository testing.

The test suite can be run by right clicking the practical-project folder and running or coverage as Junit application.


## Additional documentation  

The `documentation` folder contains the following documentation for the project:

* ERDs and UML diagrams for the project  
* Project risk assessment
* Powerpoint slides for the end of project presentation
* Selenium IDE project file
* Postman project file
* Test coverage and results
* Jira burndown chart
* Images from Jira for various points in the project

Further details of the project build can be found on the Jira board for the project at:   
`https://olly000.atlassian.net/jira/software/projects/PP/boards/5/backlog`  



### Issues/TODOs

* Need to change the reaction of the java services where there is a request for an item that isn’t present in the read and delete functions – currently throws exception but gives no user feedback.

* Need to fix the recording add and update functions so that they can safely deal with recordings that are input from bands not yet in the database – the most effective way to do this would be to check the db for the band, then give the user an alert, and a form that allows them to add the band before going on to add the recording.  At the moment there is an issue with this process where, the first time a recording by a new band is input the band is saved but the recording isn’t.  A second call to the same function adds the recording (as the band is now in the database) without adding a duplicate band.

* Similarly, need to add further data checks to ensure duplicate entries are not added to the database.

* Improve test coverage – no repo tests at present and recording tests hampered by the same issue as above.

* Need to do more with the joining tables – make them updateable from the website.

* Need to have an ‘admin’ and ‘user’ entry to the web app – ‘user’ would be restricted to searching or sending a message to the site admin to suggest a new entry/link/alteration. Could then expand admins as trusted users to give them access.

* Need to sort out test/prod profiles to allow app to build properly.  I was unable to get any assistance on this problem before/during the project week, so at the moment the relevant property elements need to be commented out in order to switch between using the h2 in memory database and the MySQL one.

* Cosmetically it would be ideal to output the data to the webpage in a table format.  Lack of time means they are currently simply displaying the raw JSON data.



## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Oliver Stockman**

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE v.3.0 - see the [LICENSE.md](LICENSE.md) file for details 


