# FOR LOCAL DEVELOPMENT #

Please, dont override local folder, use it as skeleton for developers.

Steps to reuse:
1. Copy full "local" folder to "me"
2. Run "blade server init --environment me". This will create a new Tomcat server in liferay/bundles.
3. Run "blade server start -d" to start in debug mode (port 8000)
4. Run "blade server stop" to stop server.

You can run steps 2,3,4 when you change configs or files in this project.

NEVER PUSH PERSONAL INFORMATION OR LOCAL ROUTES.