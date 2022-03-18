#include <string>
#include <vector>
#include <queue>
#include <functional>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int, vector<int>, greater<int>> pq;
    int count = 0;
    
    for (auto s: scoville) pq.push(s);
    
    while(pq.size() >= 2 && pq.top() < K) {
        int min_1 = pq.top();pq.pop();
        int min_2 = pq.top();pq.pop();
        
        pq.push(min_1 + min_2 * 2);
        count++;
    }
    
    return pq.top() < K ? -1 : count;
}