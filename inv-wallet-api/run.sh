echo "server.port=$1"
mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local" -Dspring-boot.run.arguments=--server.port=$1