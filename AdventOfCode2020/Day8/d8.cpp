#include <iostream>
#include <fstream>
#include <string>

using namespace std;

struct code{
    int op;
    int mod;
    bool visited;
};

bool works(struct code** codeArray, int &out){
    int lines = 0;
    int acc = 0;
    int length = 649;
    struct code* curr_op = codeArray[lines];
    while(!curr_op->visited && lines < 649){
        switch (curr_op->op){
        case 1:
            lines++;
            acc += curr_op->mod;
            break;
        case 2:
            lines += curr_op->mod;
            break;
        case 3:
            lines++;
            break;
        default:
            break;
        }
        curr_op->visited = true;
        curr_op = codeArray[lines];
    }
    out = acc;
    for(int i = 0; i < 649; i++){
        codeArray[i]->visited = false;
    }
    return lines < 649;
}

int main(){
    ifstream myfile("input.txt");
    string line;
    int acc = 0;
    struct code* codeArray[649];
    for(int i = 0; i < 649; i++){
        codeArray[i] = (struct code*)malloc(sizeof(struct code));
    }
    int i = 0;
    while (getline(myfile, line)){
        struct code *new_node;
        string token = line.substr(0, line.find(" "));
        string num = line.substr(line.find(" "), string::npos);
        int n = stoi(num);

        new_node = (struct code*)malloc(sizeof(struct code));
        if(token == "acc"){
            new_node->op = 1;
        }else if(token == "jmp"){
            new_node->op = 2;
        }else{
            new_node->op = 3;
        }
        new_node->mod = n;
        new_node->visited = false;

        codeArray[i] = new_node;
        i++;
    }

    int j = 0;
    while(true){
        if(codeArray[j]->visited){
            cout << acc << endl;
            break;
        }else if(codeArray[j]->op == 1){
            acc += codeArray[j]->mod;
            codeArray[j]->visited = true;
        }else if(codeArray[j]->op == 2){
            codeArray[j]->visited = true;
            j += codeArray[j]->mod - 1;
        }
        j++;
    }

    for(int i = 0; i < 649; i++){
        codeArray[i]->visited = false;
    }

    int acc2;
    for(auto &in : codeArray){
        if(in->op == 3){
            in->op = 2;
            if(works(codeArray, acc2)){
                in->op = 3;
                continue;
            }
            break;
        }else if(in->op == 2){
            in->op = 3;
            if(works(codeArray, acc2)){
                in->op = 2;
                continue;
            }
            break;
        }
    }

    cout << acc2 << endl;
    
}
