// pipeline {
//     agent any

//     stages {
//         stage('Build') {
//             steps {
//                 sh 'mvn clean install'
//             }
//         }

//         stage('Docker Build') {
//             steps {
//                 script {
//                     docker.build("task-management")
//                 }
//             }
//         }

//         stage('Docker Compose') {
//             steps {
//                 script {
//                     sh 'docker-compose --version'
//                     sh 'docker-compose up -d'
//                 }
//             }
//         }
//     }

    // post {
    //     always {
    //         sh 'docker-compose down'
    //     }
    // }
// }


// pipeline {
//     agent any

//     stages {
//         stage('Build') {
//             steps {
//                 sh 'mvn clean install'
//             }
//         }

//         stage('Docker Build') {
//             steps {
//                 script {
//                     docker.build("task-management")
//                 }
//             }
//         }

//         stage('Docker Compose') {
//             steps {
//                 script {
//                     sh 'docker-compose --version'
//                     sh 'docker-compose up -d'
//                 }
//             }
//         }
//     }
// }


// pipeline {
//     agent any

//     environment {
//         JAVA_HOME = tool 'jdk-11' // assuming 'jdk-11' is configured in Jenkins tools
//         PATH = "$JAVA_HOME/bin:$PATH"
//     }

//     stages {
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }

//         stage('Build') {
//             steps {
//                 script {
//                     sh 'mvn clean install'
//                 }
//             }
//         }

//         stage('Docker Build') {
//             steps {
//                 script {
//                     sh 'docker build -t task-management .'
//                 }
//             }
//         }

//         stage('Docker Compose') {
//             steps {
//                 script {
//                     sh 'docker-compose --version'
//                     sh 'docker-compose up -d'
//                 }
//             }
//         }
//     }
// }


pipeline {
    agent any

    // environment {
    //     JAVA_HOME = tool 'jdk-11' // assuming 'jdk-11' is configured in Jenkins tools
    //     PATH = "$JAVA_HOME/bin:$PATH"
    // }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t task-management .'
                }
            }
        }

        stage('Run Spring Boot App') {
            steps {
                script {
                    // Start the Spring Boot application
                    sh 'java -jar target/task-management-0.0.1-SNAPSHOT.war &'

                    // // Wait for the application to start (you may need to adjust the sleep time)
                    // sleep 30

                    // // Check which port the Spring Boot application is running on
                    // sh 'netstat -tuln | grep java'
                }
            }
        }

        stage('Docker Compose') {
            steps {
                script {
                    sh 'docker-compose --version'
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}
