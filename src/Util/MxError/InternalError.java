package Util.MxError;
import Util.position;

public class InternalError extends MxError {

    public InternalError(String msg, position pos) {
        super("Internal Error:" + msg, pos);
    }

}
