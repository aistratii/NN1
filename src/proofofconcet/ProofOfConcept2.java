package proofofconcet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.*;
import java.util.Queue;
import java.util.AbstractMap.SimpleEntry;

/**
 * Created by aistratii on 22/3/2016.
 */
public class ProofOfConcept2 {
    static double input[] = new double[]{0.5d, 1.0d, 1.5d, 2.0d};
    static double output[] = new double[]{0.0087f, 0.1745f, 0.2617f, 0.3489f};
    static int width = 10;
    static int height = 4;
    static double weights[][] = new double[height*width][height];
    static Queue<Map.Entry<Integer, Integer>> weightQueue;
    static double directionOfDescent[][] = new double[height*width][height];
    static double step = 0.000001d;
    static double predicted[][];

    public static void main(String [] args){
        initWeights();
        initWeightQueue();
        initDirectionOfDescent();



        for (int i = 0; i < 1000000; i++) {
            iterate();
        }

        input = new double[]{0.75d, 0d, 0d, 0d};
        predict();
        System.out.print(Arrays.toString(predicted[width-1]));

    }


    private static void iterate() {
        Entry<Integer, Integer> coords = weightQueue.poll();
        predict();
        double oldError = getError();

        double oldWeight = weights[coords.getKey()][coords.getValue()];
        double newWeight = oldWeight + oldWeight*directionOfDescent[coords.getKey()][coords.getValue()];
        weights[coords.getKey()][coords.getValue()] = newWeight;
        predict();

        double newError = getError();
        if (newError > oldError) {
            directionOfDescent[coords.getKey()][coords.getValue()] *= -1;
            System.out.println("Changed direction of descent at [" + coords.getKey() +", "+ coords.getValue() +"]" );
            weights[coords.getKey()][coords.getValue()] = oldWeight;
            return;
        }
        else if (newError < oldError) {
            System.out.println(oldError > newError);
            System.out.println("error minimized from " + oldError + " => " + newError + ",  (" + (oldError - newError) + ")");
        }
        weightQueue.add(coords);
    };


    static private void predict(){
        predicted = new double[width][height];
        predicted[0] = Arrays.copyOf(input, input.length);

        int weightIndex = 0;
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < height; j++)
                predicted[i][j] += predicted[i-1][j] * weights[weightIndex][j];
            weightIndex++;
        }
    };

    static private void initWeights() {
        //weights = new double[height*width][height];
        for (int i = 0; i < height*width; i++)
            for (int j = 0; j < height; j++)
                weights[i][j] = 0.1d;
    };

    public static double getError() {
        double error = 0.0d;
        for (int i = 0; i < height; i++)
            error += Math.pow((output[i] - predicted[width-1][i]),2);

        return  error;
    };


    private static void initWeightQueue() {
        weightQueue = new LinkedList<>();

        for (int i = 0; i < height*width; i++)
            for (int j = 0; j < height; j++)
                weightQueue.add(new SimpleEntry<Integer, Integer>(i, j));
    };


    private static void initDirectionOfDescent() {
        for (int i = 0; i < height*width; i++)
            for (int j = 0; j < height; j++)
                directionOfDescent[i][j] = 1;
    };
};
