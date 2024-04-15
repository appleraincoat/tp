package seedu.realodex.logic.parser;

import static seedu.realodex.logic.commands.DeleteCommand.MESSAGE_INDEX_AND_NAME_PROVIDED;
import static seedu.realodex.logic.commands.DeleteCommand.MESSAGE_NO_FIELDS_PROVIDED;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.realodex.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.realodex.testutil.Assert.assertThrows;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.DeleteCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Name;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    /**
     * EP: Parsing a valid index should return a DeleteCommand targeting that index.
     */
    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        // Arrange & Act & Assert
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    /**
     * EP: Parsing an invalid index should throw a ParseException.
     */
    @Test
    public void parse_invalidIndex_throwsParseException() {
        // Arrange & Act & Assert
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_INDEX));
    }

    /**
     * EP: Parsing a valid name should return a DeleteCommand targeting that name.
     */
    @Test
    public void parse_validName_returnsDeleteCommand() {
        // Arrange & Act & Assert
        assertParseSuccess(parser, " n/James", new DeleteCommand(new Name("James")));
    }

    /**
     * EP: Parsing an invalid name should throw a ParseException.
     */
    @Test
    public void parse_invalidName_throwParseException() {
        // Act & Assert
        assertThrows(ParseException.class, Name.MESSAGE_CONSTRAINTS, () -> parser.parse(" n/peter*"));
        assertThrows(ParseException.class, Name.MESSAGE_CONSTRAINTS, () -> parser.parse(" n/ "));
        assertThrows(ParseException.class, Name.MESSAGE_CONSTRAINTS, () -> parser.parse(" n/^"));
    }

    /**
     * EP: Parsing invalid arguments should throw a ParseException.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Arrange & Act & Assert
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_INDEX, DeleteCommand.MESSAGE_USAGE));
    }

    /**
     * EP: Parsing when both index and name are provided should throw a ParseException.
     */
    @Test
    public void parse_indexAndNameProvided_throwsParseException() {
        // Arrange & Act & Assert
        assertParseFailure(parser, "1 n/James", MESSAGE_INDEX_AND_NAME_PROVIDED);
    }

    /**
     * EP: Parsing when both name and index are provided should throw a ParseException.
     */
    @Test
    public void parse_nameAndIndex_throwsParseException() {
        // Arrange & Act & Assert
        assertParseFailure(parser, "1 n/James", String.format(MESSAGE_INDEX_AND_NAME_PROVIDED));
    }

    /**
     * EP: Parsing when no fields are provided should throw a ParseException.
     */
    @Test
    public void parse_noFields_throwsParseException() {
        // Arrange & Act & Assert
        assertParseFailure(parser, "", String.format(MESSAGE_NO_FIELDS_PROVIDED));
    }

    /**
     * EP: Parsing when multiple names are provided should throw a ParseException.
     */
    @Test
    public void parse_multipleName_throwsParseException() {
        // Arrange & Act & Assert
        assertParseFailure(parser, " n/James n/John", Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
        assertParseFailure(parser, " n/James n/John n/jj", Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

    }
}
