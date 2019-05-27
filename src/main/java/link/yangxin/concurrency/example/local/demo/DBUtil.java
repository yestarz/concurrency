package link.yangxin.concurrency.example.local.demo;

import link.yangxin.concurrency.util.ThreadPoolUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;

/**
 * @author yangxin
 * @date 2019/5/27
 */
public class DBUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection(){
        try {
            Connection conn = threadLocal.get();
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            threadLocal.set(connection);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = ThreadPoolUtil.createThreadPool();
        for (int i = 0; i < 10; i++) {
            Connection connection = getConnection();
            System.out.println(connection);
        }
        pool.execute(()->{
            Connection connection = getConnection();
            System.out.println(connection);
        });

    }

}