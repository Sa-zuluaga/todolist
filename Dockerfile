FROM openjdk:11
LABEL Description = "This image contains the app todolist"\
      Version = "1.0"\
      maintainers = "szuluaga200@gmail.com"

RUN mkdir -p /home/todolist

COPY build/libs/todolist-0.0.1-SNAPSHOT.jar /home/todolist/todolist.jar

RUN ls /home/todolist

WORKDIR /home/todolist

EXPOSE 8400

CMD ["java", "-jar", "todolist.jar"]