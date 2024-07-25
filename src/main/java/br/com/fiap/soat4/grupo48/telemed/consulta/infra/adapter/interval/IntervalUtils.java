package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.interval;

import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IIntervalUtils;
import org.springframework.stereotype.Component;

@Component
public class IntervalUtils implements IIntervalUtils {

    @Override
    public boolean intervalsOverlap(java.time.LocalTime horaInicio1, java.time.LocalTime horaFim1, java.time.LocalTime horaInicio2, java.time.LocalTime horaFim2) {
//        DateTime start1 = new DateTime(horaInicio1);
//        DateTime end1 = new DateTime(horaFim1);
//        DateTime start2 = new DateTime(horaInicio2);
//        DateTime end2 = new DateTime(horaFim2);
//
//        Interval jodaInterval1 = new Interval(start1, end1);
//        Interval jodaInterval2 = new Interval(start2, end2);

//        return jodaInterval1.overlaps(jodaInterval2);
        return false;
    }
}
