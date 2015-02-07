package sml;

/**
 * Created by Jo on 07/02/2015.
 */
public class BnzInstruction extends Instruction {

    private int register;
    private String nextInstruction;

    public BnzInstruction(String label, String op) {
        super(label, op);
    }

    public BnzInstruction(String label, int register, String nextInstruction) {
        this(label, "bnz");
        this.register = register;
        this.nextInstruction = nextInstruction;
    }

    @Override
    public void execute(Machine m) {

    }

    @Override
    public String toString() {
        return "";
    }
}
