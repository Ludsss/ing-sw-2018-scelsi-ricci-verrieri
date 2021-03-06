package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoundTrack extends javax.swing.JPanel {

    public List<DiceGUI> diceGUIList;

    private static final int dimXdice = 50;
    private static final int dimYdice = 50;
    private DraftPool[] roundTrack;
    private GUI gui;

    public RoundTrack(GUI gui) {
        this.gui=gui;
        initComponents();
        diceGUIList = new ArrayList<>();

        setDices();
        //imageLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ImageIcon icon = new ImageIcon(getClass().getResource("/img/texture.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(610, 512, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        imageLabel.setIcon(icon);
        imageLabel.repaint();

        repaint();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageLabel = new javax.swing.JLabel();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(610, 70));
        setMinimumSize(new java.awt.Dimension(610, 70));
        setPreferredSize(new java.awt.Dimension(610, 70));
        setLayout(null);

        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/texture.jpg"))); // NOI18N
        imageLabel.setAlignmentY(0.0F);
        imageLabel.setMaximumSize(new java.awt.Dimension(610, 70));
        imageLabel.setMinimumSize(new java.awt.Dimension(610, 70));
        imageLabel.setPreferredSize(new java.awt.Dimension(610, 70));
        add(imageLabel);
        imageLabel.setBounds(0, 0, 610, 70);
    }// </editor-fold>//GEN-END:initComponents

    public void setDices() {
        for (int i = 0; i < 10; i++) {
            DiceGUI diceGUI = new DiceGUI(false, dimXdice, dimYdice);
            imageLabel.add(diceGUI);
            diceGUI.setBounds(10 + i * (50 + 10), 10, dimXdice, dimYdice);
            diceGUI.setName(String.valueOf(11+i));
            diceGUIList.add(diceGUI);
            diceGUI.setController(gui);
        }
    }

    public void setDiceGUIList(List<DiceGUI> diceGUIList) {
        this.diceGUIList = diceGUIList;
    }

    public void setDiceGUI(int selected, Dice dice) {
        diceGUIList.get(selected);
    }

    public List<DiceGUI> getDiceGUIList() {
        return diceGUIList;
    }

    public DiceGUI getDiceGUI(int selected) {
        return diceGUIList.get(selected);
    }

    //set first element of draft pool as shown element on roundtrack
    //TODO add number as remaining dices in draft pool
    //TODO add on click open list of remaining dices
    public void setDraftPool(DraftPool[] roundTrack) {
        this.roundTrack = roundTrack;

        int counter = 0;
        for (DraftPool dices : roundTrack) {
            if (!(null == dices)) {
                List<Dice> draftPool = dices.getDraftPool();
                if (!draftPool.isEmpty()) {
                    diceGUIList.get(counter).setDice(draftPool.get(0));
                }
                counter++;
            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    // End of variables declaration//GEN-END:variables
}
