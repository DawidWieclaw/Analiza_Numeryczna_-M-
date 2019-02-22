//Dawid Więcław
//some basic arithmetic operations

#include <stdio.h>



int mult(int x, int y)
{
	x = (x<<y);
	return x;
}

int divByPowerOf2(int x, int y)
{
	x = (x>>y);
	return x;
}

int modulo(int x, int y)
{
	int z=(1<<y);
	z=z-1;
	x = x & z;
	return x;
}

int divCeilByPowerOf2(int x, int y)
{
	int q=x-1;
	int r=(1<<y)-1;
	q=q&r;
	q=q+1;
	q=(q>>y);
	q=q ^ (1<<0);
	return q+podziel(x,y);
}


int main()
{
	int a,b;
	scanf("%d", &a);
	scanf("%d", &b);
	printf("%d\n", mult(a,b));
	printf("%d\n", divByPowerOf2(a,b));
	printf("%d\n", modulo(a,b));
	printf("%d\n", divCeilByPowerOf2(a,b));
}
