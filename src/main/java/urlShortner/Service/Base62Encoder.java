package urlShortner.Service;

public class Base62Encoder {
    private static final String CHARS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = 62;

    public static String encode(long num) {
        if (num == 0) return String.valueOf(CHARS.charAt(0));
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(CHARS.charAt((int)(num % BASE)));
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static long decode(String code) {
        long num = 0;
        for (char c : code.toCharArray()) {
            num = num * BASE + CHARS.indexOf(c);
        }
        return num;
    }
}