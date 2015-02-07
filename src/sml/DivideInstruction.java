package sml;

/**
 * Created by Jo on 07/02/2015.
 */
public class DivideInstruction extends Instruction {

    private int result;
    private int op1;
    private int op2;

    public DivideInstruction(String label, String op) {
        super(label, op);
    }

    public DivideInstruction(String label, int result, int op1, int op2) {
        this(label, "div");
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public void execute(Machine m) {

    }

    @Override
    public String toString() {
        return "";
    }
}
