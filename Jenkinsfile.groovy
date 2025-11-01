pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/1hirachy/PetStoreAutomation.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML([
                        reportDir: 'reports',
                        reportFiles: 'ExtentReport.html',
                        reportName: 'PetStore Automation Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/**/*.html', fingerprint: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
