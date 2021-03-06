package sml;

/**
 * SubtractInstruction creates an object that contains the necessary fields to compute a subtraction and store the result
 * in a specified register.
 *
 * Created by Jo on 07/02/2015.
 */
public class SubInstruction extends Instruction {

    private int result;
    private int op1;
    private int op2;

    /**
     * Creates a SubtractInstruction object and sets the label and opcode.
     *
     * @param label the label
     * @param op the opcode
     */
    public SubInstruction(String label, String op) {
        super(label, op);
    }

    /**
     * Creates a SubtractInstruction object and sets the label, opcode, register for the result, and the two registers for the operands.
     *
     * @param label the label
     * @param result the register that the result should be stored in
     * @param op1 the register that the first operand is stored in
     * @param op2 the register that the second operand is stored in
     */
    public SubInstruction(String label, int result, int op1, int op2) {
        this(label, "sub");
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }

    /**
     * Executes the SubtractInstruction, placing the result of the subtraction in the named register.
     *
     * @param m the Machine that the instruction is running from.
     */
    @Override
    public void execute(Machine m) {
        int value1 = m.getRegisters().getRegister(op1);
        int value2 = m.getRegisters().getRegister(op2);
        m.getRegisters().setRegister(result, value1 - value2);
    }

    /**
     * Formats the instruction as a String.
     *
     * @return String the instruction formatted as a String.
     */
    @Override
    public String toString() {
        return super.toString() + " " + op1 + " - " + op2 + " to " + result;
    }
}
