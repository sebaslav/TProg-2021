echo "======================"
echo "===TPROG - Grupo 11==="
echo "======================"

echo "Borrando JAR y WAR previos..."
rm servidor.jar
rm web.war

echo "Generando archivo JAR..."
cd ./ServidorCentral/
mvn clean
mvn install -Dmaven.test.skip=true

echo "Generando archivo WAR..."
cd ../ServidorWeb/
mvn clean
mvn install

#Volvemos a carpeta de arriba
cd ../

# Renombramos el .jar y .war
mv ServidorCentral-0.0.1-SNAPSHOT.jar servidor.jar
mv ServidorWeb-0.0.1-SNAPSHOT.war web.war

echo "Iniciando Apache Tomcat..."
sh /home/vagrant/apache-tomcat-9.0.46/bin/startup.sh

echo "Abriendo el servidor central..."
java -jar servidor.jar

# descomentar una vez testeado
#echo "Cerrando Apache Tomcat..."
#cd /home/vagrant/apache-tomcat-9.0.46/bin/
#sh shutdown.sh
