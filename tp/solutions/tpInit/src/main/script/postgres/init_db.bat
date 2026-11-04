REM lancer si besoin le service postgres
set POSTGRES_HOME=C:\Program Files\PostgreSQL\14
cd /d %~dp0
REM mot de passe souvent root ou formation ou rien
"%POSTGRES_HOME%\bin\psql"  --username=root  --file=init_db.sql
pause