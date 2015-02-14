package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.*;

import static org.junit.Assert.assertEquals;

public class LinInstructionTest {

    private Instruction ins;
    private Instruction ins2;
    private Machine m;

    @Before
    public void setUp() throws Exception {
        ins = new LinInstruction("f1", 1, 10);
        m = new Machine();
        m.setRegisters(new Registers());
    }

    @Test
    public void testExecute() throws Exception {
        ins.execute(m); // stores the int 10 into register 1
        assertEquals(10, m.getRegisters().getRegister(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfResultRegisterParameterNotInRange(){
        ins2 = new LinInstruction("f1", 33, 10); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: lin register 1 value is 10", ins.toString());
    }
}