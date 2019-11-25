package game.entities;

import javafx.scene.canvas.GraphicsContext;
import utils.Direction;
import utils.Node;
import game.cells.Cell;
import game.cells.Cell.CellType;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import game.Map;

public class SmartFollower extends Enemy {

    private Direction dir;
    private Player player;

    public SmartFollower(int xPos, int yPos, Player p) {
        super(xPos, yPos);
        player = p;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(xPos * Cell.size - 3 + Cell.size / 2, yPos * Cell.size - 3 + Cell.size / 2, 6, 6);
        gc.strokeOval(xPos * Cell.size - 3 + Cell.size / 2, yPos * Cell.size - 3 + Cell.size / 2, 6, 6);
    }

    public void algorithm(Map map) {
        dir = null;
        LinkedList<Cell> queue = new LinkedList<>();
        Integer[][] distGrid = new Integer[map.height][map.width];
        distGrid[player.yPos][player.xPos] = 0;
        queue.add(map.getCell(player.xPos, player.yPos));

        while (!queue.isEmpty()) {
            Cell q = queue.poll();
            for (Direction d : Direction.values()) {
                Cell next = map.getCell(q.x, q.y, d);
                if (next.getType() == CellType.GROUND) {
                    int dist = distGrid[q.y][q.x] + 1;
                    if (distGrid[next.y][next.x] == null) { 
                        distGrid[next.y][next.x] = dist;
                        queue.add(next);
                    }
                }                
            }
        }

        Integer minDist = null;
        for (Direction d : Direction.values()) {
            Cell next = map.getCell(xPos, yPos, d);
                if (next.getType() == CellType.GROUND) {
                    Integer dist = distGrid[yPos+d.Y][xPos+d.X];
                    if (minDist == null) {
                        minDist = dist;
                        dir = d;
                    } else {
                        if (dist < minDist) {
                            minDist = dist;
                            dir = d;
                        }
                    }
                }        
        }

        if (!(yPos == player.yPos && xPos == player.xPos)) {
            if (dir != null) {
                xPos += dir.X;
                yPos += dir.Y;
            } 
        }
  
    }



}

    // private ArrayList<Cell> path;
    // private void find(Map map) {
    //     int minDist = Integer.MAX_VALUE;
    //     for (Direction d : Direction.values()) {
    //         path = new ArrayList<>();
    //         path.add(map.getCell(xPos, yPos));
    //         Cell nextCell = map.getCell(xPos+d.X, yPos+d.Y);
    //         if (nextCell.getType() == CellType.GROUND) {
    //             path.add(nextCell);
    //             int dist = find(xPos + d.X, yPos + d.Y, map, 0);
    //             System.out.println(d + " : " + dist);
    //             if (dist < minDist) {
    //                 minDist = dist;
    //                 dir = d;
    //             }
    //         }
    //     }
    // }

    // private int find(int x, int y, Map map, int dist) {
    //     if (x == player.getXPos() && y == player.getYPos()) return dist;
    //     for (Direction d : Direction.values()) {
    //         Cell nextCell = map.getCell(x+d.X, y+d.Y);
    //         if (nextCell.getType() == CellType.GROUND && !path.contains(nextCell)) {
    //             gc.fillOval(nextCell.x * Cell.size - 3 + Cell.size / 2, nextCell.y * Cell.size - 3 + Cell.size / 2, 6, 6);
    //             path.add(nextCell);
    //             return find(x + d.X, y + d.Y, map, dist+1);
    //         }
    //     }
    //     return Integer.MAX_VALUE;
    // }

    // public void algorithm(Map map) {
    //     find(map);
    //     System.out.println(dir);
    // }

    // public Integer searchDist(Map map, Cell c) {
    //     Queue<Cell> queue = new LinkedList<>();
    //     HashMap<Cell, Integer> dist = new HashMap<>();
    //     int distance = 0;
    //     queue.add(c);
    //     dist.put(c, 0);
    //     while (!queue.isEmpty()) {
    //         Cell next = queue.poll();
    //         distance++;
    //         if (next.x == player.xPos && next.y == player.yPos)
    //             return dist.get(next);
    //         ArrayList<Cell> surrounding = map.surrounding(next, CellType.GROUND);
    //         for (int i = 0; i < surrounding.size(); i++) {
    //             Cell cell = surrounding.get(i);
    //             if (!dist.containsKey(cell)) {
    //                 queue.add(cell);
    //                 dist.put(cell, distance);
    //             }
    //         }
    //     }
    //     return null;
    // }

    // public void algorithm(Map map) {
    //     Integer min = null;
    //     dir = null;
    //     for (Direction d : Direction.values()) {
    //         Cell cell = map.getCell(xPos+d.X, yPos+d.Y);
    //         if (Cell.is(cell, CellType.GROUND)) {
    //             Integer dist = searchDist(map, cell);
    //             System.out.println(d + " : " + dist);
    //             if (min == null && dist != null) {
    //                 min = dist;
    //                 dir = d;
    //             }
    //             else {
    //                 if (dist < min) {
    //                     min = dist;
    //                     dir = d;
    //                 }
    //             }
    //         }
    //     }

    //     if (dir != null) {
    //         // xPos += dir.X;
    //         // yPos += dir.Y;   
    //     }
    // }


    

    // private int dist(Cell cell) {
    //     return (Math.abs(player.xPos - cell.x) + Math.abs(player.yPos - cell.y));
    // }

    // public void astar(Map map) {
    //     HashMap<Cell, Integer> fMap = new HashMap<>();
    //     HashMap<Cell, Integer> dMap = new HashMap<>();

    //     ArrayList<Cell> open = new ArrayList<>();
    //     ArrayList<Cell> closed = new ArrayList<>();

    //     Cell start = map.getCell(xPos, yPos);
    //     open.add(start);
    //     dMap.put(start, 0);
    //     fMap.put(start, dist(start));

    //     while(!open.isEmpty()) {
    //         int minF = Integer.MAX_VALUE;
    //         int index = 0;
    //         for (int i = 0; i < open.size(); i++) {
    //             Cell t = open.get(i);
    //             int f = fMap.get(t);
    //             if (f < minF) {
    //                 minF = f;
    //                 index = i;
    //             }
    //         }

    //         Cell q = open.remove(index);
    //         ArrayList<Cell> successors = map.surrounding(q, CellType.GROUND);    
    //         for (int i = 0; i < successors.size(); i++) {
    //             Cell c = successors.get(i);
    //             if (c.x == player.xPos && c.y == player.yPos) {
    //                 System.out.println(dMap.get(c));
    //                 return;
    //             }

    //             int g = 1 + dMap.get(q);
    //             int h = dist(c);

    //             if ((closed.contains(c) || open.contains(c)) && fMap.get(c) < g+h) {
    //                 continue;
    //             } else {
    //                 dMap.put(c, 1 + dMap.get(q));
    //                 fMap.put(c, dMap.get(c) + dist(c));
    //                 open.add(c);
    //             }
    //         }
    //         closed.add(q);
    //     }
        
    //     System.out.println("Dist : " + dMap.get(map.getCell(player.xPos, player.yPos)));
    // }

    // public void algorithm(Map map) {
    //     ArrayList<Node<Cell>> marked = new ArrayList<>();
    //     Stack<Node<Cell>> stack = new Stack<>();
    //     Cell startCell = map.getCell(xPos, yPos);
    //     Node<Cell> root = new Node<>(startCell, 0);
    //     stack.push(root);
    //     marked.add(root);
        

    //     while (!stack.isEmpty()) {
    //         Node<Cell> head = stack.peek();
    //         ArrayList<Cell> successors = map.surrounding(head.obj, CellType.GROUND);
    //         for (int i = 0; i < successors.size(); i++) {
    //             Node<Cell> next = new Node<>(successors.get(i), head.dist+1);
    //             if (next.obj.x == player.xPos && next.obj.y == player.yPos) {
    //                 System.out.println("true");
    //             }
    //             gc.fillOval(next.obj.x * Cell.size - 3 + Cell.size / 2, next.obj.y * Cell.size - 3 + Cell.size / 2, 6, 6);
    //             if (!stack.contains(next)) {
    //                 if (!marked.contains(next)) {
    //                     marked.add(next);
    //                     stack.push(next);
    //                 } else {
    //                     stack.pop();
    //                 }
    //             }
    //         }
    //     }
        
    // }