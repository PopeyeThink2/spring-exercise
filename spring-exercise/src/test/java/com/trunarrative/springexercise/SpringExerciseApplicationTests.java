package com.trunarrative.springexercise;

import com.trunarrative.springexercise.entity.Address;
import com.trunarrative.springexercise.entity.Company;
import com.trunarrative.springexercise.entity.Officers;
import com.trunarrative.springexercise.mapper.AddressMapper;
import com.trunarrative.springexercise.mapper.CompanyMapper;
import com.trunarrative.springexercise.mapper.OfficersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringExerciseApplicationTests {

    @Autowired
    DataSource dataSource;

    // test database connection
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    OfficersMapper officersMapper;

    @Test
    void queryAllTest() {
        List<Company> companyList = companyMapper.queryAll();
        companyList.forEach(System.out::println);

        List<Address> addressList = addressMapper.queryAll();
        addressList.forEach(System.out::println);

        List<Officers> officers = officersMapper.queryAll();
        officers.forEach(System.out::println);
    }


}
