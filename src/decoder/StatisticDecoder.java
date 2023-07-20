package decoder;

import java.util.*;

public class StatisticDecoder extends Decoder {
    private final String sampleText;

    public StatisticDecoder(String sampleText) {
        this.sampleText = sampleText;
    }

    private List<Map.Entry<Character, Integer>> textFrequencyAnalysis(String text) {
        Comparator<Map.Entry<Character, Integer>> comparator = (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue());
        Map<Character, Integer> letterFrequency = new HashMap<>();
        char[] charArray = text.toCharArray();

        for (char ch: charArray) {
            if (letterFrequency.containsKey(ch)) {
                letterFrequency.put(ch, letterFrequency.get(ch) + 1);
            } else {
                letterFrequency.put(ch, 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>();
        list.addAll(letterFrequency.entrySet());
        Collections.sort(list, comparator);

        return list;
    }

    public String decode(String encryptedText) {
        List<Map.Entry<Character, Integer>> sampleTextLetterFrequency = textFrequencyAnalysis(sampleText);
        List<Map.Entry<Character, Integer>> encryptedTextLetterFrequency = textFrequencyAnalysis(encryptedText);
        Map<Character, Character> map = new HashMap<>();
        Character next2 = null;
        Character next1;
        for (int i = 0; i < encryptedTextLetterFrequency.size(); i++) {
            next1 = encryptedTextLetterFrequency.get(i).getKey();
            if (i < sampleTextLetterFrequency.size()) {
                next2 = sampleTextLetterFrequency.get(i).getKey();
            }
            map.put(next1, next2);
        }

        StringBuilder sb = new StringBuilder();
        char[] charArray = encryptedText.toCharArray();

        for (char ch: charArray) {
            sb.append(map.get(ch));
        }
            return sb.toString();
    }

}