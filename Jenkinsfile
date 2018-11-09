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
                dir('/home/ralmendro/mgmt/teste-build-jenkins') {
                    script {
                        def result = sh returnStatus: true, script: 'java -jar sample-api.jar'
                        if (result != 0) {
                            echo '[FAILURE] Failed to build'
                            currentBuild.result = 'FAILURE'
                            // this will terminate the job if result is non-zero
                            // You don't even have to set the result to FAILURE by hand
                            sh "exit ${result}"  
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
    }
}
