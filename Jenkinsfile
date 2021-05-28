
def containerBuild = "luke19/familyapiservice:${BUILD_NUMBER}"

pipeline {
  agent any

  environment {
    SONAR_TOKEN = credentials('sonarqube')
  }

  stages {

    stage('Checkout') {
      steps {
          checkout scm
      }
    }

    stage ('Clean Package Stage') {
      steps {
        script {
          try {
            sh "mvn clean package -DskipTests=true"
          }catch (exc) {
            error('Clean package failed' + exc.message)
          }
        }
      }
      
      
    }

    stage ('Test stage') {

      parallel {

        stage ('Imaging stage'){
          steps {
            script{
              try{
                withDockerRegistry(credentialsId: 'docker', url: 'https://index.docker.io/v1/') {
                    app = docker.build(containerBuild)
                    app.push()
                }
              }catch (exc) {
                error('packaging failed' + exc.message)
              }
            }
          }
        }

        stage ('Check Secrets Stage') {
          steps {
            script{
              try {
                sh "rm trufflehog.txt || true"
                sh 'docker run --rm --name trufflehog dxa4481/trufflehog --regex https://github.com/KieniL/FamilyCluster_Api.git> trufflehog.txt'
          
                publishHTML (target: [
                  allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: './',
                  reportFiles: 'trufflehog.txt',
                  reportName: "Trufflehog Report"
                ])
              }catch (exc) {
                error('Check secret failed' + exc.message)
              }   
            }
          }

        }

        stage ('Source Composition Analysis Stage') {
          steps {
            script{
              try{
                sh 'rm owasp* || true'
                sh 'wget "https://raw.githubusercontent.com/KieniL/FamilyCluster_Config/master/owasp-dependency-check.sh" '
                sh 'chmod +x owasp-dependency-check.sh'
                sh './owasp-dependency-check.sh'
                
                publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'odc-reports',
                    reportFiles: 'dependency-check-report.html',
                    reportName: "OWASP Dependency Report"
                ])
              }catch (exc) {
                error('Source composition analysis failed' + exc.message)
              }
            }
          }
        }

        stage ('Kube-score Stage') {
          steps {
            script{
              try{
                sh 'rm kube-score* || true'
                sh 'wget "https://raw.githubusercontent.com/KieniL/FamilyCluster_Config/master/kube-score.sh" '
                sh 'chmod +x kube-score.sh'
                sh './kube-score.sh'
              }catch (exc) {
                
              } 
              publishHTML (target: [
                  allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: './',
                  reportFiles: 'kube-score.txt',
                  reportName: "Kube Score Report"
              ])
              
            }
          }
        }

        stage ('Kube-val Stage') {
          steps {
            script{
              try{
                sh 'rm kube-val* || true'
                sh 'wget "https://raw.githubusercontent.com/KieniL/FamilyCluster_Config/master/kube-val.sh" '
                sh 'chmod +x kube-val.sh'
                sh './kube-val.sh'
              }catch (exc) {
                
              }  
              publishHTML (target: [
                  allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: './',
                  reportFiles: 'kube-val.txt',
                  reportName: "Kube Val Report"
              ])
              
            }
          }
        }

        stage ('Checkstyles Stage') {
          steps {
            script{
              try{
                sh 'rm checkstyle* || true'
                sh 'wget "https://raw.githubusercontent.com/KieniL/FamilyCluster_Config/master/checkstyle.sh" '
                sh 'chmod +x checkstyle.sh'
                sh './checkstyle.sh'
              }catch (exc) {
                
              } 

              
              publishHTML (target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: './',
                reportFiles: 'checkstyle.txt',
                reportName: "Checkstyle Report"
              ])
              
            }
          }
        }


        stage ('SAST') {
          steps {
            script{
              try{
                sh "mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                
                publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/sonar',
                    reportFiles: 'report-task.txt',
                    reportName: "Sonarscan Report"
                ])
              }catch (exc) {
                error('SAST failed' + exc.message)
              }
            }
          }
          
        }

        stage ('Maven Testing Stage') {
          steps {
            script{
              try{
                sh "rm test.txt || true"
                
                sh "mvn test  > test.txt"
                
                publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: './',
                    reportFiles: 'test.txt',
                    reportName: "Maven Test Report"
                ])

                publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/jacoco-report',
                    reportFiles: 'index.html',
                    reportName: "Jacoco Report"
                ])
              }catch (exc) {
                error('Maven test failed' + exc.message)
              }
            }
          }
        }

        stage ('Spotbugs Stage') {
          steps {
            script{
              sh "mvn spotbugs:spotbugs"

              publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: './',
                    reportFiles: 'spotbugs.xml',
                    reportName: "Spotbugs Report"
              ])
            }
          }
        }
      }
    }

    stage ('Deploying and Anchoring Stage') {

      parallel {
        stage ('Deploying Stage') {
          steps {
            sh "sed -i \"s/<VERSION>/${BUILD_NUMBER}/g\" deployment.yaml"
            sh "kubectl apply -f deployment.yaml"
            sh "kubectl apply -f service.yaml"
            sh "kubectl apply -f hpa.yaml"
          }
        }

        stage ('Anchoring Stage'){
          steps {
            writeFile file: 'anchore_images', text: containerBuild
                anchore name: 'anchore_images'
          }
        }

      }
      

    }

  }
}