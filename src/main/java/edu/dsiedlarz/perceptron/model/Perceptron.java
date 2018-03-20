package edu.dsiedlarz.perceptron.model;

import java.util.List;

public class Perceptron {

    public static double LEARNING_RATE = 0.01;
    private List<IrisData> inputData;
    private SubNetwork subNetwork;

    private double guessedValues = 0;
    private double tries = 0;

    public Perceptron(List<IrisData> inputData) {
        this.inputData = inputData;
        this.subNetwork = new SubNetwork(4, 4, 3);
    }

    public void learn() {
        guessedValues = 0;
        tries = 0;

        inputData.forEach(input -> {
            subNetwork.propagationPhase(input.getNeuronDataInputs());
            List<Neuron> outputs = subNetwork.getOutputNeurons();

            tries++;
            int highestIndex = 0;
            Double maxValue = Double.MIN_VALUE;
            for (int i = 0; i < outputs.size(); i++) {
                System.out.print(outputs.get(i).getOutput() + " ");
                Double outputValue = outputs.get(i).getOutput();
                if (outputValue > maxValue) {
                    maxValue = outputValue;
                    highestIndex = i;
                }
            }

            System.out.println();
            System.out.println();
            System.out.println();
            switch (input.name) {
                case "Iris-setosa":

                    if (highestIndex == 0) {
//                        System.out.println("Succses");
                        guessedValues++;
                    } else {
//                        System.out.println("Failure");

                    }
                    outputs.get(0).setDelta(1D - outputs.get(0).getOutput());
                    outputs.get(1).setDelta(0D - outputs.get(1).getOutput());
                    outputs.get(2).setDelta(0D - outputs.get(2).getOutput());
                    break;

                case "Iris-versicolor":


                    if (highestIndex == 1) {
//                        System.out.println("Succses");
                        guessedValues++;


                    } else {
//                        System.out.println("Failure");

                    }
                    outputs.get(1).setDelta(1d - outputs.get(1).getOutput());
                    outputs.get(0).setDelta(0D - outputs.get(0).getOutput());
                    outputs.get(2).setDelta(0D - outputs.get(2).getOutput());
                    break;

                case "Iris-virginica":

                    if (highestIndex == 2) {
//                        System.out.println("Succses");
                        guessedValues++;

                    } else {
//                        System.out.println("Failure");
                    }
                    outputs.get(2).setDelta(1d - outputs.get(2).getOutput());
                    outputs.get(0).setDelta(0D - outputs.get(0).getOutput());
                    outputs.get(1).setDelta(0D - outputs.get(1).getOutput());
                    break;
            }

            subNetwork.backpropagationPhase();
        });

        System.out.println("Efficiency is " + (guessedValues / tries));
    }
}
