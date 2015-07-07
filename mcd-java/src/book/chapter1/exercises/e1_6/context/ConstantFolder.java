
package book.chapter1.exercises.e1_6.context;

import book.chapter1.exercises.e1_3.parser.AbstractExpression;
import book.chapter1.exercises.e1_3.parser.AstRoot;
import book.chapter1.exercises.e1_3.parser.DigitExpression;
import book.chapter1.exercises.e1_3.parser.Expression;
import book.chapter1.exercises.e1_3.parser.ParenExpression;

public class ConstantFolder {
    
    public AstRoot annotate(AstRoot root) {
        fold(root.getExpression());
        return root;
    }
    
    public int fold(Expression expression) {
        int value;
        if (expression instanceof DigitExpression) {
            value = ((DigitExpression) expression).getDigit().getRepresentation();
        } else {
            ParenExpression parenExpression = (ParenExpression) expression;
            if (parenExpression.getOperator().getRepresentation() == '+') {
                value = fold(parenExpression.getLeft()) + 
                        fold(parenExpression.getRight());
            } else {
                value = fold(parenExpression.getLeft()) *
                        fold(parenExpression.getRight());
            }
        }
        expression.addAnnotation(
                AbstractExpression.Annotation.CONSTANT_FOLDING, 
                value);
        return value;
    }

}
