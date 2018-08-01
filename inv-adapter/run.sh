server_port=$1
echo "server.port=$server_port"
if [ "$server_port" != "" ];then
  mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local" -Dspring-boot.run.arguments=--server.port=$1
else
  mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"
fi