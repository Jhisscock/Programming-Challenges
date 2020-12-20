#include <iostream>
#include <fstream>

using namespace std;

int main(){
    fstream myfile("input.txt", ios_base::in);

    int a;
    int *nums = new int[200];
    int count = 0;
    while (myfile >> a)
    {
        nums[count] = a;
        count++;
    }

    for(int i = 0; i < 198; i++){
        for(int j = i + 1; j < 199; j++){
            for(int k = j + 1; k  < 200; k++){
                if(nums[i] + nums[j] + nums[k] == 2020){
                    printf("%d", nums[i] * nums[j] * nums[k]);
                }
            }
        }
    }

    return 0;
}