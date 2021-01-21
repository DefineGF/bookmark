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
