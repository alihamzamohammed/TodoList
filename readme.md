Coverage: 97.9%

# To Do List

To Do API and frotnend, to manage to do tasks and place them within categories.  
API is based on Java, and frontend is HTML, CSS, and JavaScript

## Documentation

All documentation, icnluding ERDs, UMLs, Risk Matrix, Presentation, and link to Issue Tracking board are located in `docs`  
API documentation can be accessed through `http://localhost:8080/swagger-ui.html` when the program is running (change 8080 to whichever port the API runs on)

## Getting Started

Download latest release. All dependencies should be included.  
To compile from source: `mvn package`  
Bypass tests: `mvn package -Dmaven.test.skip=true`  
See **Running the Tests** below for important information on compiling and running test correctly  

### Prerequisites

Java 11  
MySQL for Production use  

### Installing

Set program to use the MySQL database instead of H2 in-memory database by changing `spring.profiles.active` to `production` in `src/main/resources/application.properties`  
Also set MySQL database name and credentials in `src/main/resources/application-production.properties`  
Compile program from source using `mvn package`, and use the generated .war file as your release package  

### Tests

Tests can be run with `mvn test`  
Test reports for the backend are generated in `target/surefire-reports`  
Code coverage reports are generated in `target/site`  
Frontend extent reports are generated in `reports/frontend`  

IMPORTANT: Please ensure frontend is running on a live server PRIOR to running the tests. (`mvn test` OR `mvn package`)  
The link to the frontend can be set in `src/main/resources/application-test.properties`  
**Test will fail if Surefire cannot communicate with the frontend**  
Bypass tests: `mvn package -Dmaven.test.skip=true`  
The frontend depends on setting cookies, and browsers do not set cookies for local webpages  

## Built With

- [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

- **Ali Hamza Mohammed** - _All of it_ - [alihamzamohammed](https://github.com/alihamzamohammed)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details

_For help in [Choosing a license](https://choosealicense.com/)_

## Acknowledgments

- **Boni García** - _Selenium with JUnit 5_ - [bonigarcia](https://github.com/bonigarcia)
- **Ed Reynolds** - _Other Contributions_ - [Edrz-96](https://github.com/Edrz-96)
- **Morgan Walsh** - _Other Contributions_ - [MrWalshyType2](https://github.com/MrWalshyType2)
