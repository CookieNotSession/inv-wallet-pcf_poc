profile=$1
echo "profile=$profile. "
cd inv-adapter
if [ "$profile" != "" ];then
  echo "The following profiles are active: $profile. "
  java -Dspring.profiles.active=$profile -jar target/inv-adapter-0.0.1-SNAPSHOT.jar
else
  echo "The following profiles are active: local. "
  java -Dspring.profiles.active=local -jar target/inv-adapter-0.0.1-SNAPSHOT.jar
fi
