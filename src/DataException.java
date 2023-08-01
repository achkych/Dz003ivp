public class DataException extends Exception {
    public DataException(String i) {
        super("Exception: Некорректный формат " + i);
    }
}
