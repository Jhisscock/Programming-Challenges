#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <cstring>
using namespace std;

int graph[500][500], rGraph[500][500];
int numNodes;

struct edge{
    int x, y, flow;
    edge(int x1, int y1, int flow1){
        x = x1;
        y = y1;
        flow = flow1;
    }
};

bool bfs(int source, int sink, int path[], int numNodes){
    bool visited[10000];
    memset(visited, false, sizeof(visited));
    queue<int> nodeList;
    nodeList.push(source);
    visited[source] = true;
    path[source] = -1;

    while (!nodeList.empty()){
        int currNode = nodeList.front();
        nodeList.pop();
        for(int i = 0; i < numNodes; i++){
            if(!visited[i] && rGraph[currNode][i] > 0){
                nodeList.push(i);
                path[i] = currNode;
                visited[i] = true;
            }
        }
    }

    return visited[sink];
    
}

void maxFlow(int source, int sink, int numNodes){
    int path[500];
    memset(path, 0, sizeof(path));
    int max = 0;

    while (bfs(source, sink, path, numNodes)){
        int currFlow = INT_MAX;
        for(int i = sink; i != source; i = path[i]){
            numNodes = path[i];
            currFlow = min(currFlow, rGraph[numNodes][i]);
        }

        for(int i = sink; i != source; i = path[i]){
            numNodes = path[i];
            rGraph[numNodes][i] -= currFlow;
            rGraph[i][numNodes] += currFlow;
        }

        max += currFlow;
    }

    vector<edge> edges;
    for(int i = 0; i < numNodes; i++){
        for(int j = 0; j < numNodes; j++){
            if(graph[i][j] > 0 && rGraph[i][j] < graph[i][j]){
                edges.push_back(edge(i, j, graph[i][j] - rGraph[i][j]));
            }
        }
    }

    cout << numNodes << " " << max << " " << edges.size() << endl;
    for(int i = 0; i < edges.size(); i++){
        cout << edges[i].x << " " << edges[i].y << " " << edges[i].flow << endl;
    }
    
}

int main(){
    int numEdges, source, sink, x, y, flow;
    cin >> numNodes >> numEdges >> source >> sink;
    memset(graph, 0, sizeof(graph));
    memset(rGraph, 0, sizeof(rGraph));

    for(int i = 0; i < numEdges; i++){
        cin >> x >> y >> flow;
        graph[x][y] = flow;
        rGraph[x][y] = flow;
    }

    maxFlow(source, sink, numNodes);
}