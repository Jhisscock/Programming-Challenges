#include <iostream>
#include <fstream>

using namespace std;

int PossibleSum(int *nums, int start, int &ans, int end){
    for(int i = start; i >= end; i--){
        int find = nums[start + 1] - nums[i];
        for(int j = start; j >= end; j--){
            if(j == i){
                continue;
            }else if(find == nums[j]){
                return PossibleSum(nums, start + 1, ans, end + 1);
            }
        }
    }
    ans = nums[start + 1];
    return ans;
}

long long Part2(long long *nums){
    for(int i = 999; i >= 0; i--){
        long long sum = 70639851;
        long long max = -9999999;
        long long min = 93905084442605;
        for(int j = i; j >= 0; j--){
            if(nums[j] == 70639851){
                j -= 1;
            }
            sum -= nums[j];
            if(nums[j] > max){
                max = nums[j];
            }
            if(nums[j] < min){
                min = nums[j];
            }
            if(sum == 0){
                cout << i << " " << j << endl;
                return max + min;
            }
        }
    }
    return 0;
}

int main(){
    ifstream myfile("input.txt");
    long long n;
    long long nums[1000];
    int i = 0;
    int answer;
    while(myfile >> n){
        nums[i] = n;
        i++;
    }

    cout << Part2(nums) << endl;
}