FROM openjdk:17.0

COPY out/artifacts/demo_jar/demo.jar programa.jar

ENTRYPOINT ["java", "-jar","/programa.jar"]