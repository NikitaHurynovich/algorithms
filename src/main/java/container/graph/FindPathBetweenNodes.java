package container.graph;

/*
Does path between Node A and Node B exists? Graph is directed
*/


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class FindPathBetweenNodes {



    public boolean hasPathDepth(Node a, Node b) {
        a.visited = true;
        System.out.println("Visited " + a.value);
        if (a.value == b.value) {
            System.out.println("FOUND!");
            return true;
        }

        for (Node child: a.children) {
            if (!child.visited) {
                boolean hasPath = hasPathDepth(child, b);
                if (hasPath) {

                    return true;
                }
            }
        }
        return false;
    }


    public boolean hasPathBroad(Node a, Node b) {

        Queue<Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(a);

        while (!nodeQueue.isEmpty()) {
            Node candidate = nodeQueue.poll();
            if (!candidate.visited) {
                candidate.visited = true;
                System.out.println("Visited " + candidate.value);
                if (candidate.value == b.value) {
                    System.out.println("FOUND!");
                    return true;
                }
                for(Node child: candidate.children) {
                    if (!child.visited) {
                        nodeQueue.add(child);
                    }
                }

            }
        }
        return false;
    }



    public static class Node {
        public int value;
        public ArrayList<Node> children = new ArrayList<>();
        boolean visited;

        public Node(int value) {
            this.value = value;
        }
    }
}
