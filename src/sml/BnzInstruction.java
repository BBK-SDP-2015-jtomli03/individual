package sml;

/**
 * BnzInstruction creates an object that contains the necessary fields to alter the program counter (ie. the course of
 * the running program) if the specified registers value is not equal to zero.
 *
 * Created by Jo on 07/02/2015.
 */
public class BnzInstruction extends Instruction {

    private int register;
    private String nextInstruction;

    /**
     * Creates a BnzInstruction object and sets the label and opcode.
     *
     * @param label the label
     * @param op the opcode
     */
    public BnzInstruction(String label, String op) {
        super(label, op);
    }

    /**
     * Creates a BnzInstruction object and sets the label, opcode, register to check whether the contents equal zero,
     * and the label of the next instruction to be executed if the contents of the stated register do not equal zero.
     *
     * @param label the label
     * @param register the register to check whether the contents equal zero
     * @param nextInstruction the label of the next instruction to be executed if the contents of the stated register do not equal zero
     */
    public BnzInstruction(String label, int register, String nextInstruction) {
        this(label, "bnz");
        this.register = register;
        this.nextInstruction = nextInstruction;
    }

    /**
     * Executes the bnz instruction; if the contents of the register to check != 0 then the machines program counter
     * is set to the position of nextInstruction so that this will be executed next, otherwise the flow of instructions
     * continues one after another.
     *
     * @param m the Machine that the instruction is running from.
     */
    @Override
    public void execute(Machine m) {
        if(m.getRegisters().getRegister(register) != 0) {
            m.setPc(m.getLabels().indexOf(nextInstruction));
        }
    }

    /**
     * Formats the instruction as a String.
     *
     * @return String the instruction formatted as a String.
     */
    @Override
    public String toString() {
        return super.toString() + " if content of register " + register + " != 0 then statement " + nextInstruction + " is the next one to execute. ";
    }
}
