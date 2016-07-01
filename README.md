jsf-sandbox
===========

jsf sandbox project using [Java EE Web Profile](https://jcp.org/en/jsr/detail?id=342), mainly using:

- [GlassFish - World's first Java EE 7 Application Server](https://glassfish.java.net/downloads/ri/)
    - [Embedded GlassFish Web Plugin](http://opk.sourceforge.net/embedded-glassfish-web-plugin/)
    - mvn clean package glassfish:run
    - [Embedded GlassFish Plugin](https://embedded-glassfish.java.net/) [Last changes](https://blogs.oracle.com/Romano/entry/changes_in_the_maven_embedded)
    - mvn clean package embedded-glassfish:run
- [WildFly - Fly Fast, and Free](http://wildfly.org/downloads/)
    - [WildFly Maven Plugin](https://docs.jboss.org/wildfly/plugins/maven/latest/)
    - mvn clean wildfly:run
    - [wildfly Swarm Plugin](https://wildfly-swarm.gitbooks.io/wildfly-swarm-users-guide/content/getting-started/tooling/maven-plugin.html)
    - mvn clean install -Dwildfly-swarm.useUberJar=true
    - java -jar target/jsf-sandbox-swarm.jar
- [Apache TomEE](http://tomee.apache.org/downloads.html)
    - [TomEE Embedded Maven Plugin](http://tomee.apache.org/tomee-embedded-maven-plugin.html)
    - mvn clean package tomee-embedded:run
    - [TomEE Maven Plugin](http://tomee.apache.org/tomee-maven-plugin.html)
    - mvn clean package tomee:run
    - Creates an executable jar of the application:
    - mvn clean package tomee:exec
    - java -jar target/jsf-sandbox-exec.jar

Hoping that some guys come and get into the game as well
- [Jetty](http://www.eclipse.org/jetty/documentation/9.2.0.v20140526/jetty-javaee.html)

[Maven Build plugins](https://maven.apache.org/plugins/) properties:

```xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
        <javaee.web.api.version>7.0</javaee.web.api.version>
        <jsf-project-stage>Development</jsf-project-stage>
        <jsf-facelets-refresh-period>1</jsf-facelets-refresh-period>
        <jsf-state-saving-method>server</jsf-state-saving-method>
        <maven.compiler.plugin.version>3.3</maven.compiler.plugin.version>
        <maven.war.plugin.version>2.6</maven.war.plugin.version>
        <embedded-glassfish-web-plugin.version>2.1</embedded-glassfish-web-plugin.version>
        <maven-embedded-glassfish-plugin.version>4.1.1</maven-embedded-glassfish-plugin.version>
        <wildfly-maven-plugin.version>1.1.0.Alpha10</wildfly-maven-plugin.version>
        <wildfly-swarm-plugin.version>1.0.0.CR2</wildfly-swarm-plugin.version>
        <tomee-embedded-maven-plugin.version>7.0.1</tomee-embedded-maven-plugin.version>
        <tomee-maven-plugin.version>7.0.1</tomee-maven-plugin.version>
    </properties>   
```
