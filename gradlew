#!/bin/sh
# Gradle wrapper
APP_HOME=\$(cd "\$(dirname "\$0")" && pwd -P)
CLASSPATH="\$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
JAVACMD=\${JAVA_HOME:+\"\$JAVA_HOME/bin/\"}java
if [ ! -x "\$JAVACMD" ]; then JAVACMD=java; fi
exec "\$JAVACMD" -classpath "\$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "\$@"
