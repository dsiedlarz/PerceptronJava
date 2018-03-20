package edu.dsiedlarz.perceptron.model;

public class NeuronDataInput implements NeuronInput  {

    private Double data;

    public NeuronDataInput(Double data) {
        this.data = data;
    }

    @Override
    public Double getOutput() {

//       return 1 / (1+Math.pow(Math.E, -data));
        return data;

    }
}

