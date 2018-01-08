set SERVER_VERSION=3.8.1
set TASK_NAME=SeleniumServerNode3
set NODE_PORT=5557
set HUB_PORT=4444
set HOST=localhost
set CHROME_DRIVER=C:\Users\Lusine_Hakobyan\Desktop\SeleniumActions\grid_scripts\drivers\chromedriver.exe

java -Dwebdriver.chrome.driver=%CHROME_DRIVER% -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%HOST%:%HUB_PORT%/grid/register -browser "browserName=chrome,version=63,maxInstances=7,platform=WINDOWS" -port %NODE_PORT%
pause
