package com.pucgoias.devmobileapps.trabalhofinal.models;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Esta é a classe de modelo que implementa a entidade em foco da aplicação,
 * que é a denúncia de infrações de trânsito.
 *
 */

@Entity
@Table(name = "tb_traffic_violation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TrafficViolation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Código da denúncia")
    private Integer id;
    @ApiModelProperty(value = "título da denúncia")
    private String title;
    @ApiModelProperty(value = "Descrição da denúncia")
    private String description;
    @ApiModelProperty(value = "Data da Ocorrência da Infração de Trânsito")
    private LocalDate dateOfOccurrenceInfraction;
    @ApiModelProperty(value = "Foto do local no qual a infração foi cometida")
    private String photo;
    @ApiModelProperty(value = "Distância aproximada na qual a infração foi cometida")
    private Double violationDistance;
    @ApiModelProperty(value = "Valor proposto pelo cidadão/cidadã para cobrança da multa")
    private Double proposalAmountTrafficTicket;
}
