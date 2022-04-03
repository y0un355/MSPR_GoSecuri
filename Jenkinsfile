pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                sh 'cd /var/jenkins_home/workplace/MSPR_GoSecuri; rm -rf *'
                sh 'mvn clean'
            }
        }
        stage("Clone") {
                git branch: 'main', url: 'https://github.com/psaidani/GoSecuri.git'
            }
        stage("Test"){
            steps{
                sh 'mvn test'
            }
        }
        stage('Build'){
            steps{
                sh 'mvn exec:java'
            }
        }
    }
}