package NeuralNet;

import java.util.*;

/**
 * workflow of the network
 * 1)setInput
 * 2)setOutput
 * 3)build the net scheme
 * 4)iterate
 * Created by Senpai on 19.03.2016.
 */
public class NeuralNet {
    public ArrayList<ArrayList<Node>> netLayers;
    public static int ID = 0;
    private float outputLayerTmp[];
    public static float bias = 0.2f;
    private Queue<Node> nodeWeightUpdateQueue;
    private float learningStep = 1f;


    public NeuralNet(){
        netLayers = new ArrayList<ArrayList<Node>>();
        nodeWeightUpdateQueue =  new LinkedList<>();
    };


    public void setInputLayer(float values[]){
        netLayers.add(new ArrayList<Node>());

        for (int i = 0; i < values.length; i++)
            netLayers.get(0).add(new Node(values[i]));
    };


    public void setOutputLayer(float values[]){
        outputLayerTmp = values;
    };


    public void buildGenericNetwork(int middleLayerSize, int middleLayerDepth){
        for (int i = 0; i < middleLayerDepth; i++)
            netLayers.add(new ArrayList<Node>());

        initOutputLayer();
        initGenericHiddenLayer(middleLayerSize);
        initGenericConnections();
        initWightUpdateQueue();
    }

    private void initWightUpdateQueue() {
        for (int i = 1; i < netLayers.size(); i++)
            for (int j = 0; j < netLayers.get(i).size(); j++)
                nodeWeightUpdateQueue.add(netLayers.get(i).get(j));
    };

    private void initGenericConnections() {
        for(int i = 1; i < netLayers.size(); i++)
            for (int j = 0; j < netLayers.get(i).size(); j++)
                for (int l = 0; l < netLayers.get(i-1).size(); l++) {
                    netLayers.get(i).get(j).connectedNodes.add(
                            netLayers.get(i -1).get(l)
                    );
                    netLayers.get(i).get(j).connectedNodesWeights.add(0.01f);
                }
    };

    private void initGenericHiddenLayer(int middleLayerSize) {
        for (int i = 1; i <= netLayers.size()-2; i++) {
            for (int j = 0; j < middleLayerSize; j++)
                netLayers.get(i).add(new Node(0.01f));
        }
    };


    private void initOutputLayer() {
        netLayers.add(new ArrayList<Node>());
        for (float f : outputLayerTmp)
            netLayers.get(netLayers.size()-1).add(new Node(f));
    };

    public double getError(){
        double result = 0f;

        double lastLayerData[] = new double[netLayers.get(netLayers.size()-1).size()];

        for (int i = 0; i < netLayers.get(netLayers.size()-1).size(); i++) {
            double value = netLayers.get(netLayers.size() - 1).get(i).getValue();
            double resultTmp = netLayers.get(netLayers.size() - 1).get(i).getResult();
            lastLayerData[i] = Math.pow((value - result), 2);
            //System.out.println("level " + i +", error: " + lastLayerData[i]);
        }

        for (double d : lastLayerData)
            result += d;
        result /= netLayers.get(netLayers.size()-1).size();
        //System.out.println("total error: " + result);

        return result;
    };

    public void iterate(){
        double oldError = getError();
        System.out.println(oldError);

        Node node = nodeWeightUpdateQueue.poll();
        System.out.println(node.getID());
        for (int i = 0; i < node.connectedNodesWeights.size(); i++) {
            float oldWeight = node.connectedNodesWeights.get(i);


            float newWeight = oldWeight + learningStep;
            node.connectedNodesWeights.remove(i);
            node.connectedNodesWeights.add(i, newWeight);
            System.out.println(node.connectedNodesWeights);
            if (getError() < oldError)
                System.out.println(oldError + " " + getError());
            else {
                if (getError() > oldError) {
                    //node.connectedNodesWeights.remove(i);
                    //node.connectedNodesWeights.add(i, oldWeight);
                    newWeight = oldWeight - learningStep;
                    node.connectedNodesWeights.remove(i);
                    node.connectedNodesWeights.add(i, newWeight);
                } else if (getError() > oldError) {
                    node.connectedNodesWeights.remove(i);
                    node.connectedNodesWeights.add(i, oldWeight);
                }

            }

        }
        nodeWeightUpdateQueue.add(node);/*
        for (int i = 0; i < nodeWeightUpdateQueue.size(); i++){
            Node n = nodeWeightUpdateQueue.poll();
            System.out.println(n.getID());
            nodeWeightUpdateQueue.add(n);
        }*/
    };
};
