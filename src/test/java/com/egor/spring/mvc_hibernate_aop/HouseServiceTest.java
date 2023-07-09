package com.egor.spring.mvc_hibernate_aop;

import com.egor.spring.mvc_hibernate_aop.dao.HouseDao;
import com.egor.spring.mvc_hibernate_aop.dao.HouseDaoImpl;
import com.egor.spring.mvc_hibernate_aop.entity.House;
import com.egor.spring.mvc_hibernate_aop.service.HouseService;
import com.egor.spring.mvc_hibernate_aop.service.HouseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)//Mockito-инструмент для имитации поведенияя реальных объектов
public class HouseServiceTest {

    @InjectMocks
    private HouseServiceImpl houseService;

    @Mock
    private HouseDaoImpl houseDao;

    @Test
    void getAllHouses(){
        House house1=new House();
        House house2=new House();
        List<House> list=new ArrayList<>();
        list.add(house1);
        list.add(house2);
        Mockito.when(houseDao.getAllHouses()).thenReturn(list);
        int quantityReturnHouses=houseService.getAllHouses().size();
        Assertions.assertEquals(2,quantityReturnHouses);
    }



}
