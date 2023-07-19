#!/bin/bash
echo "*** Set -XX:NewRatio=2 ***"
sed -i 's/-XX:NewSize=768m -XX:MaxNewSize=768m/-XX:NewRatio=2/g' $LIFERAY_HOME/tomcat/bin/setenv.sh
echo "*** $LIFERAY_HOME/tomcat/bin/setenv.sh ***"
cat $LIFERAY_HOME/tomcat/bin/setenv.sh
