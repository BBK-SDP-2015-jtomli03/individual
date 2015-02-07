package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.Machine;
import sml.MultiplyInstruction;
import sml.Registers;

import static org.junit.Assert.assertEquals;

public class MultiplyInstructionTest {

    private MultiplyInstruction ins;
    private Machine m;

    @Before
    public void setUp() throws Exception {
        ins = new MultiplyInstruction("f1", 1, 2, 3);
        m = new Machine();
        m.setRegisters(new Registers());
        m.getRegisters().setRegister(2, 10);
        m.getRegisters().setRegister(3, 5);
    }

    @Test
    public void testExecute() throws Exception {
        ins.execute(m); // result of 10 * 5 into register 1
        assertEquals(50, m.getRegisters().getRegister(1));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: mul 2 * 3 to 1", ins.toString());
    }
}