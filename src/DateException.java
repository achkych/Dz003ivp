public class DateException extends Exception {
    public DateException(String i) {
        super("Exception: Некорректный формат " + i);
    }
}
