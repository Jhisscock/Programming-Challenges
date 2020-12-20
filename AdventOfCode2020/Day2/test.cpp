#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

int main(){
    string tmp = "cbccc";
    if((tmp[0] == 'c' && tmp[1] != 'c') || (tmp[0] != 'c' && tmp[1] != 'c')){
        cout << "true";
    }
}