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
		    	script {
				    def originalV = version();
				    def major = originalV[0];
				    def minor = originalV[1];
				    def v = "${major}.${minor}-${env.BUILD_NUMBER}"
				    if (v) {
				       echo "Building version ${v}"
				    }
				}
		    // Update the project pom.xml files
		    sh "${mvnHome}/bin/mvn -B versions:set -DgenerateBackupPoms=false -DnewVersion=${v}"
		    // Add the pom.xml files and create a commit+tag
		    sh 'git add .'
		    sh "git commit -m 'Raise version'"
		    sh "git tag v${v}"
		    }
	    }
	    // Create the release build
	    stage ('Release Build'){
	    	steps{
			    // Use the SSH Agent Plugin to forward the used ssh credentials 
			    // from the jenkins master to the jenkins slave. Otherwise you may 
			    // not be able to push/pull, clone
			    //sshagent(['601b6ce9-37f7-439a-ac0b-8e368947d98d']) {
		       // Invoke the maven build without tests and deploy the artifacts
		       sh "${mvnHome}/bin/mvn -B -DskipTests clean deploy"
		       // Push the commit and the created tag
		       sh "git push origin master"
		       sh "git push origin v${v}"
		       }
	    }
  	} // End of Stages
}

// Parse the pom.xml and extract the version information.
def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)-.*</version>'
    matcher ? matcher[0][1].tokenize(".") : null
}