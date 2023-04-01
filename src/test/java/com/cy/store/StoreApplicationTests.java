package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootTest
class StoreApplicationTests {
    @Autowired  //自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    /**
     * 数据库连接池
     * 1.DBCP
     * 2.C3P0
     * 3.Hikari
     * HikariProxyConnection@1184166964 wrapping com.mysql.cj.jdbc.ConnectionImpl@56928e17
     * @throws SQLException
     */
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
    @Test
    void a() {
        ArrayList<Integer> w = new ArrayList<Integer>();
        Integer[] w1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                61, 62, 63, 64,65,67,68};
        for (int i = 1; i < w1.length+1; i++) {
            w.add(i);
        }
        //数组转集合
        //System.out.println(w.get(1));
        Integer[] b = new Integer[32];//b空数组
        Random r = new Random();

        for (int i = 0; i < b.length; i++) {
            while (true) {
                Integer q = r.nextInt(67);//随机的0-63数字之一
                Integer w2= w.get(q);
                boolean flag = true;
                for (int j = 0; j < i; j++) {
                    //data值与之前的值重复,data需要重新赋值
                    if (b[j] == w2) {
                        //跳出本次判断重复循环,重新生成一个
                        flag = false;
                        break;
                    }
                }
                //如果判断没有重复，说明flag还是true 跳出死循环
                if (flag) {
                    b[i] = w2;
                    break;
                }
            }
        }
        //一条不重复的32数组
        System.out.println("b"+ Arrays.toString(b));
        List<Integer[]> list = new ArrayList<>();
        list.add(b);
    }

}
