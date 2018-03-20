package edu.dsiedlarz.perceptron.model;

import java.util.ArrayList;
import java.util.List;

public class SubNetwork {

    public List<Neuron> inputNeurons;
    public List<Neuron> outputNeurons;


    public SubNetwork(int inputNeuronsCount, int inputsPerNeuron,  int outputNeuronCount) {
        inputNeurons = new ArrayList<>();
        outputNeurons = new ArrayList<>();

        for (int i = 0; i < inputNeuronsCount ; i++) {
            Neuron neuron = new Neuron("Hidden #"+i, inputsPerNeuron);

            inputNeurons.add(neuron);
        }

        for (int i = 0; i < outputNeuronCount ; i++) {
            outputNeurons.add(new Neuron("Output #" + i, this.inputNeurons, 0));
        }


        for (int i = 0; i < inputNeuronsCount ; i++) {
            inputNeurons.get(i).outputs.addAll(outputNeurons);
        }
    }

    public void propagationPhase(List<NeuronDataInput> neuronDataInputs) {
        inputNeurons.forEach(inputNeuron -> {
            inputNeuron.computeWeightSumFromInputData(neuronDataInputs);
            System.out.println(inputNeuron.weights);
        });
        System.out.println();

        outputNeurons.forEach(Neuron::computeWeightSumFromInputNeurons);
        outputNeurons.forEach(x-> System.out.println(x.weights));
    }

    public List<Neuron> getOutputNeurons() {
        return outputNeurons;
    }

    public void backpropagationPhase() {
        outputNeurons.forEach(Neuron::afterGuess);
        inputNeurons.forEach(Neuron::afterGuessHidden);
    }
}
