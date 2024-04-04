pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    docker.build("task-management")
                }
            }
        }

        stage('Docker Compose') {
            steps {
                sh 'docker-compose -f /path/to/docker-compose.yml up -d'
            }
        }
    }

    post {
        always {
            sh 'docker-compose down'
        }
    }
}
