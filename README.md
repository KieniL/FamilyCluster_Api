
# APIService

The API Service exposed to the frontend with a swagger.

package the project with this command before the container can be built

mvn clean package

Copy the api defined in the authservice and ansparservice into the apiservice to expose it externally.<br/>
If you change something in the api there will be an error on imports.<br/>
Change import in AnsparEntryModel, AmountEntryModel and CertificationModel to import java.time.LocalDate instead of threeten


## Tested with Jenkins


There are several things tested:
* Anchore (on Docker image)
* Secrets in Git (Trufflehog which also checks Git commits)
* Owasp Dependency Check
* Checkstyles (check styles on java e.g lines not too long and more readability)
* sonar scanning
* mvn test + jacoco (unit Test coverage) --> jacoco plugin is needed
* spotbugs (own maven plugin)
* kube-score (find benchmarks for kubernetes files)
* kube-val (validates kubernetes files)


## Logging

The logging is done with the default logging of spring boot (logback-spring) from the controller.
A logback-spring.xml is added and a new environment variable (ANSPAREN_LOG_LEVEL).


### Log Levels
Set this variable to see different loggings:
The hierarchy is as follows:
OFF
FATAL
ERROR
WARN
INFO
DEBUG
TRACE


This means that every line log all things from the levels above

### Log Fields:

I thought that these informations are interesting:

* timestamp
* level (message)
* thread
* message
* logger
* mdc
  * SYSTEM_LOG_LEVEL
  * REQUEST_ID

Based on owasp security logging (https://cheatsheetseries.owasp.org/cheatsheets/Logging_Cheat_Sheet.html) I found that there are some additional information which are nice to have for logging:

* source ip
* User id
* HTTP status Code
* Reason for Status Code



So the final one is:

* timestamp
* level (message)
* thread
* message
* logger
* mdc
  * SYSTEM_LOG_LEVEL
  * REQUEST_ID
  * source ip
  * User id