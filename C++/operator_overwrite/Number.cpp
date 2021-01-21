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

