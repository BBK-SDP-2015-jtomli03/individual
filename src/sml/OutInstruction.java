package sml;

/**
 * Created by Jo on 07/02/2015.
 */
public class OutInstruction extends Instruction {

    private int register;

    public OutInstruction(String label, String opcode) {
        super(label, opcode);
    }

    public OutInstruction(String label, int register) {
        super(label, "out");
        this.register = register;
    }

    @Override
    public void execute(Machine m) {

    }

    @Override
    public String toString() {
        return "";
    }
}
