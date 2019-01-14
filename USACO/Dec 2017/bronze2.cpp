/*
http://usaco.org/index.php?page=viewproblem2&cpid=760
*/

#include<fstream>
using namespace std;

int main(void) {
	ifstream fin("shuffle.in");
	ofstream fout("shuffle.out");
	int perm[101], reverse[101], id[101], ans[101];
	int N;
	fin >> N;
	for (int i = 1; i <= N; i++)
		fin >> perm[i];
	for (int i = 1; i <= N; i++)
		fin >> id[i];
	for (int i = 1; i <= N; i++)
		reverse[perm[i]] = i;
	for (int i = 1; i <= N; i++)
		ans[reverse[reverse[reverse[i]]]] = id[i];
	for (int i = 1; i <= N; i++)
		fout << ans[i] << "\n";
	return 0;
}
