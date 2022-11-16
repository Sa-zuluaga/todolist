pipeline {
    agent any
    }

    stages {

        stage('Build') {
            steps{
                bat 'gradlew clean'
                bat 'gradlew build -x test'
            }
        }

        stage('Test') {
            steps{
                bat 'gradlew test'
                bat 'gradlew jacocoTestReport'
                bat 'gradlew jacocoTestCoverageVerification'
            }
        }

       stage('Sonar') {
            steps{
                withSonarQubeEnv('Sonar'){
                    bat 'gradlew sonar'
                }
           }
        }

    }
}