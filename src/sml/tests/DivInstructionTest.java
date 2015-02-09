package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.DivInstruction;
import sml.Machine;
import sml.Registers;

import static org.junit.Assert.assertEquals;


public class DivInstructionTest {

    private DivInstruction ins;
    private DivInstruction ins2;
    private Machine m;

    @Before
    public void setUp() throws Exception {
        ins = new DivInstruction("f1", 1, 2, 3);
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

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfResultRegisterParameterNotInRange(){
        ins2 = new DivInstruction("f1", 33, 2, 3); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfOp1RegisterParameterNotInRange(){
        ins2 = new DivInstruction("f1", 2, 33, 3); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfOp2ResultRegisterParameterNotInRange(){
        ins2 = new DivInstruction("f1", 2, 2, 33); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: div 2 / 3 to 1", ins.toString());
    }
}