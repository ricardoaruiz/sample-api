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
                sh 'rm -rf /home/ralmendro/mgmt/teste-build-jenkins/sample-api.jar'
                sh 'cp build/libs/sample-api-0.0.1-SNAPSHOT.jar  /home/ralmendro/mgmt/teste-build-jenkins/sample-api.jar'
                sh '/home/ralmendro/mgmt/teste-build-jenkins/run.sh > /home/ralmendro/mgmt/teste-build-jenkins/logs.txt &'
            }
        }
    }
}
