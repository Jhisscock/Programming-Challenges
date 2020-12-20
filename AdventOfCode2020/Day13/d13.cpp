#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <utility>

using namespace std;
vector<long long> times;
vector<pair<long long, long long>> buses;

long long Part1(){
    long long depart = times[0];
    long long min = 99999999;
    long long minTime = 0;
    for(vector<long long>::iterator i = times.begin() + 1; i != times.end(); ++i){
        long long result = depart / *i + 1;
        if(result * (*i) - depart < min){
            min = result * (*i) - depart;
            minTime = *i;
        }
    }

    return min * minTime;
}

long long Part2(){
    long long lcm = 1;
    long long time = 0;
    for(int i = 0; i < buses.size() - 1; i++){
        long long id = buses[i + 1].first;
        long long index = buses[i + 1].second;
        lcm *= buses[i].first;
        while((time + index) % id != 0){
            time += lcm;
        }
    }

    return time;
}

void Display(){
    for(auto &it : times){
        cout << it << endl;
    }

    for(auto &it : buses){
        cout << it.first << ", " << it.second << endl;
    }
}

int main(){
    ifstream myfile("input.txt");
    string line;
    long long n;
    long long index = 0;
    while(getline(myfile, line, ',')){
        stringstream ss(line);
        if(ss >> n){
            times.push_back(n);
            buses.push_back(make_pair(n, index));
        }
        index++;
    }
    
    cout << Part2() << endl;
}