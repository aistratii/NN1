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
    private ArrayList<NodeGraphicInformationContainer> nodeGraphicInformationContainer;
    private int borderDistance = 100;
    private int circleDiameter = 20;
    int drawAreaWidth;
    int drawAreaHeight;
    int horrizontalDistanceBetweenNodes;

    public NeuralNetworkGraphicRepresentation(NeuralNet net){
        super();
        setSize(500,500);
        this.net = net;
        nodeGraphicInformationContainer = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
        revalidate();
    };


    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        nodeGraphicInformationContainer = new ArrayList<>();
        drawAreaWidth = getWidth() - borderDistance*2;
        drawAreaHeight = getHeight() - borderDistance*2;
        horrizontalDistanceBetweenNodes = (drawAreaWidth - circleDiameter*net.netLayers.size())/net.netLayers.size();

        //draw nodes
        int offsetX = borderDistance;
        for (int i = 0; i < net.netLayers.size(); i++) {
            int verticalDistanceBetweenNodes = (drawAreaHeight - circleDiameter*net.netLayers.get(i).size())/net.netLayers.get(i).size();
            for (int j = 0; j < net.netLayers.get(i).size(); j++) {
                int offsetY = borderDistance + j * (verticalDistanceBetweenNodes + circleDiameter);
                g.drawOval(offsetX, offsetY, circleDiameter, circleDiameter);
                g.drawString(new Float(net.netLayers.get(i).get(j).getValue()).toString(), offsetX, offsetY);
                g.setColor(Color.RED);
                g.drawString(new Integer(net.netLayers.get(i).get(j).getID()).toString(), offsetX + 5, offsetY + 15);
                g.setColor(Color.BLACK);
                nodeGraphicInformationContainer.add(new NodeGraphicInformationContainer(net.netLayers.get(i).get(j), offsetX, offsetY));
            }
            offsetX += horrizontalDistanceBetweenNodes + circleDiameter;
        };

        //draw node connections
        for (int i = 0; i < nodeGraphicInformationContainer.size(); i++){
            NodeGraphicInformationContainer nodeContainer = nodeGraphicInformationContainer.get(i);
            //pentru fiecare dintre nodurile conectate
            for (int j = 0; j < nodeContainer.node.connectedNodes.size(); j++)
                //caut nodeContainer care contine acest nod
                for (int l = 0; l < nodeGraphicInformationContainer.size(); l++)
                    if (nodeGraphicInformationContainer.get(l).node.equals(nodeContainer.node.connectedNodes.get(j))) {
                        NodeGraphicInformationContainer targetNode = nodeGraphicInformationContainer.get(l);
                        g.drawLine( nodeContainer.x + circleDiameter/2, nodeContainer.y + circleDiameter/2,
                                    targetNode.x + circleDiameter/2, targetNode.y + circleDiameter/2);
                        break;
                    }
        }
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
