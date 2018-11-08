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

        stage('Build'){
            steps{
                sh 'mvn package -Dbuild.number=${BUILD_NUMBER}' 
            }
        }
        stage('Test'){
            //Here should go the test
            steps{
                junit 'mvn test'
            }
        }
    }
}