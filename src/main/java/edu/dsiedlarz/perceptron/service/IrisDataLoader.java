package edu.dsiedlarz.perceptron.service;

import edu.dsiedlarz.perceptron.model.IrisData;
import edu.dsiedlarz.perceptron.model.NeuronDataInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IrisDataLoader {

    String fileName;

    public IrisDataLoader(String fileName) {
        this.fileName = fileName;
    }

    public List<IrisData> loadData() {
         return new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName)))
                 .lines().map(IrisData::ofLine).collect(Collectors.toList());

    }

    public List<IrisData> normalize(List<IrisData> values) {

        List<IrisData> normalizedData = new ArrayList<>();
        final Double[] min = {Double.MAX_VALUE};
        final Double[] max = {Double.MIN_VALUE};


        values.forEach(singleValue -> {
            singleValue.values.forEach(x -> {

                if (x < min[0]) min[0] = x;
                if (x > max[0]) max[0] = x;
                }
            );
        });

        values.forEach(singleValue -> {
            normalizedData.add(new IrisData(
                    (singleValue.values.get(0) - min[0]) / (max[0] - min[0]),
                    (singleValue.values.get(1) - min[0]) / (max[0] - min[0]),
                    (singleValue.values.get(2) - min[0]) / (max[0] - min[0]),
                    (singleValue.values.get(3) - min[0]) / (max[0] - min[0]),
                singleValue.name
            ));
        });

return normalizedData;
//        return values.stream().map(x -> new NeuronDataInput((x - min[0]) / (max[0] - min[0]))).collect(Collectors.toList());
    }
}
