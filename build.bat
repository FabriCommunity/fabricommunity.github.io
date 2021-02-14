@ECHO off

if not exist SSG.jar (
    cmd /C download.bat
)

java -jar SSG.jar build^
    --default-template section/index^
    --output-path build^
    --sources-path site^
    --template-path templates^
    --section docs

xcopy site\static build\static /E/I/Q

echo %cmdcmdline% | findstr /i /c:"%~nx0" >NUL 2>&1 && set standalone=1

if defined standalone (
    echo.
    PAUSE
)
