pipeline {
    agent any 
    stages {
        stage('Build') {
            steps {
                echo 'Codigo baixado em: '
                sh 'pwd'
                
                sh './gradlew build'
                echo 'Build gerado com sucesso'
            }
        }
    }
}
