pipeline {
    agent any
    tools {
        maven 'M3'
      }
    stages {
        stage ('Build') {
            steps {
                git branch: 'main',
                credentialsId: 'a2708897-085f-4caf-a81d-8e46c2ebaa10',
                url: 'https://github.com/y0un355/MSPR_GoSecuri.git'
            }
          }
        stage('Compile') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Deliver') {
            steps {
                sh 'mvn -B -D clean package'
                sh 'java'

            }
        }
    }
}
