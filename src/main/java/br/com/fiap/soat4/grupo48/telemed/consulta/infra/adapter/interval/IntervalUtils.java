package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.interval;

import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IIntervalUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class IntervalUtils implements IIntervalUtils {

    @Override
    public boolean intervalsOverlap(LocalTime horaInicio1, LocalTime horaFim1, LocalTime horaInicio2, LocalTime horaFim2) {
        LocalDate today = LocalDate.now();
        DateTime start1 = toJodaDateTime(today, horaInicio1);
        DateTime end1 = toJodaDateTime(today, horaFim1);
        DateTime start2 = toJodaDateTime(today, horaInicio2);
        DateTime end2 = toJodaDateTime(today, horaFim2);

        Interval jodaInterval1 = new Interval(start1, end1);
        Interval jodaInterval2 = new Interval(start2, end2);

        return jodaInterval1.overlaps(jodaInterval2);
    }

    private DateTime toJodaDateTime(LocalDate date, LocalTime time) {
        return new DateTime(
            date.getYear(),
            date.getMonthValue(),
            date.getDayOfMonth(),
            time.getHour(),
            time.getMinute(),
            time.getSecond()
        );
    }
}
