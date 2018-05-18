package org.blagoverie.zcal;

import java.util.Date;
import java.util.Calendar;
import static java.util.Calendar.*;

public class ZDateCal
{
    static String descriptionFrom(ZDate zd) {
        return Descriptions.get(zd);
    }

    public static class ZDate {
        public String toDescription() {
            return ZDateCal.descriptionFrom(this);
        }
        String roz() {
            if (imah == 13) {
                switch (iroz) {
                    case 1 : return "Ahunawad";
                    case 2 : return "Oštawad";
                    case 3 : return "Spantamad";
                    case 4 : return "Vohuxšatr";
                    case 5 : return "Vahištoišt";
                    case 6 : return "Avardad-sal";
                    default: return "UNKNOWN";
                }
            }

            switch (this.iroz) {
                case 1 : return "Ormazd";
                case 2 : return "Vohuman";
                case 3 : return "Ardibehešt";
                case 4 : return "Šahrivar";
                case 5 : return "Esfand";
                case 6 : return "Xordad";
                case 7 : return "Amordad" ;
                case 8 : return "Dey be Azar";
                case 9 : return "Azar";
                case 10: return "Aban";
                case 11: return "Xoršid";
                case 12: return "Mah";
                case 13: return "Tir";
                case 14: return "Gouš";
                case 15: return "Dey be Mehr";
                case 16: return "Mehr";
                case 17: return "Soruš";
                case 18: return "Rašn";
                case 19: return "Farvardin";
                case 20: return "Warahram";
                case 21: return "Ram";
                case 22: return "Bad";
                case 23: return "Dey be Din";
                case 24: return "Din";
                case 25: return "Ard";
                case 26: return "Arshtad";
                case 27: return "Asman";
                case 28: return "Zamyad";
                case 29: return "Mantraspand";
                case 30: return "Anāram";
                default: return "UNKNOWN";
            }
        };
        int iroz;
        String mah() {
            switch (this.imah) {
                case 1 : return "Farwardin";
                case 2 : return "Ardibehešt";
                case 3 : return "Hordad";
                case 4 : return "Tir";
                case 5 : return "Amordad";
                case 6 : return "Šahrivar";
                case 7 : return "Mehr";
                case 8 : return "Aban";
                case 9 : return "Azar";
                case 10: return "Dey";
                case 11: return "Wahman";
                case 12: return "Spandarmaz";
                case 13: return "Gatha";
                default: return "UNKNOWN";
            }
        };
        int imah;
        int year;
    }

    static Date precedingNawruz(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 21);
        { Calendar aux = Calendar.getInstance();
            aux.setTime(now);
            cal.set(Calendar.YEAR,
                    aux.get(Calendar.YEAR)); }
        Date naw = cal.getTime();

        if (now.compareTo(naw) < 0) {
            cal.add(Calendar.YEAR, -1);
            naw = cal.getTime();
        }

        return naw;
    }

    static int diffInDays(Date d, Date d2) {
        return 1 + (int)
                Math.ceil(
                        (d.getTime() - d2.getTime()) /
                                (1000*60*60*24));
    }

    static int diffInYears(Date d2) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1129);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 22);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);

        int diff = cal2.get(YEAR) - cal.get(YEAR);
        if (cal.get(MONTH) > cal2.get(MONTH) ||
                (cal.get(MONTH) == cal2.get(MONTH) && cal.get(DATE) > cal2.get(DATE))) {
            diff--;
        }
        return diff + 2867;
    }

    public static ZDate fromGreg(
            java.util.Date greg) {
        int months;
        int days;
        int diff;

        Date nawruz = precedingNawruz(greg);
        diff = diffInDays(greg, nawruz);

        ZDate resu = new ZDate();
        days = diff % 30;
        if (days == 0) days = 30;
        resu.iroz = days;

        if (diff >= 360) {
            resu.imah = 13;
        } else {
            months = (int) Math.floor(diff / 30);
            resu.imah = months + 1;
        }
        resu.year = diffInYears(greg);
        return resu;
    }
}