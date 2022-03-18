#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#define DELETE 1
#define INSERT 0
using namespace std;

struct Number {
    int number;
    bool isDeleted;
    
    Number(int n) : number(n), isDeleted(false) {}
    
    void deleteNum() { this->isDeleted = true; }
};

struct Op {
    int command;
    int number;
    
    Op(string op) {
        if (op[0] == 'I') {
            this->command = INSERT;
            this->number = stoi(op.substr(2));
        } else {
            this->command = DELETE;
            this->number = op[2] == '1' ? 1 : -1;
        }
        
    } 
};


struct greaterCompare {
    bool operator() (const Number* n1, const Number* n2) {
       return n1->number > n2->number;
    }
};

struct lesserCompare{
    bool operator() (const Number* n1, const Number* n2) {
       return n1->number < n2->number;
    }
};

vector<int> solution(vector<string> operations) {
    vector<Op> ops;
    priority_queue<Number*, vector<Number*>, greaterCompare> maxPq;
    priority_queue<Number*, vector<Number*>, lesserCompare> minPq;
    
    for (string operation: operations) {
        Op op = Op(operation);
        ops.push_back(op);
    }
    int deleteCount = 0;
    int insertCount = 0;
    
    for (Op op: ops) {
        printf("%c %d", op.command == INSERT ? 'I' : 'D', op.number);
        if (op.command == INSERT) {
            Number number = Number(op.number);
            insertCount++;
            maxPq.push(&number);
            minPq.push(&number);
        } else {
            if (insertCount == deleteCount) continue;
                
            deleteCount++;
            Number* ptr = NULL;
            if (op.number == 1) {
                while(maxPq.top()->isDeleted) {
                    maxPq.pop();
                }

                ptr = maxPq.top();
                ptr->deleteNum();
            } else {
                while(minPq.top()->isDeleted) {
                    minPq.pop();
                }

                ptr = minPq.top();
                ptr->deleteNum();
            }
        }
    }
    
    if (insertCount == deleteCount) return {0, 0} ;
    
    Number* maxPtr = NULL;
    Number* minPtr = NULL;
    
    while(!maxPq.empty() && maxPq.top()->isDeleted) maxPq.pop();
    while(!minPq.empty() && minPq.top()->isDeleted) minPq.pop();
    
    return { maxPq.top()->number, minPq.top()->number };
}

int main() {
    vector<string> operations{ "I 7","I 5","I -5","D -1"};
    vector<int> result = solution(operations);
    printf("[%d, %d]",result[0], result[1]);

    return 0;
}