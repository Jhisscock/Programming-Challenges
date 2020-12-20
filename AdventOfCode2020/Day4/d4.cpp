#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <algorithm>
#include <regex>
#include <map>

using namespace std;
int main(){
    ifstream myfile("test.txt");
    map<string, string> m;
    string line;
    regex re("[byr:]^(19[2-9][0-9]|200[0-2])$");
    const sregex_token_iterator end;
    while(getline(myfile, line)){
        bool kv = false;
        string tmp = "";
        for (sregex_token_iterator it(line.begin(), line.end(), re, 0); it != end; it++){
            cout << *it << endl;
            if(!kv){
                tmp = *it;
                kv = true;
            }else{
                m[tmp] = *it;
                kv = false;
            }
        }
        if(line == ""){
            for(auto& mp : m){
                cout << "{" << mp.first << ": " << mp.second << "}\n";
            }
        }
    }

}