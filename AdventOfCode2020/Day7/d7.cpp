#include <iostream>
#include <fstream>
#include <string>
#include <math.h>
#include <vector>
#include <algorithm>
#include <stdio.h>
#include "d7.h"

using namespace std;
bagTree* root = new bagTree();
vector<struct bagTree*> vn;
vector<int> foundID;
int total = 0;

struct bagTree* InsertNode(int identifier, int quantity, string n,struct bagTree* b_node) 
{ 
    bagTree* new_node = new bagTree(); 
    new_node->id = identifier;
    new_node->q = quantity;
    new_node->name = n;
    b_node->nodes.push_back(new_node);
    return new_node;
}

void lookup(int identifier, struct bagTree* head){
   bagTree* node = new bagTree();
   node = head;
   for(bagTree* n: node->nodes){
       if (n->id == identifier){
           vn.push_back(n);
       }
       lookup(identifier, n);
   }
}

void display(struct bagTree* head){
    struct bagTree* node;
    node = head;
    for(struct bagTree* n: node->nodes){
        cout << n->name << " : " << n->id << " : " << n->q << endl;
        display(n);
    }
}

void NodeCount(int identifier, struct bagTree* head){
    struct bagTree* node;
    node = head;
    for(struct bagTree* n: node->nodes){
        foundID.push_back(n->id);
        if (n->id == identifier){
            total++;
        }else{
            NodeCount(identifier, n);
        }
    }
}

int main(){
    ifstream myfile("test.txt");
    string line;
    string bag = "bag";
    string bagTypes = "light red:bright white:muted yellow:dark orange:shiny gold:faded blue:dark olive:vibrant plum:dotted black:no other";
    while(getline(myfile, line)){
        size_t pos = line.find(" bags contain ");
        string token = line.substr(0, pos);
        bagTree* head = root;
        lookup(bagTypes.find(token), root);
        if(vn.empty()){
            total++;
            head = InsertNode(bagTypes.find(token), 1, token, root);
        }else{
            head = vn[0];
        }
        line.erase(0, pos + 14);
        pos = line.find(" bag");
        token = line.substr(0, pos);
        int quan = (int)line[0] - 48;
        if(quan == 62){
            quan = 0;
        }
        if(vn.empty()){
            InsertNode(bagTypes.find(token.substr(2)), quan, token.substr(2), head);
        }else{
            for(bagTree* n : vn){
                InsertNode(bagTypes.find(token.substr(2)), quan, token.substr(2), n);
            }
        }
        line.erase(0, pos + 4);
        if(line[0] == '.' || line[1] == '.'){
            continue;
        }else if(line[0] == 's'){
            line.erase(0, 3);
            token = line.substr(0, line.find(" bag"));
            quan = (int)line[0] - 48;
            if(quan == 62){
                quan = 0;
            }
            if(vn.empty()){
                InsertNode(bagTypes.find(token.substr(2)), quan, token.substr(2), head);
            }else{
                for(bagTree* n : vn){
                    InsertNode(bagTypes.find(token.substr(2)), quan, token.substr(2), n);
                }
            }
        }else{
            line.erase(0, 2);
            token = line.substr(0, line.find(" bag"));
            quan = (int)line[0] - 48;
            if(quan == 62){
                quan = 0;
            }
            if(vn.empty()){
                InsertNode(bagTypes.find(token.substr(2)), quan, token.substr(2), head);
            }else{
                for(bagTree* n : vn){
                    InsertNode(bagTypes.find(token.substr(2)), quan, token.substr(2), n);
                }
            }
        }
        vn.clear();
    }
    display(root);
    cout << total << endl;
}