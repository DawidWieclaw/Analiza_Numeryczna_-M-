
package geometria;
import geometria.*;
public class Test 
{
	public static void main(String arg[])
	{
		String args[]= {"p", "p", "2", "3", "p", "w"};
		geometria.Punkt p=new geometria.Punkt();
		geometria.Odcinek o=new geometria.Odcinek();
		geometria.Trojkat t=new geometria.Trojkat();
		geometria.Wektor w=new geometria.Wektor();
		geometria.Prosta pr=new geometria.Prosta();
		
		for(int i=0; i<args.length;)
		{
			if(args[i].equals("p"))
			{
				if(args[i+1].equals("o"))
				{
					try { p.obroc(new geometria.Punkt(Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3])), Double.parseDouble(args[i+4])); }
					catch(Exception e) {System.out.println("blad1"); return;}
					i+=5;
				}
				else if (args[i+1].equals("p"))
				{
					try{p.przesun(new geometria.Wektor(Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3])));}
					catch(Exception e) {System.out.println("blad"); return;}
					i+=4;
				}
				else if(args[i+1].equals("od"))
				{
					try {p.odbij(pr);}
					catch(Exception e) {System.out.println("blad");}
					i+=2;
				}
				else if(args[i+1].equals("w"))
				{
					try {System.out.println(p.toString());}
					catch(Exception e) {System.out.println("blad");}
					i+=2;
				}
			}
			else if(args[i].equals("o"))
			{
				if(args[i+1].equals("o"))
				{
					try { o.obroc(new geometria.Punkt(Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3])), Double.parseDouble(args[i+4])); }
					catch(Exception e) {System.out.println("blad"); return;}
					i+=5;
				}
				else if (args[i+1].equals("p"))
				{
					try{o.przesun(new geometria.Wektor(Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3])));}
					catch(Exception e) {System.out.println("blad"); return;}
					i+=4;
				}
				else if(args[i+1].equals("od"))
				{
					try {o.odbij(pr);}
					catch(Exception e) {System.out.println("blad");}
					i+=2;
				}
				else if(args[i+1].equals("w"))
				{
					try {o.toString();}
					catch(Exception e) {System.out.println("blad");}
					i+=2;
				}
			}
	
			else if(args[i].equals("t"))
			{
				if(args[i+1].equals("o"))
				{
					try { t.obroc(new geometria.Punkt(Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3])), Double.parseDouble(args[i+4])); }
					catch(Exception e) {System.out.println("blad"); return;}
					i+=5;
				}
				else if (args[i+1].equals("p"))
				{
					try{t.przesun(new geometria.Wektor(Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3])));}
					catch(Exception e) {System.out.println("blad"); return;}
					i+=4;
				}
				else if(args[i+1].equals("od"))
				{
					try {t.odbij(pr);}
					catch(Exception e) {System.out.println("blad");}
					i+=2;
				}
				else if(args[i+1].equals("w"))
				{
					try {t.toString();}
					catch(Exception e) {System.out.println("blad");}
					i+=2;
				}
			}
			else if(args[i].equals("w")) //nadawanie nowego geometria.Wektora jescze brakue testu tych statycznych
			{
				try {w=new geometria.Wektor(Integer.parseInt(args[i+1]), Integer.parseInt(args[i+2]));}
				catch(Exception e) {System.out.println("błąd"); return;}
				i+=3;
				continue;
			}
			
			else if(args[i].equals("pr")) //nadawanie nowej prostej
			{
				try 
				{
					pr=new geometria.Prosta(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]));
				}
				catch(Exception e) {System.out.println("błąd"); return;}
				i+=4;
				continue;
			}
			
			
		}
	}
}
