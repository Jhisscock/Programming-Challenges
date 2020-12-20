#include <iostream>
#include <fstream>
#include <string>
#include <math.h>
#include <vector>
#include <algorithm>

using namespace std;
int main(){
    ifstream myfile("input.txt");
    string line;
    int r, c;
    int max = -999999;
    vector<int> results;
    while(getline(myfile, line)){
        double l = 0;
        double h = 127;
        for(int i = 0; i < 7; i++){
            if(line[i] == 'F'){
                h -= (h - l) / 2;
                h = floor(h);
            }else{
                l += (h - l) / 2;
                l = ceil(l);
            }
        }
        r = (int)l;
        l = 0;
        h = 7;
        for(int i = 0; i < 3; i++){
            if(line[i + 7] == 'L'){
                h -= (h - l) / 2;
                h = floor(h);
            }else{
                l += (h - l) / 2;
                l = ceil(l);
            }
        }

        c = (int)l;
        int result = r * 8 + c;
        results.push_back(result);
        if(result > max){
            max = result;
        }
    }
    sort(results.begin(), results.end());
    
    int prev = 0;
    int miss = 0;
    for(int i: results){
        cout << i << endl;
        if(i - prev > 1){
            miss = i;
        }
        prev = i;
    }

    cout << miss;
}