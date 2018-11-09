pipeline {
    agent any 
    stages {
        stage('Build') {
            steps {               
                sh './gradlew build'
                echo 'Build gerado com sucesso'
            }
        }
        stage('Deploy') {
            steps {
                sh 'rm -rf /home/ralmendro/mgmt/teste-build-jenkins/sample-api.jar'
                sh 'cp build/libs/sample-api-0.0.1-SNAPSHOT.jar  /home/ralmendro/mgmt/teste-build-jenkins/sample-api.jar'
                dir('/home/ralmendro/mgmt/teste-build-jenkins') {
                    sh './run.sh'
                }
            }
        }
    }
    post {
        always {
            deleteDir() /* clean up our workspace */
        }
    }
}
