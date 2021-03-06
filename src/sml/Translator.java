package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

		try (Scanner sc = new Scanner(new File(fileName))) {
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {
		if (line.equals(""))
			return null;
		String ins = scan();

		//CODE USING JAVA REFLECTION;
		Constructor<?> constructor;
		Object[] parameters;
		try {
			constructor = getConstructor(ins);
			parameters = getParameters(constructor, label);
			return (Instruction)constructor.newInstance(parameters);
		}
		catch (InstantiationException e){
			e.printStackTrace();
		}
		catch (IllegalAccessException e){
			e.printStackTrace();
		}
		catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return null;

		/* --> CODE PRIOR TO USING JAVA REFLECTION;
		int s1; // Possible operands of the instruction
		int s2;
		int r;
		int x;
		String nextStatement;

		switch (ins) {
		case "add":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new AddInstruction(label, r, s1, s2);
		case "lin":
			r = scanInt();
			s1 = scanInt();
			return new LinInstruction(label, r, s1);

		case "sub":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new SubInstruction(label, r, s1, s2);

		case "mul":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new MulInstruction(label, r, s1, s2);

		case "div":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new DivInstruction(label, r, s1, s2);

		case "out":
			r = scanInt();
			return new OutInstruction(label, r);

		case "bnz":
			r = scanInt();
			nextStatement = scan();
			return new BnzInstruction(label, r, nextStatement);

		default:
			System.out.println("Your file contains an instruction that doesn't exist. Please amend and try again.");
			return null;
		}*/
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Returns the full constructor for the type of instruction stated by the SML.
	 *
	 * @param ins the actual instruction of the SML eg, "add".
	 * @return Constructor<?> the constructor for the Instruction.
	 */
	private Constructor<?> getConstructor(String ins){
		try {
			Constructor[] constructors = Class.forName(getInstructionType(ins)).getConstructors();
			Constructor<?> constructor = constructors[0];
			for (Constructor<?> cons : constructors) {
				if (cons.getParameterCount() > constructor.getParameterCount())
					constructor = cons;
			}
			return constructor;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the actual class name for the type of instruction stated in the SML.
	 *
	 * @param ins the actual instruction of the SML eg, "add".
	 * @return String the class name.
	 */
	private String getInstructionType(String ins){
		return "sml." + ins.toUpperCase().charAt(0) + ins.substring(1) + "Instruction";
	}

	/**
	 * Returns the parameters needed to construct an Instruction using the specified constructor.
	 *
	 * @param constructor the constructor for the Instruction.
	 * @param label the label obtained from the SML for the Instruction for which the parameters are being obtained.
	 * @return Object[] an array of parameters.
	 */
	private Object[] getParameters(Constructor<?> constructor, String label) {
		Class[] parameterTypes = constructor.getParameterTypes();
		Object[] parameters = new Object[parameterTypes.length];
		parameters[0] = label;
		for (int i = 1; i < parameterTypes.length; i++) { //start at i=1 since parameters[0] = label
			if (parameterTypes[i].equals(int.class)) {
				parameters[i] = scanInt();
			} else {
				parameters[i] = scan();
			}
		}
		return parameters;
	}
}