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
    }
}
