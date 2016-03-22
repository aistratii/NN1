package proofofconcet;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
/**
 * Created by aistratii on 22/3/2016.
 */
public class ProofOfConcept {
    static int width = 3;
    static int height = 4;
    static double input[] = new double[]{0.5d, 1.0d, 1.5d, 2.0d};
    static double output[] = new double[]{0.0087f, 0.1745f, 0.2617f, 0.3489f};
    static double hidden[] = new double[height];
    static double weights[][] = new double[height][height*2];
    static double vectorOfDescent[][] = new double[height][height*2];
    static double step = 0.000005d;
    static double bias = 0.5d;
    static Queue<Entry<Integer, Integer>> weightQueue;

    public static void main(String args[]){
        initWeights();
        initWeightQueue();
        calculateHidden();
        initVectorOfDescent();

        for (int i = 0; i < 1000; i++)
            iterate();

        //testNetWithOtherResult(1.0d);
        System.out.println(Arrays.toString(getCalculatedOutput()));
    }

    static void calculateHidden(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < height; j++)
                hidden[i] += input[j] * weights[j][i];
            //hidden[i] += bias;
            hidden[i] = 1.0d/(1.0d + Math.exp(-hidden[i]));
        }
    };

    static double[] getCalculatedOutput(){
        double result[] = new double[height];

        for (int i = 0; i < height; i++){
            for (int j = 0; j < height; j++)
                result[i] += hidden[j] * weights[j][i + height];
                //result[i] += bias;
                result[i] = 1.0d/(1.0d + Math.exp(-result[i]));
        }

        return result;
    };


    private static void initWeightQueue() {
        weightQueue = new LinkedList<>();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < height*2; j++) {
                Entry<Integer, Integer> v = new SimpleEntry<Integer, Integer>(i, j);
                weightQueue.add(v);
            }
    };


    private static void iterate() {
        Entry<Integer, Integer> weightAdress = weightQueue.poll();
        double oldWeight = weights[weightAdress.getKey()][weightAdress.getValue()];
        double oldError = getError();

        //try add
        double newWeight = oldWeight + vectorOfDescent[weightAdress.getKey()][weightAdress.getValue()]*step;
        weights[weightAdress.getKey()][weightAdress.getValue()] = newWeight;
        double newError = getError();
        if (newError < oldError){
            System.out.println("changed weight: " + oldWeight +" => "+ newWeight);
            System.out.print("minimized error from: " + oldError +" => " + newError);
            System.out.println(" at [" + weightAdress.getKey() +", " + weightAdress.getValue() +"]");
        } else {
            vectorOfDescent[weightAdress.getKey()][weightAdress.getValue()] *= -1;
            System.out.print("changed descent vector direction");
            System.out.println(" at [" + weightAdress.getKey() +", " + weightAdress.getValue() +"]");
        }

        weightQueue.add(weightAdress);
    };

    public static double getError() {
        double error = 0.0d;

        calculateHidden();
        double resultTmp[] = getCalculatedOutput();

        for (int i = 0; i < resultTmp.length; i++)
            error += Math.pow((output[i] - resultTmp[i]), 2);

        //error /= height;
        return error;
    };


    private static void initWeights() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < height*2; j++)
                weights[i][j] = 0.1d;
    };

    private static void initVectorOfDescent() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < height*2; j++)
                vectorOfDescent[i][j] = 1;
    };

    private static void testNetWithOtherResult(double testValue){
        double testInput[] = new double[height];
        double testOutput[] = new double[height];
        testInput[0] = testValue;

        //calculate hidden
        for (int i = 0; i < height; i++){
            for (int j = 0; j < height; j++)
                hidden[i] += testInput[j] * weights[j][i];
            //hidden[i] += bias;
            hidden[i] = 1.0d/(1.0d + Math.exp(hidden[i]));
        }

        //calculate output
        for (int i = 0; i < height; i++){
            for (int j = 0; j < height; j++)
                testOutput[i] += hidden[j] * weights[j][i + height];
            //testOutput[i] += bias;
            testOutput[i] = 1.0d/(1.0d + Math.exp(testOutput[i]));
        }

        System.out.println("input = " + testValue +", output = "+ testOutput[0]);

    };

};
