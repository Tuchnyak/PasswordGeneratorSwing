package ru.myproject.passwordgenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by George on 27.03.2017.
 * PasswordGenerator allows to generate passwords with length from 4 to 64 symbols.
 * There are several variations:
 * - all lower case characters
 * - both a lower and an uppercase characters
 * - characters with special symbols
 * - numbers
 **/
public class PasswordGenerator {

    private JFrame frame;
    private JTextField passField;
    private JTextField ratingField;
    private JButton plusButton, minusButton, genButton;
    private JLabel lengthLabel;
    private JCheckBox checkboxUpper, checkboxNumbers, checkboxSpecial;
    private JRadioButton radioCustom, radioOnlyNumbers;
    private int passLength = 8;
    private int minLength = 4;
    private int maxLength = 64;

    public static void main(String[] args) {
        PasswordGenerator pg = new PasswordGenerator();
        pg.gui();
    }

    private void gui() {
        //form GUI
        //frame initialization
        frame = new JFrame("PasswordGenerator v1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //components for password field, and option buttons
        passField = new JTextField(38);
        passField.setEditable(false);
        passField.setHorizontalAlignment(JTextField.CENTER);
        passField.setBackground(Color.white);
        passField.addFocusListener(new PassFieldListener());

        lengthLabel = new JLabel();
        lengthLabel.setText(Integer.toString(passLength));

        plusButton = new JButton("+");
        plusButton.addActionListener(new PlusButtonListener());

        minusButton = new JButton("-");
        minusButton.addActionListener(new MinusButtonListener());

        //panel for JTextField, +- buttons and password length label
        JPanel panel = new JPanel();
        panel.add(passField);
        panel.add(minusButton);
        panel.add(lengthLabel);
        panel.add(plusButton);

        //button which initiates generation of a new password
        genButton = new JButton("*** GENERATE PASSWORD ***");
        genButton.addActionListener(new GenButtonListener());

        //checkbox buttons for different password variations
        checkboxUpper = new JCheckBox("Uppercase characters", false);
        checkboxNumbers = new JCheckBox("Numbers", false);
        checkboxSpecial = new JCheckBox("Special symbols", false);

        //radio buttons for switching between only numbers and custom modes
        ButtonGroup radioGroup = new ButtonGroup();
        radioCustom = new JRadioButton("Custom mode", true);
        radioCustom.addActionListener(new RadioButtonListener());
        radioGroup.add(radioCustom);
        radioOnlyNumbers = new JRadioButton("Generate only numbers", false);
        radioOnlyNumbers.addActionListener(new RadioButtonListener());
        radioGroup.add(radioOnlyNumbers);

        //panel for checkbox and radio buttons
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.add(radioCustom);
        checkboxPanel.add(checkboxUpper);
        checkboxPanel.add(checkboxNumbers);
        checkboxPanel.add(checkboxSpecial);
        checkboxPanel.add(radioOnlyNumbers);

        //rating field
        ratingField = new JTextField("0", 4);
        ratingField.setBackground(Color.red);
        ratingField.setBorder(BorderFactory.createTitledBorder("Rating from 0 to 4:"));
        ratingField.setHorizontalAlignment(JTextField.CENTER);
        ratingField.setFont(new Font("Monospaced", Font.BOLD, 60));

        //panel for panel with buttons (checkboxPanel) and rating
        JPanel centerBottomPanel = new JPanel();
        centerBottomPanel.add(checkboxPanel);
        centerBottomPanel.add(ratingField);

        //panel that includes panel, radio and checkbox buttons panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Generate your password here"));
        centerPanel.add(panel);
        centerPanel.add(centerBottomPanel);

        //frame final setting
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, genButton);
        frame.setSize(640, 250);
        frame.setVisible(true);

    }

    /*LISTENERS*/
    private class PassFieldListener implements FocusListener {
        public void focusGained(FocusEvent ev) {
            passField.selectAll();
        }

        public void focusLost(FocusEvent ev) {

        }
    }

    private class PlusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (passLength < maxLength) {
                passLength++;
                lengthLabel.setText(Integer.toString(passLength));
            }
        }
    }

    private class MinusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (passLength > minLength) {
                passLength--;
                lengthLabel.setText(Integer.toString(passLength));
            }
        }
    }

    private class RadioButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (radioOnlyNumbers.isSelected()) {
                checkboxUpper.setEnabled(false);
                checkboxNumbers.setEnabled(false);
                checkboxSpecial.setEnabled(false);
            } else {
                checkboxUpper.setEnabled(true);
                checkboxNumbers.setEnabled(true);
                checkboxSpecial.setEnabled(true);
            }
        }
    }

    private class GenButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            StringBuilder str = new StringBuilder();
            char[] arr;

            if (radioCustom.isSelected()) {
                if (checkboxUpper.isSelected()) {
                    arr = Randomizer.generateAZBothCasesRange(passLength);
                    if (checkboxNumbers.isSelected()) {
                        arr = Randomizer.generateAZBothCasesAndNumbers(passLength);
                    }
                    if (checkboxSpecial.isSelected()) {
                        arr = Randomizer.generateAZBothCasesAndSpecial(passLength);
                    }
                    if (checkboxNumbers.isSelected() && checkboxSpecial.isSelected()) {
                        arr = Randomizer.generateFullRange(passLength);
                    }
                } else if (checkboxNumbers.isSelected()) {
                    arr = Randomizer.generateAZLowercaseAndNumbers(passLength);
                    if (checkboxSpecial.isSelected()) {
                        arr = Randomizer.generateAZLowercaseAndNumbersAndSpecial(passLength);
                    }
                } else if (checkboxSpecial.isSelected()) {
                    arr = Randomizer.generateAZLowercaseAndSpecial(passLength);
                } else {
                    arr = Randomizer.generateAZLowerRange(passLength);
                }
            } else {
                arr = Randomizer.generateNumbersRange(passLength);
            }

            for (char ch : arr) {
                str.append(ch);
            }

            ratingField.setText(Integer.toString(ResistanceChecker.passwordRating(arr)));
            ratingField.setBackground(ResistanceChecker.passwordColor(arr));
            passField.setText(str.toString());
        }
    }
    /*END OF LISTENERS*/

}