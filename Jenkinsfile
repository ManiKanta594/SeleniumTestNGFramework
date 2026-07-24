pipeline {

    agent any

    parameters {

        choice(
            name: 'BROWSER',
            choices: ['chrome', 'edge', 'firefox'],
            description: 'Select Browser'
        )

        choice(
            name: 'SUITE_XML',
            choices: ['sanity.xml', 'testng-smoke.xml', 'testng-regression.xml', 'parallel.xml'],
            description: 'Select TestNG Suite'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: false,
            description: 'Run in Headless Mode'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat """
                mvn clean test ^
                -DsuiteXmlFile=${params.SUITE_XML} ^
                -Dbrowser=${params.BROWSER} ^
                -Dheadless=${params.HEADLESS}
                """
            }
        }
    }

    post {

        always {
            echo 'Pipeline execution completed.'
        }

        success {
            echo 'Build Successful.'
        }

        failure {
            echo 'Build Failed.'
        }
    }
}