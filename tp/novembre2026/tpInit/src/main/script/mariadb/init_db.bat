REM lancer si besoin le service mariadb ou mysql (net start mysql ou mariadb)
set MYSQL_HOME=C:\Program Files\MariaDB 11.8
cd /d %~dp0
REM mot de passe souvent root ou formation ou rien
"%MYSQL_HOME%\bin\mysql"  -u root -p < init_db.sql
pause