package placefinder;

import java.util.HashMap;
import java.util.Map;

public class QGram {

    private final int n;

    public QGram(int n) {
        this.n = n;
    }

    public Map<String, Integer> generateGrams(String string) {
        string = addHashtag(string);

        Map<String, Integer> stringMap = new HashMap<>();
        for (int i = 0; i < string.length() - n + 1; i++) {
            String key = string.substring(i, i + n);
            int value = stringMap.getOrDefault(key, 0);
            stringMap.put(key, value + 1);
        }
        return stringMap;
    }

    private String addHashtag(String string) {
        StringBuilder aux = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < n; i++) {
            aux.append("#");
        }
        ans.append(aux).append(string).append(aux);
        return ans.toString();
    }

    public void printTokens(String word) {
        Map<String, Integer> tokens = generateGrams(word);
        for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
            System.out.printf("%s : %d\n", entry.getKey(), entry.getValue());
        }
    }

    public double similarity(String str1, String str2) {
        Map<String, Integer> token1 = generateGrams(str1);
        Map<String, Integer> token2 = generateGrams(str2);

        int inCommon = 0;
        int total = 0;

        for (Integer value : token1.values()) {
            total += value;
        }

        for (Integer value : token2.values()) {
            total += value;
        }

        for (Map.Entry<String, Integer> entry1 : token1.entrySet()) {
            for (Map.Entry<String, Integer> entry2 : token2.entrySet()) {
                if (entry1.getKey().equals(entry2.getKey())) {
                    inCommon += Math.min(entry1.getValue(), entry2.getValue()) * 2;
                }
            }
        }
        return (double) inCommon / total;
    }
}

