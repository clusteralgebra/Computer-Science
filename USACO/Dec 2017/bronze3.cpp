/*
http://usaco.org/index.php?page=viewproblem2&cpid=761
*/

#include<fstream>
#include<string>
#include<algorithm>
using namespace std;
ifstream fin("measurement.in");
ofstream fout("measurement.out");

// representation of names
int objectify(string name) {
	if (name == "Bessie") return 1;
	if (name == "Elsie") return 2;
	return 3;
}

// encoding of who leads
int genLead(int output[]) {
	int MAX = max(output[1], max(output[2], output[3]));
	int ans = 0;
	if (output[1] == MAX) ans += 1;
	if (output[2] == MAX) ans += 2;
	if (output[3] == MAX) ans += 4;
	return ans;
}

int main(void) {
	int gain[101] = { 0 }, name[101] = { 0 }, output[4] = { 0 }; // on day i
	int N;	fin >> N; 
	for (int i = 1; i <= N; i++) {
		int x, y; string z;
		fin >> x >> z >> y;
		gain[x] = y;
		name[x] = objectify(z);
	}
	int dummy, lead = 7; 
	int ans = 0;
	for (int day = 1; day <= 100; day++) {
		if (name[day] != 0) {
			output[name[day]] += gain[day];
			dummy = genLead(output);
			if (lead != dummy) ans++;
			lead = dummy;
		}
	}
	fout << ans << "\n";
	return 0;
}
