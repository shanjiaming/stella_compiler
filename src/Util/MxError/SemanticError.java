package Util.MxError;
import Util.position;

public class SemanticError extends MxError {

    public SemanticError(String msg, position pos) {
        super("Semantic Error: " + msg, pos);
    }

}
