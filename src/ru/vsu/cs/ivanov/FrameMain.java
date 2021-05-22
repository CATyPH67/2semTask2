package ru.vsu.cs.ivanov;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrameMain extends JFrame{
    private JPanel panelMain;
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton reverseButton;
    private JButton loadFromFileButton;

    public FrameMain() {
        super("Application");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        reverseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = inputTextField.getText();
                if (inputString.matches("[\\d\\s]+")) {
                    MyLinkedList<Integer> inputList = Utils.strToListInt(inputString);
                    inputList.reverse();
                    outputTextField.setText(inputList.toString());
                } else {
                    JOptionPane.showMessageDialog(panelMain,"Enter the integer numbers separated by a space!");
                }
            }
        });

        loadFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("./tests"));
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    Scanner scanner;
                    try {
                        scanner = new Scanner(new File(name));
                    } catch (FileNotFoundException fileNotFoundException) {
                        inputTextField.setText("File not found");
                        return;
                    }
                    inputTextField.setText(scanner.nextLine());
                }
            }
        });
    }
}
