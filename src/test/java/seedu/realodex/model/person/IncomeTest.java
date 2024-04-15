package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IncomeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Income(null));
    }

    @Test
    public void constructor_invalidIncome_throwsIllegalArgumentException() {
        String invalidIncome = "";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidIncome));

        String invalidIncomeWithSpaces = " ";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidIncomeWithSpaces));
    }

    @Test
    public void isValidIncome() {
        // null income
        assertThrows(NullPointerException.class, () -> Income.isValidIncome(null));

        // invalid income
        assertFalse(Income.isValidIncome("-1")); // less than 0
        assertFalse(Income.isValidIncome("-3")); // less than 0
        assertFalse(Income.isValidIncome("-121333213213131313")); // less than 0 but very negative


        // valid income numbers
        assertTrue(Income.isValidIncome("2")); // more than 1
        assertTrue(Income.isValidIncome("93121"));
        assertTrue(Income.isValidIncome("1242938231332131313")); // long number
    }

    @Test
    public void isValidIncome_equivalencePartitioning() {
        // null income
        assertThrows(NullPointerException.class, () -> Income.isValidIncome(null));

        // invalid income
        assertFalse(Income.isValidIncome("-1")); // less than 0
        assertFalse(Income.isValidIncome("-3")); // less than 0
        assertFalse(Income.isValidIncome("-121333213213131313")); // less than 0 but very negative

        // valid income numbers
        assertTrue(Income.isValidIncome("0")); // zero income
        assertTrue(Income.isValidIncome("1")); // minimum positive income
        assertTrue(Income.isValidIncome("2")); // more than 1
        assertTrue(Income.isValidIncome("93121")); // large positive income
        assertTrue(Income.isValidIncome("1242938231332131313")); // very large positive income

        // EP: income with prepended zeros
        assertTrue(Income.isValidIncome("000001")); // one prepended zero
        assertTrue(Income.isValidIncome("000002")); // two prepended zeros
        assertTrue(Income.isValidIncome("00000000000000000000000000001")); // many prepended zeros

        // EP: income with special characters (should be invalid)
        assertFalse(Income.isValidIncome("12,345")); // comma
        assertFalse(Income.isValidIncome("12.345")); // period
        assertFalse(Income.isValidIncome("12%345")); // percentage sign
        assertFalse(Income.isValidIncome("12$345")); // dollar sign
        assertFalse(Income.isValidIncome("12!345")); // exclamation mark

        // EP: income with leading/trailing spaces (should be invalid)
        assertFalse(Income.isValidIncome(" 123")); // leading space
        assertFalse(Income.isValidIncome("123 ")); // trailing space
        assertFalse(Income.isValidIncome(" 123 ")); // leading and trailing spaces

        // EP: income with alphabets (should be invalid)
        assertFalse(Income.isValidIncome("abc")); // alphabets only
        assertFalse(Income.isValidIncome("1a2b3c")); // mixed alphanumeric

        // EP: income with mixed alphanumeric and symbols (should be invalid)
        assertFalse(Income.isValidIncome("12$3")); // alphanumeric and dollar sign
        assertFalse(Income.isValidIncome("12.3")); // alphanumeric and period
        assertFalse(Income.isValidIncome("12,3")); // alphanumeric and comma

        // EP: income with negative sign in the middle (should be invalid)
        assertFalse(Income.isValidIncome("12-345")); // negative sign in the middle

        // EP: income with very large negative values (should be invalid)
        assertFalse(Income.isValidIncome("-121333213213131313")); // very negative value

        // EP: income with very large positive values (should be valid)
        assertTrue(Income.isValidIncome("1242938231332131313")); // very large positive value

        // EP: empty string (should be invalid)
        assertFalse(Income.isValidIncome("")); // empty string

        // EP: negative zero (should be invalid)
        assertFalse(Income.isValidIncome("-0")); // negative zero

        // EP: floating point number (should be invalid)
        assertFalse(Income.isValidIncome("12.34")); // floating point number
        assertFalse(Income.isValidIncome("12.0")); // floating point number
        assertFalse(Income.isValidIncome("0.123")); // floating point number
        assertFalse(Income.isValidIncome("1.0")); // floating point number
        assertFalse(Income.isValidIncome("0.0")); // floating point number
    }

    @Test
    public void equals() {
        Income income = new Income("999");

        // same values -> returns true
        assertTrue(income.equals(new Income("999")));

        // same object -> returns true
        assertTrue(income.equals(income));

        // null -> returns false
        assertFalse(income.equals(null));

        // different types (integer vs float) -> returns false
        assertFalse(income.equals(5.0f));

        // different types (integer vs word) -> returns false
        assertFalse(income.equals("imaword!"));

        // different values -> returns false
        assertFalse(income.equals(new Income("995")));
    }
}
