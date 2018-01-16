package com.gnurt.SnakeAI;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import com.badlogic.gdx.math.Vector2;
import com.gnurt.MySnake.Snake;
import com.gnurt.MySnake.Snake.Dir;

import MyBait.Bait;

public class FindMinimumPath {
	static class pair{
		int x;
		int y;
		public pair(int _x,int _y) {
			x=_x;
			y=_y;
		}
	}
	static Vector<pair> map2Dto1D;
	static Vector<Vector<Integer>> listAdjacency;
	static boolean[] listVertexCantAccess;
	static int[] trace;
	
	public static LinkedList<pair> solve(Snake snakeAI,Bait bait){
		LinkedList<Vector2> snakeBodyAI=snakeAI.getSnakeBody();
		LinkedList<pair> resultReturn = new LinkedList<pair>();	
		map2Dto1D=new Vector<pair>(1024);  
		listAdjacency=new Vector<Vector<Integer>>(32);
		listVertexCantAccess=new boolean[1024];
		trace=new int[1024];
		
		//init
		for(int i=0;i<1024;i++) {
			listVertexCantAccess[i]=false;
		}
		
		for(int i=0;i<32;i++) {
			for(int j=0;j<32;j++) {
				map2Dto1D.add(new pair(i,j));
			}
		}
		
		// init list adjacency
		for(int i=0;i<32;i++) {
			for(int j=0;j<32;j++) {
				Vector<Integer> temp=new Vector<Integer>();
				if((i-1)>=0)
					temp.add((i-1)*32+j);
				if((j-1)>=0)
					temp.add(i*32+j-1);
				if((i+1)<32)
					temp.add((i+1)*32+j);
				if((j+1)<32)
					temp.add(i*32+j+1);
				listAdjacency.add(temp);
			}
		}
		
		for(int i=0;i<snakeBodyAI.size();i++) {
			listVertexCantAccess[(int) (snakeBodyAI.get(i).x*32+snakeBodyAI.get(i).y)]=true;
		}
		for(int i=0;i<31;i++) {
			listVertexCantAccess[0*32+i]=true;
			listVertexCantAccess[31*32+i]=true;
			listVertexCantAccess[i*32+0]=true;
			listVertexCantAccess[i*32+32]=true;
		}
		
		listVertexCantAccess[bait.x*32+bait.y]=true;
		
		int start=(int) (snakeBodyAI.peekLast().x*32+snakeBodyAI.peekLast().y);
		int end=bait.x*32+bait.y;
		int conVertex=start;
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(start);
		trace[start]=-1;
		boolean stop=false;
		while(!stop&&queue.size()!=0) {
			conVertex=queue.poll();
			for(int i=0;i<listAdjacency.get(conVertex).size();i++) {
				if(listAdjacency.get(conVertex).get(i)==end) {
					trace[listAdjacency.get(conVertex).get(i)]=conVertex;
					stop=true;
					break;
				}
				else {
					if(!listVertexCantAccess[listAdjacency.get(conVertex).get(i)]) {
						trace[listAdjacency.get(conVertex).get(i)]=conVertex;
						queue.add(listAdjacency.get(conVertex).get(i));
						listVertexCantAccess[listAdjacency.get(conVertex).get(i)]=true;
					}
				}
			}
		}
		while(end!=start) {
			resultReturn.offer(map2Dto1D.get(end));
			end=trace[end];
		}
		resultReturn.offer(map2Dto1D.get(start));
		return resultReturn;
	}

	private static Dir takeDir(pair head, pair nextmove) {
		if(head.x>nextmove.x)
			return Dir.UP;
		if(head.x<nextmove.x)
			return Dir.DOWN;
		if(head.y<nextmove.y)
			return Dir.RIGHT;
		return Dir.LEFT;
	}
	public static LinkedList<pair> resultReturn = new LinkedList<pair>();
	public static Dir getDir(Snake snakeAI,Bait bait,boolean reset) {	
		if(resultReturn.size()<2||reset)
			resultReturn=solve(snakeAI,bait);			
		Dir returnDir=	takeDir(resultReturn.get(resultReturn.size()-1),resultReturn.get(resultReturn.size()-2));	
		resultReturn.removeLast();
		return returnDir;
	}
}
