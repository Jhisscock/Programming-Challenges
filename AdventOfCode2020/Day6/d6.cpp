#include <iostream>
#include <fstream>
#include <string>
#include <math.h>
#include <vector>
#include <algorithm>
#include <stdio.h>

using namespace std;
int main(){
    ifstream myfile("input.txt");
    string line;
    string groups = "";
    int total = 0;
    int gs = 0;
    while(getline(myfile, line)){
        if(!line.empty()){
            groups += line;
            gs++;
        }else if(gs != 1){
            sort(groups.begin(), groups.end());
            char prev = '1';
            int i = 0;
            for(char c : groups){
                if(c == prev){
                    prev = c;
                    i++;
                    if(i == gs - 1){
                        total++;
                    }
                }else{
                    prev = c;
                    i = 0;
                }
            }
            cout << groups << " : " << total << endl;
            gs = 0;
            groups = "";
        }else{
            total += groups.size();
        }
    }
    cout << total << endl;
}