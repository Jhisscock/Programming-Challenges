#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <utility>
#include <unordered_map>

using namespace std;

int main(){
    ifstream myfile("test.txt");
    string line;
    string mask;
    unordered_map<string, string> mem;
    while(getline(myfile, line)){
        if(line.substr(0, 4) == "mask"){
            mask = line.substr(7, string::npos);
        }else{
            
        }
    }
}