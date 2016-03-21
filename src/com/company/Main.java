package com.company;

import UI.NeuralNetworkGraphicRepresentation;
import NeuralNet.*;

public class Main {

    public static void main(String[] args) {
        NeuralNet net = new NeuralNet();
        net.setInputLayer(new float[]{0.5f, 1.0f, 1.5f, 2f});
        net.setOutputLayer(new float[]{0.0087f, 0.1745f, 0.2617f, 0.3489f});
        net.buildGenericNetwork(5, 1);
        new NeuralNetworkGraphicRepresentation(net);

        System.out.println(net.getError());
    }
}
