package edu.dsiedlarz.perceptron.model;

import edu.dsiedlarz.perceptron.model.data.IrisData;
import edu.dsiedlarz.perceptron.model.neuron.AbstractNeuron;
import edu.dsiedlarz.perceptron.model.neuron.Bias;
import edu.dsiedlarz.perceptron.model.neuron.DataNeuron;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    public static double LEARNING_RATE = 0.1;
    private List<IrisData> inputData = new ArrayList<>();
    private List<SubNetwork> subNetworks = new ArrayList<>();
    private List<AbstractNeuron> dataNeurons = new ArrayList<>();

    public Perceptron(List<IrisData> inputData) {

        this.inputData = inputData;

        dataNeurons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dataNeurons.add(new DataNeuron());
        }
        dataNeurons.add(new Bias());

        for (int i = 0; i < 1; i++) {
            this.subNetworks.add(createSubNetwork());

        }
    }

    public SubNetwork createSubNetwork() {
        SubNetwork subNetwork=  new SubNetwork(4, 3);


        for (int i = 0; i < this.dataNeurons.size() ; i++) {
            for (int j = 0; j < subNetwork.inputNeurons.size(); j++) {
                new WeightedConnection(dataNeurons.get(i), subNetwork.inputNeurons.get(j));
            }
        }

        for (int i = 0; i < subNetworks.size(); i++) {
            for (int j = 0; j < subNetworks.get(i).outputNeurons.size() ; j++) {
                for (int k = 0; k < subNetwork.inputNeurons.size(); k++) {
                    new WeightedConnection(subNetworks.get(i).outputNeurons.get(j), subNetwork.inputNeurons.get(j));
                }
            }
        }

        subNetwork.previousSubnetworks.addAll(this.subNetworks);
        return subNetwork;
    }

    public List<Double> learn() {

        subNetworks.forEach(s -> {s.tries =0; s.guessedValues = 0;});
        List<Double> efficiences = new ArrayList<>();
        inputData.forEach(input -> {

            for (int i = 0; i < input.values.size(); i++) {
                dataNeurons.get(i).outputValue = input.values.get(i);
            }

            for (int i = 0; i < this.subNetworks.size() ; i++) {

            SubNetwork subNetwork = subNetworks.get(i);

            subNetwork.propagationPhase();
            List<AbstractNeuron> outputs = subNetwork.getOutputNeurons();

            subNetwork.tries++;
            int highestIndex = 0;
            Double maxValue = Double.MIN_VALUE;
            for (int j = 0; j < subNetwork.outputNeurons.size(); j++) {
                Double outputValue = outputs.get(j).getOutputValue();
                if (outputValue > maxValue) {
                    maxValue = outputValue;
                    highestIndex = j;
                }
            }

            subNetwork.outputNeurons.forEach(neuron -> {

                    }
            );

            switch (input.name) {
                case "Iris-setosa":

                    if (highestIndex == 0) {
//                        System.out.println("Succses");
                        subNetwork.guessedValues++;
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
                        subNetwork.guessedValues++;


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
                        subNetwork.guessedValues++;

                    } else {
//                        System.out.println("Failure");
                    }
                    outputs.get(2).setDelta(1d - outputs.get(2).getOutputValue());
                    outputs.get(0).setDelta(0D - outputs.get(0).getOutputValue());
                    outputs.get(1).setDelta(0D - outputs.get(1).getOutputValue());
                    break;
            }

            subNetwork.backpropagationPhase();
            }
        });
        for (int i = 0; i < this.subNetworks.size() ; i++) {
        SubNetwork subNetwork = subNetworks.get(i);
        efficiences.add(subNetwork.guessedValues / subNetwork.tries);
            System.out.println("Efficiency " + i +" is " + (subNetwork.guessedValues / subNetwork.tries));
        }

        return efficiences;
    }
}
