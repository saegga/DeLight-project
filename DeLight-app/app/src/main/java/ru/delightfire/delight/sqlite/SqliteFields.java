package ru.delightfire.delight.sqlite;

/**
 * Created by sergei on 06.01.2016.
 */
public class SQLiteFields {

    public static final String TABLE_USER = "users";
    public static final String TABLE_TRAININGS = "trainings";
    public static final String TABLE_TRAINING_MEMBER = "training_member";
    public static final String TABLE_SHOW = "show";
    public static final String TABLE_CUSTOMERS = "customers";
    public static final String TABLE_PERFORMANCE = "performance";
    public static final String TABLE_PERF_MEMBER = "perf_member";
    public static final String TABLE_LIST_PERFORMANCES = "list_performances";
    public static final String TABLE_MEET = "meet";
    public static final String TABLE_MEET_MEMBER = "meet_member";
    public static final String TABLE_REQUISITE = "requsite";
    public static final String TABLE_HAVE_REQUISITE = "have_requsite";
    public static final String TABLE_EVENT_PLACES = "event_places";

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String ROLE = "role";
    public static final String SALT = "salt";
    public static final String COOKIE = "cookie";
    public static final String PHONE = "phone";

    public static final String TRAINING_ID = "training_id";
    public static final String NAME = "name";
    public static final String OWNER_LOGIN = "owner_login";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String DAY_OF_WEEK = "dayOfWeek";
    public static final String AGENDA = "agenda";
    public static final String PLACE = "place";

    public static final String USER_ID = "user_id";
    public static final String PERF_ID = "perf_id";
    public static final String SHOW_ID = "show_id";
    public static final String MEET_ID = "meet_id";
    public static final String REQUISITE_ID = "requisite_id";
    public static final String PLACE_ID = "place_id";

    public static final String DATE = "date";
    public static final String DESCRIPTION = "description";
    public static final String MONEY = "money";
    public static final String CUSTOMERS_ID = "customers_id";
    public static final String ADDRESS = "address";

    public static final String CREATE_TABLE_USER = "create table " + TABLE_USER + " (" +
            ID + " integer primary key autoincrement," +
            LOGIN + " varchar(20) not null," +
            PASSWORD + " varchar(40) not null," +
            FIRST_NAME + " varchar(20)," +
            LAST_NAME + " varchar(40)," +
            ROLE + " varchar(20)," +
            SALT + " varchar(13) not null," +
            COOKIE + " varchar(50)," +
            PHONE + " varchar(20))";

    public static final String CREATE_TABLE_TRAINING = "create table " + TABLE_TRAININGS + " (" +
            TRAINING_ID + " integer primary key autoincrement," +
            NAME + " varchar(45) not null default 'тренировка'," +
            OWNER_LOGIN + " varchar(20) not null," +
            START_TIME + " varchar(10) not null," +
            END_TIME + " varchar(10) not null," +
            DAY_OF_WEEK + " varchar(20) not null," +
            AGENDA + " text not null," +
            PLACE + " varchar(20))";

    public static final String CREATE_TABLE_TRAINING_MEMBER = "create table " + TABLE_TRAINING_MEMBER + " (" +
            ID + " integer primary key autoincrement," +
            USER_ID + " integer not null," +
            TRAINING_ID + " integer not null)";

    public static final String CREATE_TABLE_SHOW = "create table " + TABLE_SHOW + " (" +
            ID + " integer primary key autoincrement," +
            NAME + " varchar(40) not null default 'выступление'," +
            DATE + " datetime not null," +
            DESCRIPTION + " varchar(30) not null," +
            PLACE + " varchar(50) not null," +
            MONEY + " double," +
            CUSTOMERS_ID + " integer not null);";

    public static final String CREATE_TABLE_CUSTOMERS = "create table " + TABLE_CUSTOMERS + " (" +
            ID + " integer primary key autoincrement," +
            NAME + " varchar(30) not null," +
            PHONE + " varchar(15) not null)";

    public static final String CREATE_TABLE_PERFORMANCE = "create table " + TABLE_PERFORMANCE + " (" +
            ID + " integer primary key autoincrement," +
            NAME + " varchar(50) not null," +
            DESCRIPTION + " varchar(255) null null)";

    public static final String CREATE_TABLE_PERF_MEMBER = "create table " + TABLE_PERF_MEMBER + " (" +
            ID + " integer primary key autoincrement," +
            USER_ID + " integer not null," +
            PERF_ID + " integer not null)";

    public static final String CREATE_TABLE_LIST_PERFORMANCES = "create table " + TABLE_LIST_PERFORMANCES + " (" +
            ID + " integer primary key autoincrement," +
            SHOW_ID + " integer not null," +
            PERF_ID + " integer not null)";

    public static final String CREATE_TABLE_MEET = "create table " + TABLE_MEET + " (" +
            ID + " integer primary key autoincrement," +
            NAME + " varchar(20) not null default 'встреча'," +
            DATE + " datetime not null," +
            AGENDA + " varchar(255)," +
            PLACE + " varchar(40) not null)";

    public static final String CREATE_MEET_MEMBER = "create table " + TABLE_MEET_MEMBER + " (" +
            ID + " integer primary key autoincrement," +
            USER_ID + " integer not null," +
            MEET_ID + " integer not null)";

    public static final String CREATE_TABLE_REQUISITE = "create table " + TABLE_REQUISITE + " (" +
            ID + " integer primary key autoincrement," +
            NAME + " varchar(30) not null)";

    public static final String CREATE_TABLE_HAVE_REQUISITE = "create table " + TABLE_HAVE_REQUISITE + " (" +
            ID + " integer primary key autoincrement," +
            REQUISITE_ID + " integer not null," +
            USER_ID + " integer not null)";

    public static final String CREATE_TABLE_EVENT_PLACES = "create table " + TABLE_EVENT_PLACES + " (" +
            PLACE_ID + " integer primary key autoincrement," +
            NAME + " varchar(30) not null," +
            ADDRESS + " varchar(50) not null)";

    public static final String DELETE_DATA_USERS = "delete from " + TABLE_USER;
    public static final String DELETE_DATA_TRAININGS = "delete from " + TABLE_TRAININGS;
    public static final String DELETE_DATA_MEET = "delete from " + TABLE_MEET;
    public static final String DELETE_DATA_SHOW = "delete from " + TABLE_SHOW;
    public static final String DELETE_DATA_PERFORMANCE = "delete from " + TABLE_PERFORMANCE;
    public static final String DELETE_DATA_CUSTOMERS = "delete from " + TABLE_CUSTOMERS;
    public static final String DELETE_DATA_PERF_MEMBER = "delete from " + TABLE_PERF_MEMBER;
    public static final String DELETE_DATA_LIST_PERFORMANCES = "delete from " + TABLE_LIST_PERFORMANCES;
    public static final String DELETE_DATA_TRAINING_MEMBER = "delete from " + TABLE_TRAINING_MEMBER;
    public static final String DELETE_DATA_HAVE_REQUISITE = "delete from " + TABLE_HAVE_REQUISITE;
    public static final String DELETE_DATA_EVENT_PLACES = "delete from " + TABLE_EVENT_PLACES;
    public static final String DELETE_DATA_REQISITE = "delete from " + TABLE_REQUISITE;
    public static final String DELETE_DATA_MEET_MEMBER = "delete from " + TABLE_MEET_MEMBER;

    public static final String INSERT_USER = "insert into " + TABLE_USER + " (" +
            LOGIN + "," +
            PASSWORD + "," +
            FIRST_NAME + "," +
            LAST_NAME + "," +
            ROLE + "," +
            SALT + "," +
            PHONE + ") " + "values (?,?,?,?,?,?,?)";
    public static final String INSERT_TRAINING = "insert into " + TABLE_TRAININGS + " (" +
            NAME + "," +
            OWNER_LOGIN + "," +
            START_TIME + "," +
            END_TIME + "," +
            DAY_OF_WEEK + "," +
            AGENDA + "," +
            PLACE + ") " + "values (?,?,?,?,?,?,?)";
    public static final String INSERT_MEET = "insert into " + TABLE_MEET + " (" +
            NAME + "," +
            DATE + "," +
            AGENDA + "," +
            PLACE + ") " + " values (?,?,?,?)";
    public static final String INSERT_SHOW = "insert into " + TABLE_SHOW + " (" +
            NAME + "," +
            DATE + "," +
            DESCRIPTION + "," +
            PLACE + "," +
            MONEY + "," +
            CUSTOMERS_ID + ") " + "values (?,?,?,?,?,?)";
    public static final String INSERT_CUSTOMERS = "insert into " + TABLE_CUSTOMERS + " (" +
            NAME + "," +
            PHONE + ") " + "values (?,?)";
    public static final String INSERT_REQUISITE = "insert into " + TABLE_REQUISITE + " (" +
            NAME + ") " + "values (?)";
    public static final String INSERT_PERFORMANCE = "insert into " + TABLE_PERFORMANCE + " (" +
            NAME + "," +
            DESCRIPTION + ") " + "values (?,?)";
    public static final String INSERT_EVENT_PLACES = "insert into " + TABLE_EVENT_PLACES + " (" +
            NAME + "," +
            ADDRESS + ") " + "values (?,?)";
    public static final String INSERT_MEET_MEMBER = "insert into " + TABLE_MEET_MEMBER + " (" +
            USER_ID + "," +
            MEET_ID + ") " + "values (?,?)";
    public static final String INSERT_LIST_PERFORMANCES = "insert into " + TABLE_LIST_PERFORMANCES + " (" +
            SHOW_ID + "," +
            PERF_ID + ") " + "values (?,?)";
    public static final String PERF_MEMBER = "insert into " + TABLE_PERF_MEMBER + " (" +
            USER_ID + "," +
            PERF_ID + ") " + "values (?,?)";
    public static final String INSERT_TRAINING_MEMBER = "insert into " + TABLE_TRAINING_MEMBER + " (" +
            USER_ID + "," +
            TRAINING_ID + ") " + "values (?,?)";
    public static final String INSERT_HAVE_REQISITE = "insert into " + TABLE_HAVE_REQUISITE + " (" +
            REQUISITE_ID + "," +
            USER_ID + ") " + "values (?,?)";

}
