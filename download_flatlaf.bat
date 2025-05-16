@echo off
echo Downloading FlatLaf library...

REM Create lib directory if it doesn't exist
if not exist "lib" mkdir lib

REM Download FlatLaf JAR file
curl -L -o lib/flatlaf-3.2.1.jar https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.1/flatlaf-3.2.1.jar

echo.
if exist "lib/flatlaf-3.2.1.jar" (
    echo FlatLaf library downloaded successfully to lib/flatlaf-3.2.1.jar
    echo.
    echo To use FlatLaf, add the JAR file to your project's classpath.
    echo For NetBeans: Right-click on Libraries, select "Add JAR/Folder", and select the downloaded JAR file.
    echo For Eclipse: Right-click on the project, select Properties, Java Build Path, Libraries, Add External JARs, and select the downloaded JAR file.
    echo For IntelliJ IDEA: File, Project Structure, Libraries, + (Add), Java, and select the downloaded JAR file.
) else (
    echo Failed to download FlatLaf library.
    echo Please download it manually from https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.1/flatlaf-3.2.1.jar
)

echo.
pause
