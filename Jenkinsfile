pipeline {
    agent any

    tools{
        maven 'maven 3'
        jdk 'java 8'
    }

    stages{
        stage('Init') {
            steps{
                //here hould be the mvn build command
                sh '''
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage('Test'){
            //Here should go the test
            steps{
                dir('./reviewManager'){
                sh 'mvn test'
                }
            }
        }

        stage('Build'){
            steps{
                dir('./reviewManager'){
                sh 'mvn package -Dbuild.number=${BUILD_NUMBER}'
                }
            }
        }
        
    } post{
                success {
                junit '**/target/surefire-reports/TEST*.xml'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
}