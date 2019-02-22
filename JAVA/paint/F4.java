/**
*@Author Dawid Więcłąw
*F2.java
*Prosty program od rysowania
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.geom.GeneralPath;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;
import static java.lang.Math.*;




/**
*Klasa opisujaca dzialanie przycisków
*/
class MyButtonAdapter implements ActionListener
{
	/**obiekt w ktorym akcja dodania przycisku wykona sie*/
	private Surface s;
	/**obiekt w ktorym akcja pokazania informacji o programie wykona sie*/
	private MyFrame owner;
	/**nazwa przycisku decydujaca o wykonanej funckji*/
	String name;
	/**
	*@param s panel do rysowania na ktorym bedzie dzialala funkcja actionPerformed
	*@param name nazwa ktora generuje odpowiednia komende w funkcji actionPerformed
	*/
	MyButtonAdapter(Surface s, String name)
	{
		this.s=s;
		this.name=name;
	}
	/**
	@param owner
	*frame z ktorego bedzie wywolana funkcja pokazania informacji o programie
	*/
	MyButtonAdapter(MyFrame owner)
	{
		this.owner=owner;
		this.name="Info";
	}
	/**
	*@param e nacisniecie przycisku
	*/
	public void actionPerformed (ActionEvent e)
	{
		if(name.equals("Info"))
		{
			owner.doDialogu();
			return;
		}
		else if(name.equals("R")) s.fig=1;
		else if(name.equals("C")) s.fig=2;
		else if(name.equals("P")) s.fig=3;
		else if(name.equals("Save")) s.Save();
		else if(name.equals("Open")) s.Open();
		s.repaint();
	}
}

/** Klasa w której odbywa się rysowanie */
class Surface extends JPanel
{
	/**tablica kwadratow*/
	private ArrayList <MyRectangle> rectangles; /**tablica kol*/
	private ArrayList <MyCircle> circles; /**tablica z kolejnoscia figur*/
	private ArrayList <Integer> list; /**tablica wielokatow*/
	private ArrayList <MyPolygon> polygons; /**frame w ktorym umieszczon jest surface*/
	private MyFrame owner; /**tryb pracy klasy frame: 1 - dodanie kwadratu 2 - dodanie kola 3 - dodanie 1. pkt wielokata 4 - dodanie kolejnego pkt wielokata*/
	public int fig=0; /**plik otwierania zapisywania*/
	private File file; /**pole odpowiadajace za zapis do pliku*/
	private PrintWriter saving;
	
	/**
	*Konstruktor panelu do rysowania
	*@param owner zostaje przekazywany do dialogu kazdej figury
	*/
	public Surface(MyFrame owner) 
	{
		this.owner=owner;
		MovingAdapter ma = new MovingAdapter();
		
		rectangles=new ArrayList <MyRectangle> ();
		circles=new ArrayList <MyCircle> ();
		polygons=new ArrayList <MyPolygon> ();
		list=new ArrayList<Integer>();
		
        addMouseMotionListener(ma);
        addMouseListener(ma);
        addMouseWheelListener(new ScaleHandler());
	}
	
	/**
	*Funckja otwierająca plik w którym zapisane są dane	
	*/
	
	public void Open()
	{
		fig=0;
		repaint();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT FILES", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(owner);
		if(returnVal == JFileChooser.APPROVE_OPTION) System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
		else return;
		file=chooser.getSelectedFile();
		if(!file.exists())
		{ 
			System.out.println("niem a");
			return;
		}
		list.clear();
		rectangles.clear();
		polygons.clear();
		circles.clear();
		try
		{
			Scanner in=new Scanner(file);
			while(in.hasNextLine())
			{
				String line=in.nextLine();
				String[] parts=line.split(" ");
				try
				{
					int type=Integer.parseInt(parts[0]);
					if(type==0) rectangles.add(new MyRectangle(Float.parseFloat(parts[1]),Float.parseFloat(parts[2]),Float.parseFloat(parts[3]),Float.parseFloat(parts[4]),parts[5]));
					else if(type==1) circles.add(new MyCircle(Float.parseFloat(parts[1]),Float.parseFloat(parts[2]),Float.parseFloat(parts[3]),parts[5]));
					else if(type==2) polygons.add(new MyPolygon(parts));
					else return;
					list.add(type);
				}
				catch(NumberFormatException ex)
				{
					return;
				}
			}
			repaint();
		}
		catch(Exception e)
		{
			System.out.println("blad odczytu");
		}
	}
	
	/**
	*Funkcja odpowiedzialna za zapis do pliku
	*/
	public void Save()
	{
		fig=0;
		repaint();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT FILES", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(owner);
  		if(returnVal == JFileChooser.APPROVE_OPTION) System.out.println("You chose to save to this file: " + chooser.getSelectedFile().getName());
		else return;
		file=chooser.getSelectedFile();
		if(file.exists()) file.delete();
		file=chooser.getSelectedFile();
		
		try
		{
			file.createNewFile();
			FileOutputStream filestream = new FileOutputStream(file);
			int k=0;
	   		int l=0;
			int m=0;
			
			for(int i=0; i<list.size(); i++)
			{	
	   			if(list.get(i)==0)
				{
					String d =rectangles.get(k).getInfo() + "\n";
					byte[] buf = d.getBytes();
					filestream.write(buf, 0,buf.length);
					k++;
				}
				else if(list.get(i)==1)
				{
					String d = circles.get(l).getInfo()+ "\n";
					byte[] buf = d.getBytes();
					filestream.write(buf, 0,buf.length);
					l++;
				}
				else if(list.get(i)==2)
				{
					String d = polygons.get(m).getInfo() + "\n";
					byte[] buf = d.getBytes();
					filestream.write(buf, 0,buf.length);
					m++;
				}
			}
			
			filestream.close();
		}
		catch(Exception ex)
		{
			System.out.println("nie da sie");
		}
	}
	
	/**
	*Funkcja rysujaca
	*/
	
	 private void doDrawing(Graphics g) 
	 {
       Graphics2D g2d = (Graphics2D) g;
       
	   if(polygons.size()!=0 && !polygons.get(polygons.size()-1).complete())
	   {
	   		System.out.println(polygons.size());
	   		polygons.remove(polygons.size()-1);
			list.remove(list.size()-1);
			System.out.println(polygons.size());
	   }
	   int m=0;
	   int k=0;
	   int l=0;
	   for(int i=0; i<list.size(); i++)
	   {
	   		if(list.get(i)==0)
			{
				g2d.setPaint(rectangles.get(k).getColor());
        		g2d.fill(rectangles.get(k));
				k++;
			}
			else if(list.get(i)==1)
			{
				g2d.setPaint(circles.get(l).getColor());
        		g2d.fill(circles.get(l));
				l++;
			}
			else if(list.get(i)==2)
			{
				g2d.setPaint(polygons.get(m).getColor());
				g2d.fill(polygons.get(m).getPolygon());
				m++;
			}
		}
	}
	
    public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        doDrawing(g);        
    }
	
	/**
	*Klasa pomocnicza w zapisywaniu wirzcholkow do ArrayList
	*/
	
	class Pair
	{
		public double x;
		public double y;
		Pair(double x, double y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	/**
	*Klasa wielokąta
	*/
	class MyPolygon 
	{
		/**wspolrzedne skrajne*/
		private double xmax,ymax,xmin,ymin; /**tablica punktow*/
		private ArrayList <Pair>  points; /**pole umozliwiajacae rysowanie*/
		private GeneralPath polygon; /**kolor wielokata - domyślnie - zolty*/
		private Color c=Color.yellow; /**inforacja o domknieciu wielokata*/
		private boolean complete=false;
		/**Dialog sluacy do zmiany koloru*/
		MyDialog d; /**klawisz czerwony*/
		MyDialogButton cred; /**klawisz nibieski*/
		MyDialogButton cblue; /**klawisz zolty*/
		MyDialogButton cyellow;
		
		/**
		*Funkcja zwracajaca informacje czy myszka natrafila na figure
		*@param x wspolrzedna x klikniecia
		*@param y wspolrzedna y klikniecia
		*@return informacja o tym czy natrafiono na figure
		*/
		
		public boolean isHit(double x, double y) 
		{    
            return polygon.getBounds2D().contains(x, y);
        }
		
		/** @return informacja o figurze do zapisu w pliku */
		
		public String getInfo()
		{
			String info="";
			info+="2 ";
			info+=points.size();
			info+=" " + points.get(0).x +" "+ points.get(0).y;
			for(int i=1; i<points.size(); i+=1)
			{
				info+=" " + points.get(i).x +" "+ points.get(i).y;
			}
			
			if(this.c.equals(Color.yellow)) info+=" yellow";
			else if(this.c.equals(Color.blue)) info+=" blue";
			else if(this.c.equals(Color.red)) info+=" red";
			return info;
		}
		
		/**
		*Funkcja zmieniajaca kolor figury
		*@param c kolor ktory ma zostac ustanowiony
		*/
		
		public void setColor(Color c)
		{
			this.c=c;
		}
		
		/** Funkcja wywoluje dialog zmieniajacy kolor figury */
		
		public void doDialogu()
		{
			d=new MyDialog(owner);
			
			cred=new MyDialogButton(this, d, Color.red, "czerwony");
			cblue=new MyDialogButton(this, d, Color.blue, "nibieski");
			cyellow=new MyDialogButton(this, d, Color.yellow, "zolty");
			
			cred.setBounds(10,10,100,30);
			cblue.setBounds(10,50,100,30);
			cyellow.setBounds(10,90,100,30);
			
			d.add(cred);
			d.add(cyellow);
			d.add(cblue);
			 
			d.setVisible(true);
		}
		
		/**
		*Funkcja wywolywana podczas przesuniecia figury
		*@param x przesuniecie wspolrzednej x
		*@param y przesuniecei wspolrzednej y
		*@return figura o nowych rozmiarach
		*/
		
		public MyPolygon addXY(double x,double y) 
		{
            if(this.xmin+x>=0 && this.ymin+y>=0 && this.xmax+x<=1000 && this.ymax+y<=370)
			{	
				xmin+=x;
				xmax+=x;
				ymin+=y;
				ymax+=y;
				
				for(int i=0; i<points.size(); i++)
				{
					points.set(i, new Pair(points.get(i).x+x,points.get(i).y+y));
				}
			}
			setReady();
			return this;
        }
		
		/**@return Aktualny kolor figury*/

		public Color getColor()
		{
			return c;
		}
		
		/**
		*Konstruktor wywolywany przez reczne tworzenie figury na panelu Surface
		*@param x wspolrzedna x pierwszego wierzcholka wielokata
		*@param y wspolrzedna y wielokata
		*/
		
		public MyPolygon(double x, double y)
		{
			points=new ArrayList<Pair> ();
			points.add(new Pair(x,y));
			xmin=x;
			xmax=x;
			ymin=y;
			ymax=y;
		}
		
		/**
		*Konstruktor wywolywaniy w przypadku odczytu z pliku
		*@param parts[] zawiera wszystkei dane dotyczace figury
		*/
		
		public MyPolygon(String parts[])
		{
			points=new ArrayList<Pair> ();
			try
			{
				int pointsnumber=Integer.parseInt(parts[1]);
				xmin=Double.parseDouble(parts[2]);
				xmax=xmin;
				ymin=Double.parseDouble(parts[3]);
				ymax=ymin;
				for(int i=2; i<parts.length-2; i+=2) //watpliwa akcja
				{
					points.add(new Pair(Double.parseDouble(parts[i]) , Double.parseDouble(parts[i+1])));
					if(xmax<Double.parseDouble(parts[i])) xmax=Double.parseDouble(parts[i]);
					if(ymax<Double.parseDouble(parts[i+1])) ymax=Double.parseDouble(parts[i+1]);
					if(xmin>Double.parseDouble(parts[i])) xmin=Double.parseDouble(parts[i]);
					if(ymin>Double.parseDouble(parts[i+1])) ymin=Double.parseDouble(parts[i+1]);
				}
				points.add(points.get(0));
				if(parts[parts.length-1].equals("yellow")) setColor(Color.yellow);
				if(parts[parts.length-1].equals("blue")) setColor(Color.blue);
				if(parts[parts.length-1].equals("red")) setColor(Color.red);
				setReady();
			}
			catch(NumberFormatException ex)
			{
				System.out.println("cos nie tak");
			}
		}
		
		/** @return Wymiary figury */
		
		public String getSize()
		{
			double length=xmax-xmin;
			double width=ymax-ymin;
			
			return (Math.round(length) + "x" + Math.round(width));
		}
		
		/**
		*Funkcja wywolywana przez zmiane rozmiaru minimum to 50x50 px
		*@return Figura o nowych rozmiarach
		*@param w wartosc przesuniecia scrolla myszki
		*/
		
		public MyPolygon addSize(double w)
		{
			w/=1.91;
			double dx;
			double dy;
			double avx=(xmax+xmin)/2;
			double avy=(ymax+ymin)/2;
			double xmini,xmaxi,ymini,ymaxi;
			
			if(w>0)
			{
				dx=xmax-avx;
				dx*=w;
				xmaxi=dx+avx;
				
				
				dx=xmin-avx;
				dx*=w;
				xmini=dx+avx;
				
				dy=ymax-avy;
				dy*=w;
				ymaxi=dy+avy;
				
				dy=ymin-avy;
				dy*=w;
				ymini=dy+avy;
			}
			else if(w==0) return this;
			else
			{
				dx=xmax-avx;
				dx/=-w;
				xmaxi=dx+avx;
				
				dx=xmin-avx;
				dx/=-w;
				xmini=dx+avx;
				
				dy=ymax-avy;
				dy/=-w;
				ymaxi=dy+avy;
				
				dy=ymin-avy;
				dy/=-w;
				ymini=dy+avy;
			}
			
			if(xmaxi-xmini<50) return this;
			if(xmini<1) return this;
			if(xmaxi>999) return this;	
				
			if(ymaxi-ymini<50) return this;
			if(ymaxi>370) return this;
			if(ymini<1) return this;
			
			xmax=xmaxi;
			xmin=xmini;
			ymax=ymaxi;
			ymin=ymini;
				
			
			for(int i=0; i<points.size(); i++)
			{
				dx=points.get(i).x-avx;
				dy=points.get(i).y-avy;
				if(w>0)
				{
					dx*=w;
					dy*=w;
				}
				else if(w<0)
				{
					dx/=-w;
					dy/=-w;
				}
				dx+=avx;
				dy+=avy;
				points.set(i, new Pair(dx,dy));
			}
			
			setReady();
			return this;
		}
		
		/**
		*Funckja dodajaca koleny punkt podczas tworzenia nowej figury
		*Figura zostaje zamknieta w przypadku znalezienie sie nastepnego klikniecia w 30 pikselowym otoczeniu pierwszego punktu
		*@return informacja czy figura nie zostala zamknieta
		*@param x wspolrzednia x dodawanaego punktu
		*@param y wspolrzedna y dodawanego punktu
		*/
		
		public boolean addPoint(double x, double y)
		{
			if(Math.abs(x-points.get(0).x)<30 && Math.abs(y-points.get(0).y)<10)
			{
				System.out.println("utworzono");
				points.add(points.get(0));
				setReady();
				System.out.println(points.size());
				return false;
			}
			else if(Math.abs(x-points.get(points.size()-1).x)<30 && Math.abs(y-points.get(points.size()-1).y)<10) return true;
			else
			{	
				System.out.println(points.size());
				points.add(new Pair(x,y));
				if(x>xmax) xmax=x;
				if(x<xmin) xmin=x;
				if(y>ymax) ymax=y;
				if(y<ymin) ymin=y;
				return true;
			}
		}
		
		/**@return Informacaj czy figura zostala prawidlowo skompletowana*/
		
		public boolean complete()
		{
			return (complete);
		}
		
		/** Funckja tworzy obiekt kalsy Generalpath w ktorym rysuje zadany wielokat */
		
		private void setReady()
		{
			fig=0;
			polygon=new GeneralPath();
			polygon.moveTo(points.get(0).x,points.get(0).y);
			for(int i=1; i<points.size(); i++)
			{
				polygon.lineTo(points.get(i).x, points.get(i).y);
			}
			polygon.closePath();
			if(points.size()>2) complete=true;
		}
		
		/** @return Reprezentacja wielokata w postaci mozliwej do narysowania	*/
		
		public GeneralPath getPolygon()
		{
			return polygon;
		}
	}
	
	/** Klasa opisujaca okrag */

	class MyCircle extends Ellipse2D.Float 
	{
		/** kolor figury */
		private Color c=Color.red; /** dialog zmieniajacy kolor */
		MyDialog d; /** przycisk czerwony */
		MyDialogButton cred; /**przycisk niebieski */
		MyDialogButton cblue; /**przycisk zolty */
		MyDialogButton cyellow;
		
		/** funkcja wywolujaca dialog w ktorym zmieniamy kolor figury */
		
		public void doDialogu()
		{
			d=new MyDialog(owner);
			
			cred=new MyDialogButton(this, d, Color.red, "czerwony");
			cblue=new MyDialogButton(this, d, Color.blue, "nibieski");
			cyellow=new MyDialogButton(this, d, Color.yellow, "zolty");
			
			cred.setBounds(10,10,100,30);
			cblue.setBounds(10,50,100,30);
			cyellow.setBounds(10,90,100,30);
			
			d.add(cred);
			d.add(cyellow);
			d.add(cblue);
			 
			d.setVisible(true);
		}
		
		/**
		*Funkcja zmieniajaca kolor figury
		*@param c kolor ktory ma zostac ustanowiony
		*/
		
		public void setColor(Color c)
		{
			this.c=c;
		}
		
		/** @return Aktualny kolor figury */
		
		public Color getColor()
		{
			return this.c;	
		}
		
		/** @return Informacja o figurze do zapisu przez funkcje save */
		
		public String getInfo()
		{
			String info="1 " + this.x + " " + this.y + " " + this .width + " " + this.height;
			if(this.c.equals(Color.yellow)) info+=" yellow";
			else if(this.c.equals(Color.red)) info+=" red";
			else if(this.c.equals(Color.blue)) info+=" blue";
			return info;
		}
		
		/**
		*Konstruktor kola
		*@param x opisuje wspolrzedna x polozenia kola
		*@param y opisuje wpsolrzedna y polozenia kola
		*@param r opisuje promine kola
		*@param c opisuje kolork ola
		*/
		
        public MyCircle(float x, float y, float r, String c) 
		{
            setFrame(x, y, r, r);
			if(c.equals("yellow")) this.c=Color.yellow;
			else if(c.equals("red")) this.c=Color.red;
			else if(c.equals("blue")) this.c=Color.blue;
        }

		/**
		*@return Infomracja czy myszka natrafila na figure
		*@param x wspolrzedna x klikniecia
		*@param y wspolrzedna y klikniecia
		*/
		
        public boolean isHit(float x, float y) 
		{    
            return getBounds2D().contains(x, y);
        }
		
		/**
		*Funkcja wywolywana podczas przesuniecia figury
		*@return figura o zmienionym polozeniu
		*@param x przesuniecie wspolrzednej x
		*@param y przesuniecei wspolrzednej y
		*/

        public MyCircle addXY(float x,float y) 
		{
            
            if(this.x+x>=0 && this.y+y>=0 && this.width+this.x+x<=1000 && this.height+this.y+y<=370)
			{
            	this.x += x;
				this.y += y;
			}
			return this;
        }
		
		/**
		*Funkcja wywolywana przez zmiane rozmiaru Minmum to 10x10 pikseli
		*Jest blokada wyjscia figury poza panel Surface
		*@param w wartosc przesuniecia scrolla myszki
		*@return Figura o zmienionym rozmiarze
		*/

        public MyCircle addSize(float w) 
		{
			if(this.width+w>10 && this.height+w>10 && this.width+this.x+w<=1000 && this.height+this.y+w<=370)
			{
            	this.width += w;     
            	this.height += w;
			}
			return this;
        }
    }
	
	/** Klasa opsiujaca prostokat */
	
	class MyRectangle extends Rectangle2D.Float 
	{
		/**Kolor figury*/
		private Color c=Color.blue; /**dialog do zmiany kolorow*/
		MyDialog d; /**klawisz czerwony*/
		MyDialogButton cred; /**klawisz niebieski*/
		MyDialogButton cblue; /**klawisz zolty*/
		MyDialogButton cyellow;
		
		/** @return Aktualny kolor figury */
		
		public Color getColor()
		{
			return this.c;
		}
		
		/**
		*Funkcja zmieniajaca kolor figury
		*@param c kolor ktory ma zostac ustanowiony
		*/
		
		public void setColor(Color c)
		{
			this.c=c;
		}
			
		/** Funkcja wywoluje dialog zmieniajacy kolor figury */	
			
		public void doDialogu()
		{
			d=new MyDialog(owner);
			
			cred=new MyDialogButton(this, d, Color.red, "czerwony");
			cblue=new MyDialogButton(this, d, Color.blue, "nibieski");
			cyellow=new MyDialogButton(this, d, Color.yellow, "zolty");
			
			cred.setBounds(10,10,100,30);
			cblue.setBounds(10,50,100,30);
			cyellow.setBounds(10,90,100,30);
			
			d.add(cred);
			d.add(cyellow);
			d.add(cblue);
			 
			d.setVisible(true);
		}
		
		/** @return String linia do zapisu figury w pliku */
	
		public String getInfo()
		{
			String info="0 " + this.x + " " + this.y + " " + this .width + " " + this.height;
			if(this.c.equals(Color.yellow)) info+=" yellow";
			else if(this.c.equals(Color.red)) info+=" red";
			else if(this.c.equals(Color.blue)) info+=" blue";
			return info;
		}
		
		/**
		*konstruktor prostokata
		*@param x wspolrzednia poczatkowa x
		*@param y wpsolrzedna poczatkowa y
		*@param width szerokosc
		*@param height wysokosc
		*@param c kolor
		*/
		
		
        public MyRectangle(float x, float y, float width, float height, String c) //ma byc od stringa i ify w konstruktorze
		{
            setRect(x, y, width, height);

			if(c.equals("yellow")) this.c=Color.yellow;
			else if(c.equals("red")) this.c=Color.red;
			else if(c.equals("blue")) this.c=Color.blue;
        }
		
		/**
		*@return informacja o tym czy myszka natrafila na figure
		*@param x wspolrzedna x klikniecia
		*@param y wspolrzedna y klikniecia
		*/
		
        public boolean isHit(float x, float y) 
		{    
            return getBounds2D().contains(x, y);
        }
		
		/**
		*Funkcja wywolywana podczas przesuniecia figury
		*@return Figura o zmienionym polozeniu
		*@param x przesuniecie wspolrzednej x
		*@param y przesuniecei wspolrzednej y
		*/

        public MyRectangle addXY(float x,float y) 
		{
            if(this.x+x>=0 && this.y+y>=0 && this.width+this.x+x<=1000 && this.height+this.y+y<=370)
			{
            	this.x += x;
				
				this.y += y;
			}
			return this;
        }
		
		/**
		*Funkcja wywolywana przez zmiane rozmiaru
		*@return Figura o zmienionym rozmiarze
		*@param w wartosc przesuniecia scrolla myszki
		*/

        public MyRectangle addSize(float w) 
		{
			if(this.width+w>10 && this.height+w>10 && this.width+this.x+w<=1000 && this.height+this.y+w<=370)
			{
            	this.width += w;
				this.height += w;
			}
			return this;
        }
    }
	
	/** klasa odpowiadajacaca za obsluge Myszki */
	
	class MovingAdapter extends MouseAdapter 
	{

        private int x;
        private int y;
		/**
		*Funckaj odpowiadajacaa za klikniece myszki
		*/
		public void mouseClicked(MouseEvent e)
		{
			x=e.getX();
			y=e.getY();
			
			if(!e.isAltDown())return;
			if(fig!=0)return;
			repaint();
			int k=rectangles.size()-1;
			int l=circles.size()-1;
			int m=polygons.size()-1;
			while(l+k+m>-3)
	  	    {
	   			if(list.get(l+k+m+2)==0)
				{
	   				if(rectangles.get(k).isHit(x,y))
					{
						rectangles.get(k).doDialogu();
						repaint();
						break;
					}
					else k--;
				}
			
				else if (list.get(l+k+m+2)==1)
				{
					if(circles.get(l).isHit(x,y))
					{
						circles.get(l).doDialogu();
						repaint();
						break;
					}
					else l--;
				}
				else if(list.get(l+k+m+2)==2)
				{
					if(polygons.get(m).isHit(x,y))
					{
						polygons.get(m).doDialogu();
						repaint();
						break;
					}	
					else m--;
				}
			}
		}
	
		/**
		*Funckaj opisujaca nacisniecei myszy
		*pozwala tworzyc nowe figury
		*/
		
        public void mousePressed(MouseEvent e) 
		{
            x = e.getX();
            y = e.getY();
			if(fig==1)
			{
				fig=0;
				rectangles.add(new MyRectangle(x,y,40,40,"yellow"));
				list.add(0);
				repaint();
			}
			else if(fig==2)
			{
				fig=0;
				circles.add(new MyCircle(x,y,30,"blue"));
				list.add(1);
				repaint();
			}
			else if(fig==3)
			{
				MyPolygon p=new MyPolygon(x,y);
				list.add(2);
				polygons.add(p);
				fig=4;
				return;
			}
			else if(fig==4)
			{
				if(polygons.get(polygons.size()-1).addPoint(x,y));
				else 
				{
					repaint();
					fig=0;
				}
				return;
			}	
        }
		
		/** Funkcja wywolywana przez przesuniecei myszy */

        public void mouseDragged(MouseEvent e) 
		{
            doMove(e);
        }   
        
		/** Funckaj przesuwajaca figury */
		
        private void doMove(MouseEvent e) 
		{
            if(fig!=0)return;
            int dx = e.getX() - x;
            int dy = e.getY() - y;
			int k=rectangles.size()-1;
	   	    int l=circles.size()-1;
	   		int m=polygons.size()-1;
			while(l+k+m>-3)
	  		{
				if(list.get(l+k+m+2)==0)
				{
	   				if(rectangles.get(k).isHit(x,y))
					{
						owner.cordX.setText("x: " + rectangles.get(k).x);
						owner.cordY.setText("y: " + rectangles.get(k).y);
						owner.gsize.setText("size: " + rectangles.get(k).getHeight() + "x" + rectangles.get(k).getWidth()); 
					
						rectangles.set(k, rectangles.get(k).addXY(dx,dy));
						setHigherRectangle(k,l+k+m+2);
						repaint();
						break;
					}
				
					else k--;
					
				}
				else if (list.get(l+k+m+2)==1)
				{
					if(circles.get(l).isHit(x,y))
					{
						owner.cordX.setText("x: " + circles.get(l).x);
						owner.cordY.setText("y: " + circles.get(l).y);
						owner.gsize.setText("size: " + circles.get(l).getHeight() + "x" + circles.get(l).getWidth()); 
					
						circles.set(l, circles.get(l).addXY(dx,dy));
						setHigherCircle(l,l+k+m+2);
						repaint();
						break;
					}
					else l--;
				}
				else if(list.get(l+k+m+2)==2)
				{
					if(polygons.get(m).isHit(x,y))
					{
						owner.cordX.setText("x: " + Math.round(polygons.get(m).xmin) + "-" + Math.round(polygons.get(m).xmax));
						owner.cordY.setText("y: " + Math.round(polygons.get(m).ymin) + "-" + Math.round(polygons.get(m).ymax));
						owner.gsize.setText("size: " + polygons.get(m).getSize());
					
						polygons.set(m, polygons.get(m).addXY(dx,dy));
						setHigherPolygon(m, l+k+m+2);
						repaint();
						break;
					}
					else m--;
				}
			}
			
            x += dx;
            y += dy;    
        }
    }
	
	/** Klasa ktora opisuje zmiane rozmiaru figur */
	
	class ScaleHandler implements MouseWheelListener 
	{
        public void mouseWheelMoved(MouseWheelEvent e) 
		{
            doScale(e);
        }
        
		/**
		*Funkcja dodajaca wartusc przesuniecia scrolla myszy do rozmiar figur
		*/
		
        private void doScale(MouseWheelEvent e) 
		{
            int x = e.getX();
            int y = e.getY();

            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) 
			{
				int k=rectangles.size()-1;
				int l=circles.size()-1;
				int m=polygons.size()-1;
				
	   
	  			 while(l+k+m>-3)
	  			 {
	   				if(list.get(l+k+m+2)==0)
					{
	   					if(rectangles.get(k).isHit(x,y))
						{
							float amount =  e.getWheelRotation() * 5f;
							rectangles.set(k,rectangles.get(k).addSize(amount));
							repaint();
							break;
						}
						else k--;
					}
					else if (list.get(l+k+m+2)==1)
					{
						if(circles.get(l).isHit(x,y))
						{
							float amount =  e.getWheelRotation() * 5f;
                    		circles.set(l, circles.get(l).addSize(amount));
                   			repaint();
							break;
						}
						else l--;
					}
					else if (list.get(l+k+m+2)==2)
					{
						if(polygons.get(m).isHit(x,y))
						{
							float amount =  e.getWheelRotation() *2f;
                    		polygons.set(m, polygons.get(m).addSize(amount));
                   			repaint();
							break;
						}
						else m--;
					}
				}
            }    
        }
    }
	
	/**
	*Funckaj ustawiajaca prostokat na "samej gorze"
	*@param index - miejsce w tablicy rectangles
	*@param listindex - miejsce w tablicy list
	*/
	
	private void setHigherRectangle(int index,int listindex)
	{
		MyRectangle help;
		help=rectangles.get(index);
		for( ; index<rectangles.size()-1; index++)
		{
			rectangles.set(index, rectangles.get(index+1));
		}
		for( ; listindex<list.size()-1; listindex++)
		{
			list.set(listindex, list.get(listindex+1));
		}
		rectangles.set(rectangles.size()-1,help);
		list.set(list.size()-1, 0);
	}
	
	/**
	*Funckaj ustawiajaca kolo na "samej gorze"
	*@param index - miejsce w tablicy rectangles
	*@param listindex - miejsce w tablicy list
	*/
	
	private void setHigherCircle(int index,int listindex)
	{
		MyCircle help;
		help=circles.get(index);
		
		for( ; index<circles.size()-1; index++)
		{
			circles.set(index, circles.get(index+1));
		}
		for( ; listindex<list.size()-1; listindex++)
		{
			list.set(listindex, list.get(listindex+1));
		}
		circles.set(circles.size()-1,help);
		list.set(list.size()-1, 1);
	}	
	
	/**
	*Funckaj ustawiajaca wilokat na "samej gorze"
	*@param index - miejsce w tablicy rectangles
	*@param listindex - miejsce w tablicy list
	*/
	
	private void setHigherPolygon(int index, int listindex)
	{
		MyPolygon help;
		help=polygons.get(index);
		
		for( ; index<polygons.size()-1; index++) polygons.set(index, polygons.get(index+1));
		for( ; listindex<list.size()-1; listindex++)
		{
			list.set(listindex, list.get(listindex+1));
		}
		polygons.set(polygons.size()-1,help);
		list.set(list.size()-1, 2);
	}
	
	/**
	*Klasa opsiuajca dialog ktory zostaje wywolywany w celu zmainy koloru figury
	*/
	
	class MyDialogButton extends Button
	{
		private MyRectangle r;
		private MyCircle cr;
		private MyPolygon p;
		private MyDialog d;
		Color c;
		
		/**
		*kontsruktor dla kwadratu
		*@param r  kwadrat
		*@param d  dialog w ktorym znajduje sie przycisk
		*@param c kolor ustanawiany
		*@param nazwa nazwa przycisku czyli tez nazwa koloru na ktory ma zostac zmieniona figura
		*/
		
		MyDialogButton(MyRectangle r, MyDialog d, Color c, String nazwa)
		{
			super(nazwa);
			this.d=d;
			this.r=r;
			this.c=c;
			addActionListener(new MyDialogButtonAdapter(r,d,c));
		}
		
		/**
		*kontsruktor dla kola
		*@param cr  kolo
		*@param d  dialog w ktorym znajduje sie przycisk
		*@param c kolor ustanawiany
		*@param nazwa nazwa przycisku czyli tez nazwa koloru na ktory ma zostac zmieniona figura
		*/
		
		MyDialogButton(MyCircle cr, MyDialog d, Color c, String nazwa)
		{
			super(nazwa);
			this.d=d;
			this.cr=cr;
			this.c=c;
			addActionListener(new MyDialogButtonAdapter(cr,d,c));
		}
		
		/**
		*kontsruktor dla wielokata
		*@param p wielokat
		*@param d  dialog w ktorym znajduje sie przycisk
		*@param c kolor ustanawiany
		*@param nazwa nazwa przycisku czyli tez nazwa koloru na ktory ma zostac zmieniona figura
		*/
		
		MyDialogButton(MyPolygon p, MyDialog d, Color c, String nazwa)
		{
			super(nazwa);
			this.d=d;
			this.p=p;
			this.c=c;
			addActionListener(new MyDialogButtonAdapter(p,d,c));
		}
	}
	
	/**
	*klasa opsiujaca dzialanie przyciskow w dialogu
	*/

	class MyDialogButtonAdapter implements ActionListener
	{
		private MyRectangle r;
		private MyCircle cr;
		private MyPolygon p;
		private MyDialog d;
		private Color c;
		private int fig;
		
		/**
		*konstruktor dla kwadratu
		*@param r kwadrat
		*@param d dialog w ktorym jest przycsik
		*@param c kolor na ktory ma zostac zmieniona figura
		*/
		MyDialogButtonAdapter(MyRectangle r, MyDialog d,Color c)
		{
			fig=0;
			this.r=r;
			this.d=d;
			this.c=c;
		}
		/**
		*konstruktor dla kola
		*@param cr kolo
		*@param d dialog w ktorym jest przycsik
		*@param c kolor na ktory ma zostac zmieniona figura
		*/
		MyDialogButtonAdapter(MyCircle cr, MyDialog d,Color c)
		{
			fig=1;
			this.cr=cr;
			this.d=d;
			this.c=c;
		}
		/**
		*konstruktor dla wielokata
		*@param p wielokat
		*@param d dialog w ktorym jest przycsik
		*@param c kolor na ktory ma zostac zmieniona figura
		*/
		MyDialogButtonAdapter(MyPolygon p, MyDialog d,Color c)
		{
			fig=2;
			this.p=p;
			this.d=d;
			this.c=c;
		}
		/**
		*Funcja wywolana przez przycisniecie przycisku
		
		*zmienai kolor figury
		*/
		
		public void actionPerformed (ActionEvent e)
		{
			if(fig==0)r.setColor(c);
			else if(fig==1)cr.setColor(c);
			else if(fig==2)p.setColor(c);
			d.setVisible(false);
		}
	}
	/**
	*Klasa opisujaca dialog w ktorym mozna wybrac nowy kolor figury
	*/
	class MyDialog extends JDialog
	{		
		/*
		*konstruktor dialogu
		*@param owner okno w ktorym dziala program
		*/
		MyDialog(MyFrame owner)
		{
			super(owner,"wybierz kolor", true);
			setLayout(null);
			setBounds(100,100, 300,300);
			setResizable(false);
		}
	}
}
		
/**
*Klasa opisujaca okno
*/

class MyFrame extends JFrame
{
	/** klawisz kwadratu */
	private Button r; /**klawisz kola */
	private Button c; /**klawisz wielokata*/
	private Button p; /** powierzchnia do rysowania */
	private Surface surface; /** informacaj o rozmiarze figury */
	public Label gsize; /**informacaj o wsp x figury */
	public Label cordX; /** informacja o wsp y figury */
	public Label cordY; /** przycisk z informacja o programie */
	private Button inf; /** dialog z inforfmacja o programie */
	private JDialog info; /** text wewnatrz dialogu */
	private JTextArea aboutme; /**przycisk zapisu */
	private Button Save; /** przycisk otwierania */
	private Button Open; 
	
	MyFrame()
	{
		super("Maly Paint");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,1000,500);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setBackground(Color.gray);
		surface=new Surface(this);
		r=new Button("R");
		c=new Button("C");
		p=new Button("P");
		r.addActionListener(new MyButtonAdapter(surface,"R"));
		c.addActionListener(new MyButtonAdapter(surface,"C"));
		p.addActionListener(new MyButtonAdapter(surface,"P"));
		
		surface.setBounds(0,100,1000,400);
		surface.setBackground(Color.white);
		add(surface);
		
		r.setBounds(10,10,80,80);
		r.setBackground(Color.white);
		c.setBounds(100,10,80,80);
		c.setBackground(Color.white);
		p.setBounds(730,10,80,80);
		p.setBackground(Color.white);
		add(r);
		add(c);
		add(p);
		
		gsize=new Label();
		cordX=new Label();
		cordY=new Label();
		
		cordX.setForeground(Color.black);
		cordY.setForeground(Color.black);
		gsize.setForeground(Color.black);
		
		gsize.setBounds(200,10,180,80);
		cordX.setBounds(520,10,100,80);
		cordY.setBounds(640,10,100,80);
		
		add(gsize);
		add(cordX);
		add(cordY);
		
		info=new JDialog(this, "Info", true);
		info.setLayout(null);
		info.setBounds(100,100, 300,300);
		info.setResizable(false);
		
		aboutme=new JTextArea("Napisane przez: Dawid Więcłąw" + "\n" +  "Nazwa - maly paint" + "\n" + "Przeznaczenie - program do rysowania" + "\n" + "Klasa F");
		aboutme.setLineWrap(true);
		aboutme.setWrapStyleWord(true);
		aboutme.setBounds(0,0,300,300);
		aboutme.setEditable(false);
		info.add(aboutme);
		
		inf=new Button("Info");
		inf.addActionListener(new MyButtonAdapter(this));
		inf.setBounds(820,10,170,80);
		inf.setBackground(Color.white);
		add(inf);
		
		Save=new Button("Save");
		Open=new Button("Open");
		Save.addActionListener(new MyButtonAdapter(surface, "Save"));
		Open.addActionListener(new MyButtonAdapter(surface, "Open"));
		Open.setBounds(390,10,120,35);
		Save.setBounds(390,55,120,35);
		Open.setBackground(Color.white);
		Save.setBackground(Color.white);
		add(Open);
		add(Save);
	}
		
	public void doDialogu()
	{	
		info.setVisible(true);
	}
}
/**
*Klasa glowna
*/
public class F4
{
	public static void main(String[] args) 
 	 {
   	  	MyFrame frame = new MyFrame();
   	 	frame.setVisible(true);
  	 }
}
