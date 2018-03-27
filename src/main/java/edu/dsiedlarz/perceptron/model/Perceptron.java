package edu.dsiedlarz.perceptron.model;

import edu.dsiedlarz.perceptron.model.neuron.AbstractNeuron;
import edu.dsiedlarz.perceptron.model.neuron.Bias;
import edu.dsiedlarz.perceptron.model.neuron.DataNeuron;
import edu.dsiedlarz.perceptron.model.neuron.ProcessingNeuron;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    public static double LEARNING_RATE = 0.1;
    private List<IrisData> inputData;
    private SubNetwork subNetwork;
    private List<AbstractNeuron> dataNeurons;

    private double guessedValues = 0;
    private double tries = 0;

    public Perceptron(List<IrisData> inputData) {

        this.inputData = inputData;
        this.subNetwork = new SubNetwork(4, 3);
        dataNeurons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dataNeurons.add(new DataNeuron());
        }
        dataNeurons.add(new Bias());


        for (int i = 0; i < this.dataNeurons.size() ; i++) {
            for (int j = 0; j < this.subNetwork.inputNeurons.size(); j++) {
            new WeightedConnection(dataNeurons.get(i), this.subNetwork.inputNeurons.get(j));
            }
        }
    }

    public void learn() {
        guessedValues = 0;
        tries = 0;

        inputData.forEach(input -> {

            for (int i = 0; i < input.values.size(); i++) {
                dataNeurons.get(i).outputValue = input.values.get(i);
            }

            subNetwork.propagationPhase();
            List<AbstractNeuron> outputs = subNetwork.getOutputNeurons();

            tries++;
            int highestIndex = 0;
            Double maxValue = Double.MIN_VALUE;
            for (int i = 0; i < outputs.size(); i++) {
                Double outputValue = outputs.get(i).getOutputValue();
                if (outputValue > maxValue) {
                    maxValue = outputValue;
                    highestIndex = i;
                }
            }

            switch (input.name) {
                case "Iris-setosa":

                    if (highestIndex == 0) {
//                        System.out.println("Succses");
                        guessedValues++;
                    } else {
//                        System.out.println("Failure");

                    }
                    outputs.get(0).setDelta(1D - outputs.get(0).getOutputValue());
                    outputs.get(1).setDelta(0D - outputs.get(1).getOutputValue());
                    outputs.get(2).setDelta(0D - outputs.get(2).getOutputValue());
                    break;

                case "Iris-versicolor":


                    if (highestIndex == 1) {
//                        System.out.println("Succses");
                        guessedValues++;


                    } else {
//                        System.out.println("Failure");

                    }
                    outputs.get(1).setDelta(1d - outputs.get(1).getOutputValue());
                    outputs.get(0).setDelta(0D - outputs.get(0).getOutputValue());
                    outputs.get(2).setDelta(0D - outputs.get(2).getOutputValue());
                    break;

                case "Iris-virginica":

                    if (highestIndex == 2) {
//                        System.out.println("Succses");
                        guessedValues++;

                    } else {
//                        System.out.println("Failure");
                    }
                    outputs.get(2).setDelta(1d - outputs.get(2).getOutputValue());
                    outputs.get(0).setDelta(0D - outputs.get(0).getOutputValue());
                    outputs.get(1).setDelta(0D - outputs.get(1).getOutputValue());
                    break;
            }

            subNetwork.backpropagationPhase();
        });

        System.out.println("Efficiency is " + (guessedValues / tries));
    }
}
