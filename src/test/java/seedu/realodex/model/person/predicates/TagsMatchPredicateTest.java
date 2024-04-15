package seedu.realodex.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.Tag;
import seedu.realodex.testutil.PersonBuilder;

public class TagsMatchPredicateTest {

    @Test
    // Test for checking equality of predicates
    public void equals() {
        // Equivalence Partitioning (EP): Equality of predicates
        Set<Tag> firstTagSet = Set.of(new Tag("buyer"));
        Set<Tag> secondTagSet = Set.of(new Tag("seller"));
        Set<Tag> thirdTagSet = Set.of(new Tag("buyer"), new Tag("seller"));

        TagsMatchPredicate firstPredicate = new TagsMatchPredicate(firstTagSet);
        TagsMatchPredicate secondPredicate = new TagsMatchPredicate(secondTagSet);
        TagsMatchPredicate thirdPredicate = new TagsMatchPredicate(thirdTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsMatchPredicate firstPredicateCopy = new TagsMatchPredicate(firstTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // stricter vs less-strict predicate
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
        // Test for checking if person's tags match predicate's tags
    void test_personTagsMatchPredicateTags_returnsTrue() {
        // Equivalence Partitioning (EP): Tags match predicate's tags
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("buyer").build();

        assertTrue(predicate.test(person));
    }

    @Test
        // Test for checking if person's multiple tags including predicate's tags return true
    void test_personMultipleTagsIncludingPredicateTags_returnsTrue() {
        // Equivalence Partitioning (EP): Multiple tags including predicate's tags return true
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("buyer", "seller").build();

        assertTrue(predicate.test(person));
    }

    @Test
        // Test for checking if person's multiple tags including multiple predicate's tags return true
    void test_personMultipleTagsIncludingMultiplePredicateTags_returnsTrue() {
        // Equivalence Partitioning (EP): Multiple tags including multiple predicate's tags return true
        Set<Tag> predicateTags = Set.of(new Tag("buyer"), new Tag("seller"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("buyer", "seller").build();

        assertTrue(predicate.test(person));
    }

    @Test
        // Test for checking if person with no tags doesn't match predicate's tags
    void test_personNoTagsDontMatchPredicateTags_returnsFalse() {
        // Equivalence Partitioning (EP): Person with no tags doesn't match predicate's tags
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("").build();

        assertFalse(predicate.test(person));
    }

    @Test
        // Test for checking if person's tags don't match predicate's tags
    void test_personTagsDontMatchPredicateTags_returnsFalse() {
        // Equivalence Partitioning (EP): Person's tags don't match predicate's tags
        Set<Tag> predicateTags = Set.of(new Tag("buyer"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("seller").build();

        assertFalse(predicate.test(person));
    }

    @Test
        // Test for checking if person's tags don't match multiple predicate's tags
    void test_personTagsDontMatchMultiplePredicateTags_returnsFalse() {
        // Equivalence Partitioning (EP): Person's tags don't match multiple predicate's tags
        Set<Tag> predicateTags = Set.of(new Tag("buyer"), new Tag("seller"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(predicateTags);

        Person person = new PersonBuilder().withTags("seller").build();

        assertFalse(predicate.test(person));
    }

    @Test
    // Test for checking toString method
    public void toStringMethod() {
        // Equivalence Partitioning (EP): ToString method
        Set<Tag> tagSet = Set.of(new Tag("buyer"), new Tag("seller"));
        TagsMatchPredicate predicate = new TagsMatchPredicate(tagSet);
        String expected = TagsMatchPredicate.class.getCanonicalName() + "{Tag Set=" + tagSet + "}";
        assertEquals(expected, predicate.toString());
    }
}
