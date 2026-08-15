package container.graph;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


class FindPathBetweenNodesTest {



    private ArrayList<FindPathBetweenNodes.Node> generatePositiveSample() {
        FindPathBetweenNodes.Node node1 = new FindPathBetweenNodes.Node(1);
        FindPathBetweenNodes.Node node2 = new FindPathBetweenNodes.Node(2);
        FindPathBetweenNodes.Node node3 = new FindPathBetweenNodes.Node(3);
        FindPathBetweenNodes.Node node4 = new FindPathBetweenNodes.Node(4);
        FindPathBetweenNodes.Node node5 = new FindPathBetweenNodes.Node(5);
        FindPathBetweenNodes.Node node6 = new FindPathBetweenNodes.Node(6);
        FindPathBetweenNodes.Node node7 = new FindPathBetweenNodes.Node(7);
        FindPathBetweenNodes.Node node8 = new FindPathBetweenNodes.Node(8);
        FindPathBetweenNodes.Node node9 = new FindPathBetweenNodes.Node(9);
        FindPathBetweenNodes.Node node10 = new FindPathBetweenNodes.Node(10);

        node1.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);

        node3.children.add(node5);
        node5.children.add(node6);
        node6.children.add(node7);
        node7.children.add(node8);
        node8.children.add(node9);
        node9.children.add(node10);

        node10.children.add(node4);

        node4.children.add(node5);
        node4.children.add(node1);

        ArrayList<FindPathBetweenNodes.Node> graph = new ArrayList<>();
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);
        graph.add(node6);
        graph.add(node7);
        graph.add(node8);
        graph.add(node9);
        graph.add(node10);
        return graph;
    }


    @Test
    void testFindPathBetweenNodesDepth19() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        // 1, 9
        boolean pair1_9 = findPathBetweenNodes.hasPathDepth(graph.get(0), graph.get(8));
        assertThat(pair1_9).isTrue();

    }


    @Test
    void testFindPathBetweenNodesDepth75() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair7_5 = findPathBetweenNodes.hasPathDepth(graph.get(6), graph.get(4));
        assertThat(pair7_5).isTrue();
    }


    @Test
    void testFindPathBetweenNodesDepth51() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair5_1 = findPathBetweenNodes.hasPathDepth(graph.get(4), graph.get(0));
        assertThat(pair5_1).isTrue();

    }



    @Test
    void testFindPathBetweenNodesDepth32() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair3_4 = findPathBetweenNodes.hasPathDepth(graph.get(2), graph.get(1));
        assertThat(pair3_4).isTrue();

    }


    @Test
    void testFindPathBetweenNodesDepth28() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair3_4 = findPathBetweenNodes.hasPathDepth(graph.get(1), graph.get(7));
        assertThat(pair3_4).isFalse();

    }



    // ==== BREADTH


    @Test
    void testFindPathBetweenNodesBreadth19() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        // 1, 9
        boolean pair1_9 = findPathBetweenNodes.hasPathBroad(graph.get(0), graph.get(8));
        assertThat(pair1_9).isTrue();

    }


    @Test
    void testFindPathBetweenNodesBreadth75() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair7_5 = findPathBetweenNodes.hasPathBroad(graph.get(6), graph.get(4));
        assertThat(pair7_5).isTrue();
    }


    @Test
    void testFindPathBetweenNodesBreadth1() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair5_1 = findPathBetweenNodes.hasPathBroad(graph.get(4), graph.get(0));
        assertThat(pair5_1).isTrue();

    }



    @Test
    void testFindPathBetweenNodesBreadth32() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair3_4 = findPathBetweenNodes.hasPathBroad(graph.get(2), graph.get(1));
        assertThat(pair3_4).isTrue();

    }


    @Test
    void testFindPathBetweenNodesBreadth28() {
        FindPathBetweenNodes findPathBetweenNodes = new FindPathBetweenNodes();

        ArrayList<FindPathBetweenNodes.Node> graph = generatePositiveSample();

        boolean pair3_4 = findPathBetweenNodes.hasPathBroad(graph.get(1), graph.get(7));
        assertThat(pair3_4).isFalse();

    }

}