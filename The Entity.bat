@echo off
cd db-derby-10.14.2.0-bin\bin
call stopNetworkServer.bat
start startNetworkServer.bat
pause
cd ..\..
cd build\classes
java -cp ..\..\db-derby-10.14.2.0-bin\lib\derby.jar;..\..\db-derby-10.14.2.0-bin\lib\derbyclient.jar;  GUI.MainDriver