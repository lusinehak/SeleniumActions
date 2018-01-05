set SERVER_VERSION=3.8.1
set NODE_PORT=5559
set HUB_PORT=4444
set HOST=localhost
set IE_DRIVER=C:\Users\Lusine_Hakobyan\se\drivers\IEDriverServer.exe

java -Dwebdriver.ie.driver=%IE_DRIVER% -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%HOST%:%HUB_PORT%/grid/register -browser "browserName=internet explorer,version=11,maxinstance=1" -port %NODE_PORT%
pause