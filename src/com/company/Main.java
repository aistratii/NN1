package com.company;

import UI.NeuralNetworkGraphicRepresentation;
import NeuralNet.*;

public class Main {

    public static void main(String[] args) {
        NeuralNet net = new NeuralNet();
        net.setInputLayer(new float[]{0.5f, 1.0f/*, 1.5f, 2f*/});
        net.setOutputLayer(new float[]{0.0087f, 0.1745f/*, 0.2617f, 0.3489f*/});
        net.buildGenericNetwork(2, 1);
        new NeuralNetworkGraphicRepresentation(net);

        /*
        for (int i = 0; i < net.netLayers.size(); i++)
            for (int j = 0; j < net.netLayers.get(i).size(); j++)
                //for (Node n : net.netLayers.get(i).get(j).connectedNodes)
                //System.out.println(net.netLayers.get(i).get(j).getID() +" " +  n.getID());
                System.out.println(net.netLayers.get(i).get(j).getID() +" " +  net.netLayers.get(i).get(j).getResult());
        */

        for (int k = 0; k < 20; k++) {
            net.iterate();
            System.out.println(net.getError());

            for (int i = 0; i < net.netLayers.size(); i++)
                for (int j = 0; j < net.netLayers.get(i).size(); j++)
                    //for (Node n : net.netLayers.get(i).get(j).connectedNodes)
                    //System.out.println(net.netLayers.get(i).get(j).getID() +" " +  n.getID());
                    System.out.println(net.netLayers.get(i).get(j).getID() +" " +  net.netLayers.get(i).get(j).getResult());
        }

    }
}
