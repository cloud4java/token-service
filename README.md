# Token Processor Service


### Execution help
* Use
* * Java 17
* * Gradle 8
* * Spring-boot 3.5

### Gneratin token
* Generate token and signature using the utility class ``` JwtUtil ```

### Reference Documentation
Token Processor Service is a Web application which exposes an API that receives a JWT (string) 
    as a parameter and verifies if it is  valid according to the following rules:

* The JWT must be valid
* The JWT must contain only three claims (Name, Role, and Seed)
* The Name claim cannot contain numbers
* The Role claim must contain only one of the three values (Admin, Member, or External)
* The Seed claim must be a prime number
* The maximum size of the Name claim is 256 characters

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

