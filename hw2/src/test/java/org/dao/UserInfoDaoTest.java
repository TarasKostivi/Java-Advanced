package org.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.model.Address;
import org.model.People;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoDaoTest {
    private UserInfoDao userInfoDao;

    @BeforeEach
    public void before(){
        userInfoDao = new UserInfoDao();
    }

    @Test
    void getPeopleInfo() {
        People people = People.builder()
                .name("Denis")
                .surname("Ilohon")
                .age(23)
                .build();

        userInfoDao.deletePeoples(people);
        List<People> userPeopleInfo = userInfoDao.getPeopleInfo();

        System.out.println(userPeopleInfo);
    }


    @Test
    void getAddressInfo(){
        List<Address> userAddressInfo = userInfoDao.getAddressInfo();
        userInfoDao.getAddressInfo();

        System.out.println(userAddressInfo);
    }


}