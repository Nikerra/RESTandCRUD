net stop tomcat
mvn -version

cd C:\ProgramData\Jenkins\.jenkins\workspace\rest
mvn clean package

cmd /c call C:\ProgramData\Jenkins\.jenkins\workspace\rest\cleanPackage.bat



cd C:\tomcat\webapps
del RESTandCRUD.war

copy C:\ProgramData\Jenkins\.jenkins\workspace\rest\target\RESTandCRUD.war C:\tomcat\webapps

net start tomcat
