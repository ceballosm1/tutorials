import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;

class OvalDraw extends Oval {
    OvalDraw() {
        super();
    }

    OvalDraw(int panelWidthIn, int panelHeightIn) {
        // Create random and appropriate dimensions given the inputs of panel width and height.
        final int widthMin = 10;
        int width = GetNumberBetween(widthMin, panelWidthIn);

        final int heightMin = 10;
        int height = GetNumberBetween(heightMin, panelHeightIn);

        final int xMin = 0;
        int xMax = panelWidthIn - width;
        int x = GetNumberBetween(xMin, xMax);

        final int yMin = 0;
        int yMax = panelHeightIn - height;
        int y = GetNumberBetween(yMin, yMax);

        setPositionX(x);
        setPositionY(y);
        setWidth(width);
        setHeight(height);
    }

    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min);
    }

    public void paintComponent(Graphics g) {
        g.drawOval(getPositionX(),getPositionY(),getWidth(),getHeight());

        System.out.format("OvalDraw::pc(x=%d,y=%d,w=%d,h=%d\n",
            getPositionX(), getPositionY(), getWidth(), getHeight());
        }
    }


class DrawPanel extends JPanel {
    private ArrayList<OvalDraw> OvalDrawList;

    DrawPanel() {
        super();
        OvalDrawList = new ArrayList<OvalDraw>();
    }

    public void addRandomOval() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        OvalDrawList.add (new OvalDraw(panelWidth, panelHeight));
        repaint();
}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (OvalDraw oD : OvalDrawList) {
            oD.paintComponent(g);
        }   
    }
}

class DrawFrame extends JFrame implements ActionListener {
    private DrawPanel myDrawPanel;

    public DrawFrame() {
        setBounds(100,100,600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel myButtonPanel = new JPanel();
        myButtonPanel.setLayout(new FlowLayout());

        JButton newOvalButton = new JButton("Add Random Oval");
        newOvalButton.addActionListener(this);
        myButtonPanel.add(newOvalButton);
        contentPane.add(myButtonPanel, BorderLayout.SOUTH);

        myDrawPanel = new DrawPanel();
        contentPane.add(myDrawPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        myDrawPanel.addRandomOval();
    }
}    

public class ovalDrawPlusActionListeners {
    public static void main(String[] args) {
        System.out.println("ovalDrawPlusActioListeners starting.");

        DrawFrame myDrawFrame = new DrawFrame();
    }
}