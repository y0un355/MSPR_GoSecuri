pipeline {
    agent any
    
    stages {
        stage ('Build') {
            steps {
                git branch: 'main',
                credentialsId: 'a2708897-085f-4caf-a81d-8e46c2ebaa10',
                url: 'https://github.com/y0un355/MSPR_GoSecuri.git'
            }
          }
         stage('show files list') {
              sh 'ls'
              sh 'ls /var/www/html/'
          }
          stage('Clean old files') {
              sh 'cd /var/www/html/; rm -rf *'
          }
          stage('copy files to web server') {
              sh 'pwd'
              sh 'mv ./*  /var/www/html/'
          }
          stage('Compile JAR File') {
              sh 'cd /var/www/html/MSPR_GoSecuri; mvn clean install'
          }
          stage('Make JAR File') {
            sh 'java -jar /var/www/html/MSPR_GoSecuri/MSPR_GoSecuri-v1.jar'

          }
    }
}
