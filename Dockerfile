FROM maven:3.9.11-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN mvn clean compile

CMD ["mvn", "clean", "test", "-DsuiteXmlFile=sanity.xml"]