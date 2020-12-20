#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

struct password{
    int l, h;
    char key;
};

int main(){
    ifstream myfile("input.txt");
    string line;
    vector<string> delims = {"-", " ", ": ", "\n"};
    int total = 0;
    while (getline(myfile, line))
    {
        size_t pos = 0;
        string token;
        int count = 0;
        int firstNum = 0;
        password currPass;
        while((pos = line.find(delims[count])) != string::npos){
            token = line.substr(0, pos);
            line.erase(0, pos + delims[count].length());
            stringstream tmp(token);

            switch (count){
                case 0: ;
                    tmp >> currPass.l;
                    break;
                case 1:
                    tmp >> currPass.h;
                case 2:
                    currPass.key = token[0];
                default:
                    break;
            }

            count++;
        }

        if((line[currPass.l - 1] == currPass.key && line[currPass.h - 1] != currPass.key) || (line[currPass.l - 1] != currPass.key && line[currPass.h - 1] == currPass.key)){
            total++;
        }
    }

    cout << total << "\n";
}