package sml;

/**
 * OutInstruction creates an object that contains the necessary fields to enable the instruction to print the contents
 * of a named register on the Java console when its execute() method is called.
 *
 * Created by Jo on 07/02/2015.
 */
public class OutInstruction extends Instruction {

    private int register;

    /**
     * Creates an OutInstruction object and sets the label and opcode.
     *
     * @param label the label
     * @param opcode the opcode    
     */
    public OutInstruction(String label, String opcode) {
        super(label, opcode);
    }

    /**
     * Creates an OutInstruction object and sets the label, opcode, and register for which the contents are to be printed.
     *
     * @param label the label
     * @param register the named register to store the integer
     */
    public OutInstruction(String label, int register) {
        super(label, "out");
        this.register = register;
    }

    /**
     * Executes the OutInstruction which prints the contents of the specified register onto the Java console
     *
     * @param m the Machine that the instruction is running from.
     */
    @Override
    public void execute(Machine m) {
        System.out.println(m.getRegisters().getRegister(register));
    }

    /**
     * Formats the instruction as a String.
     *
     * @return String the instruction formatted as a String.
     */
    @Override
    public String toString() {
        return super.toString() + " register " + register;
    }
}
