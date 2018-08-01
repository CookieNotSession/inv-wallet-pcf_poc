profile=$1
echo "profile=$profile. "
cd inv-config-server
if [ "$profile" != "" ];then
  echo "The following profiles are active: $profile. "
  java -Dspring.profiles.active=$profile -jar target/inv-config-server-0.0.1-SNAPSHOT.jar 
else
  echo "The following profiles are active: native. "
  java -Dspring.profiles.active=native -jar target/inv-config-server-0.0.1-SNAPSHOT.jar 
fi
