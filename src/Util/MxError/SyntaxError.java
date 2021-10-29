package Util.MxError;
import Util.position;

public class SyntaxError extends MxError {

    public SyntaxError(String msg, position pos) {
        super("SyntaxError: " + msg, pos);
    }

}
