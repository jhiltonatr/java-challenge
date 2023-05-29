## Original challenge restrictions
Notes have been moved [here](./challenge.md)

## Decisions
- Although I personally prefer Gradle to Maven, due it's high flexibility and performance in big projects, no migration was done as the project is small and straightforward.

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

