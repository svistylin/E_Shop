package service;


import model.Code;
import model.Orders;

public interface CodeService {

    void add(Code code);

    int getCode(Orders orders);
}
