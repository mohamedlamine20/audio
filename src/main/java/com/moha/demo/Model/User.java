package com.moha.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id ;

    @Column
    private String name ;

    @Column
    private String email;


}
