
package exercises.e1_8.context;

import exercises.e1_3.parser.AstRoot;
import exercises.e1_3.parser.DigitExpression;
import exercises.e1_3.parser.Expression;
import exercises.e1_3.parser.ParenExpression;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class LispCodeGenerator {
    
    public void generate(AstRoot root, OutputStream outputStream) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        doGenerate(root.getExpression(), writer);
        writer.flush();
    }

    private void doGenerate(Expression expression, Writer writer) throws IOException {
        if (expression instanceof DigitExpression) {
            writer.write(String.valueOf(((DigitExpression)expression).getDigit().getRepresentation()));
        } else {
            ParenExpression parenExpression = (ParenExpression) expression;
            writer.write("( " + parenExpression.getOperator().getRepresentation() + " ");
            doGenerate(parenExpression.getLeft(), writer);
            writer.write(" ");
            doGenerate(parenExpression.getRight(), writer);
            writer.write(")");
        }
    }
}
