FROM maven:latest AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM quay.io/wildfly/wildfly:latest

COPY --from=builder /app/target/sysark_lab2-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "--debug"]