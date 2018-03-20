package edu.dsiedlarz.perceptron.service;

import edu.dsiedlarz.perceptron.model.IrisData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

//    public void normalize
}
