
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
