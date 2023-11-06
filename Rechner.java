import cc.redberry.libdivide4j.FastDivision;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import static java.lang.Math.toIntExact;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.10.2022
 * @author Cetendo
 */

public class Rechner extends JFrame {
    // Anfang Attribute
    private JPanel pnlFenster = new JPanel(null, true);
    private JPanel pnlErgebnis = new JPanel(null, true);
    private JPanel pnlTasten = new JPanel(null, true);
    private JButton btnProzent = new JButton();
    private JButton btnClearEntry = new JButton();
    private JButton btnClear = new JButton();
    private JButton btnDel = new JButton();
    private JButton btnKehrwert = new JButton();
    private JButton btnQuadrat = new JButton();
    private JButton btnQuadratwurzel = new JButton();
    private JButton btnDivision = new JButton();
    private JButton btnSieben = new JButton();
    private JButton btnAcht = new JButton();
    private JButton btnNeun = new JButton();
    private JButton btnMultiplikation = new JButton();
    private JButton btnVier = new JButton();
    private JButton btnFuenf = new JButton();
    private JButton btnSechs = new JButton();
    private JButton btnSubtraktion = new JButton();
    private JButton btnEins = new JButton();
    private JButton btnZwei = new JButton();
    private JButton btnDrei = new JButton();
    private JButton btnAddition = new JButton();
    private JButton btnVorzeichenwechsel = new JButton();
    private JButton btnNull = new JButton();
    private JButton btnKomma = new JButton();
    private JButton btnErgebnis = new JButton();
    private JLabel lblOben = new JLabel();
    private JLabel lblUnten = new JLabel();

    // Ende Attribute
    public Rechner() {
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 400;
        int frameHeight = 510;
        setSize(frameWidth, frameHeight);
        setMinimumSize(new Dimension(340, 410));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Rechner");
        setResizable(true);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten
        pnlFenster.setBounds(0, 0, 280, 256);
        pnlFenster.setOpaque(true);
        //pnlFenster.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
        pnlFenster.setBackground(Color.black);
        pnlFenster.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
            public void ancestorResized(HierarchyEvent evt) {
                pnlFenster_AncestorResized(evt);
            }
        });
        pnlErgebnis.setBounds(0, 0, 10, 10);
        pnlErgebnis.setOpaque(false);
        pnlFenster.add(pnlErgebnis);
        pnlTasten.setBounds(0, 0, 10, 10);
        pnlTasten.setOpaque(false);
        pnlFenster.add(pnlTasten);
        lblOben.setBounds(0, 0, 100, 100);
        lblOben.setBackground(Color.black);
        lblOben.setForeground(Color.white);
        lblOben.setOpaque(true);
        lblOben.setHorizontalAlignment(SwingConstants.RIGHT);
        lblOben.setText("");
        //lblOben.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
        pnlErgebnis.add(lblOben);
        lblUnten.setBounds(0, 0, 100, 100);
        lblUnten.setBackground(Color.black);
        lblUnten.setForeground(Color.white);
        lblUnten.setOpaque(true);
        lblUnten.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUnten.setText("0");
        lblUnten.setFont(new Font("Dialog", Font.BOLD, 16));
        //lblUnten.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
        pnlErgebnis.add(lblUnten);
        cp.add(pnlFenster);
        // Ende Komponenten

        // Rest
        JButton[] buttons = {
                btnProzent, btnClearEntry, btnClear, btnDel,
                btnKehrwert, btnQuadrat, btnQuadratwurzel, btnDivision,
                btnSieben, btnAcht, btnNeun, btnMultiplikation,
                btnVier, btnFuenf, btnSechs, btnSubtraktion,
                btnEins, btnZwei, btnDrei, btnAddition,
                btnVorzeichenwechsel, btnNull, btnKomma, btnErgebnis};

        Color geileFarbe = new Color(212, 8, 59);
        int counter = 0;
        for (JButton button : buttons) {
            button.setBounds(0, 0, 50, 50);
            button.setText(buttonText[counter]);
            button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, geileFarbe));
            button.setMargin(new Insets(2, 2, 2, 2));
            button.setBackground(Color.BLACK);
            button.setForeground(Color.RED);
            if (!keyBind[counter].equals("none")) {
                button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyBind[counter]), "keyPressed");
                button.getActionMap().put("keyPressed", new AbstractAction("keyPressed") {
                    public void actionPerformed(ActionEvent evt) {
                        //System.out.println(evt.getActionCommand() +" "+ ((JButton)evt.getSource()).getText());
                        ((JButton) evt.getSource()).setBackground(Color.white);
                        //System.out.println("key "+((JButton)evt.getSource()).getText());
                        String c = ((JButton) evt.getSource()).getText();
                        switch (c) {
                            case "CE" -> btnClearEntry_ActionPerformed(evt);
                            case "C" -> btnClear_ActionPerformed(evt);
                            case "DEL" -> btnDel_ActionPerformed(evt);
                            case "/" -> btnDivision_ActionPerformed(evt);
                            case "7" -> btnSieben_ActionPerformed(evt);
                            case "8" -> btnAcht_ActionPerformed(evt);
                            case "9" -> btnNeun_ActionPerformed(evt);
                            case "*" -> btnMultiplikation_ActionPerformed(evt);
                            case "4" -> btnVier_ActionPerformed(evt);
                            case "5" -> btnFuenf_ActionPerformed(evt);
                            case "6" -> btnSechs_ActionPerformed(evt);
                            case "-" -> btnSubtraktion_ActionPerformed(evt);
                            case "1" -> btnEins_ActionPerformed(evt);
                            case "2" -> btnZwei_ActionPerformed(evt);
                            case "3" -> btnDrei_ActionPerformed(evt);
                            case "+" -> btnAddition_ActionPerformed(evt);
                            case "0" -> btnNull_ActionPerformed(evt);
                            case "," -> btnKomma_ActionPerformed(evt);
                            case "=" -> btnErgebnis_ActionPerformed(evt);
                            default -> {
                            }
                        } // end of switch
                    }
                });
                button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + keyBind[counter]), "keyReleased");
                button.getActionMap().put("keyReleased", new AbstractAction("keyReleased") {
                    public void actionPerformed(ActionEvent evt) {
                        //System.out.println(evt.getActionCommand() +" "+ ((JButton)evt.getSource()).getText());
                        ((JButton) evt.getSource()).setBackground(Color.black);
                    }
                });
            }
            pnlTasten.add(button);
            counter++;
        } // end of for
        counter = 0;
        pnlTasten.setLayout(new GridLayout(6, 4));

        Map<String, JButton> buttonMap = new HashMap<>(); //HashMap<String, JButton>();
        for (int i = 0; i < nameOfButtons.length; i++) {
            buttonMap.put("btn" + nameOfButtons[i], buttons[i]);
        }

        for (String nameOfButton : nameOfButtons) {
            buttonMap.get("btn" + nameOfButton).addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    btnX_MouseEntered(evt);
                }
            });

            buttonMap.get("btn" + nameOfButton).addMouseListener(new MouseAdapter() {
                public void mouseExited(MouseEvent evt) {
                    btnX_MouseExited(evt);
                }
            });

            final String c = nameOfButton;
            buttonMap.get("btn" + nameOfButton).addActionListener(evt -> {
                switch (c) {
                    case "Prozent" -> btnProzent_ActionPerformed(evt);
                    case "ClearEntry" -> btnClearEntry_ActionPerformed(evt);
                    case "Clear" -> btnClear_ActionPerformed(evt);
                    case "Del" -> btnDel_ActionPerformed(evt);
                    case "Kehrwert" -> btnKehrwert_ActionPerformed(evt);
                    case "Quadrat" -> btnQuadrat_ActionPerformed(evt);
                    case "Quadratwurzel" -> btnQuadratwurzel_ActionPerformed(evt);
                    case "Division" -> btnDivision_ActionPerformed(evt);
                    case "Sieben" -> btnSieben_ActionPerformed(evt);
                    case "Acht" -> btnAcht_ActionPerformed(evt);
                    case "Neun" -> btnNeun_ActionPerformed(evt);
                    case "Multiplikation" -> btnMultiplikation_ActionPerformed(evt);
                    case "Vier" -> btnVier_ActionPerformed(evt);
                    case "Fuenf" -> btnFuenf_ActionPerformed(evt);
                    case "Sechs" -> btnSechs_ActionPerformed(evt);
                    case "Subtraktion" -> btnSubtraktion_ActionPerformed(evt);
                    case "Eins" -> btnEins_ActionPerformed(evt);
                    case "Zwei" -> btnZwei_ActionPerformed(evt);
                    case "Drei" -> btnDrei_ActionPerformed(evt);
                    case "Addition" -> btnAddition_ActionPerformed(evt);
                    case "Vorzeichenwechsel" -> btnVorzeichenwechsel_ActionPerformed(evt);
                    case "Null" -> btnNull_ActionPerformed(evt);
                    case "Komma" -> btnKomma_ActionPerformed(evt);
                    case "Ergebnis" -> btnErgebnis_ActionPerformed(evt);
                    default -> {
                    }
                } // end of switch
            });
        }

        setVisible(true);
    } // end of public Rechner

    // Variablen
    public static final String[] nameOfButtons = {
            "Prozent", "ClearEntry", "Clear", "Del",
            "Kehrwert", "Quadrat", "Quadratwurzel", "Division",
            "Sieben", "Acht", "Neun", "Multiplikation",
            "Vier", "Fuenf", "Sechs", "Subtraktion",
            "Eins", "Zwei", "Drei", "Addition",
            "Vorzeichenwechsel", "Null", "Komma", "Ergebnis"};
    public static final String[] buttonText = {
            "%", "CE", "C", "DEL",
            "1/x", "x²", "2√x", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ",", "="};
    public static final String[] keyBind = {
            "none", "DELETE", "ESCAPE", "BACK_SPACE",
            "none", "none", "none", "DIVIDE",
            "NUMPAD7", "NUMPAD8", "NUMPAD9", "MULTIPLY",
            "NUMPAD4", "NUMPAD5", "NUMPAD6", "SUBTRACT",
            "NUMPAD1", "NUMPAD2", "NUMPAD3", "ADD",
            "none", "NUMPAD0", "DECIMAL", "ENTER"};
    public static BigDecimal zahl1 = new BigDecimal("0");
    public static BigDecimal zahl2 = new BigDecimal("0");
    public static BigDecimal zahl3 = new BigDecimal("0");
    public static char vorZeichen = 'n', zeichen = 'n';
    public static int kommaStelle = -1; // Wie viele Stellen hinterm Komma die Eingabe ist. -1 = kein Komma
    public static boolean logKeys = true;

    // Anfang Methoden

    public static void main(String[] args) {
        new Rechner();
    } // end of main


    public void pnlFenster_AncestorResized(HierarchyEvent evt) {
        Rectangle size = this.getBounds();
        Insets insets = this.getInsets();
        int innerWidth = size.width - insets.left - insets.right;
        int innerHeight = size.height - insets.top - insets.bottom;
        int tasteWidth = innerWidth - (innerWidth % 4);
        int innerRest = innerHeight % 4;
        FastDivision.Magic magic = FastDivision.magicSigned(4L);
        int viertelHeight = toIntExact(FastDivision.divideSignedFast(innerHeight, magic));
        int tasteHeight = (viertelHeight * 3) - ((viertelHeight * 3) % 6);
        pnlFenster.setBounds(0, 0, innerWidth, innerHeight);
        pnlErgebnis.setBounds(0, 0, innerWidth, (viertelHeight + ((viertelHeight * 3) % 6) + innerRest));
        pnlTasten.setBounds(0, (viertelHeight + ((viertelHeight * 3) % 6) + innerRest), tasteWidth, tasteHeight);
        lblOben.setBounds(0, 0, innerWidth, ((viertelHeight + ((viertelHeight * 3) % 6) + innerRest) / 2));
        lblUnten.setBounds(0, ((viertelHeight + ((viertelHeight * 3) % 6) + innerRest) / 2), innerWidth, ((viertelHeight + ((viertelHeight * 3) % 6) + innerRest) / 2));
    } // end of pnlFenster_AncestorResized

    public void btnX_MouseEntered(MouseEvent evt) {
        ((JButton) evt.getSource()).setForeground(Color.blue);
        ((JButton) evt.getSource()).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
    } // end of btnx_MouseEntered

    public void btnX_MouseExited(MouseEvent evt) {
        Color geileFarbe = new Color(212, 8, 59);
        ((JButton) evt.getSource()).setForeground(Color.red);
        ((JButton) evt.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, geileFarbe));
    } // end of btnx_MouseExited

    public void numberPressed(int number) {
        int length = 0; // check lenght of the number
        String[] split = zahl3.toString()
                .split("\\.");
        for (String element : split) {
            length = length + element.length();
        }

        if (lblOben.getText().endsWith("=")) { // clear if "=" is included
            zahl1 = zahl2 = zahl3 = BigDecimal.valueOf(0);
            kommaStelle = -1;
            zeichen = vorZeichen = 'n';
            lblOben.setText("");
        }

        if (length < 25 && kommaStelle >= -1) {
            if (kommaStelle == -1) {
                if (zahl3.compareTo(BigDecimal.valueOf(0)) != 0) {
                    zahl3 = zahl3.multiply(BigDecimal.valueOf(10)).add(BigDecimal.valueOf(number));
                } else {
                    zahl3 = BigDecimal.valueOf(number);
                } // end of if-else
            } else {
                kommaStelle++;
                if (number != 0) {
                    zahl3 = zahl3.add(BigDecimal.valueOf(number).divide(BigDecimal.valueOf(10).pow(kommaStelle),RoundingMode.UNNECESSARY)); // idk ob richtiger Rundungsmodus
                } else {
                    zahl3 = zahl3.setScale(zahl3.scale() + 1, RoundingMode.UNNECESSARY); // idk ob richtiger Rundungsmodus
                }
            } // end of if-else
            lblUnten.setText(zahl3.toString());
        } else {
            System.out.println("zahl zu lang");
        } // end of if-else
    }

    public void rechenZeichen(char c) {
        if (lblOben.getText().endsWith("=")) { // clear if "=" is included
            zahl2 = BigDecimal.valueOf(0);
            kommaStelle = -1;
            zeichen = vorZeichen = 'n';
        }
        if (vorZeichen != 'n') {
            zeichen = vorZeichen;
        }
        vorZeichen = c;
        if (!zahl1.equals(BigDecimal.valueOf(0)) && !zahl3.equals(BigDecimal.valueOf(0))) {
            switch (zeichen) {
                case '+' -> zahl1 = zahl1.add(zahl3);
                case '-' -> zahl1 = zahl1.subtract(zahl3);
                case '*' -> zahl1 = zahl1.multiply(zahl3);
                case '/' -> zahl1 = zahl1.divide(zahl3, RoundingMode.UNNECESSARY); // idk ob richtiger Rundungsmodus
            }
        } else {
            if (zahl1.equals(BigDecimal.valueOf(0))) {
                zahl1 = zahl3;
            }
        }
        lblOben.setText(zahl1.toPlainString() + " " + c + " ");
        zahl3 = BigDecimal.valueOf(0); //zahl3 = 0
        zeichen = 'n';
        kommaStelle = -1;
    }

    public void istGleich() { //TODO fix doesnt clear right
        if (!lblUnten.getText().equals("Teilen durch 0 nicht möglich")) {
            zahl3 = BigDecimal.valueOf(Double.parseDouble(lblUnten.getText())).stripTrailingZeros();
        } // TODO check ob summe zu groß
        if (zahl2.equals(new BigDecimal(0))) {
            zahl2 = zahl3;
        }
        if (vorZeichen == 'n') {
            lblOben.setText(zahl3.toPlainString() + " =");
            lblUnten.setText(zahl3.toPlainString());
        } else {
            switch (vorZeichen) {
                case '+' -> zahl3 = zahl1.add(zahl2);
                case '-' -> zahl3 = zahl1.subtract(zahl2);
                case '*' -> zahl3 = zahl1.multiply(zahl2);
                case '/' -> {
                    if (!zahl2.equals(BigDecimal.valueOf(0))) {
                        zahl3 = zahl1.divide(zahl2, RoundingMode.UNNECESSARY); // idk ob richtiger Rundungsmodus //TODO Exception in thread "AWT-EventQueue-0" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
                    } else {
                        lblUnten.setText("Teilen durch 0 nicht möglich");
                    }
                }
            } // end of switch
            if (!zahl2.equals(BigDecimal.valueOf(0)) /*zahl2 != 0*/) {
                lblOben.setText(zahl1.toPlainString() + " " + vorZeichen + " " + zahl2.toPlainString());
                lblUnten.setText(zahl3.toPlainString());
            } else {
                lblOben.setText(zahl1 + " " + vorZeichen + " " + zahl2);
                lblUnten.setText("Teilen durch 0 nicht möglich");
            } // end of if-else
        }
        zahl1 = zahl3;
        zeichen = 'n';
        zahl3 = BigDecimal.valueOf(0);
        kommaStelle = -1;
    }

    // Button Click Event
    public void btnProzent_ActionPerformed(ActionEvent evt) {
        //TODO Limit???
        zahl3 = BigDecimal.valueOf(Double.parseDouble(lblUnten.getText()));
        zahl3 = zahl1.multiply(zahl3.divide(new BigDecimal(100), RoundingMode.UNNECESSARY)); // idk ob richtiger Rundungsmodus
        if (zahl3.scale() > 0) {
            zahl3 = zahl3.stripTrailingZeros();
        }
        zahl2 = zahl3;
        lblOben.setText(zahl1.toPlainString() + " " + vorZeichen + " " + zahl2.toPlainString()); // todo wenn vorzeichen = n
        lblUnten.setText(zahl3.toPlainString());
    } // end of btnProzent_ActionPerformed

    public void btnClearEntry_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: CE (Entf)");}
        // Unteres Label löschen
        zahl3 = BigDecimal.valueOf(0);
        kommaStelle = -1;
        lblUnten.setText("0");
    } // end of btnClearEntry_ActionPerformed

    public void btnClear_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: C (Esc)");}
        // Alles Zurückseten
        zahl1 = zahl2 = zahl3 = BigDecimal.valueOf(0);
        kommaStelle = -1;
        zeichen = vorZeichen = 'n';
        lblUnten.setText("0");
        lblOben.setText("");
    } // end of btnClear_ActionPerformed

    public void btnDel_ActionPerformed(ActionEvent evt) {
        if (logKeys) { log("Key: DEL");}
        if (kommaStelle == -1) {
            zahl3 = zahl3.subtract(zahl3.abs().remainder(new BigDecimal(10))).divide(new BigDecimal(10), RoundingMode.UNNECESSARY); // idk ob richtiger Rundungsmodus
            lblUnten.setText(zahl3.toString());
        } else {
            if (zahl3.scale() != 0) {
                zahl3 = zahl3.setScale(zahl3.scale() - 1, RoundingMode.DOWN);
            }
            lblUnten.setText(zahl3.toString());
            if (kommaStelle != 1) {
                kommaStelle--;
            } else {
                kommaStelle = -1;
            }
        } // end of if-else
    } // end of btnDel_ActionPerformed

    public void btnKehrwert_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of btnKehrwert_ActionPerformed

    public void btnQuadrat_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of btnQuadrat_ActionPerformed

    public void btnQuadratwurzel_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of btnQuadratwurzel_ActionPerformed

    public void btnDivision_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: /");}
        rechenZeichen('/');
    } // end of btnDivision_ActionPerformed

    public void btnSieben_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 7");}
        numberPressed(7);
    } // end of btnSieben_ActionPerformed

    public void btnAcht_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 8");}
        numberPressed(8);
    } // end of btnAcht_ActionPerformed

    public void btnNeun_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 9");}
        numberPressed(9);
    } // end of btnNeun_ActionPerformed

    public void btnMultiplikation_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: *");}
        rechenZeichen('*');
    } // end of btnMultiplikation_ActionPerformed

    public void btnVier_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 4");}
        numberPressed(4);
    } // end of btnVier_ActionPerformed

    public void btnFuenf_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 5");}
        numberPressed(5);
    } // end of btnFuenf_ActionPerformed

    public void btnSechs_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 6");}
        numberPressed(6);
    } // end of btnSechs_ActionPerformed

    public void btnSubtraktion_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: -");}
        rechenZeichen('-');
    } // end of btnSubtraktion_ActionPerformed

    public void btnEins_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 1");}
        numberPressed(1);
    } // end of btnEins_ActionPerformed

    public void btnZwei_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 2");}
        numberPressed(2);
    } // end of btnZwei_ActionPerformed

    public void btnDrei_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 3");}
        numberPressed(3);
    } // end of btnDrei_ActionPerformed

    public void btnAddition_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: +");}
        rechenZeichen('+');
    } // end of btnAddition_ActionPerformed

    public void btnVorzeichenwechsel_ActionPerformed(ActionEvent evt) {
        if (zahl3.equals(BigDecimal.valueOf(0))) { zahl3 = BigDecimal.valueOf(Double.parseDouble(lblUnten.getText())); }
        zahl3 = zahl3.negate();
        zahl3 = zahl3.stripTrailingZeros();
        lblUnten.setText(zahl3.toPlainString());

    } // end of btnVorzeichenwechsel_ActionPerformed

    public void btnNull_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: 0");}
        numberPressed(0);
    } // end of btnNull_ActionPerformed

    public void btnKomma_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: ,");}
        if (kommaStelle == -1) {
            kommaStelle = 0;
            lblUnten.setText(lblUnten.getText() + ".");
        } else {
            System.out.println("nope kein Komma");
        } // end of if-else
    } // end of btnKomma_ActionPerformed

    public void btnErgebnis_ActionPerformed(ActionEvent evt) {
        if (logKeys) {log("KEY: =");}
        istGleich();
    } // end of btnErgebnis_ActionPerformed
    // Ende Methoden
    public void log(String str){
        System.out.println(str);
    }
    public void log(Object str){
        System.out.println(str);
    }
}