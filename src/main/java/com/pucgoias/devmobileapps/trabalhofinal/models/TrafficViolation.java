package com.pucgoias.devmobileapps.trabalhofinal.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(value = "título da denúncia")
    @Column(name = "title")
    private String title;
    @ApiModelProperty(value = "Descrição da denúncia")
    @Column(name = "description")
    private String description;
//    @ApiModelProperty(value = "Data da Ocorrência da Infração de Trânsito")
//    @JsonFormat
//            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
//    @Column(name = "dateOccurrence")
//    private Date dateOfOccurrenceInfraction;
    @ApiModelProperty(value = "Foto do local no qual a infração foi cometida")
    @Column(name = "photo")
    private String photo;
    @ApiModelProperty(value = "Distância aproximada na qual a infração foi cometida")
    @Column(name = "distance")
    private Double violationDistance;
    @ApiModelProperty(value = "Valor proposto pelo cidadão/cidadã para cobrança da multa")
    @Column(name = "value")
    private Double proposalAmountTrafficTicket;
}
