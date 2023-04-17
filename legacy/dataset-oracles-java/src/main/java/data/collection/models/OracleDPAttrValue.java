package data.collection.models;

import java.util.HashMap;
import data.collection.enums.OracleDPAttrKey;

/**
 * A support record that contains the value of a key of an oracle data-point hash map
 * {@link HashMap<OracleDPAttrKey,OracleDPAttrValue>}.
 * @param value The value of the data-point key.
 * @param <T> The generic type of the value {@link #value}.
 */
public record OracleDPAttrValue<T>(
        T value
){}
