package com.pucgoias.devmobileapps.trabalhofinal.models;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

/**
 * Esta é a classe de modelo que implementa a entidade em foco da aplicação,
 * que é a denúncia de infrações de trânsito.
 *
 */

@Entity
@Table(name = "tb_denuncia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Código da denúncia")
    private Integer id;
    @ApiModelProperty(value = "título da denúncia")
    private String tituloDenuncia;
    @ApiModelProperty(value = "Descrição da denúncia")
    private String corpoDenuncia;
    @ApiModelProperty(value = "Foto do local no qual a infração foi cometida")
    private String urlFotoDenuncia;

}
