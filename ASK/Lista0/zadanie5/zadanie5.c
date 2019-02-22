#include <stdio.h>
//Dawid Więcław
//checking if x is the power of 2

int ifPow(int x)
{
	x=x&(x-1);
	return x;
}

int main()
{
	int a;
	scanf("%d", &a);
	printf("%d", ifPow(a));
}
