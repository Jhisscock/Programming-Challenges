#include <iostream>
#include <fstream>
#include <string>
#include <math.h>
#include <vector>
#include <algorithm>
#include <stdio.h>
using namespace std;
int main(){
    ifstream myfile("test.txt");
    string line;

    while(getline(myfile, line)){
        size_t pos = line.find(" bags contain ");
        string token = line.substr(0, pos);
        cout << token << endl;
        line.erase(0, pos + 14);
        pos = line.find(" bag");
        token = line.substr(0, pos);
        int quan = (int)line[0] - 48;
        if(quan == 62){
            quan = 0;
        }
        cout << token << endl;
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
            cout << token << endl;
        }else{
            line.erase(0, 2);
            token = line.substr(0, line.find(" bag"));
            quan = (int)line[0] - 48;
            if(quan == 62){
                quan = 0;
            }
            cout << token << endl;
        }
    }
}