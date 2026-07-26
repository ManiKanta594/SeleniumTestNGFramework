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
            choices: [
                'sanity.xml',
                'testng-smoke.xml',
                'testng-regression.xml',
                'parallel.xml'
            ],
            description: 'Select TestNG Suite'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: false,
            description: 'Run in Headless Mode'
        )

        choice(
            name: 'EXECUTION_MODE',
            choices: ['LOCAL', 'GRID'],
            description: 'Execution Mode'
        )

        choice(
            name: 'ENVIRONMENT',
            choices: ['QA', 'UAT', 'PROD'],
            description: 'Target Environment'
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

                echo "=============================="
                echo "Browser        : ${params.BROWSER}"
                echo "Suite          : ${params.SUITE_XML}"
                echo "Headless       : ${params.HEADLESS}"
                echo "Execution Mode : ${params.EXECUTION_MODE}"
                echo "Environment    : ${params.ENVIRONMENT}"
                echo "=============================="

                bat """
                mvn clean test ^
                -DsuiteXmlFile=${params.SUITE_XML} ^
                -Dbrowser=${params.BROWSER} ^
                -Dheadless=${params.HEADLESS} ^
                -Dexecution.mode=${params.EXECUTION_MODE} ^
                -Denvironment=${params.ENVIRONMENT}
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

                <p><b>Browser:</b> ${params.BROWSER}</p>

                <p><b>Suite:</b> ${params.SUITE_XML}</p>

                <p><b>Execution Mode:</b> ${params.EXECUTION_MODE}</p>

                <p><b>Environment:</b> ${params.ENVIRONMENT}</p>

                <p><b>Headless:</b> ${params.HEADLESS}</p>

                <hr>

                <p><b>Build URL:</b></p>

                <a href="${env.BUILD_URL}">
                ${env.BUILD_URL}
                </a>

                <hr>

                <h3>Reports</h3>

                <a href="${env.BUILD_URL}Extent_20Automation_20Report/">
                📊 Extent Report
                </a>

                <br><br>

                <a href="${env.BUILD_URL}allure/">
                📈 Allure Report
                </a>

                <hr>

                Regards,<br>
                Jenkins Pipeline
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

                <p><b>Browser:</b> ${params.BROWSER}</p>

                <p><b>Suite:</b> ${params.SUITE_XML}</p>

                <p><b>Execution Mode:</b> ${params.EXECUTION_MODE}</p>

                <p><b>Environment:</b> ${params.ENVIRONMENT}</p>

                <p><b>Headless:</b> ${params.HEADLESS}</p>

                <hr>

                <p><b>Build URL:</b></p>

                <a href="${env.BUILD_URL}">
                ${env.BUILD_URL}
                </a>

                <hr>

                Please check Jenkins Console Output, Extent Report and Allure Report.
                """,
                mimeType: 'text/html',
                to: 'mandalamanikanta594@gmail.com'
            )

            echo 'Build Failed.'
        }
    }
}