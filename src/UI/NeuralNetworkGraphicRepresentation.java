package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import NeuralNet.*;

/**
 * Created by Senpai on 19.03.2016.
 */
public class NeuralNetworkGraphicRepresentation extends JFrame {
    private NeuralNet net;
    private ArrayList<NodeGraphicInformationContainer> nodeGraphicInformationContainers;
    private int borderDistance = 100;
    private int circleDiameter = 20;
    int drawAreaWidth;
    int drawAreaHeight;
    int horrizontalDistanceBetweenNodes;

    public NeuralNetworkGraphicRepresentation(NeuralNet net){
        super();
        setSize(500,500);
        this.net = net;
        nodeGraphicInformationContainers = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
        revalidate();
    };


    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        nodeGraphicInformationContainers = new ArrayList<>();
        drawAreaWidth = getWidth() - borderDistance*2;
        drawAreaHeight = getHeight() - borderDistance*2;
        horrizontalDistanceBetweenNodes = (drawAreaWidth - circleDiameter*net.netLayers.size())/net.netLayers.size();

        int offsetX = borderDistance;
        for (int i = 0; i < net.netLayers.size(); i++) {
            int verticalDistanceBetweenNodes = (drawAreaHeight - circleDiameter*net.netLayers.get(i).size())/net.netLayers.get(i).size();
            for (int j = 0; j < net.netLayers.get(i).size(); j++) {
                int offsetY = borderDistance + j * (verticalDistanceBetweenNodes + circleDiameter);
                g.drawOval(offsetX, offsetY, circleDiameter, circleDiameter);
            }
            offsetX += horrizontalDistanceBetweenNodes + circleDiameter;
            System.out.println("Layer " + i + " nodes: " + net.netLayers.get(i).size());
        };
    };


    //Inner class
    class NodeGraphicInformationContainer{
        private Node node;
        int x, y;
        NodeGraphicInformationContainer(Node node, int x, int y){
            this.node = node;
            this.x = x;
            this.y = y;
        };
    };
};
