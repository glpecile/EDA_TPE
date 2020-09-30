package placefinder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class QGramTest {

    @Test
    public void SimilarityTest() {
        QGram triGram = new QGram(3);
        Assertions.assertTrue(Math.abs(0.3636 - triGram.similarity("JOHN", "JOE")) < 0.0001);
        QGram twoGram = new QGram(2);
        Assertions.assertTrue(Math.abs(0.3076 - twoGram.similarity("salesal", "vale")) < 0.0001);
        Assertions.assertTrue(Math.abs(0.4285 - twoGram.similarity("salesal", "alale")) < 0.0001);
    }
}
