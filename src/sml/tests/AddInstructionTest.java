package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.*;

import static org.junit.Assert.assertEquals;

public class AddInstructionTest {

    private Instruction ins1;
    private Instruction ins2;
    private Machine m;

    @Before
    public void setUp() throws Exception {
        ins1  = new AddInstruction("f1", 3, 1, 2);
        m = new Machine();
        m.setRegisters(new Registers());
        m.getRegisters().setRegister(1, 10);
        m.getRegisters().setRegister(2, 5);
    }

    @Test
    public void testExecute() throws Exception {
        ins1.execute(m); // result of 10 + 5 into register 3
        assertEquals(15, m.getRegisters().getRegister(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfResultRegisterParameterNotInRange(){
        ins2 = new AddInstruction("f1", 33, 2, 3); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfOp1RegisterParameterNotInRange(){
        ins2 = new AddInstruction("f1", 2, 33, 3); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfOp2ResultRegisterParameterNotInRange(){
        ins2 = new AddInstruction("f1", 2, 2, 33); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: add 1 + 2 to 3", ins1.toString());
    }
}