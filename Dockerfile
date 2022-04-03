FROM openjdk:11
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=SimpleHabitTrackerBot
ENV BOT_TOKEN=5271928589:AAFM5mk4PaFBA5hveS58PtddMjixbCrbpCE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot_username=${BOT_NAME}", "Dbot_token=${BOT_TOKEN}", "-jar", "/app.jar"]