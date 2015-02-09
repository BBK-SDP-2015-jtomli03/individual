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
        if(m.getRegisters().getRegister(register) != 0) {
            m.setPc(m.getLabels().indexOf(nextInstruction));
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
