pipeline {
    agent any
    
    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    try {
                        // Get code from GitHub
                        git 'https://github.com/goluhunk/CcucmberBDDFramework.git'
                        
                        // Run Maven
                        sh "mvn clean package test -Dbrowser=chrome -Denvironment=local -Dtest=RunAcceptance.java"
                    } catch (Exception e) {
                        // Handle error
                        currentBuild.result = 'FAILURE'
                        echo "Build failed: ${e.message}"
                    }
                }
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
                failure {
                    echo "Build failed! Check the logs."
                }
            }
        }
        
        stage('Deploy to QA') {
            steps {
                script {
                    echo "Deployment done on QA"
                }
            }
        }     
    }

    post {
        always {
            // Notify stakeholders
            echo "Pipeline finished."
        }
    }
}
