public class GenderException extends Exception {
    public GenderException(String i) {
        super("Exception: Некорректные данные" + i);
    }
}
