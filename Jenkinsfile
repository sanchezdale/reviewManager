pipeline {
    agent any
    stages{
        stage('Build') {
            steps{
                //here hould be the mvn build command
                mvn package -Dbuild.number=${BUILD_NUMBER}
            }
        }

        stage('Test'){
            //Here should go the test
            steps{
                mvn test
            }
        }
    }
}