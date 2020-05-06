
# Interview Process Coding Assesment

This is a single page Spring Boot and React application. The functionality of applications is...
* all trainers is listed on the main page
* click 'Add Trainer' to define a trainer
    * 2 trainer's names can not be same
* list workouts of a trainer by clicking 'Show Workouts' button of related trainer
* click 'Add Workout' button to add a workout (a trainer should already be selected)
    * a trainer can not have multiple workouts which have same name
    

### Prerequisites

* Java 11
* Git
* Maven 4
* npm 6+
* Docker (if you want to run it in a container)

### Installing

just clone whole source code as follows and go to coding-assesment-reporting folder
```
git clone https://github.com/ittechstack/assesment.git
```
```
cd assesment
```

### Running backend tests

```
./mvnw test
```

### Prepare frontend: install npm packages
React codes are under src/webapp directory. All frontend codes should be run in this dir

```
cd src/webapp
```

```
npm install
```

### Running frontend tests

```
npm test
```

### Running application

```
cd src/webapp
```

```
npm run build
```
Go to main directory which contains pom.xml
```
./mvnw spring-boot:run
```

### Running application with docker container

```
cd src/webapp
```

```
npm run build
```
Go to main directory which contains pom.xml

```
./mvnw package
```

```
docker build -t assesment .
```

```
docker run -p 8080:8080 assesment
```

### Test application

Open a browser and open http//localhost:8080

