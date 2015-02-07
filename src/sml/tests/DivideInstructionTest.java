package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.DivideInstruction;
import sml.Machine;
import sml.Registers;

import static org.junit.Assert.assertEquals;


public class DivideInstructionTest {

    private DivideInstruction ins;
    private Machine m;

    @Before
    public void setUp() throws Exception {
        ins = new DivideInstruction("f1", 1, 2, 3);
        m = new Machine();
        m.setRegisters(new Registers());
        m.getRegisters().setRegister(2, 10);
        m.getRegisters().setRegister(3, 5);
    }

    @Test
    public void testExecute() throws Exception {
        ins.execute(m); // result of 10 / 5 into register 1
        assertEquals(2, m.getRegisters().getRegister(1));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: div 2 / 3 to 1", ins.toString());
    }
}