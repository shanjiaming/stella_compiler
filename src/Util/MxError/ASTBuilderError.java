package Util.MxError;
import Util.position;

public class ASTBuilderError extends MxError {

    public ASTBuilderError(String msg, position pos) {
        super("ASTBuilderError: " + msg, pos);
    }

}
