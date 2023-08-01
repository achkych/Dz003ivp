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

        if (data.length < 4) {
            System.out.println("Ошибка: недостаточно данных");
            return dataDic;
        }

        for (String i : data) {
            if (i.length() == 1) {
                if (i.equals("f") || i.equals("m")) {
                    dataDic.put("gender", i);
                } else {
                    try {
                        throw new GenderException(i);
                    } catch (GenderException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (i.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
                String[] arrayDate = i.split("\\.");
                int day = Integer.parseInt(arrayDate[0]);
                int month = Integer.parseInt(arrayDate[1]);
                int year = Integer.parseInt(arrayDate[2]);

                try {
                    LocalDate.parse(i, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    // Дата корректна, добавляем ее в словарь
                    dataDic.put("date", i);
                } catch (DateTimeParseException e) {
                    System.out.println("Ошибка: некорректный формат даты - " + i);
                }
            } else if (i.matches("[0-9]+")) {
                dataDic.put("tel", i);
            } else if (i.matches("[A-Za-z]+")) {
                sb.append(i).append(" ");
            } else {
                try {
                    throw new DataException(i);
                } catch (DataException e) {
                    System.out.println(e.getMessage());
                }
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
}