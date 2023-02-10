@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Target_ROS startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and TARGET_ROS_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Target_ROS-0.1-SNAPSHOT.jar;%APP_HOME%\lib\rosjava-0.3.6.jar;%APP_HOME%\lib\commons-configuration2-2.3.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\commons-collections4-4.1.jar;%APP_HOME%\lib\rosjava_test_msgs-0.3.0.jar;%APP_HOME%\lib\rosgraph_msgs-1.11.2.jar;%APP_HOME%\lib\nav_msgs-1.12.7.jar;%APP_HOME%\lib\tf2_msgs-0.5.20.jar;%APP_HOME%\lib\geometry_msgs-1.12.7.jar;%APP_HOME%\lib\actionlib_msgs-1.12.7.jar;%APP_HOME%\lib\std_msgs-0.5.11.jar;%APP_HOME%\lib\message_generation-0.3.3.jar;%APP_HOME%\lib\apache_xmlrpc_server-0.3.6.jar;%APP_HOME%\lib\apache_xmlrpc_client-0.3.6.jar;%APP_HOME%\lib\apache_xmlrpc_common-0.3.6.jar;%APP_HOME%\lib\dnsjava-2.1.1.jar;%APP_HOME%\lib\com.springsource.org.apache.commons.httpclient-3.1.0.jar;%APP_HOME%\lib\com.springsource.org.apache.commons.logging-1.1.1.jar;%APP_HOME%\lib\com.springsource.org.apache.commons.net-2.0.0.jar;%APP_HOME%\lib\guava-12.0.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\netty-3.5.2.Final.jar;%APP_HOME%\lib\com.springsource.org.apache.commons.codec-1.3.0.jar;%APP_HOME%\lib\com.springsource.org.apache.commons.io-1.4.0.jar;%APP_HOME%\lib\commons-pool-1.6.jar;%APP_HOME%\lib\com.springsource.org.apache.commons.lang-2.4.0.jar;%APP_HOME%\lib\gradle_plugins-0.3.3.jar;%APP_HOME%\lib\ws-commons-util-1.0.1.jar;%APP_HOME%\lib\junit-3.8.2.jar;%APP_HOME%\lib\jsr305-1.3.9.jar;%APP_HOME%\lib\xml-apis-1.0.b2.jar


@rem Execute Target_ROS
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %TARGET_ROS_OPTS%  -classpath "%CLASSPATH%" eu.spyros.koukas.ros.examples.Main %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable TARGET_ROS_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%TARGET_ROS_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
