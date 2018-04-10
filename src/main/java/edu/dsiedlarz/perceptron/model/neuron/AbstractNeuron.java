package edu.dsiedlarz.perceptron.model.neuron;

import edu.dsiedlarz.perceptron.model.WeightedConnection;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNeuron {

    public List<WeightedConnection> inputs = new ArrayList<>();
    public List<WeightedConnection> outputs = new ArrayList<>();

    public Double weightedSum;
    public Double delta;
    public Double outputValue;

    public Double getOutputValue() {
        return outputValue;
    }

    public void computeWeightSumFromInputData() {
        weightedSum = 0D;
        for (int i = 0; i < inputs.size(); i++) {
            weightedSum += inputs.get(i).getWeightedOutput();

        }

        outputValue = 1 / (1 + Math.pow(Math.E, -weightedSum));
    }

    public abstract void afterGuess();
    public abstract void afterGuessHidden();

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public Double getDelta() {
        if (delta == null) {
            return 0D;
        }
        return delta;
    }
}
