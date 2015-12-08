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
            $query = "select salt from candidate_users where login = '$login'";
            $result = $this->db->getConnection()->query($query);
            $row = $result->fetch_array();
            if (!$row) {
                return false;
            }
        }
        return $row["salt"];
    }

    public function authorize($remember = false)
    {

        $login = $this->login;
        $salt = $this->getSalt($login);

        if (!$salt) {
            return false;
        }

        $hashes = $this->passwordHash($this->password, $salt);
        $password = $hashes["hash"];

        $query = "select login from users where
            login = '$login' and password = '$password'";
        
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
        $login = $this->login;
        $_SESSION["user_id"] = $login;

        if ($remember) {

            $sid = session_id();

            $expire = time() + $days * 24 * 3600;
            $domain = "";
            $secure = false;
            $path = "/";

            $cookie = setcookie("sid", $sid, $expire, $path, $domain, $secure, $http_only);

            $cookie = $_COOKIE["sid"];
            $query = "update users set cookie = '$cookie' where login = '$login'";

            $this->db->getConnection()->query($query);
        }
    }

    public function create() {
        $login = $this->login;
        $user_exists = $this->getSalt($login);

        if ($user_exists) {
            print_r($user_exists);
            return false;
        }

        $hashes = $this->passwordHash($this->password);

        $password = $hashes["hash"];
        $salt = $hashes["salt"];

        $query = "insert into candidate_users (login, password, salt)
            values ('$login', '$password', '$salt')";

        $this->db->getConnection()->query($query);

        return true;
    }
}

?>