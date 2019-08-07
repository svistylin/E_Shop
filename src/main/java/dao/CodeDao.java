package dao;


import model.Code;
import model.Orders;

public interface CodeDao  {

    void  add(Code code);

    int getCode(Orders orders);
}
