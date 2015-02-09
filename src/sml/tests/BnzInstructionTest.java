package sml.tests;

import org.junit.Before;
import org.junit.Test;
import sml.BnzInstruction;
import sml.DuplicateEntryException;
import sml.Machine;
import sml.Registers;

import static org.junit.Assert.assertEquals;

public class BnzInstructionTest {

    private BnzInstruction ins;
    private BnzInstruction ins2;
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

    @Test(expected = DuplicateEntryException.class)
    public void testExecuteResultsInDuplicateEntryExceptionThrownIfDuplicateLabels(){
        m.getLabels().addLabel("f3"); //label "f3" is already used in labels (duplicate)
        ins2 = new BnzInstruction("f1", 1, "f3");
        ins2.execute(m);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testExecuteThrowsExceptionIfRegisterParameterNotInRange(){
        ins2 = new BnzInstruction("f1", 33, "f3"); //register 33 doesn't exist
        ins2.execute(m);
    }

    @Test
    public void testExecuteSetsPcToInvalidNumberIfNextLabelToExecuteParameterDoesNotExist(){
        ins2 = new BnzInstruction("f1", 1, "f4"); //label f4 does not exist
        ins2.execute(m);
        assertEquals(-1, m.getPc()); //invalid label should set PC to -1, which will then throw an ArrayIndexOutOfBoundsException when the next instruction is not found at position -1 of the ArrayList prog
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("f1: bnz if content of register 1 != 0 then statement f3 is the next one to execute. ", ins.toString());
    }
}