package Util.MxError;
import Util.position;

abstract public class MxError extends RuntimeException {
    private position pos;
    private String message;

    public MxError(String msg, position pos) {
        this.pos = pos;
        this.message = msg;
    }

    public String toString() {
        return message + ": " + pos.toString();
    }
}
