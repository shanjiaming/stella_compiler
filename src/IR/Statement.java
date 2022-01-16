package IR;

import Backend.Pass;

public abstract class Statement {
    abstract public void accept(Pass visitor);
}
