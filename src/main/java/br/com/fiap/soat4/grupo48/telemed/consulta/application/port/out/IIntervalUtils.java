package br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out;

import java.time.LocalTime;

public interface IIntervalUtils {

    /**
     * Verifica se dois intervalos de tempo se sobrepõem
     *
     * @param horaInicio1 hora de início do intervalo 1
     * @param horaFim1    hora de fim do intervalo 1
     * @param horaInicio2 hora de início do intervalo 2
     * @param horaFim2    hora de fim do intervalo 2
     * @return true se os intervalos se sobrepõem, false caso contrário
     */
    boolean intervalsOverlap(LocalTime horaInicio1, LocalTime horaFim1, LocalTime horaInicio2, LocalTime horaFim2);
}
