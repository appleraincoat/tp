package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    //May seem redundant, but for further iterations where we may validate remarks.
    @Test
    public void isValidRemark() {
        // null name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // valid name
        assertTrue(Remark.isValidRemark("I love apples")); // alphabets only
        assertTrue(Remark.isValidRemark("12345")); // numbers only
        assertTrue(Remark.isValidRemark("James Lau the 1st")); // alphanumeric characters
        assertTrue(Remark.isValidRemark("New York")); // with capital letters
        assertTrue(Remark.isValidRemark("Tony Stark, genius playboy billionaire philanthropist")); // long remark
    }

    @Test
    public void isValidRemark_equivalencePartitioning() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // EP: valid remark (should always return true)
        assertTrue(Remark.isValidRemark("I love apples"));
        assertTrue(Remark.isValidRemark("12345"));
        assertTrue(Remark.isValidRemark("James Lau the 1st"));
        assertTrue(Remark.isValidRemark("New York"));
        assertTrue(Remark.isValidRemark("Tony Stark, genius playboy billionaire philanthropist"));

        // EP: empty remark (should always return true)
        assertTrue(Remark.isValidRemark(""));

        // EP: remark with whitespaces (should always return true)
        assertTrue(Remark.isValidRemark("   "));
        assertTrue(Remark.isValidRemark("  \t  \n  "));

        // EP: remark with special characters (should always return true)
        assertTrue(Remark.isValidRemark("!@#$%^&*()_+{}|:\"<>?"));

        // EP: remark with leading and trailing whitespaces (should always return true)
        assertTrue(Remark.isValidRemark("  Remark with leading and trailing spaces  "));
    }

    @Test
    public void equals() {
        Remark remark = new Remark("Valid Remark");

        // same values -> returns true
        assertTrue(remark.equals(new Remark("Valid Remark")));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // null -> returns false
        assertFalse(remark == null);

        // different types -> returns false
        assertFalse(remark.equals(5.0f));

        // different values -> returns false
        assertFalse(remark.equals(new Remark("Other Valid Name")));
    }
}

