package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.BnzInstruction;
import sml.Machine;
import sml.Registers;

import static org.junit.Assert.assertEquals;

public class BnzInstructionTest {

    private BnzInstruction ins;
    private Machine m;

    @Before
    public void setUp() throws Exception {
        ins = new BnzInstruction("f1", 1, "f3");
        m = new Machine();
        m.getLabels().addLabel("f1");
        m.getLabels().addLabel("f2");
        m.getLabels().addLabel("f3");
        m.setPc(0);
        m.setRegisters(new Registers());
        m.getRegisters().setRegister(1, 100);
    }

    @Test
    public void testExecute() throws Exception {
        ins.execute(m); // should increase the machines PC to 2, ie the position of label "f3" and hence the corresponding instruction.
        assertEquals(2, m.getPc());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: bnz if content of register 1 != 0 then statement f3 is the next one to execute. ", ins.toString());
    }
}