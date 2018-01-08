set SERVER_VERSION=3.8.1
set NODE_PORT=5558
set HUB_PORT=4444
set HOST=localhost
set GECKO_DRIVER=C:\Users\Lusine_Hakobyan\Desktop\SeleniumActions\grid_scripts\drivers\geckodriver.exe

java -Dwebdriver.gecko.driver=%GECKO_DRIVER%e -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%HOST%:%HUB_PORT%/grid/register -browser "browserName=firefox,maxInstances=3,platform=WINDOWS" -port %NODE_PORT%
pause
