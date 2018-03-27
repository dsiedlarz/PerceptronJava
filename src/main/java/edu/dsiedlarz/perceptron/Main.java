package edu.dsiedlarz.perceptron;

import edu.dsiedlarz.perceptron.model.IrisData;
import edu.dsiedlarz.perceptron.model.Perceptron;
import edu.dsiedlarz.perceptron.service.IrisDataLoader;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IrisDataLoader irisDataLoader = new IrisDataLoader("Iris.csv");
        List<IrisData> inputs = irisDataLoader.loadData();
        inputs = irisDataLoader.normalize(inputs);

        Collections.shuffle(inputs);

        Perceptron perceptron = new Perceptron(inputs);

        for (int i = 0; i  < 1000 ; i++) {
            perceptron.learn();
            Perceptron.LEARNING_RATE = Perceptron.LEARNING_RATE * 0.999D;
        }
    }
}
