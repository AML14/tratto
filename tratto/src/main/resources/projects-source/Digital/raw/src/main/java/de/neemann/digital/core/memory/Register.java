/*
 * Copyright (c) 2016 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digital.core.memory;

import de.neemann.digital.core.*;
import de.neemann.digital.core.element.Element;
import de.neemann.digital.core.element.ElementAttributes;
import de.neemann.digital.core.element.ElementTypeDescription;
import de.neemann.digital.core.element.Keys;
import de.neemann.digital.core.stats.Countable;

import static de.neemann.digital.core.element.PinInfo.input;

/**
 * A simple register.
 */
public class Register extends Node implements Element, Countable, ProgramCounter {

    /**
     * The registers {@link ElementTypeDescription}
     */
    public static final ElementTypeDescription DESCRIPTION
            = new ElementTypeDescription(Register.class, input("D"), input("C").setClock(), input("en"))
            .addAttribute(Keys.ROTATE)
            .addAttribute(Keys.BITS)
            .addAttribute(Keys.LABEL)
            .addAttribute(Keys.INVERTER_CONFIG)
            .addAttribute(Keys.IS_PROGRAM_COUNTER)
            .addAttribute(Keys.VALUE_IS_PROBE)
            .supportsHDL();

    private final int bits;
    private final boolean isProbe;
    private final String label;
    private final boolean isProgramCounter;
    private final ObservableValue q;
    private ObservableValue dVal;
    private ObservableValue clockVal;
    private ObservableValue enableVal;
    private boolean lastClock;
    private long value;
    private boolean enable;

    /**
     * Creates a new instance
     *
     * @param attributes the elements attributes
     */
    public Register(ElementAttributes attributes) {
        super(true);
        bits = attributes.getBits();
        this.q = new ObservableValue("Q", bits).setPinDescription(DESCRIPTION);
        isProbe = attributes.get(Keys.VALUE_IS_PROBE);
        label = attributes.get(Keys.LABEL);
        isProgramCounter = attributes.get(Keys.IS_PROGRAM_COUNTER);
    }

    @Override
    public void readInputs() throws NodeException {
        enable = enableVal.getBool();
        boolean clock = clockVal.getBool();
        if (clock && !lastClock && enable)
            value = dVal.getValue();
        lastClock = clock;
    }

    @Override
    public void writeOutputs() throws NodeException {
        q.setValue(value);
    }

    @Override
    public void setInputs(ObservableValues inputs) throws BitsException {
        dVal = inputs.get(0).checkBits(bits, this);
        clockVal = inputs.get(1).addObserverToValue(this).checkBits(1, this);
        enableVal = inputs.get(2).checkBits(1, this);
    }

    @Override
    public ObservableValues getOutputs() {
        return q.asList();
    }

    @Override
    public void registerNodes(Model model) {
        super.registerNodes(model);
        if (isProbe)
            model.addSignal(new Signal(label, q, (value, highZ) -> setValue(value)).setTestOutput());
    }

    @Override
    public boolean isProgramCounter() {
        return isProgramCounter;
    }

    @Override
    public long getProgramCounter() {
        return value;
    }

    @Override
    public int getDataBits() {
        return bits;
    }

    /**
     * Returns the stored value.
     * Used to improve custom testing capabilities.
     *
     * @return the stared value
     */
    public long getValue() {
        return value;
    }

    /**
     * Sets the stored value.
     * Used to improve custom testing capabilities.
     *
     * @param v the value
     */
    public void setValue(long v) {
        value = v;
        q.setValue(value);
    }

    /**
     * Returns the used label.
     * Used to improve custom testing capabilities.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the enable input value.
     * Used to improve custom testing capabilities.
     *
     * @return true if enable was set at the last clock change.
     */
    public boolean isEnabled() {
        return enable;
    }
}
