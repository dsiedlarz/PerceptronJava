package edu.dsiedlarz.perceptron;

import edu.dsiedlarz.perceptron.model.data.IrisData;
import edu.dsiedlarz.perceptron.model.Perceptron;
import edu.dsiedlarz.perceptron.model.data.IrisDataLoader;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IrisDataLoader irisDataLoader = new IrisDataLoader("Iris.csv");
        List<IrisData> inputs = irisDataLoader.loadData();
        inputs = irisDataLoader.normalize(inputs);

        Collections.shuffle(inputs);

        Perceptron perceptron = new Perceptron(inputs);

        for (int i = 0; i  < 1000000 ; i++) {

            List<Double> efficiences = perceptron.learn();
            for (int j = 0; j <  efficiences.size(); j++) {
                if (efficiences.get(j) == 1) {
                    System.out.println(i); return;}
            }

            if (i % 100 == 0) {
                Perceptron.LEARNING_RATE = Perceptron.LEARNING_RATE * 0.9999999999999D;
            }
        }
    }
}
