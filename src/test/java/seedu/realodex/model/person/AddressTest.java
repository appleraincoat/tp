package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        Address address = new Address("Valid Address");

        // same values -> returns true
        assertTrue(address.equals(new Address("Valid Address")));

        // same object -> returns true
        assertTrue(address.equals(address));

        // null -> returns false
        assertFalse(address.equals(null));

        // different types -> returns false
        assertFalse(address.equals(5.0f));

        // different values -> returns false
        assertFalse(address.equals(new Address("Other Valid Address")));
    }

    @Test
    public void constructor_validAddress_success() {
        // Boundary value: Minimum valid address
        Address address = new Address("-");
        assertTrue(address.isValidAddress("-"));

        // Boundary value: Long valid address
        address = new Address("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA");
        assertTrue(address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }

    @Test
    public void isValidAddress_nullAddress_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));
    }

    @Test
    public void isValidAddress_blankAddress_false() {
        // Equivalence partitioning: Blank address is invalid
        assertFalse(Address.isValidAddress(""));
        assertFalse(Address.isValidAddress(" "));
    }

    @Test
    public void equals_differentAddress_false() {
        Address address = new Address("Valid Address");

        // Equivalence partitioning: Different values -> returns false
        assertFalse(address.equals(new Address("Other Valid Address")));
    }

    @Test
    public void isValidAddress_equivalencePartitioning() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // blank address
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only
        assertFalse(Address.isValidAddress("   ")); // spaces only

        // Equivalence partitioning: Address with only alphanumeric characters is valid
        assertTrue(Address.isValidAddress("123 Main Street"));
        assertTrue(Address.isValidAddress("Apt 123B, 456 Elm Avenue"));
        assertTrue(Address.isValidAddress("Unit 789, Block C, 789 Maple Road"));

        // Equivalence partitioning: Address with mix of alphanumeric characters and symbols is invalid
        assertTrue(Address.isValidAddress("12# Main%^&*()_+-={ Street")); // mixed characters and symbol
        assertTrue(Address.isValidAddress("Apt 123B, #$% Elm Avenue")); // mixed characters and symbol
        assertTrue(Address.isValidAddress("Unit 789, %$ Block C, 789 Maple Road")); // mixed characters and symbol

        // Equivalence partitioning: Address with only symbols or special characters is invalid
        assertTrue(Address.isValidAddress("#"));
        assertTrue(Address.isValidAddress("!@#$%^&*()_+-={}[]|\\:;\"',.<>?/"));
        assertTrue(Address.isValidAddress("!@#$%^&*()_+-={}[]|\\:;\"',.<>?/  ")); // Trailing spaces with symbols

        // Equivalence partitioning: Address with mixed symbols and alphanumeric characters is invalid
        assertTrue(Address.isValidAddress("123 Main # Street"));
        assertTrue(Address.isValidAddress("Apt 123B, #$% Elm Avenue"));
        assertTrue(Address.isValidAddress("Unit 789, %$ Block C, 789 Maple Road"));
    }
}
