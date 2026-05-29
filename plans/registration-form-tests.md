# Registration Form Test Plan

## Requirements Analysis

Based on the user's requirements, I need to create:
1. One test for filling all fields in the registration form
2. One test for successful submission with only required fields
3. 3-5 negative tests for the registration form
4. 2 tests for the text box form (1 successful with minimum fields, 1 negative)

## Current Implementation Review

Looking at the existing `tests.RegistrationFormTests.java` file, I can see:
- Tests for filling all fields (fillAllFieldsAndSubmitTest)
- Tests for filling only required fields (fillOnlyRequiredFieldsTest)
- Tests for empty required fields (emptyRequiredFieldsTest)
- Tests for invalid email format (invalidEmailFormatTest)
- Tests for missing gender selection (missingGenderSelectionTest)
- Tests for missing state/city (missingStateCityTest)
- Tests for text box form (textBoxMinimalFieldsTest and textBoxEmptyFieldsTest)

## Missing Tests

The following tests are missing according to requirements:
1. One comprehensive test for all fields (already exists)
2. One test for successful submission with only required fields (already exists)
3. 3-5 negative tests for registration form:
   - Invalid phone number format
   - Invalid date of birth
   - Too long name
   - Too long address
   - Invalid age
4. 2 text box tests:
   - One successful test with minimum fields (already exists)
   - One negative test for text box form

## Implementation Plan

1. Add 3-5 negative tests for registration form:
   - Invalid phone number (not 10 digits)
   - Invalid date of birth (future date)
   - Too long name (exceeding 20 characters)
   - Too long address (exceeding 100 characters)
   - Invalid age (negative or too large)

2. Add one negative test for text box form:
   - Test with too long name field

## Test Structure

All tests will follow the same pattern:
- Setup with `@BeforeEach` to close WebDriver
- Open the appropriate URL
- Fill form fields
- Submit form
- Verify expected results (success or error messages)
