package NeuralNet;

import java.util.ArrayList;

/**
 * Created by Senpai on 19.03.2016.
 */
public class NeuralNet {
    public ArrayList<ArrayList<Node>> netLayers;
    public static int ID = 0;
    private float outputLayerTmp[];


    public NeuralNet(){
        netLayers = new ArrayList<ArrayList<Node>>();
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
        initHiddenLayer(middleLayerSize);
    };

    private void initHiddenLayer(int middleLayerSize) {
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
};
