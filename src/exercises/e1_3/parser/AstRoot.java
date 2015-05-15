
package exercises.e1_3.parser;

public class AstRoot {
    private final Expression expression;

    public AstRoot(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
