package seedu.realodex.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.person.Person;
import seedu.realodex.testutil.PersonBuilder;

public class NameContainsKeyphrasePredicateTest {

    @Test
    // Test for equals method to ensure consistency and symmetry
    public void equals() {
        // Equivalence Partitioning (EP): Same keyphrase, different keyphrase, null, different type
        String firstPredicateKeyphrase = "first";
        String secondPredicateKeyphrase = "first second";

        NameContainsKeyphrasePredicate firstPredicate = new NameContainsKeyphrasePredicate(firstPredicateKeyphrase);
        NameContainsKeyphrasePredicate secondPredicate = new NameContainsKeyphrasePredicate(secondPredicateKeyphrase);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeyphrasePredicate firstPredicateCopy = new NameContainsKeyphrasePredicate(firstPredicateKeyphrase);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyphrase -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    // Test for nameContainsKeyphrasePredicate to return true when name contains keyphrase
    public void test_nameContainsKeyphrase_returnsTrue() {
        // Equivalence Partitioning (EP): Matching keyphrase in different scenarios
        // Keyphrase is one word
        NameContainsKeyphrasePredicate predicate = new NameContainsKeyphrasePredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Keyphrase is more than one word
        predicate = new NameContainsKeyphrasePredicate("Alice Tan");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Name starts with matching keyphrase
        predicate = new NameContainsKeyphrasePredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Name does not start with matching keyphrase
        predicate = new NameContainsKeyphrasePredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Annoying Alice").build()));

        // Keyphrase is not the full word
        predicate = new NameContainsKeyphrasePredicate("lice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Not exact word and not starting with keyphrase
        predicate = new NameContainsKeyphrasePredicate("lice");
        assertTrue(predicate.test(new PersonBuilder().withName("Annoying Alice").build()));

        // Mixed-case keyphrase
        predicate = new NameContainsKeyphrasePredicate("aLIce bOB");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    // Test for nameContainsKeyphrasePredicate to return false when name does not contain keyphrase
    public void test_nameDoesNotContainKeyphrase_returnsFalse() {
        // Equivalence Partitioning (EP): Non-matching keyphrase in different scenarios
        // Non-matching keyphrase
        NameContainsKeyphrasePredicate predicate = new NameContainsKeyphrasePredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        Person alice = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();

        // Keyphrase matches phone but does not match name
        predicate = new NameContainsKeyphrasePredicate("12345");
        assertFalse(predicate.test(alice));

        // Keyphrase matches email but does not match name
        predicate = new NameContainsKeyphrasePredicate("alice@email.com");
        assertFalse(predicate.test(alice));

        // Keyphrase matches address but does not match name
        predicate = new NameContainsKeyphrasePredicate("Main");
        assertFalse(predicate.test(alice));
        predicate = new NameContainsKeyphrasePredicate("Street");
        assertFalse(predicate.test(alice));
    }

    @Test
    // Test toString method to ensure it returns correct string representation
    public void toStringMethod() {
        String keyphrase = "keyphrase yapyap";
        NameContainsKeyphrasePredicate predicate = new NameContainsKeyphrasePredicate(keyphrase);
        String expected = NameContainsKeyphrasePredicate.class.getCanonicalName() + "{keyphrase=" + keyphrase + "}";
        assertEquals(expected, predicate.toString());
    }
}
