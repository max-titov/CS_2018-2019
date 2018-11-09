import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class WhiteSpace {

    private JPanel gui = null;
    private BorderLayout mainLayout;
    private FlowLayout buttonLayout;
    private EmptyBorder border;

    public Container getGui() {
        if (gui==null) {
            mainLayout = new BorderLayout(0,0);
            gui = new JPanel(mainLayout);
            gui.setBackground(Color.RED);
            border = new EmptyBorder(0,0,0,0);

            JTree tree = new JTree();
            tree.setVisibleRowCount(10);
            for (int ii = tree.getRowCount(); ii>-1; ii--) {
                tree.expandRow(ii);
            }
            gui.add(new JScrollPane(
                    tree, 
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), 
                    BorderLayout.LINE_START);
            gui.add(new JScrollPane(new JTextArea(10,30)));

            buttonLayout = new FlowLayout(FlowLayout.CENTER,0,0);
            JPanel buttonPanel = new JPanel(buttonLayout);
            gui.add(buttonPanel, BorderLayout.PAGE_START);

            buttonPanel.add(new JLabel("H Gap"));
            final JSpinner hSpinner = 
                    new JSpinner(new SpinnerNumberModel(0,0,15,1));
            buttonPanel.add(hSpinner);

            buttonPanel.add(new JLabel("V Gap"));
            final JSpinner vSpinner = 
                    new JSpinner(new SpinnerNumberModel(0,0,15,1));
            buttonPanel.add(vSpinner);

            buttonPanel.add(new JLabel("H Border"));
            final JSpinner hBorderSpinner = 
                    new JSpinner(new SpinnerNumberModel(0,0,15,1));
            buttonPanel.add(hBorderSpinner);

            buttonPanel.add(new JLabel("V Border"));
            final JSpinner vBorderSpinner = 
                    new JSpinner(new SpinnerNumberModel(0,0,15,1));
            buttonPanel.add(vBorderSpinner);

            ChangeListener changeListener = new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    int hGap = ((Integer)hSpinner.getValue()).intValue();
                    int vGap = ((Integer)vSpinner.getValue()).intValue();
                    int hBorder = ((Integer)hBorderSpinner.getValue()).intValue();
                    int vBorder = ((Integer)vBorderSpinner.getValue()).intValue();
                    adjustWhiteSpace(hGap,vGap,hBorder,vBorder);
                }
            };

            hSpinner.addChangeListener(changeListener);
            vSpinner.addChangeListener(changeListener);
            hBorderSpinner.addChangeListener(changeListener);
            vBorderSpinner.addChangeListener(changeListener);
        }

        return gui;
    }

    private void adjustWhiteSpace(int hGap, int vGap, int hBorder, int vBorder) {
        mainLayout.setHgap(hGap);
        mainLayout.setVgap(vGap);
        buttonLayout.setHgap(hGap);
        gui.setBorder(new EmptyBorder(vBorder,hBorder,vBorder,hBorder));
        Container c = gui.getTopLevelAncestor();
        if (c instanceof Window) {
            Window w = (Window)c;
            w.pack();
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                WhiteSpace ws = new WhiteSpace();
                // the GUI as seen by the user (without frame)
                Container gui = ws.getGui();

                JFrame f = new JFrame("White (OK Red) Space");
                f.add(gui);
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See http://stackoverflow.com/a/7143398/418556 for demo.
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.setResizable(false);
                f.pack();
                // should be done last, to avoid flickering, moving,
                // resizing artifacts.
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}