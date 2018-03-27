package edu.dsiedlarz.perceptron.model.neuron;

public class Bias extends AbstractNeuron{
    @Override
    public Double getOutputValue() {
        return 1D;
    }

    @Override
    public void afterGuess() {

    }

    @Override
    public void afterGuessHidden() {

    }
}
