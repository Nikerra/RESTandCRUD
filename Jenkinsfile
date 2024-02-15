pipeline {
  agent any

  stages {
    stage('Stop Tomcat') {
      steps {
        bat 'net stop tomcat'
      }
    }

    stage('Build and Package') {
      steps {
        bat 'mvn -version'
        bat 'cd C:\\ProgramData\\Jenkins.jenkins\\workspace\\rest && mvn clean package'
        bat 'cmd /c call C:\\ProgramData\\Jenkins.jenkins\\workspace\\rest\\cleanPackage.bat'
      }
    }

    stage('Deploy to Tomcat') {
      steps {
        bat 'cd C:\\tomcat\\webapps && del RESTandCRUD.war'
        bat 'copy C:\\ProgramData\\Jenkins.jenkins\\workspace\\rest\\target\\RESTandCRUD.war C:\\tomcat\\webapps'
      }
    }

    stage('Start Tomcat') {
      steps {
        bat 'net start tomcat'
      }
    }
  }
}
