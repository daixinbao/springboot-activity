package com.springboot.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	DataSource dataSource;
	@Test
	public void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		DruidDataSource druidDataSource = (DruidDataSource) this.dataSource;
		System.out.println(druidDataSource.getMaxActive());
		System.out.println(druidDataSource.getActiveCount());
		System.out.println("默认数据源："+connection);
		connection.close();
	}

}
