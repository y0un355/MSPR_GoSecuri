pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Clone') {
            steps {
                echo 'Hello'
                git branch: 'main', credentialsId: 'a2708897-085f-4caf-a81d-8e46c2ebaa10', url: 'https://github.com/y0un355/MSPR_GoSecuri.git'
              //  sh "mvn -Dmaven.test.failure.ignore=true clean package"

            }
        }
         stage('Build') {
              steps {
                 sh 'ls -la && cd /var/jenkins_home/workspace/GoSecuri/ && mvn --version && mvn clean && mvn package && mvn install'
            }
         }
        stage('Deploy') {
              steps {
                sh 'ls -la && cd /var/jenkins_home/workspace/GoSecuri/ && java -jar target/GoSecuri-1.0.0-BETATEST.jar && ls -la'

          }
      }
    }
    post {
        always{
            junit(
                allowEmptyResults:true,
                testResults: "test-reports/.xml")
        }
    }

}
