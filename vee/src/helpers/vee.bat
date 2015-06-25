@echo off
dir /b vee*.jar > tmpFile
set /p Jar= < tmpFile
java -jar %Jar% -e "%CD%\%1"