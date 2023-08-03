import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;

public class ParsData {

    public HashMap<String, Object> parsInputData() {
        InputData inputData = new InputData();
        String[] data = inputData.enterData();
        HashMap<String, Object> dataDic = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        if (data.length < 6) {
            System.out.println("Ошибка: недостаточно данных");
            return dataDic;
        }

        for (String i : data) {
            if (i.length() == 1) {
                if (i.equals("f") || i.equals("m")) {
                    dataDic.put("gender", i);
                } else {
                    System.out.println("Ошибка: некорректные данные - " + i);
                    return dataDic;
                }
            } else if (i.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
                String[] arrayDate = i.split("\\.");
                int day = Integer.parseInt(arrayDate[0]);
                int month = Integer.parseInt(arrayDate[1]);
                int year = Integer.parseInt(arrayDate[2]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                if (isValidDate(i, formatter)) {
                    dataDic.put("date", i);
                } else {
                    System.out.println("Ошибка: некорректный формат даты - " + i);
                    return dataDic;
                }
            } else if (i.matches("[0-9]+")) {
                dataDic.put("tel", i);
            } else if (i.matches("[A-Za-z]+")) {
                sb.append(i).append(" ");
            } else {
                System.out.println("Ошибка: некорректный формат данных - " + i);
                return dataDic;
            }
        }

        String[] fullName = String.valueOf(sb).split(" ");
        if (fullName.length == 3) {
            dataDic.put("firstName", fullName[1]);
            dataDic.put("lastName", fullName[0]);
            dataDic.put("patronymic", fullName[2]);
        }

        return dataDic;
    }

    private boolean isValidDate(String dateStr, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}