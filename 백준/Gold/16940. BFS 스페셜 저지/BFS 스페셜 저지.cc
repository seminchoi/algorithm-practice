#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

int n, a, b, pos;
vector<int> v[100001];
queue<int> order;
int bfs_order[100001];

bool comp(const int& a, const int& b) {
    return bfs_order[a] < bfs_order[b];
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> n;
    for (int i = 0; i < n - 1; i++) {
        cin >> a >> b;
        v[a].push_back(b);
        v[b].push_back(a);
    }
    for (int i = 1; i <= n; i++) {
        cin >> a;
        order.push(a);
        bfs_order[a] = i;
    }
    for (int i = 1; i <= n; i++) {
        sort(v[i].begin(), v[i].end(), comp);
    }
    if (order.front() != 1) {
        cout << "0" << "\n";
        exit(0);
    }
    else {
        queue<int> temp;
        temp.push(1);
        order.pop();
        while (!temp.empty()) {
            pos = temp.front();
            temp.pop();
            for (auto i : v[pos]) {
                if (i == order.front()) {
                    temp.push(order.front());
                    order.pop();
                }
                else;
            }
        }
    }
    if (order.empty()) cout << "1" << "\n";
    else cout << "0" << "\n";
    return 0;
}