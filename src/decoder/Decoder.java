package decoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Decoder {
    public abstract String decode(String encryptedText);

    protected boolean isDecodingCorrect(String decryptedText) {
        List<String> checks = new ArrayList<>();
        checks.add("[^-А-Яа-яёЁ.,!?: \"]");
        checks.add("[?,:-]{2,}");
        checks.add("\\s{2,}");
        checks.add("[.,!?][^ ]");
        checks.add("[.!?] [^А-ЯЁ]");

        for (String check: checks) {
            Pattern pattern = Pattern.compile(check);
            Matcher matcher = pattern.matcher(decryptedText);
            if (matcher.find()) {
                return false;
            }
        }

        String[] words = decryptedText.split(" ");
        for (String word: words) {
            if (word.length() == 1) {
                if (!word.matches("[авикосуяАВИКОСУЯ]")) {
                    return false;
                }
            }
        }

        return decryptedText.contains(" ") || decryptedText.length() < 35;
    }

}
