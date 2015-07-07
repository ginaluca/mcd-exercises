
package book.chapter1.exercises.e1_3.parser;

import book.chapter1.exercises.e1_3.lexer.DigitToken;
import java.util.HashMap;
import java.util.Map;

public class AbstractExpression implements Expression {
    public enum Annotation { CONSTANT_FOLDING }
    
    protected final Map<Annotation, Object> annotations = new HashMap<>();

    @Override
    public void addAnnotation(Annotation key, Object value) {
        annotations.put(key, value);
    }
    
}
