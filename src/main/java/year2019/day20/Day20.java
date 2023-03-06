package year2019.day20;

import utils.FileHelper;
import utils.Point;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day20 {

    static HashMap<Point, Node> allNodes= new HashMap<>();
    static HashMap<Point, Character> map = new HashMap<>();
    static int maxY=0,maxX=0,minY=Integer.MAX_VALUE, minX=Integer.MAX_VALUE;

    static void makeMap(List<String> input) {
        map = new HashMap<>();
        int x=0,y=0;
        for (String row : input){
            for (Character c : row.toCharArray()) {
                Point p = new Point(x,y);
                map.put(p, c);
                x++;
            }
            x=0;
            y++;
        }
        simplifyMap();
    }

    static Character findOtherLetter(Point p) {
        char b;
        char n = map.getOrDefault(p.north(), ' ');
        char w = map.getOrDefault(p.west(), ' ');
        char s = map.getOrDefault(p.south(), ' ');
        char e = map.getOrDefault(p.east(), ' ');

        if ((int) n >= 65 && (int) n <= 90) {
            b = n;
        } else if ((int) w >= 65 && (int) w <= 90) {
            b = w;
        } else if ((int) e >= 65 && (int) e <= 90) {
            b = e;
        } else if ((int) s >= 65 && (int) s <= 90) {
            b = s;
        } else {
            throw new RuntimeException("Found no other letter!");
        }
        return b;
    }
    static Point findFreeSpotNearPortal(Point p) {
        if (map.getOrDefault(p.north(),'?') == '.') {
            return p.north();
        }
        if (map.getOrDefault(p.south(),'?') == '.') {
            return p.south();
        }
        if (map.getOrDefault(p.west(),'?') == '.') {
            return p.west();
        }
        if (map.getOrDefault(p.east(),'?') == '.') {
            return p.east();
        }
        return null;
    }
    static boolean isPortal(Point p) {
        int n = (int) map.getOrDefault(p, ' ');
        if (n>=65 && n<=90) {
            return map.getOrDefault(p.north(), ' ') == '.' ||
                    map.getOrDefault(p.south(), ' ') == '.' ||
                    map.getOrDefault(p.west(), ' ') == '.' ||
                    map.getOrDefault(p.east(), ' ') == '.';
        }
        return false;
    }


    static void simplifyMap() {
        findAllPortals();
        pIsForPortal();
        for ( Map.Entry<Point, Character> e: map.entrySet()) {
            if((int) e.getValue()>=65 && (int) e.getValue()<=90) {
                e.setValue('#');
            }
        }
        HashMap<Point, Character> currentMap = new HashMap<>(map);
        boolean keepOn=true;
        while (keepOn) {
            keepOn=false;
            HashMap<Point, Character> newMap = new HashMap<>(currentMap);
            for (Map.Entry<Point, Character> e : currentMap.entrySet()) {
                if (e.getValue() == '.' || e.getValue() == '#') {
                    int blockedSides = 0;
                    if ((currentMap.getOrDefault(e.getKey().north(), '#') == '#') || (currentMap.getOrDefault(e.getKey().north(), ' ')==' ')) blockedSides++;
                    if ((currentMap.getOrDefault(e.getKey().south(), '#') == '#') || (currentMap.getOrDefault(e.getKey().south(), ' ')==' ')) blockedSides++;
                    if ((currentMap.getOrDefault(e.getKey().west(), '#') == '#') || (currentMap.getOrDefault(e.getKey().west(), ' ')==' ')) blockedSides++;
                    if ((currentMap.getOrDefault(e.getKey().east(), '#') == '#') || (currentMap.getOrDefault(e.getKey().east(), ' ')==' ')) blockedSides++;

                    if (blockedSides == 3 && e.getValue()!='#') {
                        newMap.put(e.getKey(), '#');
                        keepOn=true;
                    }
                    if (blockedSides == 4) {
                        newMap.remove(e.getKey());
                        keepOn=true;

                    }
                }
            }
            currentMap=newMap;
        }

        map = currentMap;
        setUpConnections();
    }

    static Node findStart() {
        for ( Map.Entry<Point, Node> e : allNodes.entrySet() ){
            if (e.getValue().getName().equals("AA")) return e.getValue();
        }
        throw new RuntimeException("Found no Start!");
    }
    public static void main(String[] args) {
        var day = MethodHandles.lookup().lookupClass().getSimpleName();
        var inputs = new FileHelper().readFile("2019/"+day+".txt");
        makeMap(inputs);

        System.out.println("Day20A "+findShortestRoute(false));
        System.out.println("Day20B "+findShortestRoute(true));
    }

    static void findAllPortals() {
        allNodes = new HashMap<>();
        for(Point position : map.keySet()) {
            if (isPortal( position )) {
                char[] portal = new char[2];
                portal[0]= map.get(position);
                portal[1]= findOtherLetter(position);
                position = findFreeSpotNearPortal(position);
                if (position!=null) {
                    Arrays.sort(portal);
                    String name = new String(portal);
                    Node node = new Node(name, position);
                    allNodes.put(position, node);
                }
            }
        }
    }
    static void pIsForPortal() {
        for(Point p : allNodes.keySet()) {
            map.put(p, 'p');
        }
    }
    static void setUpConnections() {
        for (Node node : allNodes.values() ) {
            minY=Math.min(minY,node.getEntrance().getY());
            maxY=Math.max(maxY,node.getEntrance().getY());
            minX=Math.min(minX,node.getEntrance().getX());
            maxX=Math.max(maxX,node.getEntrance().getX());

            HashSet<Point> queue = new HashSet<>();
            queue.add(node.getEntrance());
            HashSet<Point> visited=new HashSet<>();

            int steps=0;
            while (!queue.isEmpty()) {
                HashSet<Point> newToAdd = new HashSet<>();
                HashSet<Point> toBeRemoved = new HashSet<>();
                for (Point current : queue) {
                    toBeRemoved.add(current);
                    if (visited.contains(current)) {
                        continue;
                    }
                    visited.add(current);

                    Node n = getPortalAtPosition(current);
                    if (steps > 1 && n != null) {
                        node.addChild(n, steps+1);
                    } else {
                        newToAdd.addAll(getPaths(current));
                    }
                }
                steps++;
                queue.addAll(newToAdd);
                queue.removeAll(toBeRemoved);
            }
        }
    }
    static Node getPortalAtPosition( Point p ) {
        return allNodes.get(p);
    }

    static int findShortestRoute(boolean withRecursion) { //Width first
        Node start=findStart();

        NodeState startState = new NodeState(start);

        HashSet<NodeState> queue = new HashSet<>();
        HashMap<NodeState, Integer> visited= new HashMap<>();
        queue.add(startState);

        int fewestSteps=Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            HashSet<NodeState> newToAdd  = new HashSet<>();
            HashSet<NodeState> toBeRemoved = new HashSet<>();

            for (NodeState current : queue ) {

                toBeRemoved.add(current);
                if (current.getSteps() >= fewestSteps) {
                    continue;
                }if (visited.containsKey(current) && visited.get(current)<current.getSteps()) {
                    continue;
                } else {
                    visited.put(current, current.getSteps());
                }
                if ( current.getCurrentNode().getName().equals("ZZ") ) {
                    if (!withRecursion) {
                        fewestSteps = Math.min(current.getSteps()-1, fewestSteps);
                    } else {
                        if ( current.isTopLevel() ) {
                            fewestSteps = Math.min(current.getSteps()-1, fewestSteps);
                        }
                    }
                } else {
                    for (Map.Entry<Node, Integer> child : current.getCurrentNode().getConnections().entrySet()) {
                        if(withRecursion && current.isTopLevel() && child.getKey().isOuterNode() && !child.getKey().getName().equals("ZZ"))
                            continue;
                        if(child.getKey().getName().equals("AA")) continue;
                        newToAdd.add(new NodeState(child.getKey(), child.getValue(), current));
                    }
                }
            }
            queue.addAll(newToAdd);
            queue.removeAll(toBeRemoved);
        }
        return fewestSteps;
    }


    static List<Point> getPaths(Point p) {
        List<Point> freeArea=new ArrayList<>();
        if (map.getOrDefault(p.north(), '#') != '#') {
            freeArea.add(p.north());
        }
        if (map.getOrDefault(p.south(), '#') != '#') {
            freeArea.add(p.south());
        }
        if (map.getOrDefault(p.west(), '#') != '#') {
            freeArea.add(p.west());
        }
        if (map.getOrDefault(p.east(), '#') != '#') {
            freeArea.add(p.east());
        }
        return freeArea;
    }

    static class Node {
        String name;
        Point entrance;
        boolean isOuter;
        HashMap<Node, Integer> children = new HashMap<>();
        HashMap<Node, Integer> connections = null;

        public Node(String name, Point entrance) {
            this.name=name;
            this.entrance=entrance;
            this.isOuter=isOuterNode();

        }
        HashMap<Node, Integer> getConnections() {
            if(name.equals("AA")) return children;
            if (connections==null) {
                connections=new HashMap<>();
                Node otherEndOfPortal = getOtherSide(this);
                this.connections=otherEndOfPortal.getChildren();
                this.isOuter=isOuterNode();

            }
            return connections;
        }

        public String getName() {
            return name;
        }
        public Point getEntrance() {
            return entrance;
        }
        public boolean isOuterNode() {
            return entrance.getY()==minY
                || entrance.getY()==maxY
                || entrance.getX()==minX
                || entrance.getX()==maxX;
        }

        public void addChild(Node node, int steps) {
            if (children.getOrDefault(node, Integer.MAX_VALUE) > steps) {
                children.put(node, steps);
            }
        }
        public HashMap<Node, Integer> getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Node)) return false;
            Node o = (Node) other;
            return ( this.getName().equals(o.getName())
                  && this.getEntrance().equals(o.getEntrance()) );
        }
        @Override
        public int hashCode() {
            return 17*getName().hashCode()+1;
        }
    }
    static Node getOtherSide(Node node) {
        for (Node n : allNodes.values()) {
            if (n.getName().equals(node.getName()) && !node.equals(n)) {
                return n;
           }
        }
        throw new RuntimeException("Could not find other end for node "+node.getName()+" "+node.getEntrance().toString());
    }
    static class NodeState {
        List<Node> previousNodes;
        Node currentNode;
        int steps;

        public NodeState(Node currentNode) {
            this.previousNodes = new ArrayList<>();
            this.currentNode = currentNode;
            this.steps = 0;
        }
        public NodeState(Node currentNode, int steps, NodeState previous) {
            this.previousNodes = new ArrayList<>(previous.getPreviousNodes());
            previousNodes.add(currentNode);

            this.currentNode = currentNode;
            this.steps = previous.getSteps()+steps;
        }

        public List<Node> getPreviousNodes() {
            return previousNodes;
        }

        public Node getCurrentNode() {
            return currentNode;
        }

        public boolean isTopLevel() {
            int level=0;
            for(Node n : previousNodes) {
                if(n.name.equals("ZZ")) {
                    continue;
                }
                if(n.isOuterNode()) {
                    level--;
                } else {
                    level++;
                }
            }
            return level==0;
        }
        public int getSteps() {
            return steps;
        }
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof NodeState)) return false;
            NodeState o = (NodeState) other;
            return ( this.getCurrentNode().equals(o.getCurrentNode())
                    && this.getPreviousNodes().size()==o.getPreviousNodes().size()
                    && new HashSet<>(this.getPreviousNodes()).containsAll(o.getPreviousNodes()) );
        }
    }
}
