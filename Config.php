<?php
/**
   This is the file which contains all the database configurations and other configurations like Message sending
   details and GCM details.
 */
define('DB_USERNAME', 'root');
define('DB_PASSWORD', '');
define('DB_HOST', 'localhost');
define('DB_NAME', 'notify');
 
/**
 * MSG91 configuration
 */
define('MSG91_AUTH_KEY', "96166AZrXyluyCsoC562fabc4");
// sender id should 6 character long
define('MSG91_SENDER_ID', 'NOTIFY');
 
define('USER_CREATED_SUCCESSFULLY', 0);
define('USER_CREATE_FAILED', 1);
define('USER_ALREADY_EXISTED', 2);
define('ROOT', 3);
?>