pipeline {
    agent any
    tools { 
        maven 'Maven' 
        jdk 'jdk8' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

	    // Prepare the version by extracting major and minor number and creating a new number: [major].[minor]-[BUILDNUMBER]
	    stage ('Set Version') {
	    	steps {
		    	
		    // Update the project pom.xml files
		    sh "${M2_HOME}/bin/mvn -B versions:set -DgenerateBackupPoms=false -DnewVersion=3.0"
		    // Add the pom.xml files and create a commit+tag
		    sh 'git add .'
		    sh "git commit -m 'Raise version new'"
		    sh "git tag v3.0"
		    }
	    }
	   
  	} // End of Stages
}

