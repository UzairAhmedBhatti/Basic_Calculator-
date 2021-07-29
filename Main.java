package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.lang.Math;


public class Main extends JFrame {

    JPanel panel = new JPanel(new GridLayout(6,4));

    JTextArea textArea = new JTextArea("0");

    JButton b1 = new JButton("%");
    JButton b2 = new JButton("√");
    JButton b3 = new JButton("x²");
    JButton b4 = new JButton("1/x");
    JButton b5 = new JButton("CE");
    JButton b6 = new JButton("C");
    JButton b7 = new JButton("☒");
    JButton b8 = new JButton("÷");
    JButton b9 = new JButton("7");
    JButton b10 = new JButton("8");
    JButton b11 = new JButton("9");
    JButton b12 = new JButton("×");
    JButton b13 = new JButton("4");
    JButton b14 = new JButton("5");
    JButton b15 = new JButton("6");
    JButton b16 = new JButton("-");
    JButton b17 = new JButton("1");
    JButton b18 = new JButton("2");
    JButton b19 = new JButton("3");
    JButton b20 = new JButton("+");
    JButton b21 = new JButton("±");
    JButton b22 = new JButton("0");
    JButton b23 = new JButton(".");
    JButton b24 = new JButton("=");

    float num1 = 0;
    float num2 = 0;
    String operator = "";
    Boolean isEqual = false;
    Boolean isMinus = false;

    String[] arr = {"%", "÷", "×", "+", "-"};

    public static String formatter(String num)
    {

        double amount = (float) Double.parseDouble(num);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###,###");

        return formatter.format(amount);
    }

    public void ButtonPressed(String number)
    {
        if(isEqual)
        {
            textArea.setText(number);
            return;
        }
        String str = "";
        for(String element: arr)
        {
            if(textArea.getText().contains(element))
            {
                str = element;
                break;
            }
        }
        System.out.println(str);

        if(!(str.equals("")) && isMinus)
        {
            System.out.println(textArea.getText());
            String s = "";
            String s1 = "";
            if(str.compareTo("+") == 0)
            {
                s = textArea.getText().split("\\n")[0].split(" +")[0];
                s1 = textArea.getText().split("\\n")[1];
            }
            else {
                s = textArea.getText().split("\\n")[0].split(str)[0];
                s1 = textArea.getText().split("\\n")[1];
            }

            if(s1.compareTo("0")==0)
            {
                if(number.compareTo(".") == 0)
                {
                    s1 = "0.";
                    textArea.setText(s + str + '\n' + s1);
                    num2 = (float) 0.0;
                }
                else
                {
                    s1 = number;
                    textArea.setText(s + str + '\n' + s1);
                    num2 = (float) Double.parseDouble(number);
                }

            }
            else
            {
                if (s1.length() < 18)
                    s1 = s1 + number;

                if(s1.contains("."))
                {
                    textArea.setText(s + str + '\n' + s1);
                    num2 = (float) Double.parseDouble(s1.replace(",", ""));
                }
                else
                {
                    textArea.setText(s + str + '\n' + formatter(s1.replace(",", "")));
                    num2 = (float) Double.parseDouble(s1.replace(",", ""));
                }
            }
        }
        else {
            isMinus = false;
            if (textArea.getText().compareTo("0") == 0) {

                if(number.compareTo(".") == 0)
                {
                    textArea.setText("0.");
                    num1 = (float) 0.0;
                }
                else {
                    textArea.setText(number);
                    num1 = (float) Double.parseDouble(textArea.getText());
                }
            }
            else {
                if (textArea.getText().length() < 18) {
                    if(number.compareTo(".") == 0 || textArea.getText().contains("."))
                    {
                        textArea.setText(textArea.getText() + number);
                        num1 = (float) Double.parseDouble(textArea.getText().replace(",", ""));
                    }
                    else
                    {
                        textArea.setText(formatter(textArea.getText().replace(",","") + number));
                        num1 = (float) Double.parseDouble(textArea.getText().replace(",", ""));
                    }
                }
            }

        }
    }

    Main()
    {
        super("Calculator");

       /* tx1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tx1.setAlignmentY(SwingConstants.BOTTOM);
        //tx1.setMargin(new Insets(60, 0, 0, 0));
        tx1.setText("0");*/


        textArea.setMargin(new Insets(100, 0, 10, 0));
        textArea.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
        textArea.setForeground(Color.BLACK);


        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {

                e.consume();
                /*if (!Character.isDigit(e.getKeyChar()) || textArea.getText().length() > 17)
                    e.consume();
                else {
                    if(textArea.getText().compareTo("0") == 0)
                        textArea.setText("");*/
                    //textArea.setText(formatter(textArea.getText().replace(",", "")));
               // }
            }
        });

        //textArea.setAlignmentY(Component.RIGHT_ALIGNMENT);
        //textArea.setAlignmentX(Component.RIGHT_ALIGNMENT);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(operator.compareTo("") == 0 || num2 == -1 || isEqual)
                {
                    textArea.setText(num1 + " %" + '\n' + 0);
                    num2 = -1;
                    operator = "%";
                }
                else {
                    if(operator.compareTo("+") == 0)
                    {
                        textArea.setText(add(num1, num2) + " %" + '\n' + 0);
                        num1 = add(num1, num2);
                        operator = "%";
                    }
                    else if(operator.compareTo("-") == 0) {
                        textArea.setText(subtract(num1, num2) + " %" + '\n' + 0);
                        num1 = subtract(num1, num2);
                        operator = "%";
                    }
                    else if(operator.compareTo("÷") == 0)
                    {
                        textArea.setText(divide(num1, num2) + " %" + '\n' + 0);
                        num1 = divide(num1, num2);
                        operator = "%";
                    }
                    else if(operator.compareTo("×") == 0)
                    {
                        textArea.setText(multiply(num1, num2) + " ÷" + '\n' + 0);
                        num1 = multiply(num1, num2);
                        operator = "%";
                    }
                    else if(operator.compareTo("%") == 0)
                    {
                        textArea.setText(mod(num1, num2) + " ÷" + '\n' + 0);
                        num1 = mod(num1, num2);
                        operator = "%";
                    }
                }
                isEqual = false;
                isMinus = true;
            }
        });


        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMinus)
                    return;
                textArea.setText("√" + textArea.getText().replace(",", "") + '\n' + root(num1));
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMinus)
                    return;
                textArea.setText("sqr(" + textArea.getText().replace(",", "") + ")" + '\n' + square(num1));
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMinus)
                    return;
                textArea.setText("1/" + textArea.getText().replace(",", "") + '\n' + reciprocal(num1));
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("0");
                operator = "";
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("0");
                operator = "";
            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEqual)
                {
                    return;
                }
                isMinus = false;
                String str = "";
                for(String element: arr)
                {
                    if(textArea.getText().contains(element))
                    {
                        str = element;
                        break;
                    }
                }
                System.out.println(str);

                if(!(str.equals("")))
                {
                    String s = textArea.getText().split("\\n")[0].split(str)[0];
                    String s1 = textArea.getText().split("\\n")[1];

                    if(s1.compareTo("0")==0)
                    {
                        s1 = "0";
                        textArea.setText(s + str + '\n' + s1);
                        num2 = 0;
                    }
                    else
                    {
                        s1 = s1.substring(0, s.length()-1);
                        textArea.setText(s + str + '\n' + formatter(s1.replace(",", "")));
                        num2 = (float) Double.parseDouble(textArea.getText().replace(",", ""));
                    }
                }
                else {
                    isMinus = false;
                    if (textArea.getText().compareTo("0") == 0) {
                        textArea.setText("0");
                        num1 = 0;
                    }
                    else {
                        if (textArea.getText().length() < 18) {
                            textArea.setText(formatter(textArea.getText().replace(",","").substring(0, textArea.getText().length()-1)));
                            num1 = (float) Double.parseDouble(textArea.getText().replace(",", ""));
                        }
                    }
                }
                isEqual = false;
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(operator.compareTo("") == 0 || num2 == -1 || isEqual)
                {
                    textArea.setText(num1 + " ÷" + '\n' + 0);
                    num2 = -1;
                    operator = "÷";
                }
                else {
                    if(operator.compareTo("+") == 0)
                    {
                        textArea.setText(add(num1, num2) + " ÷" + '\n' + 0);
                        num1 = add(num1, num2);
                        operator = "÷";
                    }
                    else if(operator.compareTo("-") == 0) {
                        textArea.setText(subtract(num1, num2) + " ÷" + '\n' + 0);
                        num1 = subtract(num1, num2);
                        operator = "÷";
                    }
                    else if(operator.compareTo("÷") == 0)
                    {
                        textArea.setText(divide(num1, num2) + " ÷" + '\n' + 0);
                        num1 = divide(num1, num2);
                        operator = "÷";
                    }
                    else if(operator.compareTo("×") == 0)
                    {
                        textArea.setText(multiply(num1, num2) + " ÷" + '\n' + 0);
                        num1 = multiply(num1, num2);
                        operator = "÷";
                    }
                    else if(operator.compareTo("%") == 0)
                    {
                        textArea.setText(mod(num1, num2) + " ÷" + '\n' + 0);
                        num1 = mod(num1, num2);
                        operator = "÷";
                    }
                }
                isEqual = false;
                isMinus = true;
            }
        });


        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("7");
            }
        });

        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("8");
            }
        });

        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("9");
            }
        });

        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(operator.compareTo("") == 0 || num2 == -1 || isEqual)
                {
                    textArea.setText(num1 + " ×" + '\n' + 0);
                    num2 = -1;
                    operator = "×";
                }
                else {
                    if(operator.compareTo("+") == 0)
                    {
                        textArea.setText(add(num1, num2) + " ×" + '\n' + 0);
                        num1 = add(num1, num2);
                        operator = "×";
                    }
                    else if(operator.compareTo("-") == 0) {
                        textArea.setText(subtract(num1, num2) + " ×" + '\n' + 0);
                        num1 = subtract(num1, num2);
                        operator = "×";
                    }
                    else if(operator.compareTo("÷") == 0)
                    {
                        textArea.setText(divide(num1, num2) + " ×" + '\n' + 0);
                        num1 = divide(num1, num2);
                        operator = "×";
                    }
                    else if(operator.compareTo("×") == 0)
                    {
                        textArea.setText(multiply(num1, num2) + " ×" + '\n' + 0);
                        num1 = multiply(num1, num2);
                        operator = "×";
                    }
                    else if(operator.compareTo("%") == 0)
                    {
                        textArea.setText(mod(num1, num2) + " ×" + '\n' + 0);
                        num1 = mod(num1, num2);
                        operator = "×";
                    }
                }
                isEqual = false;
                isMinus = true;
            }
        });

        b13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("4");
            }
        });

        b14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("5");
            }
        });


        b15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("6");
            }
        });

        b16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMinus = true;
                if(operator.compareTo("") == 0 || num2 == -1 || isEqual)
                {
                    textArea.setText(num1 + " -" + '\n' + 0);
                    num2 = -1;
                    operator = "-";
                }
                else {
                    if(operator.compareTo("+") == 0)
                    {
                        textArea.setText(add(num1, num2) + " -" + '\n' + 0);
                        num1 = add(num1, num2);
                        operator = "-";
                    }
                    else if(operator.compareTo("-") == 0) {
                        textArea.setText(subtract(num1, num2) + " -" + '\n' + 0);
                        num1 = subtract(num1, num2);
                        operator = "-";
                    }
                    else if(operator.compareTo("÷") == 0)
                    {
                        textArea.setText(divide(num1, num2) + " -" + '\n' + 0);
                        num1 = divide(num1, num2);
                        operator = "-";
                    }
                    else if(operator.compareTo("×") == 0)
                    {
                        textArea.setText(multiply(num1, num2) + " -" + '\n' + 0);
                        num1 = multiply(num1, num2);
                        operator = "-";
                    }
                    else if(operator.compareTo("%") == 0)
                    {
                        textArea.setText(mod(num1, num2) + " -" + '\n' + 0);
                        num1 = mod(num1, num2);
                        operator = "-";
                    }
                }
                isEqual = false;
                isMinus = true;
            }
        });


        b17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    ButtonPressed("1");
            }
        });


        b18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("2");
            }
        });

        b19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPressed("3");
            }
        });

        b20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(operator.compareTo("") == 0 || num2 == -1  || isEqual)
                {
                    textArea.setText(num1 + " +" + '\n' + 0);
                    num2 = -1;
                    operator = "+";
                }
                else {
                    if(operator.compareTo("+") == 0)
                    {
                        textArea.setText(add(num1, num2) + " +" + '\n' + 0);
                        num1 = add(num1, num2);
                        operator = "+";
                    }
                    else if(operator.compareTo("-") == 0) {
                        textArea.setText(subtract(num1, num2) + " +" + '\n' + 0);
                        num1 = subtract(num1, num2);
                        operator = "+";
                    }
                    else if(operator.compareTo("÷") == 0)
                    {
                        textArea.setText(divide(num1, num2) + " +" + '\n' + 0);
                        num1 = divide(num1, num2);
                        operator = "+";
                    }
                    else if(operator.compareTo("×") == 0)
                    {
                        textArea.setText(multiply(num1, num2) + " +" + '\n' + 0);
                        num1 = multiply(num1, num2);
                        operator = "+";
                    }
                    else if(operator.compareTo("%") == 0)
                    {
                        textArea.setText(mod(num1, num2) + " +" + '\n' + 0);
                        num1 = mod(num1, num2);
                        operator = "+";
                    }
                }
                isEqual = false;
                isMinus = true;
            }
        });

        b21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMinus = false;
                String str = "";
                for(String element: arr)
                {
                    if(textArea.getText().contains(element))
                    {
                        str = element;
                        break;
                    }
                }
                System.out.println(str);

                if(!(str.equals("")))
                {
                    String s = textArea.getText().split("\\n")[0].split(str)[0];
                    String s1 = textArea.getText().split("\\n")[1];

                    if(s1.compareTo("0")==0)
                    {
                        s1 = "-0";
                        textArea.setText(s + str + '\n' + s1);
                        num2 = -0;
                    }
                    else
                    {
                        num2 =  sign(num2);
                        if(num2 > 0) {
                            if (s1.length() < 18)
                                s1 = s1;
                            textArea.setText(s + str + '\n' + formatter(s1.replace(",", "").replace("-", "")));
                        }
                        else {
                            if (s1.length() < 18)
                                s1 = "-" + s1;
                            textArea.setText(s + str + '\n' + formatter(s1.replace(",", "")));
                        }
                    }
                }
                else {
                    isMinus = false;
                    if (textArea.getText().compareTo("0") == 0) {
                        textArea.setText("-0");
                        num1 = -0;
                    }
                    else {
                        if (textArea.getText().length() < 18) {
                            num1 = sign(num1);
                            if(num1 > 0)
                            {
                                textArea.setText(formatter(textArea.getText().replace(",","").replace("-", "")));
                            }
                            else
                            {
                                textArea.setText("-" + formatter(textArea.getText().replace(",","")));
                            }
                        }
                    }
                }
                isEqual = false;

            }
        });


        b22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ButtonPressed("0");
            }
        });


        b23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ButtonPressed(".");
            }
        });


        b24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(operator.compareTo("+") == 0)
                {
                    textArea.setText(num1 + " + " + num2 + " =" + '\n' + add(num1, num2));
                    num1 = add(num1, num2);
                }
                else if(operator.compareTo("-") == 0) {
                    textArea.setText(num1 + " - " + num2 + " =" + '\n' + subtract(num1, num2));
                    num1 = subtract(num1, num2);
                }
                else if(operator.compareTo("÷") == 0)
                {
                    textArea.setText(num1 + " ÷ " + num2 + " =" + '\n' + divide(num1, num2));
                    num1 =  divide(num1, num2);
                }
                else if(operator.compareTo("×") == 0)
                {
                    textArea.setText(num1 + " × " + num2 + " =" + '\n' + multiply(num1, num2));
                    num1 = multiply(num1, num2);
                }
                else if(operator.compareTo("%") == 0)
                {
                    textArea.setText(num1 + " % " + num2 + " =" + '\n' + mod(num1, num2));
                    num1 = mod(num1, num2);
                }
                operator = "";
                isEqual = true;
            }
        });

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);

        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
        panel.add(b8);

        panel.add(b9);
        panel.add(b10);
        panel.add(b11);
        panel.add(b12);

        panel.add(b13);
        panel.add(b14);
        panel.add(b15);
        panel.add(b16);

        panel.add(b17);
        panel.add(b18);
        panel.add(b19);
        panel.add(b20);

        panel.add(b21);
        panel.add(b22);
        panel.add(b23);
        panel.add(b24);

        add(textArea, BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);

        this.setVisible(true);
        this.setSize(330,530);
    }

    public static float add(float num1, float num2)
    {
        return num1 + num2;
    }
    public static float subtract(float num1, float num2)
    {
        return num1 - num2;
    }
    public static float multiply(float num1, float num2)
    {
        return num1 * num2;
    }
    public static float divide(float num1, float num2)
    {
        System.out.println(8.0 / 9.0);
        return num1 / num2;
    }
    public static double reciprocal(float num)
    {
        return Math.pow(num , -1);
    }
    public static float square(float num)
    {
        return num * num;
    }
    public static double root(float num)
    {
        return Math.sqrt(num);
    }
    public static float mod(float num1, float num2)
    {
        return num1 % num2;
    }
    public static float sign(float num)
    {
        return num * -1;
    }


    public static void main(String[] args) {

        Main main = new Main();
    }
}
