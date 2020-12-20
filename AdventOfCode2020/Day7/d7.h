#ifndef D7_H
#define D7_H

#include <vector>
#include <string>
struct bagTree{
    std::string name;
    int id;
    int q;
    std::vector<struct bagTree*> nodes;
};

void display();
struct bagTree* InsertNode(int identifier, int quantity, std::string n,struct bagTree* b_node);
void lookup(int identifier, struct bagTree* head);
void NodeCount(int identifier, struct bagTree* head);

#endif