@echo off
set /p sifre=Enter MySQL password:

echo Creating database bookrecommendationsystem
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql" -u root -p%sifre% -h localhost -e "CREATE DATABASE bookrecommendationsystem;"

echo Importing SQL file to MySQL server...
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql" -u root -p%sifre% -h localhost bookrecommendationsystem < %~dp0bookrecommendationsystem.sql

echo SQL file import completed.