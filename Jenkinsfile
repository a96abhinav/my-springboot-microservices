pipeline {
    agent any
    environment {
        KUBECONFIG = '"C:/Users/Dell/.kube/config"'
    }
    stages{
        stage('Git Checkout'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://a96abhinav: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@github.com/a96abhinav/my-springboot-microservices.git']])
            }
        }
        stage('Maven Build'){
            steps{
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Create Docker Images') {
            steps {
                script {
                    bat "docker-compose build"
                }
            }
        }
        stage('Push Images to DockerHub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub_pass', variable: 'dockerhub_pass')]) {
                        bat 'docker login -u a96abhinav -p %dockerhub_pass%'

                        bat "docker tag my-springboot-microservices-service-registry a96abhinav/my-springboot-microservices-service-registry:latest"
                        bat "docker tag my-springboot-microservices-config-server a96abhinav/my-springboot-microservices-config-server:latest"
                        bat "docker tag my-springboot-microservices-api-gateway a96abhinav/my-springboot-microservices-api-gateway:latest"
                        bat "docker tag my-springboot-microservices-springboot-app a96abhinav/my-springboot-microservices-springboot-app:latest"

                        bat "docker push a96abhinav/my-springboot-microservices-service-registry:latest"
                        bat "docker push a96abhinav/my-springboot-microservices-config-server:latest"
                        bat "docker push a96abhinav/my-springboot-microservices-api-gateway:latest"
                        bat "docker push a96abhinav/my-springboot-microservices-springboot-app:latest"
                    }
                }
            }
        }

        // stage('Deploy to Docker') {
        //     steps {
        //         script {
        //             bat "docker-compose up -d"
        //         }
        //     }
        // }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    bat "kubectl delete -f k8s-deployment/"
                    bat "kubectl apply -f k8s-deployment/"
                }
            }
        }
    }
}