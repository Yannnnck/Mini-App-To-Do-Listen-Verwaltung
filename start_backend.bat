@echo off
echo Starte die To-Do App...
cd /d "%~dp0backend"

echo Baue das Projekt mit Maven...
mvn clean install

if %errorlevel% neq 0 (
    echo Fehler beim Bauen des Projekts!
    echo Drücke eine beliebige Taste zum Beenden...
    pause
    exit /b %errorlevel%
)

echo Starte die Spring Boot Anwendung...
mvn spring-boot:run

echo Anwendung wurde beendet.
echo Drücke eine beliebige Taste zum Schließen...
pause
