package edu.dsiedlarz.perceptron.model;

import edu.dsiedlarz.perceptron.model.neuron.AbstractNeuron;
import edu.dsiedlarz.perceptron.model.neuron.Bias;
import edu.dsiedlarz.perceptron.model.neuron.ProcessingNeuron;

import java.util.ArrayList;
import java.util.List;

public class SubNetwork {

    List<SubNetwork> previousSubnetworks = new ArrayList<>();

    public double guessedValues = 0;
    public double tries = 0;


    public List<AbstractNeuron> inputNeurons;
    public List<AbstractNeuron> outputNeurons;


    public SubNetwork(int inputNeuronsCount,  int outputNeuronCount) {
        inputNeurons = new ArrayList<>();
        outputNeurons = new ArrayList<>();

        for (int i = 0; i < inputNeuronsCount ; i++) {
            inputNeurons.add(new ProcessingNeuron());
        }

        for (int i = 0; i < outputNeuronCount ; i++) {
            outputNeurons.add(new ProcessingNeuron());
        }

        inputNeurons.add(new Bias());

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

    public List<AbstractNeuron> getOutputNeurons() {
        return outputNeurons;
    }

    public void backpropagationPhase() {
        outputNeurons.forEach(AbstractNeuron::afterGuess);
        inputNeurons.forEach(AbstractNeuron::afterGuessHidden);
//        this.previousSubnetworks.forEach(SubNetwork::nextBackbprobagation);
    }

    public void nextBackbprobagation() {
        outputNeurons.forEach(AbstractNeuron::afterGuessHidden);
        inputNeurons.forEach(AbstractNeuron::afterGuessHidden);
        this.previousSubnetworks.forEach(SubNetwork::nextBackbprobagation);
    }


}
