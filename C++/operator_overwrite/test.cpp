#include <iostream>
#include "Number.cpp"

using namespace std;

int main()
{
    Number n1;
    Number n2(2.5), n3(0.5);
    n1.print();
    n1 = n2 + n3;
    n1.print();
    n1 = n2 - n3;
    n1.print();
    system("pause");
    return 0;
}
