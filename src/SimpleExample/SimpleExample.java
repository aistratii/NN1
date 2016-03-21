package SimpleExample;

/**
 * Created by Senpai on 22.03.2016.
 */
public class SimpleExample {
    double values[][], weights[][];
    int width = 3, height = 4;
    double inputValues[], outputValues[];
    {
        inputValues = new double[]{0.5d, 1.0d, 1.5d, 2.0d};
        outputValues = new double[]{0.0087d, 0.1745d, 0.2617d, 0.3489d};

        values = new double[width][height];
        weights = new double[width][height];

        values[0][0] = 0.5d;
        values[0][1] = 1.0d;
        values[0][2] = 1.5d;
        values[0][3] = 2.0d;

        values[width -1][0] = 0.0087d;
        values[width -1][1] = 0.1745d;
        values[width -1][2] = 0.2617d;
        values[width -1][3] = 0.3489d;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                weights[i][j] = 0.001f;
    }

    double calculateForNode(int i, int j){
        double sum = 0d;
        for (int k = 0; k < )
    }


}
