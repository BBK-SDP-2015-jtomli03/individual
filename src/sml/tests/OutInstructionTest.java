package sml.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sml.Machine;
import sml.OutInstruction;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class OutInstructionTest {

    private OutInstruction ins;
    private OutInstruction ins2;
    private Machine m;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(output));
        ins = new OutInstruction("f1", 1);
        m = new Machine();
        m.setRegisters(new Registers());
        m.getRegisters().setRegister(1, 10);
    }

    @After
    public void cleanUp() throws Exception{
        System.setOut(null);
    }

    @Test
    public void testExecute() throws Exception {
        ins.execute(m);
        assertEquals("10\n", output.toString());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfRegisterParameterNotInRange(){
        ins2 = new OutInstruction("f1", 33); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: out register 1", ins.toString());
    }
}