job('NodeJS Docker example') {
    scm {
        git('git://github.com/krinub06/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('ksrinub06')
            node / gitConfigEmail('ksrinub06@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ksrinub06/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('ksrinub06')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
