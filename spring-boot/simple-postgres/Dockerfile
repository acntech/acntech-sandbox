FROM acntechie/jre

ARG APPLICATION_NAME

COPY ./target/${APPLICATION_NAME}.war /application.war

ENV JAVA_OPTS=""

EXPOSE 8080

CMD ["java", "-jar", "/application.war"]