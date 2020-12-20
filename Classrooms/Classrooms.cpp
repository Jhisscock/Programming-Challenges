#include <iostream>
#include <algorithm>
#include <set>
using namespace std;

struct event{
    long long start, end;
};

bool compare(event& x, event& y){
    if(x.end == y.end){
        return x.start < y.start;
    }
    return x.end < y.end;
}

int main(){
    int n, rooms;

    cin >> n >> rooms;

    event *events = new event[n];
    for(int i = 0; i < n ; i++){
        cin >> events[i].start >> events[i].end;
    }

    sort(events, events + n, compare);

    int total = 0;
    multiset<int> time;
    for(int i = 0; i < n; i++){
        auto pos = time.lower_bound(-events[i].start);
        
        if(pos == time.end()) {
            if(time.size() < rooms) {
                time.insert(-events[i].end-1);
                total++;
            }
            continue;
        }

        time.erase(pos);
        time.insert(-events[i].end-1);
        total++;
        
    }

    cout << total;

    return 0;
}