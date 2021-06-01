package com.pucgoias.devmobileapps.trabalhofinal.models;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_denuncia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String senha;
}
