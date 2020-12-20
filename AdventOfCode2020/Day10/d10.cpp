#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int Part1(long long *nums){
    long long one = 0;
    long long three = 1;
    switch (nums[0])
    {
    case 1:
        one++;
        break;
    case 2:
        break;
    case 3:
        three++;
        break;
    default:
        break;
    }
    for(long long i = 0; i < 106; i++){
        long long result = nums[i+1] - nums[i];
        switch (result)
        {
        case 1:
            one++;
            break;
        case 2:
            break;
        case 3:
            three++;
            break;
        default:
            break;
        }
    }

    cout << one << ", " << three << ": " << one * three << endl;
    return one * three;
}

long long result = 0;
void Part2(long long *nums, int value, int start){
    int j = start + 1;
    while(nums[j] - value <= 3){
        Part2(nums, nums[j], j);
        j++;
    }
    if(j == 31){
        result++;
    }
}

int main(){
    ifstream myfile("test.txt");
    long long n;
    long long nums[32];
    nums[0] = 0;
    long long i = 1;
    while(myfile >> n){
        nums[i] = n;
        i++;
    }

    sort(nums, nums + 32);
    cout << "[ ";
    for(auto &x : nums){
        cout << x << ", ";
    }
    cout << " ]" << endl;

    Part2(nums, 0, 0);
    cout << result << endl;
}