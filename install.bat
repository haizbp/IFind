@echo off
call mvn clean install &
xcopy target\crawler-0.0.1-SNAPSHOT.jar execute\crawler.jar* /Y &
xcopy config execute\config /E/Y &
pause