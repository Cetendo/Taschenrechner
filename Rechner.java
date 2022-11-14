import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.border.EtchedBorder;
import java.lang.reflect.*;
import static java.lang.Math.toIntExact;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.10.2022
 * @author 
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
    int frameWidth = 300;
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    setMinimumSize(new Dimension(200, 250));
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
    //lblUnten.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
    pnlErgebnis.add(lblUnten);
    cp.add(pnlFenster);
    // Ende Komponenten
    
    // Rest
    JButton[]  buttons = {
    btnProzent, btnClearEntry, btnClear, btnDel,
    btnKehrwert, btnQuadrat, btnQuadratwurzel, btnDivision,
    btnSieben, btnAcht, btnNeun, btnMultiplikation,
    btnVier, btnFuenf, btnSechs, btnSubtraktion,
    btnEins, btnZwei, btnDrei, btnAddition,
    btnVorzeichenwechsel, btnNull, btnKomma, btnErgebnis};
   
    Color geileFarbe = new Color(212, 8, 59);
    int counter = 0;
    for (JButton button: buttons) {
      button.setBounds(0, 0, 50, 50);
      button.setText(buttonText[counter]);
      button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, geileFarbe));
      button.setMargin(new Insets(2, 2, 2, 2));
      button.setBackground(Color.BLACK);
      button.setForeground(Color.RED);
      if (keyBind[counter] != "none") {
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyBind[counter]), "keyPressed");
        button.getActionMap().put("keyPressed", new AbstractAction("keyPressed") {
          public void actionPerformed(ActionEvent evt) {
            //System.out.println(evt.getActionCommand() +" "+ ((JButton)evt.getSource()).getText());
            ((JButton)evt.getSource()).setBackground(Color.white);
            //System.out.println("key "+((JButton)evt.getSource()).getText());
            String c = ((JButton)evt.getSource()).getText();
            switch (c) {
              case "CE":    btnClearEntry_ActionPerformed(evt); break;
              case "C":          btnClear_ActionPerformed(evt); break;
              case "DEL":          btnDel_ActionPerformed(evt); break;
              case "/":       btnDivision_ActionPerformed(evt); break;
              case "7":         btnSieben_ActionPerformed(evt); break;
              case "8":           btnAcht_ActionPerformed(evt); break;
              case "9":           btnNeun_ActionPerformed(evt); break;
              case "*": btnMultiplikation_ActionPerformed(evt); break;
              case "4":           btnVier_ActionPerformed(evt); break;
              case "5":          btnFuenf_ActionPerformed(evt); break;
              case "6":          btnSechs_ActionPerformed(evt); break;
              case "-":    btnSubtraktion_ActionPerformed(evt); break;
              case "1":           btnEins_ActionPerformed(evt); break;
              case "2":           btnZwei_ActionPerformed(evt); break;
              case "3":           btnDrei_ActionPerformed(evt); break;
              case "+":       btnAddition_ActionPerformed(evt); break;
              case "0":           btnNull_ActionPerformed(evt); break;
              case ",":          btnKomma_ActionPerformed(evt); break;
              case "=":       btnErgebnis_ActionPerformed(evt); break;
              default:
                
            } // end of switch
          }
        });
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released "+keyBind[counter]), "keyReleased");
        button.getActionMap().put("keyReleased", new AbstractAction("keyReleased") {
          public void actionPerformed(ActionEvent evt) {
            //System.out.println(evt.getActionCommand() +" "+ ((JButton)evt.getSource()).getText());
            ((JButton)evt.getSource()).setBackground(Color.black);
          }
        });
      }   
      pnlTasten.add(button);
      counter++;
    } // end of for
    counter = 0;
    pnlTasten.setLayout(new GridLayout(6, 4));
    
    Map<String, JButton> buttonMap = new HashMap<String, JButton>();
    for (int i = 0; i < nameOfButtons.length; i++) {
      buttonMap.put("btn"+nameOfButtons[i], buttons[i]);
    }
    
    for (int i = 0; i < nameOfButtons.length; i++) {
      buttonMap.get("btn"+nameOfButtons[i]).addMouseListener(new MouseAdapter() { 
        public void mouseEntered(MouseEvent evt) { 
          btnX_MouseEntered(evt);
        }
      });
      
      buttonMap.get("btn"+nameOfButtons[i]).addMouseListener(new MouseAdapter() { 
        public void mouseExited(MouseEvent evt) { 
          btnX_MouseExited(evt);
        }
      }); 
      
      final String c = nameOfButtons[i];
      buttonMap.get("btn"+nameOfButtons[i]).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          
          switch (c) {               
            case "Prozent":           btnProzent_ActionPerformed(evt);           break;
            case "ClearEntry":        btnClearEntry_ActionPerformed(evt);        break;
            case "Clear":             btnClear_ActionPerformed(evt);             break;
            case "Del":               btnDel_ActionPerformed(evt);               break;
            case "Kehrwert":          btnKehrwert_ActionPerformed(evt);          break;
            case "Quadrat":           btnQuadrat_ActionPerformed(evt);           break;
            case "Quadratwurzel":     btnQuadratwurzel_ActionPerformed(evt);     break;
            case "Division":          btnDivision_ActionPerformed(evt);          break;
            case "Sieben":            btnSieben_ActionPerformed(evt);            break;
            case "Acht":              btnAcht_ActionPerformed(evt);              break;
            case "Neun":              btnNeun_ActionPerformed(evt);              break;
            case "Multiplikation":    btnMultiplikation_ActionPerformed(evt);    break;
            case "Vier":              btnVier_ActionPerformed(evt);              break;
            case "Fuenf":             btnFuenf_ActionPerformed(evt);             break;
            case "Sechs":             btnSechs_ActionPerformed(evt);             break;
            case "Subtraktion":       btnSubtraktion_ActionPerformed(evt);       break;
            case "Eins":              btnEins_ActionPerformed(evt);              break;
            case "Zwei":              btnZwei_ActionPerformed(evt);              break;
            case "Drei":              btnDrei_ActionPerformed(evt);              break;
            case "Addition":          btnAddition_ActionPerformed(evt);          break;
            case "Vorzeichenwechsel": btnVorzeichenwechsel_ActionPerformed(evt); break;
            case "Null":              btnNull_ActionPerformed(evt);              break;
            case "Komma":             btnKomma_ActionPerformed(evt);             break;
            case "Ergebnis":          btnErgebnis_ActionPerformed(evt);          break;
            default:
              
          } // end of switch
        }
      });
      counter++; 
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
  public static final  String[] buttonText = {
    "%", "CE", "C", "DEL",
    "1/x", "x^2", "2vx", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "+/-", "0", ",", "="};
  public static final  String[] keyBind = {
    "none", "DELETE", "ESCAPE", "BACK_SPACE",
    "none", "none", "none", "DIVIDE",
    "NUMPAD7", "NUMPAD8", "NUMPAD9", "MULTIPLY",
    "NUMPAD4", "NUMPAD5", "NUMPAD6", "SUBTRACT",
    "NUMPAD1", "NUMPAD2", "NUMPAD3", "ADD",
    "none", "NUMPAD0", "DECIMAL", "ENTER"};
  public static double zahl1, zahl2, zahl3 = 0;
  public static int kommaStelle = 0; // Wie viele Stellen hinterm Komma die Eingabe ist. 0 = kein Komma
        
        // Anfang Methoden
        
  public static void main(String[] args) {
    new Rechner();
  } // end of main
        
        
  public void pnlFenster_AncestorResized(HierarchyEvent evt) {
    long start = System.nanoTime();
    //
    Rectangle size = this.getBounds();
    Insets insets = this.getInsets();
    int innerWidth = size.width - insets.left - insets.right;
    int innerHeight = size.height - insets.top - insets.bottom;
    int tasteWidth =  innerWidth - (innerWidth % 4);
    int innerRest = innerHeight % 4;
    FastDivision.Magic magic = FastDivision.magicSigned(4L);
    int viertelHeight = toIntExact(FastDivision.divideSignedFast(innerHeight, magic));
    int tasteHeight = (viertelHeight*3) - ((viertelHeight*3) % 6);
    pnlFenster.setBounds(0, 0, innerWidth, innerHeight);
    pnlErgebnis.setBounds(0, 0, innerWidth, (viertelHeight + ((viertelHeight*3) % 6) + innerRest));
    pnlTasten.setBounds(0, (viertelHeight + ((viertelHeight*3) % 6) + innerRest), tasteWidth, tasteHeight);
    lblOben.setBounds(0, 0, innerWidth, ((viertelHeight + ((viertelHeight*3) % 6) + innerRest)/2));
    lblUnten.setBounds(0, ((viertelHeight + ((viertelHeight*3) % 6) + innerRest)/2), innerWidth, ((viertelHeight + ((viertelHeight*3) % 6) + innerRest)/2));
    //this.setTitle(System.nanoTime() - start+" ns ");
  } // end of pnlFenster_AncestorResized  
        
  public void btnX_MouseEntered(MouseEvent evt) {
    // TODO hier Quelltext einfügen
    ((JButton)evt.getSource()).setForeground(Color.blue);
    ((JButton)evt.getSource()).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
  } // end of btnx_MouseEntered
        
  public void btnX_MouseExited(MouseEvent evt) {
    // TODO hier Quelltext einfügen
    Color geileFarbe = new Color(212, 8, 59);
    ((JButton)evt.getSource()).setForeground(Color.red);
    ((JButton)evt.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, geileFarbe));
  } // end of btnx_MouseExited
  
  public void numberPressed(int number) {
    String pattern = "##.#######";
    DecimalFormat df = new DecimalFormat(pattern);
    
    double max = Math.pow(10, 30);
    if (zahl3 <= max && kommaStelle < 30) {
      //if (kommaStelle == 0) {
        if (zahl3 != 0) {
          zahl3 = zahl3 * 10 + number;
        } else {
          zahl3 = number;
        } // end of if-else
      /*} else {
        if (number != 0) {
          zahl3 = zahl3 + (number/Math.pow(10, kommaStelle));
          System.out.println(df.format(zahl3)+" \n "+(df.format(Math.pow(10, kommaStelle)))); 
          kommaStelle++;
        } // end of if-else
      } // end of if-else */
      lblUnten.setText(df.format(zahl3));
      System.out.println((String.format("%f", zahl3));
    } else {
      System.out.println("zahl zulang");  
    } // end of if-else
  }
  
  // Button Click Event      
  public void btnProzent_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
  } // end of btnProzent_ActionPerformed
        
  public void btnClearEntry_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    zahl3 = kommaStelle = 0;
    lblUnten.setText("0");
    System.out.println("CE");
  } // end of btnClearEntry_ActionPerformed
        
  public void btnClear_ActionPerformed(ActionEvent evt) {
    // Alles Zurückseten
    zahl1 = zahl2 = zahl3 = kommaStelle = 0;
    //zeichen = vorZeichen = 'n';
    lblUnten.setText("0");
    lblOben.setText("");
    System.out.println("C");
  } // end of btnClear_ActionPerformed
        
  public void btnDel_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("DEL");
    if (kommaStelle == 0) {
      
    } else {
      
      kommaStelle--;
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
    // TODO hier Quelltext einfügen
    System.out.println("/");
  } // end of btnDivision_ActionPerformed
        
  public void btnSieben_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("7");
    numberPressed(7);
  } // end of btnSieben_ActionPerformed
        
  public void btnAcht_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("8");
    numberPressed(8);
  } // end of btnAcht_ActionPerformed
        
  public void btnNeun_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("9");
    numberPressed(9);
  } // end of btnNeun_ActionPerformed
        
  public void btnMultiplikation_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("*");
  } // end of btnMultiplikation_ActionPerformed
        
  public void btnVier_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("4");
    numberPressed(4);
  } // end of btnVier_ActionPerformed
        
  public void btnFuenf_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("5");
    numberPressed(5);
  } // end of btnFuenf_ActionPerformed
        
  public void btnSechs_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("6");
    numberPressed(6);
  } // end of btnSechs_ActionPerformed
        
  public void btnSubtraktion_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("-");
  } // end of btnSubtraktion_ActionPerformed
        
  public void btnEins_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("1");
    numberPressed(1);
  } // end of btnEins_ActionPerformed
        
  public void btnZwei_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("2");
    numberPressed(2);
  } // end of btnZwei_ActionPerformed
        
  public void btnDrei_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("3");
    numberPressed(3);
  } // end of btnDrei_ActionPerformed
        
  public void btnAddition_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("+");
  } // end of btnAddition_ActionPerformed
        
  public void btnVorzeichenwechsel_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
  } // end of btnVorzeichenwechsel_ActionPerformed
        
  public void btnNull_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("0");
    numberPressed(0);
  } // end of btnNull_ActionPerformed
        
  public void btnKomma_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println(",");
    if (kommaStelle == 0) {
      kommaStelle = 1;
    } else {
      System.out.println("nope kein Komma");
    } // end of if-else
  } // end of btnKomma_ActionPerformed
        
  public void btnErgebnis_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println("=");
  } // end of btnErgebnis_ActionPerformed
        // Ende Methoden
  
  
  /**********************************************************************
    * All below is copied from https://github.com/PoslavskySV/libdivide4j
    ********************************************************************/
  @SuppressWarnings("Duplicates")
  public final class FastDivision {
    private FastDivision() {}
          
          /**
          * Returns highest 64 bits of (signed) long multiplication.
          *
          * @param x the number
          * @param y the number
          * @return highest 64 bits of (signed) long multiplication.
          */
    public static long multiplyHighSigned(long x, long y) {
      long x_high = x >> 32;
      long x_low = x & 0xFFFFFFFFL;
      long y_high = y >> 32;
      long y_low = y & 0xFFFFFFFFL;
      
      long z2 = x_low * y_low;
      long t = x_high * y_low + (z2 >>> 32);
      long z1 = t & 0xFFFFFFFFL;
      long z0 = t >> 32;
      z1 += x_low * y_high;
      return x_high * y_high + z0 + (z1 >> 32);
    }
          
          /**
          * Returns highest 64 bits of (unsigned) long multiplication.
          *
          * @param x the number
          * @param y the number
          * @return highest 64 bits of (unsigned) long multiplication.
          */
    public static long multiplyHighUnsigned(long x, long y) {
      long x_high = x >>> 32;
      long y_high = y >>> 32;
      long x_low = x & 0xFFFFFFFFL;
      long y_low = y & 0xFFFFFFFFL;
      
      long z2 = x_low * y_low;
      long t = x_high * y_low + (z2 >>> 32);
      long z1 = t & 0xFFFFFFFFL;
      long z0 = t >>> 32;
      z1 += x_low * y_high;
      return x_high * y_high + z0 + (z1 >>> 32);
    }
          
          /**
          * Returns lowest 64 bits of either signed or unsigned long multiplication.
          *
          * @param x the number
          * @param y the number
          * @return lowest 64 bits of long multiplication.
          */
    public static long multiplyLow(long x, long y) {
      long n = x * y;
      return n;
    }
          
          /**
          * Return's quotient and remainder of 128 bit integer division by 64 bit integer. <p> Code taken from Hacker's
          * Delight: http://www.hackersdelight.org/HDcode/divlu.c.
          *
          * @param u1 highest 64 dividend bits
          * @param u0 lowest 64 dividend bits
          * @param v  the divider
          * @return {quotient, remainder}
          */
    public static long[] divideAndRemainder128(long u1, long u0, long v) {
      long b = (1L << 32); // Number base (16 bits).
      long
      un1, un0,           // Norm. dividend LSD's.
      vn1, vn0,           // Norm. divisor digits.
      q1, q0,             // Quotient digits.
      un64, un21, un10,   // Dividend digit pairs.
      rhat;               // A remainder.
      int s;              // Shift amount for norm.
      
      if (u1 >= v)                          // If overflow, set rem.
        return new long[]{-1L, -1L};      // possible quotient.
      
      
      // count leading zeros
      s = Long.numberOfLeadingZeros(v); // 0 <= s <= 63.
      if (s > 0) {
        v = v << s;         // Normalize divisor.
        un64 = (u1 << s) | ((u0 >>> (64 - s)) & (-s >> 31));
        un10 = u0 << s;     // Shift dividend left.
      } else {
        // Avoid undefined behavior.
        un64 = u1 | u0;
        un10 = u0;
      }
      
      vn1 = v >>> 32;            // Break divisor up into
      vn0 = v & 0xFFFFFFFFL;     // two 32-bit digits.
      
      un1 = un10 >>> 32;         // Break right half of
      un0 = un10 & 0xFFFFFFFFL;  // dividend into two digits.
      
      q1 = Long.divideUnsigned(un64, vn1);            // Compute the first
      rhat = un64 - q1 * vn1;     // quotient digit, q1.
      while (true) {
        if (Long.compareUnsigned(q1, b) >= 0 || Long.compareUnsigned(q1 * vn0, b * rhat + un1) > 0) { //if (q1 >= b || q1 * vn0 > b * rhat + un1) {
          q1 = q1 - 1;
          rhat = rhat + vn1;
          if (Long.compareUnsigned(rhat, b) < 0)
            continue;
        }
        break;
      }
      
      un21 = un64 * b + un1 - q1 * v;  // Multiply and subtract.
      
      q0 = Long.divideUnsigned(un21, vn1);            // Compute the second
      rhat = un21 - q0 * vn1;     // quotient digit, q0.
      while (true) {
        if (Long.compareUnsigned(q0, b) >= 0 || Long.compareUnsigned(q0 * vn0, b * rhat + un0) > 0) {
          q0 = q0 - 1;
          rhat = rhat + vn1;
          if (Long.compareUnsigned(rhat, b) < 0)
            continue;
        }
        break;
      }
      long r = (un21 * b + un0 - q0 * v) >>> s;    // return it.
      return new long[]{q1 * b + q0, r};
    }
          
          /**
          * Computes magic for fast unsigned integer division.
          *
          * @param d the divider
          * @return the magic
          */
    public static Magic magicUnsigned(long d) {
      return magicUnsigned(d, false);
    }
          
          /**
          * Computes magic for fast unsigned integer division.
          *
          * @param d          the divider
          * @param branchfree branching free
          * @return the magic
          */
    public static Magic magicUnsigned(long d, boolean branchfree) {
      if (d == 0)
        throw new ArithmeticException("divide by zero");
      // 1 is not supported with branchfree algorithm
      assert (!branchfree || d != 1);
      
      long resultMagic;
      int resultMore;
      int floor_log_2_d = 63 - Long.numberOfLeadingZeros(d);
      if ((d & (d - 1)) == 0) {
        // Power of 2
        if (!branchfree) {
          resultMagic = 0;
          resultMore = floor_log_2_d | 0x80;
        } else {
          // We want a magic number of 2**64 and a shift of floor_log_2_d
          // but one of the shifts is taken up by LIBDIVIDE_ADD_MARKER, so we
          // subtract 1 from the shift
          resultMagic = 0;
          resultMore = (floor_log_2_d - 1) | 0x40;
        }
      } else {
        long proposed_m, rem;
        int more;
        
        long[] tmp = divideAndRemainder128(1L << floor_log_2_d, 0, d); // == (1 << (64 + floor_log_2_d)) / d
        proposed_m = tmp[0];
        rem = tmp[1];
        
        //            assert (rem > 0 && rem < d);
        long e = d - rem;
        
        // This power works if e < 2**floor_log_2_d.
        if (!branchfree && e < (1L << floor_log_2_d)) {
          // This power works
          more = floor_log_2_d;
        } else {
          // We have to use the general 65-bit algorithm.  We need to compute
          // (2**power) / d. However, we already have (2**(power-1))/d and
          // its remainder. By doubling both, and then correcting the
          // remainder, we can compute the larger division.
          // don't care about overflow here - in fact, we expect it
          proposed_m += proposed_m;
          long twice_rem = rem + rem;
          if (twice_rem >= d || twice_rem < rem) proposed_m += 1;
          more = floor_log_2_d | 0x40;
        }
        resultMagic = 1 + proposed_m;
        resultMore = more;
        // result.more's shift should in general be ceil_log_2_d. But if we
        // used the smaller power, we subtract one from the shift because we're
        // using the smaller power. If we're using the larger power, we
        // subtract one from the shift because it's taken care of by the add
        // indicator. So floor_log_2_d happens to be correct in both cases,
        // which is why we do it outside of the if statement.
      }
      return new Magic(resultMagic, resultMore, d);
    }
          
          /**
          * Returns unsigned {@code dividend / divider} using fast integer division
          *
          * @param dividend the dividend
          * @param divider  the divider
          * @return {@code dividend / divider }
          */
    public static long divideUnsignedFast(long dividend, Magic divider) {
      int more = divider.more;
      if ((more & 0x80) != 0) {
        return dividend >>> (more & 0x3F);
      } else {
        long q = multiplyHighUnsigned(divider.magic, dividend);
        if ((more & 0x40) != 0) {
          long t = ((dividend - q) >>> 1) + q;
          return t >>> (more & 0x3F);
        } else {
          return q >>> more; // all upper bits are 0 - don't need to mask them off
        }
      }
    }
          
          
          /**
          * Computes magic for fast signed integer division.
          *
          * @param d the divider
          * @return the magic
          */
    public static Magic magicSigned(long d) {
      return magicSigned(d, false);
    }
          
          /**
          * Computes magic for fast integer division.
          *
          * @param d          the divider
          * @param branchfree use branch free computation
          * @return the magic
          */
    public static Magic magicSigned(long d, boolean branchfree) {
      if (d == 0)
        throw new ArithmeticException("divide by zero");
      assert (!branchfree || (d != 1 && d != -1));
      
      long resultMagic;
      int resultMore;
      // If d is a power of 2, or negative a power of 2, we have to use a shift.
      // This is especially important because the magic algorithm fails for -1.
      // To check if d is a power of 2 or its inverse, it suffices to check
      // whether its absolute value has exactly one bit set.  This works even for
      // INT_MIN, because abs(INT_MIN) == INT_MIN, and INT_MIN has one bit set
      // and is a power of 2.
      long ud = d;
      long absD = (d < 0 ? -ud : ud); // gcc optimizes this to the fast abs trick
      int floor_log_2_d = 63 - Long.numberOfLeadingZeros(absD);
      // check if exactly one bit is set,
      // don't care if absD is 0 since that's divide by zero
      if ((absD & (absD - 1)) == 0) {
        // Branchfree and non-branchfree cases are the same
        resultMagic = 0;
        resultMore = floor_log_2_d;//|(d < 0 ? 0x80 : 0);
      } else {
        // the dividend here is 2**(floor_log_2_d + 63), so the low 64 bit word
        // is 0 and the high word is floor_log_2_d - 1
        int more;
        long rem, proposed_m;
        long[] tmp = divideAndRemainder128(1L << (floor_log_2_d - 1), 0, absD);
        proposed_m = tmp[0];
        rem = tmp[1];
        long e = absD - rem;
        
        // We are going to start with a power of floor_log_2_d - 1.
        // This works if works if e < 2**floor_log_2_d.
        if (!branchfree && e < (1L << floor_log_2_d)) {
          // This power works
          more = floor_log_2_d - 1;
        } else {
          // We need to go one higher. This should not make proposed_m
          // overflow, but it will make it negative when interpreted as an
          // int32_t.
          proposed_m += proposed_m;
          long twice_rem = rem + rem;
          if (Long.compareUnsigned(twice_rem, absD) >= 0 || Long.compareUnsigned(twice_rem, rem) < 0)
            proposed_m += 1;
          // note that we only set the LIBDIVIDE_NEGATIVE_DIVISOR bit if we
          // also set ADD_MARKER this is an annoying optimization that
          // enables algorithm #4 to avoid the mask. However we always set it
          // in the branchfree case
          more = floor_log_2_d | 0x40;
        }
        proposed_m += 1;
        long magic = proposed_m;
        
        //            //Mark if we are negative
        //            if (d < 0) {
        //                more |= 0x80;
        //                if (!branchfree) {
        //                    magic = -magic;
        //                }
        //            }
        
        resultMore = more;
        resultMagic = magic;
      }
      return new Magic(resultMagic, resultMore, d);
    }
          
          /**
          * Returns signed {@code dividend / divider} using fast integer division
          *
          * @param dividend the dividend
          * @param divider  the divider
          * @return {@code dividend / divider }
          */
    public static long divideSignedFast(long dividend, Magic divider) {
      int more = divider.more;
      long magic = divider.magic;
      if (magic == 0) { //shift path
        int shifter = more & 0x3F;
        long uq = dividend + ((dividend >> 63) & ((1L << shifter) - 1));
        long q = uq;
        q = q >> shifter;
        // must be arithmetic shift and then sign-extend
        long shiftMask = more >> 7;
        q = (q ^ shiftMask) - shiftMask;
        return divider.divider < 0 ? -q : q;
      } else {
        long uq = multiplyHighSigned(magic, dividend);
        if ((more & 0x40) != 0) {
          // must be arithmetic shift and then sign extend
          long sign = more >>> 7;
          uq += (((long) dividend ^ sign) - sign);
        }
        long q = (long) uq;
        q >>= more & 0x3F;
        if (q < 0)
          q += 1;
        return divider.divider < 0 ? -q : q;
      }
    }
          
          /**
          * Calculates the remainder using fast integer division
          *
          * @param dividend the dividend
          * @param divider  the divider
          * @return {@code dividend % divider }
          */
    public static long remainderSignedFast(long dividend, Magic divider) {
      long quot = divideSignedFast(dividend, divider);
      return dividend - quot * divider.divider;
    }
          
          /**
          * Calculates the remainder using fast integer division
          *
          * @param dividend the dividend
          * @param divider  the divider
          * @return {@code dividend % divider }
          */
    public static long remainderUnsignedFast(long dividend, Magic divider) {
      long quot = divideUnsignedFast(dividend, divider);
      return dividend - quot * divider.divider;
    }
          
          /**
          * Computes floor division of the dividend by the divider using fast integer division returning (meaningful for
          * signed operations)
          *
          * @param dividend the dividend
          * @param divider  the divider
          * @return {@code dividend / divider }
          * @see Math#floorDiv(long, long)
          */
    public static long floorDivideFast(long dividend, Magic divider) {
      long r = divideSignedFast(dividend, divider);
      // if the signs are different and modulo not zero, round down
      if ((dividend ^ divider.divider) < 0 && (r * divider.divider != dividend)) {
        r--;
      }
      return r;
    }
          
          /**
          * Calculates the modulus using fast integer division
          *
          * @param dividend the dividend
          * @param divider  the divider
          * @return {@code dividend % divider }
          */
    public static long modSignedFast(long dividend, Magic divider) {
      long div = divideSignedFast(dividend, divider);
      long m = dividend - div * divider.divider;
      if (m < 0)
        m += divider.divider;
      return m;
    }
          
          /**
          * Calculates the modulus using fast integer division
          *
          * @param dividend the dividend
          * @param divider  the divider
          * @return {@code dividend % divider }
          */
    public static long modUnsignedFast(long dividend, Magic divider) {
      return dividend - divideUnsignedFast(dividend, divider) * divider.divider;
    }
          
          /**
          * Computes magic for fast mulmod operation.
          *
          * @param divider the divider (must be positive)
          * @return the magic
          */
    public static Magic magic32ForMultiplyMod(long divider) {
      long v = divider;
      int s = Long.numberOfLeadingZeros(v); // 0 <= s <= 63.
      if (s > 0)
        v = v << s;
      return magicUnsigned(v >>> 32);
    }
          
          /**
          * Returns unsigned {@code (a*b)%divider}
          *
          * @param a       the first multiplier
          * @param b       the second multiplier
          * @param divider the divider
          * @param magic32 magic for fast division {@link #magic32ForMultiplyMod(long)}
          * @return {@code (a*b)%divider }
          */
    public static long multiplyMod128Unsigned(long a, long b, long divider, Magic magic32) {
      return multiplyMod128Unsigned0(multiplyHighUnsigned(a, b), multiplyLow(a, b), divider, magic32);
    }
          
          /**
          * Returns unsigned {@code (low|(high<<64))%divider}
          *
          * @param high    the highest bits
          * @param low     the lowest bits
          * @param divider the divider
          * @param magic32 magic for fast division {@link #magic32ForMultiplyMod(long)}
          * @return {@code (low|(high<<64))%divider}
          */
    public static long multiplyMod128Unsigned0(long high, long low, long divider, Magic magic32) {
      long b = (1L << 32); // Number base (16 bits).
      long
      un1, un0,           // Norm. dividend LSD's.
      vn1, vn0,           // Norm. divisor digits.
      q1, q0,             // Quotient digits.
      un64, un21, un10,   // Dividend digit pairs.
      rhat;               // A remainder.
      int s;              // Shift amount for norm.
      
      if (high >= divider)                          // If overflow, set rem.
        throw new IllegalArgumentException();
      
      
      // count leading zeros
      s = Long.numberOfLeadingZeros(divider); // 0 <= s <= 63.
      if (s > 0) {
        divider = divider << s;         // Normalize divisor.
        un64 = (high << s) | ((low >>> (64 - s)) & (-s >> 31));
        un10 = low << s;     // Shift dividend left.
      } else {
        // Avoid undefined behavior.
        un64 = high | low;
        un10 = low;
      }
      
      vn1 = divider >>> 32;            // Break divisor up into
      vn0 = divider & 0xFFFFFFFFL;     // two 32-bit digits.
      
      un1 = un10 >>> 32;         // Break right half of
      un0 = un10 & 0xFFFFFFFFL;  // dividend into two digits.
      
      q1 = divideUnsignedFast(un64, magic32);            // Compute the first
      rhat = un64 - q1 * vn1;     // quotient digit, q1.
      while (true) {
        if (Long.compareUnsigned(q1, b) >= 0 || Long.compareUnsigned(q1 * vn0, b * rhat + un1) > 0) { //if (q1 >= b || q1 * vn0 > b * rhat + un1) {
          q1 = q1 - 1;
          rhat = rhat + vn1;
          if (Long.compareUnsigned(rhat, b) < 0)
            continue;
        }
        break;
      }
      
      un21 = un64 * b + un1 - q1 * divider;  // Multiply and subtract.
      
      q0 = divideUnsignedFast(un21, magic32);            // Compute the second
      rhat = un21 - q0 * vn1;     // quotient digit, q0.
      while (true) {
        if (Long.compareUnsigned(q0, b) >= 0 || Long.compareUnsigned(q0 * vn0, b * rhat + un0) > 0) {
          q0 = q0 - 1;
          rhat = rhat + vn1;
          if (Long.compareUnsigned(rhat, b) < 0)
            continue;
        }
        break;
      }
      long r = (un21 * b + un0 - q0 * divider) >>> s;    // return it.
      return r;
    }
          
          /**
          * Magic structure.
          */
    public static final class Magic
    implements java.io.Serializable {
      private static final long serialVersionUID = 1L;
            /** The magic number */
      public final long magic;
            /** Shifting bits **/
      public final int more;
            /** The divider **/
      public final long divider;
            
      public Magic(long magic, int more, long divider) {
        this.magic = magic;
        this.more = more;
        this.divider = divider;
      }
    }
  }
} // end of class Rechner
    
