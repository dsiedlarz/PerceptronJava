package edu.dsiedlarz.perceptron.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron implements NeuronInput {
    public final String name;
    private List<Neuron> inputs = new ArrayList<>();
    public List<Neuron> outputs = new ArrayList<>();
    public List<Double> weights = new ArrayList<>();
    public List<NeuronDataInput> dataInputs = new ArrayList<>();

    public Double weightedSum;
    private Double delta;
    private Double outputValue;

//    private Double bias;

    private Double beta = 1D;

    public Neuron(String name, int inputDataCount) {
        this.name = name;
        Random random = new Random();
//        bias = random.nextDouble() * 0.2 - 0.1;
        for (int i = 0; i < inputDataCount; i++) {
            weights.add(random.nextDouble() * 0.2 - 0.1);
        }
    }

    public Neuron(String name, List<Neuron> inputNeurons, int inputDataCount) {
        this.name = name;
        this.inputs = inputNeurons;

        Random random = new Random();

//        bias = random.nextDouble() * 0.2 - 0.1;

        for (int i = 0; i < inputNeurons.size() + inputDataCount; i++) {
            weights.add(random.nextDouble() * 0.2 - 0.1);
        }
    }


    public void computeWeightSumFromInputData(List<NeuronDataInput> neuronDataInputs) {
        this.dataInputs = neuronDataInputs;
        weightedSum = 0D;
        for (int i = 0; i < neuronDataInputs.size(); i++) {
            weightedSum += neuronDataInputs.get(i).getOutput() * weights.get(i);

//            System.out.println(String.format("%d hidden neuron: %f * %f = %f", i, neuronDataInputs.get(i).getOutput(), weights.get(i), neuronDataInputs.get(i).getOutput() * weights.get(i)));
        }
//        System.out.println("Hhidden neuron: WeightedSum = " + weightedSum);
//        weightedSum += bias;

        outputValue = 1 / (1 + Math.pow(Math.E, -weightedSum));

        System.out.println("Hhidden neuron: outputValue = " + outputValue);

    }

    public void computeWeightSumFromInputNeurons() {
        weightedSum = 0D;
        for (int i = 0; i < inputs.size(); i++) {
            weightedSum += inputs.get(i).getOutput() * weights.get(i);

//            System.out.println(String.format("%d output neuron: %f * %f = %f", i, inputs.get(i).getOutput(), weights.get(i), inputs.get(i).getOutput() * weights.get(i)));

        }

//        System.out.println("Output neuron: WeightedSum = " + weightedSum);

//        weightedSum += bias;

//        outputValue = Math.tanh(beta * weightedSum);

        outputValue = 1 / (1 + Math.pow(Math.E, -weightedSum));
        System.out.println("Output neuron: outputValue = " + outputValue);

    }

    @Override
    public Double getOutput() {

        return this.outputValue;
    }

    public void afterGuess() {

        for (int i = 0; i < inputs.size(); i++) {
            Double deltaW = Perceptron.LEARNING_RATE * delta * (1 - outputValue) * outputValue * inputs.get(i).getOutput();

            weights.set(i, weights.get(i) + deltaW);
        }

//        Double deltaW = -Perceptron.LEARNING_RATE * delta * (1 - outputValue) * outputValue * 1;
//        bias = bias + deltaW;

    }

    public void afterGuessHidden() {
        delta = 0D;

        for (int i = 0; i < outputs.size(); i++) {
            delta += outputs.get(i).getWeightedDelta(this);

        }

        for (int i = 0; i < dataInputs.size(); i++) {
            Double deltaW = Perceptron.LEARNING_RATE * delta * (1 - outputValue) * outputValue * dataInputs.get(i).getOutput();

            weights.set(i, weights.get(i) + deltaW);
        }

//        Double deltaW = -Perceptron.LEARNING_RATE * delta * (1 - outputValue) * outputValue * 1;
//        bias = bias + deltaW;
    }

    private Double getWeightedDelta(Neuron neuron) {
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i) == neuron) {
                return delta * weights.get(i) * (1 - outputValue) * outputValue;
            }
        }
        return null;
    }



    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Double getDelta() {
        return delta;
    }

    @Override
    public String toString() {
        return weightedSum.toString();
    }
}
