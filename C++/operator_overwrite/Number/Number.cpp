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

// const 成员函数，可以访问但不能修改成员变量
// this -> n = this -> n + operator2.n; return *this; 为错误写法
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

