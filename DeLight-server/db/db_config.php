<?php 
//Файл с данными для доступа к БД, не выкладывать никуда кроме ftp
    define('DB_USER', getenv('OPENSHIFT_MYSQL_DB_USERNAME'));
    define('DB_PASSWORD', getenv('OPENSHIFT_MYSQL_DB_PASSWORD'));
    define('DB_DATABASE', getenv('OPENSHIFT_GEAR_NAME'));
    define('DB_SERVER', getenv('OPENSHIFT_MYSQL_DB_HOST'));
?>