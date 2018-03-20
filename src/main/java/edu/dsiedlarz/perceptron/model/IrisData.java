package edu.dsiedlarz.perceptron.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IrisData {

    List<Double> values = new ArrayList<>();

    public final String name;

    private IrisData(Double var1, Double var2, Double var3, Double var4, String name) {
        values.add(var1);
        values.add(var2);
        values.add(var3);
        values.add(var4);
        this.name = name;
    }

    public static IrisData ofLine(String line) {
        String[] values = line.split(",");
        return new IrisData(
                Double.valueOf(values[0]),
                Double.valueOf(values[1]),
                Double.valueOf(values[2]),
                Double.valueOf(values[3]),
                values[4]
        );
    }

    public List<NeuronDataInput> getNeuronDataInputs() {

        return values.stream().map(NeuronDataInput::new).collect(Collectors.toList());
//
//        final Double[] min = {Double.MAX_VALUE};
//        final Double[] max = {Double.MIN_VALUE};

//        values.forEach(x -> {
//            if (x < min[0]) min[0] = x;
//            if (x > max[0]) max[0] = x;
//        });
//
//        return values.stream().map(x -> new NeuronDataInput((x - min[0]) / (max[0] - min[0]))).collect(Collectors.toList());
    }
}
