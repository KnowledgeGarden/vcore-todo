#cd to this directory
psql postgres
#create a user
create user vcore;
#database name and owner correspond to config.json settings
create database todotest owner vcore;

#leave postgres
\q
#install
psql todotest -f todo.sql

