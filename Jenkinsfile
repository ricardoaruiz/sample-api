pipeline {
    agent any 
    stages {
        stage('Build') {
            steps {
                sh 'echo "codigo baixado em: " pwd'
                sh './gradlew build'
            }
        }
    }
}
