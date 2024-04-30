FROM openjdk:17.0

COPY artefactos/jar/demo.jar programa.jar

ENTRYPOINT ["java", "-jar","/programa.jar"]