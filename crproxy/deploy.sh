mvn clean install -Dmaven.test.skip=true
cd target
cp crproxy.war /home/dan/jboss-as-7.1.1.Final/standalone/deployments
