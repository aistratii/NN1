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
    public static int inBetweenLayerDistance = 100;
    private int circleSize = 20;
    private ArrayList<NodeGraphicInformationContainer> nodeGraphicInformationContainers;

    public NeuralNetworkGraphicRepresentation(NeuralNet net){
        super();
        setSize(500,500);
        this.net = net;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
        revalidate();
    };


    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        int offsetX = 100;
        int offsetY = 22 + circleSize;
        nodeGraphicInformationContainers = new ArrayList<>();

        //drwa inputLayer nodes
        for (int i = 0; i < net.inputLayer.size(); i++) {
            int deltaDistance = ((getHeight()-offsetY) / net.inputLayer.size());
            //deltaDistance = deltaDistance + deltaDistance/net.inputLayer.size();

            String value = new Float(net.inputLayer.get(i).getValue()).toString();
            int x = offsetX;
            int y = i*deltaDistance +offsetY;
            g.drawString(value,
                    x +2,
                    y + circleSize - g.getFont().getSize()/2);
            g.drawOval(x, y, circleSize, circleSize);
            nodeGraphicInformationContainers.add(new NodeGraphicInformationContainer( net.inputLayer.get(i), x, y));
        }

        //draw middleLayerNodes
        offsetX += inBetweenLayerDistance;
        for (int i = 0; i < net.middleLayer.size(); i++) {
            for (int j = 0; j < net.middleLayer.get(i).size(); j++) {
                int deltaDistance = ((getHeight() - offsetY) / net.middleLayer.get(i).size());
                String value = new Float(net.middleLayer.get(i).get(j).getValue()).toString();
                int x = offsetX;
                int y = j * deltaDistance + offsetY;

                g.drawString(value,
                        x + 2,
                        y + circleSize - g.getFont().getSize() / 2);;
                g.drawOval(x, y, circleSize, circleSize);

                nodeGraphicInformationContainers.add(new NodeGraphicInformationContainer( net.middleLayer.get(i).get(j), x, y));
            }
            offsetX += inBetweenLayerDistance;
        }

        //draw outputLayer nodes
        for (int i = 0; i < net.outputLayer.size(); i++) {
            int deltaDistance = ((getHeight()-offsetY) / net.outputLayer.size());

            String value = new Float(net.outputLayer.get(i).getValue()).toString();
            int x = offsetX;
            int y = i*deltaDistance +offsetY;
            g.drawString(value,
                    x +2,
                    i*deltaDistance + offsetY + circleSize - g.getFont().getSize()/2);
            g.drawOval(x, y, circleSize, circleSize);
            nodeGraphicInformationContainers.add(new NodeGraphicInformationContainer( net.outputLayer.get(i), x, y));
        };

        //draw connections
        for (NodeGraphicInformationContainer sourceNode : nodeGraphicInformationContainers)
            for ( Node targetNode : sourceNode.node.connectedNodes )
                g.drawLine(sourceNode.x, sourceNode.y, sourceNode.);


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
