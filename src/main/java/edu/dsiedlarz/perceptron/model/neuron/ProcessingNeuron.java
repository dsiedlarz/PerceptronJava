package edu.dsiedlarz.perceptron.model.neuron;

import edu.dsiedlarz.perceptron.model.Perceptron;

public class ProcessingNeuron extends AbstractNeuron {

    public void afterGuess() {

        for (int i = 0; i < inputs.size(); i++) {
            Double deltaW = Perceptron.LEARNING_RATE * delta * (1 - outputValue) * outputValue * inputs.get(i).in.getOutputValue();
            inputs.get(i).weight = inputs.get(i).weight + deltaW;
            }
    }

    public void afterGuessHidden() {
        delta = 0D;

        for (int i = 0; i < outputs.size(); i++) {
            delta += outputs.get(i).getWeightedDelta();

        }

        for (int i = 0; i < inputs.size(); i++) {
            Double deltaW = Perceptron.LEARNING_RATE * delta * (1 - outputValue) * outputValue * inputs.get(i).in.getOutputValue();
            inputs.get(i).weight = inputs.get(i).weight + deltaW;

        }
    }
}
