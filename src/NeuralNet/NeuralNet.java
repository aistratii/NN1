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
        initGenericHiddenLayer(middleLayerSize);
        initGenericConnections();
    };

    private void initGenericConnections() {
        for(int i = 0; i < netLayers.size() -1; i++)
            for (int j = 0; j < netLayers.get(i).size(); j++)
                for (int l = 0; l < netLayers.get(i+1).size(); l++) {
                    netLayers.get(i).get(j).connectedNodes.add(
                            netLayers.get(i + 1).get(l)
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


    public float getError(){
        float result = 0f;

        for (int i = 0; i < netLayers.size(); i++)
            return result;
    };
};
