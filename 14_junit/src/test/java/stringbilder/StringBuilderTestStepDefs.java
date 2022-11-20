package stringbilder;

import io.cucumber.java.en.*;
import org.junit.Assert;

public class StringBuilderTestStepDefs {
    StringBuilder stringBuilder;
    CharSequence charSequence;
    private Throwable throwable;
    char result;

    @When("Creating a StringBuilder object with an empty constructor")
    public void createAStringBuilderObjectWithAnEmptyConstructor() {
        stringBuilder = new StringBuilder();
    }

    @Then("The result has to be sixteen")
    public void theResultHasToBe() {
        Assert.assertEquals("Expected to get capacity that equals 16", 16, stringBuilder.capacity());
    }

    @Given("A CharSequence object")
    public void initializeACharSequenceObject() {
        charSequence = "abcde";
    }

    @When("Creating a StringBuilder object and passing the CharSequence object to it")
    public void createAStringBuilderObjectAndPassTheCharSequenceObjectToIt() {
        stringBuilder = new StringBuilder(charSequence);
    }

    @Then("Getting CharSequence object")
    public void getCharSequenceObject() {
        Assert.assertEquals("Expected to get stringBuilder object after passing the CharSequence object", stringBuilder, stringBuilder);
    }

    @When("Creating a StringBuilder object with the initial capacity = {int}")
    public void createAStringBuilderObjectWithTheInitialCapacity(int capacity) {
        try {
            stringBuilder = new StringBuilder(capacity);
        } catch (NegativeArraySizeException e) {
            throwable = e;
        }
    }

    @Then("A NegativeArraySizeException should be thrown")
    public void aNegativeArraySizeExceptionShouldBeThrown() {
        Assert.assertTrue("the result should be true", throwable instanceof NegativeArraySizeException);
    }

    @When("Passing null object to the new StringBuilder object")
    public void passingObjectToTheNewStringBuilderObject() {
        try {
            stringBuilder = new StringBuilder(null);
        } catch (NullPointerException e) {
            throwable = e;
        }
    }

    @Then("A NullPointerException should be thrown")
    public void aNullPointerExceptionShouldBeThrown() {
        Assert.assertTrue("the result should be true", throwable instanceof NullPointerException);
    }

    @Given("StringBuilder object with {string} string")
    public void stringBuilderObjectWithString(String str) {
        stringBuilder = new StringBuilder(str);
    }

    @When("Appending a {string} string to the object")
    public void appendingAStringToTheObject(String str) {
        stringBuilder.append(str);
    }

    @Then("Result is Hello world")
    public void resultIs() {
        Assert.assertEquals("Hello World", stringBuilder.toString());
    }

    @Given("StringBuilder object with {string} string for insert")
    public void stringBuilderObjectWithStringForInsert(String str) {
        stringBuilder = new StringBuilder(str);
    }

    @When("Inserting a {string} string to the object with the offset = {int}")
    public void insertingAStringToTheObjectWithTheOffset(String str, int offset) {
        stringBuilder.insert(offset, str);
    }

    @Then("Result is HelWorldlo")
    public void resultIsNext() {
        Assert.assertEquals("HelWorldlo", stringBuilder.toString());
    }

    @Given("StringBuilder object with {string} string for replace")
    public void stringBuilderObjectWithStringForReplace(String str) {
        stringBuilder = new StringBuilder(str);
    }

    @When("Inserting a {string} string to the object with the start = {int} and end = {int}")
    public void insertingAStringToTheObjectWithTheStartAndEnd(String str, int start, int end) {
        stringBuilder.replace(start, end, str);
    }

    @Then("Result is HelWorldo")
    public void resultIsHelWorldloeaad() {
        Assert.assertEquals("HelWorldo", stringBuilder.toString());
    }

    @Given("StringBuilder object with {string} string for delete")
    public void stringbuilderObjectWithStringForDelete(String arg0) {
        stringBuilder = new StringBuilder(arg0);
    }

    @When("Delete a string from {int} and and to {int}")
    public void deleteAStringFromAndAndTo(int arg0, int arg1) {
        stringBuilder.delete(arg0, arg1);
    }

    @Then("Result is HelWorld")
    public void resultIsHelorld() {
        Assert.assertEquals("HelWorld", stringBuilder.toString());
    }

    @Given("StringBuilder object with {string} string for reverse")
    public void stringbuilderObjectWithStringForReverse(String arg0) {
        stringBuilder = new StringBuilder(arg0);
    }

    @When("Reverse a string")
    public void reverseAString() {
        stringBuilder.reverse();
    }

    @Then("Result is dlroW_olleH")
    public void resultIsDlroW_olleH() {
        Assert.assertEquals("dlroW_olleH", stringBuilder.toString());
    }

    @Given("StringBuilder object")
    public void stringbuilderObject() {
        stringBuilder = new StringBuilder();
    }

    @When("Appending object with {string} string")
    public void appendingObjectWithString(String arg0) {
        stringBuilder.append(arg0);
    }

    @Then("Capacity = sixteen")
    public void capacitySix() {
        Assert.assertEquals(16, stringBuilder.capacity());
    }

    @But("Appending object with another {string} string")
    public void appendingObjectWithAnotherString(String arg0) {
        stringBuilder.append(arg0);
    }

    @Then("Capacity = thirty for")
    public void capacitySeventeen() {
        Assert.assertEquals(34, stringBuilder.capacity());
    }

    @Given("Empty StringBuilder object")
    public void emptyStringBuilderObject() {
        stringBuilder = new StringBuilder();
    }

    @When("Appending object with {string} string for appending")
    public void appendingObjectWithStringForAppending(String arg0) {
        stringBuilder.append(arg0);
    }

    @Then("Length = twelve")
    public void lengthFifteen() {
        Assert.assertEquals(12, stringBuilder.length());
    }

    @Given("StringBuilder obj with {string} string")
    public void stringbuilderObjWithString(String arg0) {
        stringBuilder = new StringBuilder(arg0);
    }

    @When("Passing an index that equals {int}")
    public void passingAnIndexThatEquals(int arg0) {
        result = stringBuilder.charAt(arg0);
    }

    @Then("Chart equals o")
    public void chartEqualsO() {
        Assert.assertEquals('o', result);
    }

    @Given("StringBuilder obj")
    public void stringbuilderObj() {
        stringBuilder = new StringBuilder();
    }

    @When("Appending a {string} string")
    public void appendingAString(String arg0) {
        stringBuilder.append(arg0);
    }

    @Then("Result is five")
    public void resultIsFive() {
        Assert.assertEquals(5, stringBuilder.indexOf("ber"));
    }
}
