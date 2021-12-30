package IR;

import Util.Type;

public abstract class Entity {
    public String val;

    public Entity() {
    }

    public Entity(String val) {
        this.val = val;
    }

    public String regorconst() {
        return ((this instanceof Register) ? ((Register) this).regToString() : this.toString());
    }
}
