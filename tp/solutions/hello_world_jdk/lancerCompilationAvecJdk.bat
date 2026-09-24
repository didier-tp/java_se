set JAVA_HOME=C:\Program Files\Java\jdk-21
REM set JAVA_HOME=C:\Program Files\Java\jdk-25.0.2
set PATH=%JAVA_HOME%\bin
cd "%~dp0/src"
REM cd src/main/java
REM javac -d ../../../bin tp/MyApp.java
javac -d ../bin tp/MyApp.java
pause