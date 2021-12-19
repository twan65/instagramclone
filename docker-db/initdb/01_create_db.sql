CREATE DATABASE instagramclonedb CHARACTER SET utf8mb4;
CREATE USER 'myadmin'@localhost IDENTIFIED BY 'myadminpass';
GRANT ALL ON instagramclonedb.* TO 'myadmin'@'localhost';