FROM quay.io/wildfly/wildfly

LABEL authors="simon"

COPY target/sysark_lab2-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone.deployments/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]