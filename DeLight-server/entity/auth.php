<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

class Auth
{
    private $login;
    private $db;
    private $password;

    private $is_authorized = false;

    public function __construct($login, $password)
    {
        $this->password = $password;
        $this->login = $login;
        $this->db = new DB_CONNECT();
    }

    public function __destruct()
    {
        $this->db = null;
    }

    public static function isAuthorized()
    {
        if (!empty($_SESSION["user_id"])) {
            return (bool) $_SESSION["user_id"];
        }
        return false;
    }

    public function passwordHash($password, $salt = null, $iterations = 10)
    {
        $salt || $salt = uniqid();
        $hash = md5(md5($password . md5(sha1($salt))));

        for ($i = 0; $i < $iterations; ++$i) {
            $hash = md5(md5(sha1($hash)));
        }

        return array('hash' => $hash, 'salt' => $salt);
    }

    public function getSalt($login) {
        $query = "select salt from users where login = '$login'";
        $result = $this->db->getConnection()->query($query);
        $row = $result->fetch_array();
        if (!$row) {
            return false;
        }
        return $row["salt"];
    }

    public function authorize($remember = false)
    {
        $login = $this->login;
        $password = $this->password;
        $query = "select login from users where
            login = '$login' and password = '$password'";
        $salt = $this->getSalt($login);

        if (!$salt) {
            return false;
        }

        $hashes = $this->passwordHash($password, $salt);
        $password = $hashes["hash"];
        
        $result = $this->db->getConnection()->query($query);

        $user = $result->fetch_array();

        if (!$user) {
            $this->is_authorized = false;
        } else {
            $this->is_authorized = true;
            $this->saveSession($remember);
        }

        return $this->is_authorized;
    }

    public function logout()
    {
        if (!empty($_SESSION["user_id"])) {
            unset($_SESSION["user_id"]);
        }
    }

    public function saveSession($remember = false, $http_only = true, $days = 7)
    {
        $_SESSION["user_id"] = $this->login;

        if ($remember) {

            $sid = session_id();

            $expire = time() + $days * 24 * 3600;
            $domain = "";
            $secure = false;
            $path = "/";

            $cookie = setcookie("sid", $sid, $expire, $path, $domain, $secure, $http_only);
        }
    }

    public function create() {
        $login = $this->login;
        $password = $this->password;
        $user_exists = $this->getSalt($login);

        if ($user_exists) {
            throw new \Exception("User exists: " . $login, 1);
        }

        $query = "insert into users (login, password, salt)
            values ('$login', '$password', '$salt')";
        $hashes = $this->passwordHash($password);

        $db->getConnection->query($query);

    }
}

?>