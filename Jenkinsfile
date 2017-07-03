pipeline {
  agent { label 'maven' }

  options {
	buildDiscarder(logRotator(numToKeepStr: '5'))
	timeout(time: 1, unit: 'HOURS')
  }

  triggers { cron('@daily') }

  stages {
	stage('prepare'){
	  steps {
	    checkout scm
	  }
	}

	stage('deploy'){ steps { sh "mvn clean -U -B deploy" } }
  }

  post {
	failure {
	  slackSend color: 'danger', message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} Failure (<${env.BUILD_URL}|Open>)"
	}
	unstable {
	  slackSend color: 'warning', message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} Unstable (<${env.BUILD_URL}|Open>)"
	}
	changed {
	  script{
		if('SUCCESS'.equals(currentBuild.result)) {
		  slackSend color: 'good', message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} Back to normal (<${env.BUILD_URL}|Open>)"
		}
	  }
	}
  }
}