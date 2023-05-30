## Original challenge restrictions
Notes have been moved [here](./challenge.md)

## Decisions
- Although I personally prefer Gradle to Maven, due it's high flexibility and performance in big projects, no migration was done as the project is small and straightforward.
- `EmployeeController#updateEmployee` method will update the employee with id equal to the `employeeId` path variable. Contrary to the original implementation that prefers the id inside the JSON body.
  - This is done in order to avoid inconsistencies when a request uses different values between the path variable and the JSON body.
  - A different approach could be to reject the request altogether, but for simplicity I decided to still accept the request.
  - Other alternative is to move such decisions making into the Service layer, but since I considered this as Request control flow and not Business Logic I decided to leave it in the Controller layer.
- Cache is not implemented as the use cases, amount of data, RPS are not clear
  - It could be implemented to show how to do it, but I have decided to approach the project in a "what would I consider in the real-world" standpoint rather than just a theoretical exercise.
  - Early optimization can lead to costly operations, and added complexity without real benefits.
  - If required it would be implemented at `Repository` level, allowing deletions to evict records from the cache. 


## Project improvements

### JVM version
As Java 8 is a restriction no upgrade has been done, however just upgradingt the runtime can bring a lot of benefits, such as more effective GC implementations which might impact positively performance.


### Frameworks version
✔️ As newer versions of frameworks will have fewer vulnerabilities, performance improvements and more features to profit from. Keeping up-to-date becomes important !

ℹ️ As the usage of Java 8 is a **restriction**, The following versions will be the latest version supporting this Java version.

| Framework  | Before | After                      | Comments                                                         |
|------------|--------|----------------------------|------------------------------------------------------------------|
| Springboot | 2.1.4  | 2.7.4 (latest at May 2023) | Version 3.x requires Java 17                                     |
| Swagger    | 2      | 3                          | Swagger URL moves to http://localhost:8080/swagger-ui/index.html |

### Testing

#### Unit Test 
Unit tests created for the following classes
- EmployeeController
- EmployeeServiceImpl

### Code changes
#### Controller
- Removed unnecessary `setEmployeeService` method
- `getEmployees` Unify assignation and return statement
- `saveEmployee`
  - Removed log entry as deemed unnecessary. IF it is required for Observability it could be replaced by proper use of `Logger` instead of `System.out` usage.
  - Adding a new entity should return a copy of the saved data, or at minimum the generated `id` so the entity can be looked up using the `getEmployee` operation.
- `deleteEmployee` Removed log entry as deemed unnecessary. IF it is required for Observability it could be replaced by proper use of `Logger` instead of `System.out` usage.
- `updateEmployee`
  - Preferred usage of utility function `Objects#nonNull` over `!=` operator.
  - Prevent discrepancies between `employeeId` path variable and request body. The value in path variable is preferred as the correct value to update.
  - Updating an entity should return a copy of the saved data.

#### Service
- Removed unnecessary `setEmployeeRepository` method
- Renamed redundant naming in Service methods. Since the Service is an `EmployeeService` it stands that operation `get`, `save`, etc. are Employee related, hence it is not required to add them in the method name.
- `getAll` Unify assignation and return statement
- `getById`
    - Fix **Bug** `Optional#get` may fail if empty. Using a `null` default value when `Optional` object is empty
    - Unify assignation and return statement

#### Security
- Added Spring Security to secure the API endpoints
  - Security guided by API KEY strategy (as this is closer to an Auth Token without having an actual IAM system)
  - The secret security key is configurable in the `application.yml` under the `auth` key.

#### Database
- Schema normalization.
  - Department entity separated as many employees may work on the same departments
  - Basic departments created as "IT", "HR" and "Sales" in `src/main/resources/data.sql` file.
- Input validation
  - Employee salary must not be a negative number
  - Every employee belongs to 1 department
  - Every employee must have a name

---
## Personal

- [x] I have more than 10 years experience in Java, from which 7 years have been with version 8 or later
- [x] I have been working in Springboot related projects the last 5-6 years
- [x] I have used several features of Spring, such as Spring Security, Spring Data, JmsListener, etc. in order to cover different types of requirements
- [x] I have experience with code review process, SonarQube, and other auxiliary tools and practices that helps maintain a good codebase quality
- [x] I am a person that focuses a lot in code quality, best practices and I like being proactive in proposing possible improvements that helps reduce the technical debt, operational support cost, error probability, etc.