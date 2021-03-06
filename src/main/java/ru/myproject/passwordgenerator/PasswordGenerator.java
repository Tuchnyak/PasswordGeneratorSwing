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

    private static final int WIFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

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

        //Main menu
        JMenuBar menuBar = new JMenuBar();

        JMenu jmInfo = new JMenu("Info");

        JMenuItem jmiAbout = new JMenuItem("About this app");
        jmiAbout.addActionListener(new AboutMenuListener());

        JMenuItem jmiHotKeys = new JMenuItem("Hot keys");
        jmiHotKeys.addActionListener(new HotKeysMenuListener());
//        JMenuItem jmiLinks = new JMenuItem("Links about passwords");

        jmInfo.add(jmiAbout);
        jmInfo.add(jmiHotKeys);
//        jmInfo.addSeparator();
//        jmInfo.add(jmiLinks);

        menuBar.add(jmInfo);

        //components for password field, and option buttons
        passField = new JTextField(38);
        passField.setEditable(false);
        passField.setHorizontalAlignment(JTextField.CENTER);
        passField.setBackground(Color.white);
        passField.addFocusListener(new PassFieldListener());

        lengthLabel = new JLabel();
        lengthLabel.setText(Integer.toString(passLength));

        //*KeyBinding for lengthLabel which increase and decrease password length
        //PLUS
        lengthLabel.getInputMap(WIFW).put(KeyStroke.getKeyStroke("ADD"), "INCREASE_PASSWORD_LENGTH");
        lengthLabel.getInputMap(WIFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.SHIFT_DOWN_MASK),
                "INCREASE_PASSWORD_LENGTH");
        lengthLabel.getActionMap().put("INCREASE_PASSWORD_LENGTH", new PlusButtonListener());
        //MINUS
        lengthLabel.getInputMap(WIFW).put(KeyStroke.getKeyStroke("SUBTRACT"), "DECREASE_PASSWORD_LENGTH");
        lengthLabel.getInputMap(WIFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.SHIFT_DOWN_MASK),
                "DECREASE_PASSWORD_LENGTH");
        lengthLabel.getActionMap().put("DECREASE_PASSWORD_LENGTH", new MinusButtonListener());
        //************

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

        //KeyBinding for password generation by pressing enter or spacebar buttons
        genButton.getInputMap(WIFW).put(KeyStroke.getKeyStroke("SPACE"), "GENERATE_PASSWORD");
        genButton.getInputMap(WIFW).put(KeyStroke.getKeyStroke("ENTER"), "GENERATE_PASSWORD");
        genButton.getActionMap().put("GENERATE_PASSWORD", new GenButtonListener());

        //checkbox buttons for different password variations
        checkboxUpper = new JCheckBox("Uppercase characters", true);
        checkboxNumbers = new JCheckBox("Numbers", true);
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
        ratingField.setEditable(false);

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
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, genButton);
        frame.setSize(640, 300);
        frame.setVisible(true);

    }

    /*LISTENERS & ACTIONS*/
    private class PassFieldListener implements FocusListener {
        public void focusGained(FocusEvent ev) {
            passField.selectAll();
        }

        public void focusLost(FocusEvent ev) {

        }
    }

    private class PlusButtonListener extends AbstractAction implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (passLength < maxLength) {
                passLength++;
                lengthLabel.setText(Integer.toString(passLength));
            }
        }
    }

    private class MinusButtonListener extends AbstractAction implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            if (passLength > minLength) {
                passLength--;
                lengthLabel.setText(Integer.toString(passLength));
            }
        }

    }

    private class AboutMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame aboutFrame = new JFrame("About");

            String aboutText = "Greetings!\n" +
                    "Thank you for using this application.\n" +
                    "This is password generator made to easily generate password in your everyday life.\n" +
                    "Password generator assesses password's resistance from 0 to 4 points\n" +
                    "\nApp has been made by Georgii Schennikov in intent to learn Java.";

            JTextArea aboutTextArea = new JTextArea(aboutText);
            aboutTextArea.setEditable(false);
            aboutTextArea.setBackground(Color.WHITE);
            aboutTextArea.setLineWrap(true);
            aboutTextArea.setWrapStyleWord(true);
            aboutTextArea.setFont(new Font("Monospaced", Font.BOLD, 14));

            aboutFrame.getContentPane().add(BorderLayout.CENTER, aboutTextArea);
            aboutFrame.setSize(565, 300);
            aboutFrame.setVisible(true);
        }
    }

    private class HotKeysMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame hotKeysFrame = new JFrame("Hot keys");

            String hotKeysText = "Hot keys:\n" +
                    "* press Enter to generate password\n" +
                    "* press + or - to increase or decrease password length\n" +
                    "* password will be selected when you click on password field by LMB, so press CTRL + C then" +
                    " to copy a password.";

            JTextArea hotKeysTextArea = new JTextArea(hotKeysText);
            hotKeysTextArea.setEditable(false);
            hotKeysTextArea.setBackground(Color.WHITE);
            hotKeysTextArea.setLineWrap(true);
            hotKeysTextArea.setWrapStyleWord(true);
            hotKeysTextArea.setFont(new Font("Monospaced", Font.BOLD, 14));
//            hotKeysTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

            hotKeysFrame.getContentPane().add(BorderLayout.CENTER, hotKeysTextArea);
            hotKeysFrame.setSize(565, 300);
            hotKeysFrame.setVisible(true);
        }
    }

    /*
    I know that such code commenting is a bad practice but I prefer to leave it here for learning purposes
     */
//    private class IncreasePasswordLengthAction extends AbstractAction {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (passLength < maxLength) {
//                passLength++;
//                lengthLabel.setText(Integer.toString(passLength));
//            }
//        }
//    }
//
//    private class DecreasePasswordLengthAction extends AbstractAction {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (passLength > minLength) {
//                passLength--;
//                lengthLabel.setText(Integer.toString(passLength));
//            }
//        }
//    }

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

    private class GenButtonListener extends AbstractAction implements ActionListener {
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