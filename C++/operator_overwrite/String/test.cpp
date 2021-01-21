#include <iostream>
#include "my_string.cpp"

using namespace std;


int main()
{
    String s1, s2("cheng"), s3("");
    if (!s1) {
        cout << "s1 is empty" << endl;
    }

    if (!s2) {
        cout << "s2 is empty" << endl;
    } else {
        cout << "s2 is not empty" << endl;
    }

    if (!s3) {
        cout << "s3 is empty" << endl;
    }
    system("pause");
    return 0;
}
