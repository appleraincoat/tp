package seedu.realodex.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.commands.FilterCommand;
import seedu.realodex.logic.parser.Prefix;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.HousingType;
import seedu.realodex.model.person.Person;
import seedu.realodex.testutil.PersonBuilder;

class PredicateProducerTest {

    @Test
    // Test for creating predicate based on name prefix
    void createPredicate_validNamePrefix_createsCorrectPredicate() throws ParseException {
        // Equivalence Partitioning (EP): Valid name prefix and keyphrase
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrase = List.of("Alice");

        Person alice = new PersonBuilder().withName("Alice").withRemark("She is a lice").build();
        assertEquals(predicateProducer.createPredicate(PREFIX_NAME, keyphrase),
                     new NameContainsKeyphrasePredicate("Alice"));
        assertTrue(predicateProducer.createPredicate(PREFIX_NAME, keyphrase).test(alice));
    }

    @Test
    // Test for creating predicate based on remark prefix
    void createPredicate_validRemarkPrefix_createsCorrectPredicate() throws ParseException {
        // Equivalence Partitioning (EP): Valid remark prefix and keyphrase
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrase = List.of("She is a lice");

        Person alice = new PersonBuilder().withName("Alice").withRemark("She is a lice").build();
        assertEquals(predicateProducer.createPredicate(PREFIX_REMARK, keyphrase),
                     new RemarkContainsKeyphrasePredicate("She is a lice"));
        assertTrue(predicateProducer.createPredicate(PREFIX_REMARK, keyphrase).test(alice));
    }

    @Test
    // Test for creating predicate based on tag prefix
    void createPredicate_validTagPrefix_createsCorrectPredicate() throws ParseException {
        // Equivalence Partitioning (EP): Valid tag prefix and keyphrases
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrases = List.of("buyer");

        Person bob = new PersonBuilder().withName("Bob").withTags("buyer").build();
        assertTrue(predicateProducer.createPredicate(PREFIX_TAG, keyphrases).test(bob));
    }

    @Test
    // Test for creating predicate based on multiple tag prefixes
    void createPredicate_validMultipleTagPrefixes_createsCorrectPredicate() throws ParseException {
        // Equivalence Partitioning (EP): Valid tag prefixes and keyphrases
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrases = List.of("buyer", "seller");

        Person bob = new PersonBuilder().withName("Bob").withTags("buyer", "seller").build();
        assertTrue(predicateProducer.createPredicate(PREFIX_TAG, keyphrases).test(bob));
    }

    @Test
    // Test for handling invalid tag prefix
    void createMatchTagPredicate_invalidTagString_assertionErrorWhenInvalidPrefix() {
        // Equivalence Partitioning (EP): Invalid tag prefix
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrases = List.of("customer");

        assertNull(predicateProducer.createMatchTagsPredicate(keyphrases));
    }

    @Test
    // Test for creating housing type match predicate with valid housing type strings
    void createHousingTypeMatchPredicate_validHousingTypeStrings_createsCorrectPredicate() {
        // Equivalence Partitioning (EP): Valid housing type strings
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrases = List.of("hdb");
        HousingType housingType = new HousingType("hdb");
        assertEquals(predicateProducer.createHousingTypeMatchPredicate(keyphrases),
                     new HousingTypeMatchPredicate(housingType));
    }

    @Test
    // Test for handling invalid housing type strings
    void createHousingTypeMatchPredicate_invalidHousingTypeStrings_assertionErrorWhenInvalidPrefix() {
        // Equivalence Partitioning (EP): Invalid housing type strings
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrases = List.of("hdbb");

        assertNull(predicateProducer.createHousingTypeMatchPredicate(keyphrases));
    }

    @Test
    // Test for handling empty keyphrase when creating predicate
    void createPredicate_emptyKeyphrase_throwsParseException() {
        // Equivalence Partitioning (EP): Empty keyphrase
        PredicateProducer predicateProducer = new PredicateProducer();
        List<String> keyphrase = List.of("");

        ParseException exception = assertThrows(ParseException.class, () ->
                predicateProducer.createPredicate(PREFIX_NAME, keyphrase));

        assertTrue(exception.getMessage().contains(FilterCommand.MESSAGE_USAGE));
    }

    @Test
    // Test for handling unknown prefix when creating predicate
    void createPredicate_returnsNullWhenInvalidPrefix() {
        // Equivalence Partitioning (EP): Unknown prefix
        PredicateProducer predicateProducer = new PredicateProducer();
        Prefix unhandledPrefix = new Prefix("unhandled/");
        List<String> keyphrase = List.of("keyphrase");

        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);

        assertThrows(AssertionError.class, () -> predicateProducer.createPredicate(unhandledPrefix, keyphrase));
    }
}
