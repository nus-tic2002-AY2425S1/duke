@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist .\data rmdir /S /Q .\data

REM compile the code into the bin folder
javac -cp ..\src\main\java\wkduke\WKDuke.java -Xlint:none -d ..\bin ^
    ..\src\main\java\wkduke\*.java ^
    ..\src\main\java\wkduke\command\*.java ^
    ..\src\main\java\wkduke\common\*.java ^
    ..\src\main\java\wkduke\exception\*.java ^
    ..\src\main\java\wkduke\parser\*.java ^
    ..\src\main\java\wkduke\storage\*.java ^
    ..\src\main\java\wkduke\task\*.java ^
    ..\src\main\java\wkduke\ui\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin wkduke.WKDuke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
