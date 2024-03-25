package data;

import java.util.List;

/**
 * An enum of all TOGs supported by the experiment module.
 */
public enum TogType {
    JDOCTOR,
    TOGA,
    TRATTO,

    BASELINE;

    /** A list of all supported axiomatic test oracle generators. */
    public static final List<TogType> axiomaticTogs = List.of(
            TogType.JDOCTOR,
            TogType.TRATTO
    );

    /**
     * Checks if the TOG is axiomatic.
     *
     * @return true iff this TOG generates axiomatic test oracles (known
     * a priori)
     * @see TogType#axiomaticTogs
     */
    public boolean isAxiomatic() {
        return axiomaticTogs.contains(this);
    }
}
