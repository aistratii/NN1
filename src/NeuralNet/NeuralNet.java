package NeuralNet;

import java.util.ArrayList;

/**
 * Created by Senpai on 19.03.2016.
 */
public class NeuralNet {
    public ArrayList<Node> inputLayer;
    public ArrayList<Node> outputLayer;
    public ArrayList<ArrayList<Node>> middleLayer;
    public static int ID = 0;

    public NeuralNet(){
        inputLayer = new ArrayList<Node>();
        outputLayer = new ArrayList<Node>();
        middleLayer = new ArrayList<ArrayList<Node>>();
    };

    public void setInputLayer(float values[]){
        for (int i = 0; i < values.length; i++)
            inputLayer.add(new Node(values[i]));
    };

    public void setOutputLayer(float values[]){
        for (int i = 0; i < values.length; i++)
            outputLayer.add(new Node(values[i]));
    };

    public void buildGenericNetwork(int middleLayerSize, int middleLayerDepth){
        for (int i = 0; i < middleLayerDepth; i++)
            middleLayer.add(new ArrayList<Node>());

        for (ArrayList<Node> nodeList : middleLayer)
            for (int i = 0; i < middleLayerSize; i++)
                nodeList.add(new Node(0.01f));

        //establish link between first midLayer and inputLayer
        for (Node middleNode : middleLayer.get(0))
            for (Node inputNode : inputLayer){
                middleNode.connectedNodes.add(inputNode);
                middleNode.connectedNodesWeights.add(0.01f);
            };

        //establish link between midLayer and outputLayer
        for (Node ouptutNode : outputLayer)
            for (Node middleNode : middleLayer.get(middleLayer.size()-1)){
                ouptutNode.connectedNodes.add(middleNode);
                ouptutNode.connectedNodesWeights.add(0.01f);
            };

        //establish link between middle layers
        for (int i = 1; i < middleLayerDepth; i++)
            for (int l = 0; l < middleLayerSize; l++)
                for (int j = 0; j < middleLayerSize; j++)
                    middleLayer.get(i).get(l).connectedNodes.add(middleLayer.get(i-1).get(j));
    };

    public int getMaxLayerSize(){
        return Math.max(
                Math.max(inputLayer.size(), middleLayer.size()),
                outputLayer.size());
    };
};
