//Panel de Dibujo
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;

public class DrawFinal extends JFrame {

  //Variables
  String[] tablaString = new String[26];
  boolean debug = true;
  int Dibujar = 0;
  Color colorActual = Color.WHITE;
  private final BufferStrategy bf;
  int x1;
  int x2;
  int y1;
  int y2;
  int r;
  int xfix;
  int yfix;

  public DrawFinal()
  {
    //Tamaño ventana
    int width = 600;
    int height = 400;

    //Titulo
    this.setTitle("Entorno Grafico");
    setSize( width, height );

    //Fondo
    this.setBackground(Color.BLACK);

    //Salir
    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setVisible( true );

    //Fix de Area de Dibujo
    Insets insets = this.getInsets();
      int wreal = width - (insets.left + insets.right);
      int hreal = height - (insets.top + insets.bottom);
      //Arreglo para ventana grafica
      xfix = (int)((width - wreal)/2);
      yfix = (int)((height - hreal)- xfix);
      x1 = xfix;
      x2 = yfix;

    System.out.println("Cargando Entorno Grafico... espere un momento");
    System.out.println("Ingrese Instrucciones al Programa:");

    createBufferStrategy(2);
    bf = this.getBufferStrategy();

  }


  public void setColor(String color)
  {
    if(color.equals("rojo")) this.colorActual = Color.RED;
    else if(color.equals("verde")) this.colorActual = Color.GREEN;
    else if(color.equals("azul")) this.colorActual = Color.BLUE;
    else if(color.equals("amarillo")) this.colorActual = Color.YELLOW;
    else if(color.equals("blanco")) this.colorActual = Color.WHITE;
    this.repaint();
  }

  public void drawLinea(int tamaño, String seleccion)
  {
    switch(seleccion){
      case "arr":
      this.x2 = x1;
      this.y2 = y1-44*tamaño;
      break;
      case "aba":
      this.x2=x1;
      this.y2 = y1+44*tamaño;
      break;
      case "der":
      this.x2 = x1+44*tamaño;
      this.y2=y1;

      break;
      case "izq":
      this.x2 = x1-44*tamaño;
      this.y2 = y1;
      break;
    }
    this.Dibujar = 1;
    this.repaint();
  }


  public void setPosition(int x, int y)
  {
    this.x1=x;
    this.y1=y;
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = null;

    try {
      g2 = (Graphics2D) bf.getDrawGraphics();

      paint(g2);

      bf.show();
    }
    catch(Exception e)
    {
    }

  }
  public void toStringTable(String id, String valor)
  {
      tablaString[id.charAt(0)-'A'] = valor;
  }


  public String fromStringTable(String id)
  {
      return tablaString[id.charAt(0)-'A'];
  }


  public void paint( Graphics2D g2 )
  {
    //Setea el Color
    g2.setColor(this.colorActual);
    //Antialiasing

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //Gruesor del borde de las lineas

    g2.setStroke(new BasicStroke(2.0f));

    //Si dibujar=1, entonces dibujara la linea correspondiente
    if(this.Dibujar==1)
    {
      g2.drawLine(this.x1 +xfix, this.y1 +yfix, this.x2+xfix, this.y2+yfix );
      setPosition(this.x2,this.y2);

    }
    this.Dibujar = 0;

  }

}
