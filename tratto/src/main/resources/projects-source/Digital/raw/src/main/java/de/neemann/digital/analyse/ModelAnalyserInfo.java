/*
 * Copyright (c) 2018 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digital.analyse;

import de.neemann.digital.core.Model;
import de.neemann.digital.core.Node;
import de.neemann.digital.core.Signal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Additional infos obtained from the model
 */
public class ModelAnalyserInfo {
    private final String clockPin;
    private final ArrayList<Bus> inputBusList;
    private final ArrayList<Bus> outputBusList;
    private final HashMap<String, Long> initValueMap;
    private final boolean isSequential;
    private TreeMap<String, String> pins;
    private ArrayList<Signal> inputs;
    private ArrayList<Signal> outputs;
    private ArrayList<String> pinsWithoutNumber;
    private String stateSignalName;
    private ArrayList<String> stateSignalBitNames;

    /**
     * creates a new instance
     *
     * @param model the model used
     */
    public ModelAnalyserInfo(Model model) {
        if (model != null && model.getClocks().size() == 1)
            clockPin = model.getClocks().get(0).getClockPin();
        else
            clockPin = null;

        isSequential = model != null && !model.findNode(Node::hasState).isEmpty();

        inputBusList = new ArrayList<>();
        outputBusList = new ArrayList<>();

        initValueMap = new HashMap<>();
    }

    /**
     * Sets the inputs and outputs
     *
     * @param inputs  the inputs
     * @param outputs the outputs
     */
    public void setInOut(ArrayList<Signal> inputs, ArrayList<Signal> outputs) {
        this.inputs = new ArrayList<>(inputs);
        this.outputs = new ArrayList<>(outputs);
    }

    /**
     * @return the assigned pins
     */
    public TreeMap<String, String> getPins() {
        if (pins == null) {
            pins = new TreeMap<>();
            for (Signal s : this.inputs)
                addPinNumber(s);
            for (Signal s : this.outputs)
                addPinNumber(s);
        }
        return pins;
    }

    private void addPinNumber(Signal s) {
        String p = s.getPinNumber();
        if (p != null && p.length() > 0) pins.put(s.getName(), p);
    }

    /**
     * @return list of pins without a number. Never null, maybe a empty list
     */
    public ArrayList<String> getPinsWithoutNumber() {
        if (pinsWithoutNumber == null) {
            pinsWithoutNumber = new ArrayList<>();
            for (Signal s : inputs)
                if (s.missingPinNumber())
                    pinsWithoutNumber.add((s.getName()));
            for (Signal s : outputs)
                if (s.missingPinNumber())
                    pinsWithoutNumber.add((s.getName()));
        }
        return pinsWithoutNumber;
    }

    /**
     * @return the clock pin
     */
    public int getClockPinInt() {
        if (clockPin == null || clockPin.length() == 0)
            return 0;

        try {
            return Integer.parseInt(clockPin);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    void addInputBus(String name, ArrayList<String> names) {
        for (Bus b : inputBusList)
            if (b.getBusName().equals(name))
                return;
        inputBusList.add(new Bus(name, names));
    }

    /**
     * Adds an output bus.
     *
     * @param name  the bus name
     * @param names the individual names in the truth table
     */
    public void addOutputBus(String name, ArrayList<String> names) {
        for (Bus b : outputBusList)
            if (b.getBusName().equals(name))
                return;
        outputBusList.add(new Bus(name, names));
    }

    /**
     * @return input bus map
     */
    public ArrayList<Bus> getInputBusList() {
        return inputBusList;
    }

    /**
     * @return output bus map
     */
    public ArrayList<Bus> getOutputBusList() {
        return outputBusList;
    }

    /**
     * @return true if model is sequential
     */
    public boolean isSequential() {
        return isSequential;
    }

    /**
     * Gets the init value of a sequential state machine
     *
     * @param name the name of the state variable
     * @return the init value
     */
    public long getSequentialInitValue(String name) {
        final Long init = initValueMap.get(name);
        if (init == null)
            return 0;
        return init;
    }

    /**
     * Sets a init value of a sequential state machine
     *
     * @param name  state variable
     * @param value initial state
     */
    public void setSequentialInitValue(String name, long value) {
        initValueMap.put(name, value);
    }

    /**
     * @return the state variable name, maybe null
     */
    public String getStateSignalName() {
        return stateSignalName;
    }

    /**
     * Sets the state variable name
     *
     * @param stateSignalName the state variable name
     */
    public void setStateSignalName(String stateSignalName) {
        this.stateSignalName = stateSignalName;
    }

    /**
     * Sets the state bit names in correct order
     *
     * @param stateSignalBitName the state bit names
     */
    public void setStateSignalBitNames(ArrayList<String> stateSignalBitName) {
        this.stateSignalBitNames = stateSignalBitName;
    }

    /**
     * Returns the state bit names in correct order
     *
     * @return the list of signals required by the FSM gui to show the active state and transition.
     */
    public ArrayList<String> getStateSignalBitNames() {
        return stateSignalBitNames;
    }

    /**
     * Description of a bus used as input or output of the circuit
     */
    public static final class Bus {
        private final String busName;
        private final ArrayList<String> signalNames;

        private Bus(String busName, ArrayList<String> signalNames) {
            this.busName = busName;
            this.signalNames = signalNames;
        }

        /**
         * @return the name of the bus
         */
        public String getBusName() {
            return busName;
        }

        /**
         * @return list of names of the signals the bus is made of
         */
        public ArrayList<String> getSignalNames() {
            return signalNames;
        }
    }
}
