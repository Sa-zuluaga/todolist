pipeline {
    agent any

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
                withSonarQubeEnv('sonarJenkins'){
                    bat 'gradlew sonar'
                }
           }
        }
       stage("Quality Gate"){
            options {
                timeout(time: 1, unit: 'HOURS')
            }
            steps{
                script{
                    sleep(10)
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
       stage('Build Image') {
            steps{
                script{
                    bat 'docker login'
                    bat 'docker build -t todolist .'
                    }
                }
            }
       stage('Push Image') {
            steps{
                script{
                    bat 'docker docker push sazuluaga/todolist:latest'
                    }
                }
            }

    }
}