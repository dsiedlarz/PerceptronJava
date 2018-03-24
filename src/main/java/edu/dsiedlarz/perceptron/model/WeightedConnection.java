package edu.dsiedlarz.perceptron.model;

import edu.dsiedlarz.perceptron.model.neuron.AbstractNeuron;
import edu.dsiedlarz.perceptron.model.neuron.ProcessingNeuron;

import java.util.Random;

public class WeightedConnection {

    private static final Random random = new Random();

    public AbstractNeuron in;
    public AbstractNeuron out;

    public Double weight;


    public WeightedConnection(AbstractNeuron in, AbstractNeuron out) {
        this.in = in;
        this.out = out;
        weight = random.nextDouble() * 0.2 - 0.1;

        this.in.outputs.add(this);
        this.out.inputs.add(this);
    }

    public Double getWeightedOutput() {
        return in.getOutputValue() * weight;
    }

    public Double getWeightedDelta() {
        return weight * out.delta;
    }
}
