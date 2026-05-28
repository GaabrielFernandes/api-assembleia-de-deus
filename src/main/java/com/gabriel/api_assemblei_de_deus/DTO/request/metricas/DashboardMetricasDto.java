package com.gabriel.api_assemblei_de_deus.DTO.request.metricas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardMetricasDto {
    private Long totalMembros;
    private Long totalMembrosAtivos;
    private Long totalMembrosDisciplina;
}
