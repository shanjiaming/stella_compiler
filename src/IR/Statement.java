package IR;

import Backend.Pass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Statement {

    protected void addIfIsVirtualRegister(Set<Integer> r, PointerRegister p){
        if(p.offset == Register.s0) r.add(p.address);
    }

    abstract public void accept(Pass visitor);

    public Set<Statement> goin = new HashSet<>();
    public Set<Statement> goout = new HashSet<>();
    abstract public Set<Integer> defs();
    abstract public Set<Integer> uses();
    public Set<Integer> ins = new HashSet<>();
    public Set<Integer> outs = new HashSet<>();
}
