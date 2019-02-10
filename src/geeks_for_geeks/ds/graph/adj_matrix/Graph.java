package geeks_for_geeks.ds.graph.adj_matrix;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:08
 **/
public class Graph extends GraphBase {

    public Graph(int vertices) {
        super(vertices);
    }

    @Override
    public Graph addEdge(int src, int dest, int weight) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        values[src][dest] = weight;
        return this;
    }

    @Override
    public Graph addEdge(int src, int dest) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        values[src][dest] = 1;
        return this;
    }

    @Override
    public int vertices() {
        return values.length;
    }

}

