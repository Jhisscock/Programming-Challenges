#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int layout[99][93];
void Display();
void Part1(){
    int copy[99][93];
    bool eq = false;
    while(!eq){
        for(int i = 0; i < 99; i++){
            for(int j = 0; j < 93; j++){
                copy[i][j] = layout[i][j];
            }
        }

        for(int i = 1; i < 98; i++){
            for(int j = 1; j < 92; j++){
                if(copy[i][j] == 0 && copy[i + 1][j] < 1 && copy[i - 1][j] < 1 && copy[i][j - 1] < 1 && copy[i][j + 1] < 1 && copy[i + 1][j + 1] < 1 && copy[i - 1][j - 1] < 1 && copy[i + 1][j - 1] < 1 && copy[i - 1][j + 1] < 1){
                    layout[i][j] = 1;
                }else if(copy[i][j] == 1){
                    int adj = 0;
                    if(copy[i + 1][j] == 1) adj++;
                    if(copy[i - 1][j] == 1) adj++;
                    if(copy[i][j - 1] == 1) adj++;
                    if(copy[i][j + 1] == 1) adj++;
                    if(copy[i + 1][j + 1] == 1) adj++;
                    if(copy[i - 1][j - 1] == 1) adj++;
                    if(copy[i + 1][j - 1] == 1) adj++;
                    if(copy[i - 1][j + 1] == 1) adj++;

                    if(adj >= 4) layout[i][j] = 0;
                }
            }
        }

        bool noteq = false;
        for(int i = 1; i < 98; i++){
            for(int j = 1; j < 92; j++){
                if(copy[i][j] != layout[i][j]){
                    noteq = true;
                }
            }
        }

        //Display();
        if(!noteq){
            eq = true;
        }
        
    }
    
    int result = 0;
    for(int i = 0; i < 99; i++){
        for(int j = 0; j < 93; j++){
            if(layout[i][j] == 1){
                result++;
            }
        }
    }

    cout << result << endl;
}

void Part2(){
    int copy[99][93];
    bool eq = false;
    while(!eq){
        for(int i = 0; i < 99; i++){
            for(int j = 0; j < 93; j++){
                copy[i][j] = layout[i][j];
            }
        }

        for(int i = 1; i < 98; i++){
            for(int j = 1; j < 92; j++){
                if(copy[i][j] == 0){
                    int it = 1;
                    while(copy[i + it][j] == -1){
                        it++;
                    }
                    if(copy[i + it][j] == 1){
                        continue;
                    }
                    it = 1;
                    while(copy[i - it][j] == -1){
                        it++;
                    }
                    if(copy[i - it][j] == 1){
                        continue;
                    }
                    it = 1;
                    while(copy[i][j + it] == -1){
                        it++;
                    }
                    if(copy[i][j + it] == 1){
                        continue;
                    }
                    it = 1;
                    while(copy[i][j - it] == -1){
                        it++;
                    }
                    if(copy[i][j - it] == 1){
                        continue;
                    }
                    it = 1;
                    while(copy[i + it][j - it] == -1){
                        it++;
                    }
                    if(copy[i + it][j - it] == 1){
                        continue;
                    }
                    it = 1;
                    while(copy[i - it][j - it] == -1){
                        it++;
                    }
                    if(copy[i - it][j - it] == 1){
                        continue;
                    }
                    it = 1;
                    while(copy[i + it][j + it] == -1){
                        it++;
                    }
                    if(copy[i + it][j + it] == 1){
                        continue;
                    }
                    it = 1;
                    while(copy[i - it][j + it] == -1){
                        it++;
                    }
                    if(copy[i - it][j + it] == 1){
                        continue;
                    }else{
                        layout[i][j] = 1;
                    }
                }else if(copy[i][j] == 1){
                    int adj = 0;
                    int it = 1;
                    while(copy[i + it][j] == -1){
                        it++;
                    }
                    if(copy[i + it][j] == 1) adj++;

                    it = 1;
                    while(copy[i - it][j] == -1){
                        it++;
                    }
                    if(copy[i - it][j] == 1) adj++;

                    it = 1;
                    while(copy[i][j - it] == -1){
                        it++;
                    }
                    if(copy[i][j - it] == 1) adj++;

                    it = 1;
                    while(copy[i][j + it] == -1){
                        it++;
                    }
                    if(copy[i][j + it] == 1) adj++;

                    it = 1;
                    while(copy[i + it][j + it] == -1){
                        it++;
                    }
                    if(copy[i + it][j + it] == 1) adj++;

                    it = 1;
                    while(copy[i - it][j - it] == -1){
                        it++;
                    }
                    if(copy[i - it][j - it] == 1) adj++;
                    
                    it = 1;
                    while(copy[i + it][j - it] == -1){
                        it++;
                    }
                    if(copy[i + it][j - it] == 1) adj++;

                    it = 1;
                    while(copy[i - it][j + it] == -1){
                        it++;
                    }
                    if(copy[i - it][j + it] == 1) adj++;

                    if(adj >= 5) layout[i][j] = 0;
                }
            }
        }

        bool noteq = false;
        for(int i = 1; i < 98; i++){
            for(int j = 1; j < 92; j++){
                if(copy[i][j] != layout[i][j]){
                    noteq = true;
                }
            }
        }

        //Display();
        if(!noteq){
            eq = true;
        }
        
    }
    
    int result = 0;
    for(int i = 0; i < 99; i++){
        for(int j = 0; j < 93; j++){
            if(layout[i][j] == 1){
                result++;
            }
        }
    }

    cout << result << endl;
}

int main(){
    ifstream myfile("input.txt");
    string line;
    for(int x = 0; x < 99; x++){
        for(int y = 0; y < 93; y++){
           layout[x][y] = -2;
        }
    }
    int i = 1;
    while(getline(myfile, line)){
        int j = 1;
        for(auto &c : line){
            switch (c)
            {
            case 'L':
                layout[i][j] = 0;
                break;
            case '.':
                layout[i][j] = -1;
                break;
            case '#':
                layout[i][j] = 1;
                break;
            default:
                break;
            }
            j++;
        }
        i++;
    }

    Part2();
}

void Display(){
    for(int x = 1; x < 98; x++){
        for(int y = 1; y < 92; y++){
            switch (layout[x][y])
            {
            case -1:
                cout << ".";
                break;
            case 0:
                cout << "L";
                break;
            case 1:
                cout << "#";
                break;
            default:
                break;
            }
        }
        cout << endl;
    }
    cout << endl << endl;
}