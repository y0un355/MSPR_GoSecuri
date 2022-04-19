pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'ls -la && cd /var/jenkins_home/workspace/GoSecuri/ && mvn --version && mvn clean && mvn package && mvn install'
      }
    }

    stage('Run') {
      steps {
        sh 'ls -la && cd /var/jenkins_home/workspace/GoSecuri/ && java -jar target/GoSecuri-1.0.0-BETATEST.jar && ls -la'
        sh 'docker ps'

        sh 'cp /var/jenkins_home/workspace/GoSecuri/src/files/.htpasswd   $HOME/gosecuri/'
        sh 'cp /var/jenkins_home/workspace/GoSecuri/src/files/*  $HOME/gosecuri/'
        sh 'cp -r /var/jenkins_home/workspace/GoSecuri/src/font/* $HOME/gosecuri/'
        sh 'cp -r /var/jenkins_home/workspace/GoSecuri/src/css/* $HOME/gosecuri/'
        sh 'cp -r /var/jenkins_home/workspace/GoSecuri/src/img/* $HOME/gosecuri/'

        //sh 'cd GoSecuriApp/src/main/java/com/epsi/gosecuri/ && ls -la'
        stash includes: '/var/jenkins_home/workspace/GoSecuri/src/files/*', name: 'files'
        stash includes: '/var/jenkins_home/workspace/GoSecuri/src/css/*', name: 'css'
        stash includes: '/var/jenkins_home/workspace/GoSecuri/src/img/*', name: 'img'
        stash includes: '/var/jenkins_home/workspace/GoSecuri/src/font/*', name: 'font'

      }
    }

    stage('Deploy') {
      agent {
        docker {
          image 'gosecuri:latest'
          args '-u root --privileged'
        }
      }
      steps {

        sh 'pwd && cat /etc/nginx/conf.d/default.conf'
        //dir('/usr/share/nginx/html'){
        dir('html'){
          unstash 'files'
          unstash 'css'
          unstash 'img'
          unstash 'font'
        }
        sh 'ls -la'
        sh 'ls -la  html/GoSecuriApp/src/'
        sh 'cp html/GoSecuriApp/src/files/.htpasswd  /usr/share/nginx/html/'
        sh 'cp html/GoSecuriApp/src/files/*  /usr/share/nginx/html/'
        sh 'cp -r html/GoSecuriApp/src/css/* /usr/share/nginx/html/'
        sh 'cp -r html/GoSecuriApp/src/img/* /usr/share/nginx/html/'
        sh 'cp -r html/GoSecuriApp/src/font/* /usr/share/nginx/html/'
        sh 'ls -la  /usr/share/nginx/html/'
        sh 'ls -la  /usr/share/nginx/html/files/'
      }
    }

  }
  tools {
    maven 'Maven'
    nodejs 'NodeJS'
  }
}
