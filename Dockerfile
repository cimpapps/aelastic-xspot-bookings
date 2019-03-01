FROM openjdk:8-jdk-alpine

ARG DEPENDENCY=target/dependency
ENV BOOTSTRAP_SERVERS="0.0.0.0:9092"
ENV PRODUCER_CLIENT_ID="bookings_service"

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.aelastic.xspot.bookings.BookingsApplications","-Dspring.kafka.bootstrap-servers=$BOOTSTRAP_SERVERS","-Dspring.kafka.producer.client-id=$PRODUCER_CLIENT_ID"]