package def;

import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler; 
import java.awt.Point;


import javax.xml.parsers.DocumentBuilder;  

public class Main {	  
	static Vector<Nodes> nodList = new Vector<Nodes>();
	static Vector<Arc> arcList = new Vector<Arc>();
	static boolean aux = false;
private static void initUI() {
    JFrame f = new JFrame("Algoritmica Grafurilor");

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    
   
    
    
    try   
    {  
    SAXParserFactory factory = SAXParserFactory.newInstance();  
    SAXParser saxParser = factory.newSAXParser();  
    DefaultHandler handler = new DefaultHandler()   
    {  
   
    Integer id,latitude,longitude,arcFrom,arcTo,arcLenght;
    int minLat=573929;
    int maxLat =652685;
    
    int minLong=4945029;
    int maxLong =5018275;
    //parser starts parsing a specific element inside the document    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException   
    {  
   // System.out.println("Start Element :" + qName);  
    if(qName.equalsIgnoreCase("node"))  
    {   
    
    id =Integer.parseInt(attributes.getValue("id"));
    latitude=Integer.parseInt(attributes.getValue("latitude"));
    longitude=Integer.parseInt(attributes.getValue("longitude"));
  if(latitude<minLat)
	  minLat=latitude;
  if(latitude>maxLat)
	  maxLat=latitude;
  if(longitude<minLong)
	  minLong=longitude;
  if(longitude>maxLong)
	  maxLong=longitude;
  
  Nodes newNodes = new Nodes((maxLat-latitude)/100+200,(maxLong-longitude)/100,id);
  nodList.add(newNodes);
  //max long - curent long /100
  //max lat - curent lat /100 +200
  
  


    }  
    
    if(qName.equalsIgnoreCase("arc"))  
    {   
    
    arcFrom =Integer.parseInt(attributes.getValue("from"));
    arcTo=Integer.parseInt(attributes.getValue("to"));
    arcLenght=Integer.parseInt(attributes.getValue("length"));
 
    Point start =new Point(nodList.get(arcFrom).getCoordX(),nodList.get(arcFrom).getCoordY());
    Point finish =new Point(nodList.get(arcTo).getCoordX(),nodList.get(arcTo).getCoordY());
    Point neighboor = new Point(arcTo,arcLenght);
    nodList.get(arcFrom).addNeighboor(neighboor);
  Arc newArc = new Arc(start,finish,arcLenght,new Point(arcFrom,arcTo));
  arcList.add(newArc);

    }  
    }  
    //parser ends parsing the specific element inside the document  
    };  
    saxParser.parse("hartaLuxembourg.xml", handler);  
    }   
    catch (Exception e)   
    {  
    e.printStackTrace();  
    }  
   
//   for(Nodes a : nodList)
//   {  for(Point b : a.getNeighboors())
//   System.out.print(b.x+" ");
//   System.out.println(" ");
//   }
    JPanel panouB =new drawMap(nodList,arcList);
    f.add(panouB);
    f.setSize(1200, 800);
}

public static void main(String[] args)
{
	final long startTime = System.nanoTime();
	SwingUtilities.invokeLater(new Runnable() //new Thread()
	{
        public void run() 
        {
        	initUI(); 
        }
    });
	final long duration = System.nanoTime() - startTime;
	System.out.println("citire: "+duration/1000000000.);
}	
}