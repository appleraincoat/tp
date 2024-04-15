package seedu.realodex.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.realodex.testutil.PersonBuilder;

class BirthdayIsInMonthPredicateTest {

    @Test
    // Test for equals method to ensure consistency and symmetry
    public void equals() {
        // Equivalence Partitioning (EP): Same month, different month, null, different type
        String firstMonth = "jan";
        String secondMonth = "feb";

        BirthdayIsInMonthPredicate firstPredicate = new BirthdayIsInMonthPredicate(firstMonth);
        BirthdayIsInMonthPredicate secondPredicate = new BirthdayIsInMonthPredicate(secondMonth);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BirthdayIsInMonthPredicate firstPredicateCopy = new BirthdayIsInMonthPredicate(firstMonth);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different month -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    // Test for birthdayIsInMonthPredicate to return true when birthday month matches predicate month
    public void test_birthdayIsInMonth_returnsTrue() {
        // Equivalence Partitioning (EP): Valid month with different lengths, leap year
        // Keyphrase is valid month with 3 letters
        BirthdayIsInMonthPredicate predicate = new BirthdayIsInMonthPredicate("Jan");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("1Jan2001").build()));

        // Keyphrase is valid month with 4 letters
        predicate = new BirthdayIsInMonthPredicate("Oct");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("11Oct2001").build()));

        // Keyphrase is February and Birthday in leap year
        predicate = new BirthdayIsInMonthPredicate("Feb");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("29Feb2000").build()));

        // Mixed-case keyphrase
        predicate = new BirthdayIsInMonthPredicate("jAn");
        assertTrue(predicate.test(new PersonBuilder().withBirthday("1Jan2001").build()));
    }

    @Test
    // Test for birthdayIsInMonthPredicate to return false when birthday month does not match predicate month
    public void test_birthdayIsNotInMonth_returnsTrue() {
        // Equivalence Partitioning (EP): Valid month with different lengths, mixed-case keyphrase
        // Keyphrase is valid month with 3 letters
        BirthdayIsInMonthPredicate predicate = new BirthdayIsInMonthPredicate("Jun");
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1Jan2001").build()));

        // Keyphrase is valid month with full month
        predicate = new BirthdayIsInMonthPredicate("September");
        assertFalse(predicate.test(new PersonBuilder().withBirthday("11October2001").build()));

        // Mixed-case keyphrase
        predicate = new BirthdayIsInMonthPredicate("jAn");
        assertFalse(predicate.test(new PersonBuilder().withBirthday("1Feb2001").build()));
    }

    @Test
    // Test for birthdayIsInMonthPredicate to return false when birthday is not specified
    public void test_birthdayNotSpecified_returnsFalse() {
        // Equivalence Partitioning (EP): Birthday not specified
        BirthdayIsInMonthPredicate predicate = new BirthdayIsInMonthPredicate("Jun");
        assertFalse(predicate.test(new PersonBuilder().build()));

        predicate = new BirthdayIsInMonthPredicate("October");
        assertFalse(predicate.test(new PersonBuilder().withBirthday("").build()));
    }

    @Test
        // Test toString method to ensure it returns correct string representation
    void toStringMethod() {
        String keyphrase = "Aug";
        BirthdayIsInMonthPredicate predicate = new BirthdayIsInMonthPredicate(keyphrase);
        String expected = BirthdayIsInMonthPredicate.class.getCanonicalName() + "{month=Aug}";
        assertEquals(expected, predicate.toString());
    }

    // The following tests are to meet CodeCov requirements, will not be reached under normal use
    @Test
    // Test toString method when input is invalid or empty
    void toStringMethod_noBirthMonthSpecified() {
        // Equivalence Partitioning (EP): Empty String, Invalid String
        // Empty String input
        String keyphraseEmptyString = "";
        assertThrows(AssertionError.class, null, () -> new BirthdayIsInMonthPredicate(keyphraseEmptyString));

        // Invalid String input
        String keyphraseInvalidString = "sndjkfnksdnf";
        assertThrows(AssertionError.class, null, () -> new BirthdayIsInMonthPredicate(keyphraseInvalidString));
    }
}
