pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                sh 'cp -r /var/lib/jenkins/workspace/MSPR_GoSecuri/* /var/lib/jenkins/workspace/MSPR_Pipeline/'
                sh 'mvn clean'
                sh 'mvn compile'
            }
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
        stage('Deploy'){
            steps{
                sh 'scp -r htmlFiles/* /home/webuser/www/html'
            }
        }
    }
}