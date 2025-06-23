package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_TIME = 2;
    private static final int INDEX_MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        if (names.length == 0 || data.length == 0
                || dateFrom == null || dateTo == null
                || dateFrom.isBlank() || dateTo.isBlank()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder();
        int[] moneyUserAndName = new int[names.length];
        for (String line : data) {
            String[] userData = line.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(userData[INDEX_NAME])) {
                    LocalDate timeEmployee = LocalDate.parse(userData[INDEX_DATE], formatter);
                    if ((timeEmployee.isEqual(from) || timeEmployee.isAfter(from))
                            && (timeEmployee.isEqual(to) || timeEmployee.isBefore(to))) {
                        moneyUserAndName[i] += (Integer.parseInt(userData[INDEX_TIME])
                                * Integer.parseInt(userData[INDEX_MONEY]));
                    }
                }
            }
        }

        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < moneyUserAndName.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(moneyUserAndName[i]);
        }

        return builder.toString();
    }
}

