package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author 4llysa
public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // EP: Null tag name passed to constructor -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        // EP: Invalid tag name passed to constructor -> throws IllegalArgumentException
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // EP: Null tag name passed to isValidTagName method -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // EP: Invalid tag names
        assertFalse(Tag.isValidTagName("")); // Empty tag name
        assertFalse(Tag.isValidTagName("Buyers")); // Tag should not be plural
        assertFalse(Tag.isValidTagName("Sellers ")); // Extra space at the end
        assertFalse(Tag.isValidTagName("Invalid Tag")); // Space within the tag name

        // EP: Valid tag names
        assertTrue(Tag.isValidTagName("buyer"));
        assertTrue(Tag.isValidTagName("seller"));
        assertTrue(Tag.isValidTagName("buYeR")); // Tag converts string to uppercase
        assertTrue(Tag.isValidTagName("seLLer")); // Tag converts string to uppercase
    }
}
