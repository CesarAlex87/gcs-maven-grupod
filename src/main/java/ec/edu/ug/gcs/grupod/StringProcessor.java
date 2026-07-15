package ec.edu.ug.gcs.grupod;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * StringProcessor class providing various string manipulation operations
 * using Apache Commons Lang3 utilities.
 */
public class StringProcessor {
    private static final Logger logger = LogManager.getLogger(StringProcessor.class);

    /**
     * Capitalizes the first character of a string.
     *
     * @param input the string to capitalize
     * @return capitalized string, or null if input is null
     */
    public String capitalize(String input) {
        if (input == null) {
            logger.warn("capitalize called with null input");
            return null;
        }
        String result = StringUtils.capitalize(input);
        logger.info("capitalize('{}') = '{}'", input, result);
        return result;
    }

    /**
     * Reverses a string.
     *
     * @param input the string to reverse
     * @return reversed string, or null if input is null
     */
    public String reverse(String input) {
        if (input == null) {
            logger.warn("reverse called with null input");
            return null;
        }
        String result = StringUtils.reverse(input);
        logger.info("reverse('{}') = '{}'", input, result);
        return result;
    }

    /**
     * Checks if a string is a palindrome.
     * A palindrome reads the same forwards and backwards (ignoring spaces and case).
     *
     * @param input the string to check
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            logger.warn("isPalindrome called with null or empty input");
            return false;
        }
        String clean = StringUtils.remove(StringUtils.remove(input.toLowerCase(), " "), "-");
        boolean result = clean.equals(StringUtils.reverse(clean));
        logger.info("isPalindrome('{}') = {}", input, result);
        return result;
    }

    /**
     * Counts vowels in a string.
     *
     * @param input the string to analyze
     * @return number of vowels found
     */
    public int countVowels(String input) {
        if (input == null) {
            logger.warn("countVowels called with null input");
            return 0;
        }
        String lowerInput = input.toLowerCase();
        int count = 0;
        for (char c : lowerInput.toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        logger.info("countVowels('{}') = {}", input, count);
        return count;
    }

    /**
     * Converts string to uppercase.
     *
     * @param input the string to convert
     * @return uppercase string, or null if input is null
     */
    public String toUpperCase(String input) {
        if (input == null) {
            logger.warn("toUpperCase called with null input");
            return null;
        }
        String result = input.toUpperCase();
        logger.info("toUpperCase('{}') = '{}'", input, result);
        return result;
    }

    /**
     * Checks if a string is empty or only contains whitespace.
     *
     * @param input the string to check
     * @return true if blank, false otherwise
     */
    public boolean isBlank(String input) {
        boolean result = StringUtils.isBlank(input);
        logger.info("isBlank('{}') = {}", input, result);
        return result;
    }
}
