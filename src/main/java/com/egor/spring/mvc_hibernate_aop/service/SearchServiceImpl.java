package com.egor.spring.mvc_hibernate_aop.service;

import com.egor.spring.mvc_hibernate_aop.dao.HouseDao;
import com.egor.spring.mvc_hibernate_aop.dao.UserDao;
import com.egor.spring.mvc_hibernate_aop.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    @Qualifier("houseRep")
    HouseDao houseDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<House> sortAllHousesByPriceAsc() {
        List<House> houseList=houseDao.getAllHouses();
        List<House> sortedList=houseList.stream().
                        sorted((x, y)->x.getPrice()-y.getPrice()).collect(Collectors.toList());

        return sortedList;
    }

    @Override
    @Transactional
    public List<House> sortAllHousesByPriceDesc() {
        List<House> houseList=houseDao.getAllHouses();
        List<House> sortedList=houseList.stream().
                sorted((x, y)->y.getPrice()-x.getPrice()).collect(Collectors.toList());

        return sortedList;
    }

    @Override
    @Transactional
    public List<House> sortAllHousesByDateAsc() {
        return null;
    }

    @Override
    @Transactional
    public List<House> sortAllHousesByDateDesc() {
        return null;
    }

    @Override
    @Transactional
    public List<House> getSearchAllHousesByPriceRange(int from, int to) {
        List<House> houseList=houseDao.getAllHouses();
        List<House> sortedList=houseList.stream().filter(el->el.getPrice()>=from&&el.getPrice()<=to)
                .collect(Collectors.toList());

        return sortedList;
    }

    @Override
    @Transactional
    public List<House> getSearchAllHousesBySquareRange(int from, int to) {
        return null;
    }

    @Override
    @Transactional
    public List<House> getAllNotRentedHouses() {
        return null;
    }

    @Override
    @Transactional
    public List<House> getAllHousesWhereRatingFrom(int rating) {
        return null;
    }
}
