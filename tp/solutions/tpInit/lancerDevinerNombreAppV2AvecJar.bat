set JAVA_HOME=C:\Program Files\Java\jdk-21
REM set JAVA_HOME=C:\Program Files\Java\jdk-25.0.2
set PATH=%JAVA_HOME%\bin
cd "%~dp0"
REM target/tpInit.jar à construire via mvn package
java -jar target/tpInit.jar
pause