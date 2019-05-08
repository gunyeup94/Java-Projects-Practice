public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m += 1) {
            precedingMonthDays += getMonthLength(m);
        }
        return precedingMonthDays + dayOfMonth;
    }

    @Override
    public Date nextDate() {
        int newYear, newMonth, newDay;
        if (month == 12 && dayOfMonth == 31) {
            newYear = year + 1;
            newMonth = 1;
            newDay = 1;
        } else if (dayOfMonth == getMonthLength(month)) {
            newYear = year;
            newMonth = month + 1;
            newDay = 1;
        } else {
            newYear = year;
            newMonth = month;
            newDay = dayOfMonth + 1;
        }
        return new GregorianDate(newYear, newMonth, newDay);
    }

    private static int getMonthLength(int m) {
        return MONTH_LENGTHS[m - 1];
    }
}
