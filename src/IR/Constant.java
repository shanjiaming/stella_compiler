package IR;


public class Constant extends Entity {
	public Constant(){}
	public Constant(String val){super(val);}
	public Constant(int val){super("" + val);}

	@Override public String toString() {return val;}
}
