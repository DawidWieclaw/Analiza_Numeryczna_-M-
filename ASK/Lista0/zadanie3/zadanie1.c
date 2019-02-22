#include <stdio.h>

int toZero(int x, int k)
{
	//k=k-1
	x=x & ~(1 << k);
	return x;
}

int set(int x, int k)
{
	//k=k-1
	x=x | (1 << k);
	return x;
}

int negation(int x, int k)
{
	//k=k-1
	x=x & ^ (1 << k);
	return x;
}



int main()
{
	int a, b;
	scanf("%d", &a);
	scanf("%d", &b);
	printf("%d", toZero(a,b));
	printf("%d", set(a,b));
	printf("%d", negation(a,b));
}
