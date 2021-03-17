##### Number.h

```c++
#ifndef _NUMBER_H_
#define _NUMBER_H_

class Number {
public:
    Number(double=0.0);
    ~Number();
    Number operator+(const Number &) const;
    Number operator-(const Number &) const;
    Number &operator=(const Number &);
    void print();
private:
    double n;
};

#endif
```

##### Number.cpp

```c++
#include "Number.h"
#include <iostream>
using namespace std;

Number::Number(double n) {
    this -> n = n;
    cout << "value = " << n << " create~" << endl;
}

Number::~Number() {
    cout << "value = " << n <<  " destory~" << endl;
}

Number Number::operator+(const Number&operator2) const{
    Number sum;
    sum.n = this -> n + operator2.n;
    return sum;
}

Number Number::operator-(const Number&operator2) const {
    Number sum;
    sum.n = this -> n - operator2.n;
    return sum;
}

Number &Number::operator=(const Number &right) {
    n = right.n;
    return *this; // this 为 指针类型
}

void Number::print() {
    cout << "current value is: " << n << endl;
}


```

##### test.h

```c++
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
```

