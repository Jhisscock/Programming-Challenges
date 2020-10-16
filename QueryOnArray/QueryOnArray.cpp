#include <iostream> 
  
using namespace std;
const long long MOD = 1000000007;

long long *createBitArray(long long arr[], int size){
    for(int i = 0; i <= size; i++){
        arr[i] = 0;
    }
    return arr;
}
void updateBitArray(int i, long long value, int size, long long arr[]){
    i++;
    while (i <= size){
        arr[i] = (arr[i] + value)%MOD;
        i += (i & -i);
    }
}

long long sum(int i, long long arr[]){
    long long sum = 0;
    i++;
    while(i > 0){
        sum = (arr[i] + sum)%MOD;
        i -= (i & -i);
    }
    return sum;
}

long long sumRange(int i, int j, long long arr[]){
    return sum(j, arr) - sum(i - 1, arr);
}

int main(){
    int arraySize;
    cin >> arraySize;
    long long numOfQueries;
    cin >> numOfQueries;
    long long *bitArray = new long long[arraySize + 1];
    createBitArray(bitArray, arraySize);

    for(int i = 0; i < numOfQueries; i++){
        int queryType;
        cin >> queryType;
        int x;
        cin >> x;
        x -= 1;
        int y;
        cin >> y;
        y -= 1;

        long long count = 0;
        long long sum = 0;
        switch (queryType){
        case 0:
            sum = sumRange(x, y, bitArray);
            if(sum < 0){
                sum += MOD;
            }
            cout << sum << "\n";
            break;
        case 1:
            for(int j = x; j <= y; j++){
                updateBitArray(j, (count + (long long)1) * (count + (long long)2) * (count + (long long)3), arraySize, bitArray);
                count++;
            }
            break;
        case 2:
            for(int j = x; j <= y; j++){
                updateBitArray(j, -(count + (long long)1) * (count + (long long)2) * (count + (long long)3), arraySize, bitArray);
                count++;
            }
            break;
        default:
            break;
        }
    }

    return 0;
}
