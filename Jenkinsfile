pipeline {

    agent any

    tools {
        allure 'Allure'
    }

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

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/Reports/Latest',
                reportFiles: 'AutomationReport.html',
                reportName: 'Extent Automation Report',
                reportTitles: 'Extent Report'
            ])

            allure(
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            )

            // Archive only Extent Report
            archiveArtifacts(
                artifacts: 'target/Reports/**/*',
                fingerprint: true
            )

            echo 'Pipeline execution completed.'
        }

        success {

            emailext(
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <h2>Build Successful</h2>

                <p><b>Project:</b> ${env.JOB_NAME}</p>
                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Build URL:</b> ${env.BUILD_URL}</p>

                <p><b>Reports Available:</b></p>

                <ul>
                    <li>Extent Report</li>
                    <li>Allure Report</li>
                </ul>

                <p>Please check Jenkins for the reports.</p>
                """,
                mimeType: 'text/html',
                to: 'mandalamanikanta594@gmail.com'
            )

            echo 'Build Successful.'
        }

        failure {

            emailext(
                subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <h2>Build Failed</h2>

                <p><b>Project:</b> ${env.JOB_NAME}</p>
                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Build URL:</b> ${env.BUILD_URL}</p>

                <p>Please check Jenkins Console Output, Extent Report and Allure Report for failure details.</p>
                """,
                mimeType: 'text/html',
                to: 'mandalamanikanta594@gmail.com'
            )

            echo 'Build Failed.'
        }
    }
}