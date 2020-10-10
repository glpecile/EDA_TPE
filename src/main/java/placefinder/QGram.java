package placefinder;

import java.util.HashMap;
import java.util.Map;

/**
 * Método de búsqueda de coincidencias entre palabras.
 */
public class QGram {

    private final int n;

    /**
     * Crea un Qgram.
     *
     * @param n tamaños de los n-gramas para la búsqueda.
     */
    public QGram(int n) {
        this.n = n;
    }

    /**
     * Genera los n-gramas del string que recibe.
     *
     * @param string, palabra a la que se le generan los n-gramas.
     * @return Map con los n-gramas.
     */
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

    /**
     * Calcula la similitud entre dos strings.
     *
     * @param str1, palabra a la que se le calculará la similitud.
     * @param str2, palabra a la que se le calculará la similitud.
     * @return double valor de similaritud entre str1 y str2.
     */
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

