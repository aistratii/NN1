package NeuralNet;

import java.util.ArrayList;

/**
 * Created by Senpai on 19.03.2016.
 */
public class Node {
    private int ID = 0;
    private float value = 0f;
    public ArrayList<Node> connectedNodes;
    public ArrayList<Float> connectedNodesWeights;

    public Node(float value){
        this.value = value;
        connectedNodes = new ArrayList<Node>();
        connectedNodesWeights = new ArrayList<Float>();
        this.ID = NeuralNet.ID;
        NeuralNet.ID++;
    };

    public void setValue(float value){
        this.value = value;
    };

    public float getValue(){
        return value;
    };

    public float getActivationFunctionResult(){
        float result = 0f;
        for (int i = 0; i < connectedNodes.size(); i++){
            result += connectedNodes.get(i).getValue()*connectedNodesWeights.get(i);
        }
        result = activationFunction(result);
        return result;
    };

    private float activationFunction(float result){
        return (float) (1.0f/(1.0f*Math.exp(-result)));
    };


    public float getActivationFunctionValue(){
        float result = 0f;

        for (int i = 0; i < connectedNodes.size(); i++){

        }

        return result;
    };
};
