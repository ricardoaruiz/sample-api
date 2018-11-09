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
        stage('Deploy') {
            steps {
                sh 'fuser -n tcp -k 8081 > log-stop.txt'
                sh 'rm -rf /home/ralmendro/mgmt/teste-build-jenkins/sample-api.jar'
                sh 'cp build/libs/sample-api-0.0.1-SNAPSHOT.jar  /home/ralmendro/mgmt/teste-build-jenkins/sample-api.jar'
                sh 'java -jar /home/ralmendro/mgmt/teste-build-jenkins/sample-api.jar & > log.txt'
            }
        }
    }
}
