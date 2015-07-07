package book.chapter1.exercises.e1_3.lexer;

public abstract class AbstractToken<T> implements Token<T> {
    private final String filename;
    private final int lineNumber;

    public AbstractToken(String filename, int lineNumber) {
        this.filename = filename;
        this.lineNumber = lineNumber;
    }

    public String getFilename() {
        return filename;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "filename=" + filename + ", lineNumber=" + lineNumber;
    }

    
}
