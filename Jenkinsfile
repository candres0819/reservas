#!groovy

pipeline {
    agent any

    tools {
        maven 'apache-maven-3.5.2'
    }

    environment {
        MAVEN_SH = "${maven}/bin/mvn"

        PACKAGE = "com/carloscardona/tns/${PROJECT}"
        FILE_PATTERN = "**/target/*.jar"
    }

    stages {
        stage('BUILD CODE') {
            steps {
                echo "[EXEC] - Compilación de código fuente."
                sh "mvn -f ${BUILD_MAVEN} compile package -Dmaven.test.skip=true"
            }
        }

        stage('UNIT TEST') {
            steps {
                echo "[EXEC] - Ejecución de pruebas unitarias."
                sh "mvn -f ${BUILD_MAVEN} test"
            }
        }

        stage('CODE ANA') {
            steps {
                echo "[EXEC] - Análisis estático de código"
                sh "mvn -f ${BUILD_MAVEN} sonar:sonar -Dsonar.host.url=${SONAR_URL} -Dsonar.junit.reportsPath=target/surefire-reports -Dsonar.surefire.reportsPath=target/surefire-reports -Dsonar.jacoco.reportPath=target/jacoco.exec -Dsonar.binaries=target/classes -Dsonar.java.coveragePlugin=jacoco"
            }
        }

        stage('NEXUS UP') {
            steps {
                echo "[EXEC] - Almacenando artefactos en Artifactory Server"
                sh "mvn -f ${BUILD_MAVEN} deploy"
            }
        }

        stage("DEPLOY") {
            when {
                branch 'master'    //only run these steps on the master branch
            }
            steps {
                echo "[TODO] - Despliegue de aplicacion"
            }
        }
    }

    post {
        always {
            archive "target/**/*"
            junit "target/surefire-reports/*.xml"

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'HTML Report',
                reportTitles: 'Jacoco'
            ])
        }
        failure {
            mail to: "${EMAIL_REPORTE}",
            subject: "Failed Pipeline ${PROJECT}",
            body: "Something is wrong ${env.BUILD_URL}"
        }
    }
}