#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <utility>
#include <set>
#include <math.h>

using namespace std;

struct Waypoint{
    int ns;
    int ew;
    int angle;
};

void Display();
vector<pair<string, int>> instructions;

int Part1(){
    int ns = 0;
    int ew = 0;
    int currDir = 0;
    for(auto &x : instructions){
        if(x.first == "F"){
            switch (currDir){
            case 0:
                ew += x.second;
                break;
            case 90:
                ns -= x.second;
                break;
            case 180:
                ew -= x.second;
                break;
            case 270:
                ns += x.second;
                break;
            default:
                break;
            }
        }else if(x.first == "N"){
            ns += x.second;
        }else if(x.first == "S"){
            ns -= x.second;
        }else if(x.first == "E"){
            ew += x.second;
        }else if(x.first == "W"){
            ew -= x.second;
        }else if(x.first == "R"){
            currDir += x.second;
            if(currDir >= 360){
                currDir -= 360;
            }
        }else if(x.first == "L"){
            currDir -= x.second;
            if(currDir < 0){
                currDir += 360;
            }
        }
    }

    return abs(ns) + abs(ew);
}

int Part2(){
    struct Waypoint *wp = (struct Waypoint*)malloc(sizeof(struct Waypoint));
    wp->ns = 1;
    wp->ew = 10;
    wp->angle = 0;

    int ns = 0;
    int ew = 0;
    for(auto &x : instructions){
        if(x.first == "F"){
            ns += wp->ns * x.second;
            ew += wp->ew * x.second;
        }else if(x.first == "N"){
            wp->ns += x.second;
        }else if(x.first == "S"){
            wp->ns -= x.second;
        }else if(x.first == "E"){
            wp->ew += x.second;
        }else if(x.first == "W"){
            wp->ew -= x.second;
        }else if(x.first == "R"){
            int r = x.second / 90;
            for(int i = 0; i < r; i++){
                int tmp = wp->ew;

                wp->ew = wp->ns;
                wp->ns = tmp * -1;
            }
        }else if(x.first == "L"){
            int r = (-1 * x.second + 360) / 90;
            for(int i = 0; i < r; i++){
                int tmp = wp->ew;

                wp->ew = wp->ns;
                wp->ns = tmp * -1;
            }
        }
    }

    return abs(ns) + abs(ew);
}

int main(){
    ifstream myfile("input.txt");
    string line;
    int n;
    while(getline(myfile, line)){
        stringstream tmp(line.substr(1, string::npos));
        string dir = line.substr(0, 1);
        tmp >> n;
        pair<string, int> x = make_pair(dir, n);
        instructions.push_back(x);
    }
    //Display();
    cout << Part2() << endl;
}

void Display(){
    for(auto &x : instructions){
            cout << x.first << ", " << x.second << endl;
    }
}