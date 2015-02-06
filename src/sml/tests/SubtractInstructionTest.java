package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.Machine;
import sml.Registers;
import sml.SubtractInstruction;

import static org.junit.Assert.assertEquals;

public class SubtractInstructionTest {

    private SubtractInstruction ins;
    private Machine m;

    @Before
    public void setUp() throws Exception {
        ins = new SubtractInstruction("f1", 1, 2, 3);
        m = new Machine();
        m.setRegisters(new Registers());
        m.getRegisters().setRegister(2, 10);
        m.getRegisters().setRegister(3, 5);
    }

    @Test
    public void testExecute() throws Exception {
        ins.execute(m); // result of 10 - 5 into register 1
        assertEquals(5, m.getRegisters().getRegister(1));
    }



}