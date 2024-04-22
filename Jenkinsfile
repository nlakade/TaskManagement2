// pipeline {
//     agent any

//     environment {
//         JAVA_HOME = tool 'jdk-8' // assuming 'jdk-8' is configured in Jenkins tools
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
//                 sh 'mvn clean install'
//             }
//         }

//         stage('Docker Build') {
//             steps {
//                 sh 'docker build -t task-management .'
//             }
//         }

//         // stage('Run Spring Boot App') {
//         //     steps {
//         //         script {
//         //             // Run the Spring Boot application in a separate container
//         //             // sh 'docker run -d -p 9091:9091 task-management'

//         //            // // Run the Spring Boot application in a separate container on port 9091
//         //            //  sh 'docker run -d -p 9091:9091 task-management'

//         //            //  // Run the Spring Boot application in a separate container on port 9092
//         //            //  sh 'docker run -d -p 9092:9092 task-management'
                       

//         //             // // Wait for the application to start (you may need to adjust the sleep time)
//         //             // sleep 30

//         //             // // Check which port the Spring Boot application is running on
//         //             // sh 'netstat -tuln | grep java'
//         //         }
//         //     }
//         // }

//         stage('Docker Compose') {
//             steps {
//                 script {
//                     // Install a specific version of Docker Compose
//                     def workspaceDir = pwd()
//                     sh "curl -L https://github.com/docker/compose/releases/download/1.29.2/docker-compose-\$(uname -s)-\$(uname -m) -o ${workspaceDir}/docker-compose"
//                     sh "chmod +x ${workspaceDir}/docker-compose"
        
//                     // Check Docker Compose version
//                     sh "${workspaceDir}/docker-compose --version"
        
//                     // Run Docker Compose
//                     sh "${workspaceDir}/docker-compose up -d"
//                 }
//             }
//         }
//     }
// }




pipeline {
    agent any

    tools {
        jdk 'jdk-8' // specify the JDK tool name directly in the tools section
    }

    environment {
        JAVA_HOME = tool 'jdk-8'
        PATH = "${JAVA_HOME}/bin:${PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t task-management .'
            }
        }

        stage('Docker Compose') {
            steps {
                script {
                    // Install Docker Compose
                    def dockerComposeVersion = '1.29.2'
                    def dockerComposeUrl = "https://github.com/docker/compose/releases/download/${dockerComposeVersion}/docker-compose-$(uname -s)-$(uname -m)"
                    def workspaceDir = pwd()
                    sh "curl -L ${dockerComposeUrl} -o ${workspaceDir}/docker-compose"
                    sh "chmod +x ${workspaceDir}/docker-compose"

                    // Check Docker Compose version
                    sh "${workspaceDir}/docker-compose --version"

                    // Run Docker Compose
                    sh "${workspaceDir}/docker-compose up -d"
                }
            }
        }
    }

    post {
        always {
            // Optional: run tests on the built Docker image
            sh 'docker run --rm task-management mvn test'
        }
    }
}
