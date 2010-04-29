package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Changes.SubscriptionItem;
import backend.ButtonManager;

public class SubscriptionItemViewer extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3820782173640263639L;
    private ButtonManager     buttonMgr;
    private String            id;
    private ImageIcon         icon;
    private ImageIcon         viewIcon         = new ImageIcon(
                                                       "src/icons/eye.png");
    private ImageIcon         bombIcon         = new ImageIcon(
                                                       "src/icons/bomb.png");
    private JLabel            iconAndName;
    private JButton           deleteButton;
    private JButton           viewButton;
    private SubscriptionItem  _subscriptItem;
    private boolean           itemIsSearch     = false;
    private JCheckBox         selectSubscriptionCheckBox;

    SubscriptionItemViewer(SubscriptionItem subscriptItem,
            ButtonManager newButtonMgr) {

        _subscriptItem = subscriptItem;
        buttonMgr = newButtonMgr;
        icon = subscriptItem.icon();
        id = "  " + subscriptItem.text();
        iconAndName = new JLabel(id);
        iconAndName.setIcon(icon);
        iconAndName.setIconTextGap(2);

        setPreferredSize(new Dimension(220, 80));

        JPanel buttonHolder = new JPanel();
        JPanel infoHolder   = new JPanel(new BorderLayout());

        deleteButton = new JButton(bombIcon);
        viewButton   = new JButton(viewIcon);

        selectSubscriptionCheckBox = new JCheckBox();
        selectSubscriptionCheckBox.setSelected(true);
        selectSubscriptionCheckBox.setMnemonic(KeyEvent.VK_R);


        buttonHolder.setLayout(new GridLayout(1, 0));
        buttonHolder.add(viewButton);
        buttonHolder.add(deleteButton);

        infoHolder.add(selectSubscriptionCheckBox, BorderLayout.WEST);
        infoHolder.add(iconAndName, BorderLayout.CENTER);
        infoHolder.add(buttonHolder, BorderLayout.SOUTH);

        viewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonMgr.doSelectSubscription(_subscriptItem);
            }
        });

        deleteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonMgr.doDeleteSubscription(_subscriptItem);
            }
        });

        selectSubscriptionCheckBox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // buttonMgr.doAddToComposite();
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    // buttonMgr.doRemoveFromComposite();
                }
            }
        });

        setLayout(new BorderLayout());
        add(infoHolder, BorderLayout.CENTER);
    }

    public boolean isSearch() {
        return itemIsSearch;
    }

}
