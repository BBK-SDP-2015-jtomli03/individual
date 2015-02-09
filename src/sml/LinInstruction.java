package sml;

/**
 * LinInstruction creates an object that contains the necessary fields to store an integer in a specified register.
 * 
 * @author someone
 */

public class LinInstruction extends Instruction {
	private int register;
	private int value;

	/**
	 * Creates a LinInstruction object and sets the label and opcode.
	 *
	 * @param label the label
	 * @param opcode the opcode
	 */
	public LinInstruction(String label, String opcode) {
		super(label, opcode);
	}

	/**
	 * Creates a LinInstruction object and sets the label, opcode, register to store the integer, and the integer to be stored.
	 *
	 * @param label the label
	 * @param register the named register to store the integer
	 * @param value the integer to be stored in the named register
	 */
	public LinInstruction(String label, int register, int value) {
		super(label, "lin");
		this.register = register;
		this.value = value;

	}

	/**
	 * Executes the LinInstruction, storing the integer into the named register
	 *
	 * @param m the Machine that the instruction is running from.
	 */
	@Override
	public void execute(Machine m) {
		m.getRegisters().setRegister(register, value);
	}

	/**
	 * Formats the instruction as a String.
	 *
	 * @return String the instruction formatted as a String.
	 */
	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + value;
	}
}
