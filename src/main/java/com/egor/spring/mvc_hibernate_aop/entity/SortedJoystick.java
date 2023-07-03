package com.egor.spring.mvc_hibernate_aop.entity;



import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Table(name = "sorted_joystick")
public class SortedJoystick {

    private boolean byPrice;
    private boolean sortPriceByAsc;

    private boolean byDate;
    private boolean sortDateByAsc;



}
