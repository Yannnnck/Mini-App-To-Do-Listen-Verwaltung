@echo off
cd /d "%~dp0backend"
echo Starte Spring Boot Anwendung...
IF EXIST mvnw (
    call mvnw spring-boot:run
) ELSE IF EXIST gradlew (
    call gradlew bootRun
) ELSE (
    echo Kein Build-Tool gefunden! Stelle sicher, dass Maven oder Gradle vorhanden ist.
    pause
)
exit
