package com.egor.spring.mvc_hibernate_aop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "houses")
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    @NotBlank(message = "address is required field")
    private String address;


    @Column(name = "price")
    private int price;

    @Column(name = "is_rented")
    boolean isRented=false;//арендован или нет

    @Column(name = "is_deleted")
    boolean isDeleted=false;
//когда аккаунт владельца удален,
//вместе с этим блокируются объявления-они не отображаются пользователям
//и арендованные дома становятся свободны


    @Column(name = "description_")
    @NotBlank(message = "description is required field")
    private String description;



    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="owner_")
    User owner;//владелец дома



    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="tenant")
    private User tenant;//арендатор дома

    @Override
    public String toString() {
        return "House{" +
                "address='" + address + '\'' +
                ", price=" + price +
                ", isRented=" + isRented +
                ", isDeleted=" + isDeleted +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", tenant=" + tenant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return id == house.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
