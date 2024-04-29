package data;

public enum TogaInputType {
    /* Generate the toga input, considering all the occurrence of methods of the class under test, within the test prefix */
    ANY_OCCURRENCE,
    /* Generate the toga input, considering the last occurrence of a method of the class under test, within the test prefix */
    LAST_OCCURRENCE
}
