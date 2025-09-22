pipeline {
    agent any

    stages {

        // ===== FRONTEND BUILD =====
        stage('Build Frontend') {
            steps {
                dir('FrontEnd/timetable') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        // ===== FRONTEND DEPLOY =====
        stage('Deploy Frontend to Tomcat') {
            steps {
                bat '''
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\timetable-Reactapp" (
                    rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\timetable-Reactapp"
                )
                mkdir "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\timetable-Reactapp"
                xcopy /E /I /Y FrontEnd\\timetable\\dist\\* "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\timetable-Reactapp"
                '''
            }
        }

        // ===== BACKEND BUILD =====
        stage('Build Backend') {
            steps {
                dir('BackEnd/Timetable-backend') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        // ===== BACKEND DEPLOY =====
        stage('Deploy Backend to Tomcat') {
    steps {
        bat '''
        if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Timetable-SpringBoot.war" (
            del /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Timetable-SpringBoot.war"
        )
        if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Timetable-SpringBoot" (
            rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Timetable-SpringBoot"
        )
        copy "BackEnd\\Timetable-backend\\target\\Timetable-SpringBoot.war" "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Timetable-SpringBoot.war"
        '''
    }
}

    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Pipeline Failed.'
        }
    }
}