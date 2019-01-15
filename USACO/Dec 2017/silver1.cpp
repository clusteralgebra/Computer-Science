//http://usaco.org/index.php?page=viewproblem2&cpid=762

#include<fstream>
using namespace std;
ifstream fin("homework.in");
ofstream fout("homework.out");

long long raw[100000];
long long scores[100000];

int main(void) {	
	int N, max = 1;
	fin >> N;
	for (int i = 1; i <= N; i++) fin >> scores[i];
	int min = N;
	int cumsum = scores[N - 1] + scores[N];
	if (scores[N - 1] < scores[N]) min--;
	raw[N - 2] = cumsum - scores[min];
	for (int K = N - 3; K >= 1; K--) {
		cumsum += scores[K+1];
		if (scores[K + 1] < scores[min]) min = K + 1;
		raw[K] = cumsum - scores[min];
	} 
	for (int i = 2; i <= N-2; i++) 
		if ((N - max - 1) * raw[i] > (N - i - 1)* raw[max]) max = i;
	for (int i = 1; i <= N - 2; i++)
		if ((N - max - 1) * raw[i] == (N - i - 1) * raw[max])
			fout << i << "\n";
	return 0;
}
