FROM openjdk:11

RUN mkdir -p /home/todolist

COPY . /home/todolist

WORKDIR /home/todolist

EXPOSE 8400

CMD ["./gradlew", "bootRun"]