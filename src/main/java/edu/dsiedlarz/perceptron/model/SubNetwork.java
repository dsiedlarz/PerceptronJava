package edu.dsiedlarz.perceptron.model;

import edu.dsiedlarz.perceptron.model.neuron.AbstractNeuron;
import edu.dsiedlarz.perceptron.model.neuron.ProcessingNeuron;

import java.util.ArrayList;
import java.util.List;

public class SubNetwork {

    public List<ProcessingNeuron> inputNeurons;
    public List<ProcessingNeuron> outputNeurons;


    public SubNetwork(int inputNeuronsCount,  int outputNeuronCount) {
        inputNeurons = new ArrayList<>();
        outputNeurons = new ArrayList<>();

        for (int i = 0; i < inputNeuronsCount ; i++) {
            inputNeurons.add(new ProcessingNeuron());
        }

        for (int i = 0; i < outputNeuronCount ; i++) {
            outputNeurons.add(new ProcessingNeuron());
        }


        for (int i = 0; i < inputNeuronsCount ; i++) {
            for (int j = 0; j < outputNeuronCount; j++) {
                new WeightedConnection(this.inputNeurons.get(i), this.outputNeurons.get(j));
            }
        }
    }

    public void propagationPhase() {
        inputNeurons.forEach(AbstractNeuron::computeWeightSumFromInputData);
        outputNeurons.forEach(AbstractNeuron::computeWeightSumFromInputData);
    }

    public List<ProcessingNeuron> getOutputNeurons() {
        return outputNeurons;
    }

    public void backpropagationPhase() {
        outputNeurons.forEach(ProcessingNeuron::afterGuess);
        inputNeurons.forEach(ProcessingNeuron::afterGuessHidden);
    }
}
