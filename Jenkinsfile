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

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    sh 'echo done' 
                }
            }
        }
        
        stage ('Pramot to Deploy') {
            steps {
                input('Is sanity check good and ready to deploy?')
            }
         }
         
         stage ('Deploy') {
            steps {
                sh 'echo ha ha pass bhail' 
            }
            post {
                success {
                    sh 'echo deploy completed' 
                }
            }
        }
        
        
    }//End of Stages
    
    
}