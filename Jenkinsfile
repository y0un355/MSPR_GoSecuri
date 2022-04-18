pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'ls -la && cd MSPR_GoSecuri && mvn --version && mvn clean && mvn package && mvn install'
      }
    }

    stage('Run') {
      steps {
        sh 'ls -la && cd MSPR_GoSecuri && java -jar target/MSPR_GoSecuri-1.0-SNAPSHOT.jar && ls -la'
        sh 'docker ps'

        sh 'cp MSPR_GoSecuri/src/files/.htpasswd   $HOME/gosecuri/'
        sh 'cp MSPR_GoSecuri/src/files/*  $HOME/gosecuri/'
        sh 'cp -r MSPR_GoSecuri/src/font/* $HOME/gosecuri/'
        sh 'cp -r MSPR_GoSecuri/src/css/* $HOME/gosecuri/'
        sh 'cp -r MSPR_GoSecuri/src/img/* $HOME/gosecuri/'

        //sh 'cd GoSecuriApp/src/main/java/com/epsi/gosecuri/ && ls -la'
        stash includes: 'MSPR_GoSecuri/src/files/*', name: 'files'
        stash includes: 'MSPR_GoSecuri/src/css/*', name: 'css'
        stash includes: 'MSPR_GoSecuri/src/img/*', name: 'img'
        stash includes: 'MSPR_GoSecuri/src/font/*', name: 'font'

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
