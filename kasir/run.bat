@echo off
title Aplikasi Kasir POS (Point of Sales)
chcp 65001 > nul
cls

set "NB_JDK=C:\Program Files\Apache NetBeans\jdk\bin"

if exist "%NB_JDK%\java.exe" (
    set "JAVA_EXE=%NB_JDK%\java.exe"
    set "JAVAC_EXE=%NB_JDK%\javac.exe"
) else (
    set "JAVA_EXE=java"
    set "JAVAC_EXE=javac"
)

echo ========================================================
echo        MEMPERSIAPKAN APLIKASI KASIR POS (GUI)...
echo ========================================================

if not exist "build\classes" mkdir "build\classes"

echo [1/2] Mengompilasi kode sumber Java...
dir /s /B src\*.java > sources.txt
"%JAVAC_EXE%" -encoding UTF-8 -d "build\classes" @sources.txt
if %errorlevel% neq 0 (
    echo [ERROR] Kompilasi gagal! Periksa instalasi Java Anda.
    del sources.txt 2>nul
    pause
    exit /b %errorlevel%
)
del sources.txt 2>nul

echo [2/2] Membuka Jendela Aplikasi Kasir (GUI JFrame)...
start "" "%JAVA_EXE%" -cp "build\classes" com.kasir.main.MainKasir
echo.
echo [OK] Jendela aplikasi kasir telah dibuka di layar komputer Anda!
timeout /t 3 > nul
exit
