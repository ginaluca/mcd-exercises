
package exercises.e1_3.parser;

import exercises.e1_3.lexer.OperatorToken;

public class ParenExpression extends AbstractExpression {
    private Expression left, right;
    private OperatorToken operator;

    public ParenExpression(Expression left, Expression right, OperatorToken operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public OperatorToken getOperator() {
        return operator;
    }
    
        @Override
    public String toString() {
        return "ParenExpression{" + "operator=" + operator + "; annotations=" + annotations + '}';
    }

    
}
