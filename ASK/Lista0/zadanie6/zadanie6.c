#include <stdio.h>
//Dawid Więcław
//little endian to big endian conversion methods

unsigned int byteSwap(unsigned int a)// b1b2b3b4
{
	unsigned int b1,b2,b3,b4;
	b1=(a>>24);
	b2=(a>>16); //b2=b1b2
	b2=b2-(b1<<8); //b2-b100
	b3=(a>>8); //b3=b1b2b3
	b3=b3-(b1<<16);	//b3=b2b3
	b3=b3-(b2<<8); //b3=b3
	b4=a-(b3<<8)-(b2<<16)-(b1<<24); 
	a=(b1)+(b2<<8)+(b3<<16)+(b4<<24);
	return a;
}

unsigned int fasterByteSwap(unsigned int a)
{
	unsigned int b=((a<<8) & 0xFF00FF00); //b=b200b400
	b=b | ((a>>8) & 0xFF00FF); //b=b2b1b4b3
	b=((b<<16) | (b>>16)); //b4b3b2b1
	return b;
}

int main()
{
	unsigned int x;
	scanf("%d", &x);
	printf("%d\n", byteSwap(x));
	printf("%d\n", fasterByteSwap(x));
	
}
