/*
http://usaco.org/index.php?page=viewproblem2&cpid=759
*/
#include<iostream>
#include<fstream>
using namespace std;

int max(int x, int y) {
	if (x > y) return x;
	return y;
}
int min(int x, int y) {
	if (x > y) return y;
	return x;
}

int main(void) {
	ifstream fin("billboard.in");
	ofstream fout("billboard.out");
	int x1[3], y1[3], x2[3], y2[3];
	for (int i = 0; i < 3; i++)
		fin >> x1[i] >> y1[i] >> x2[i] >> y2[i];
	int ans = (x2[0] - x1[0])*(y2[0] - y1[0]) + (x2[1] - x1[1])*(y2[1] - y1[1]);
	for (int i = 0; i <= 1; i++) {
		int w1, w2, z1, z2;
		z1 = max(y1[i], y1[2]);
		z2 = min(y2[i], y2[2]);
		w1 = max(x1[i], x1[2]);
		w2 = min(x2[i], x2[2]);
		if (z1 <= z2 && w1 <= w2)
			ans -= (z2 - z1)*(w2 - w1);
	}
	fout << ans << "\n";
	return 0;
}
