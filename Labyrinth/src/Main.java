import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JFrame;

public class Main {
	static int collumns,rows;
	static Node Start;
	static Node[][] matrix;
	public static void searchNeighboors(Node[][] matrix,Node newNode,int i,int j)
	{
		if(i+1<rows)
		if(matrix[i+1][j].getNumber()!=0)
			newNode.setNeighboors(matrix[i+1][j].getNumber());
		if(i-1>=0)
		if(matrix[i-1][j].getNumber()!=0)
			newNode.setNeighboors(matrix[i-1][j].getNumber());
		if(j+1<collumns)
		if(matrix[i][j+1].getNumber()!=0)
			newNode.setNeighboors(matrix[i][j+1].getNumber());
		if(j-1>=0)
		if(matrix[i][j-1].getNumber()!=0)
			newNode.setNeighboors(matrix[i][j-1].getNumber());
	}
	
	public static Vector<Node> transformToGraph()
	{
		
		int countNumber=1;
		int r=0;
		Vector<Node> nodes= new Vector<Node>();
		try 
		{
			int i=0,j=0;
			Path filePath = Paths.get("C:\\Users\\40763\\eclipse-workspace\\Tema3\\src\\Matrice");
			Scanner scanner = new Scanner(filePath);
			rows=scanner.nextInt();
			collumns=scanner.nextInt();
			 matrix = new Node[rows][collumns];
			while (i<rows)
		    {
				int aux1 =scanner.nextInt();
				if(aux1!=0)
				{
					if(aux1==2)
						{
						Node aux =new Node(countNumber++,true);
						matrix[i][j]=aux;
						}
					else
					if(aux1==3)
					{
					Node aux =new Node(countNumber++);
					aux.setStart(true);
					Start=aux;
					matrix[i][j]=aux;
					}
					else
					{
						Node aux =new Node(countNumber++);
						matrix[i][j]=aux;
					}
					
				}
				else matrix[i][j]=new Node(0);
				j++;
				if(j==collumns)
				{
				j=0;
				i++;
				}
		    }
			countNumber=1;
			
			for(i=0;i<rows;i++)
			{
				for(j=0;j<collumns;j++)
				{
					if(matrix[i][j].getNumber()!=0)
					{
	
						Node newNode= matrix[i][j];
						searchNeighboors(matrix,newNode,i,j);
						nodes.add(newNode);
						
					}
					
				}
			}
			
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		return nodes;
	}
	
	public static void BFS(Vector<Node> nodes)

	{
		Vector<Queue<Node>> path = new Vector<Queue<Node>>();
		Start.setVisited(true);
		Node vizit = Start;
		Queue<Node> queue = new LinkedList<Node>();
		Vector<Integer> Neighboors;
		boolean found=false;
		queue.add(vizit);
		while(queue.size()>0)
		{
			found=false;
			Neighboors=queue.peek().getNeighboors();          
			for(int i=0;i<Neighboors.size();i++)
			{
				Node aux=nodes.get(Neighboors.get(i)-1);
				if(aux.getVisited()==false)
				{
					aux.setParent(queue.peek().getNumber());
					if(aux.getFinish()==true)
					{
						Queue<Node> copy =new LinkedList<Node>();
						Node getQueue =aux;
						while(getQueue.getParent()!=-1)
						{
							copy.add(getQueue);
							getQueue=nodes.get(getQueue.getParent()-1);
						}
						copy.add(Start);
						path.add(copy);
					}
					//System.out.print(aux.getNumber()+" ");
					found=true;
					nodes.get(Neighboors.get(i)-1).setVisited(true);
					//System.out.print(nodes.get(Neighboors.get(i)-1).getVisited());
					queue.add(aux);
					
				}
				
			}
			if(found==false)queue.remove();
			}
				
			
		
		
	
	
		Frame(path);
		
		
			
			
	}

	public static void Frame(Vector<Queue<Node>> paths)
	{
		JFrame frame = new JFrame();
		frame.setSize(1200,900);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new Window(paths,matrix,collumns,rows));
		
	}
	public static void main(String[] args) {
		Vector<Node> nodes2=transformToGraph();
		
		BFS(nodes2);
		
		
		
		
			

	}

}
