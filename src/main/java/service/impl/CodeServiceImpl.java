package service.impl;

import dao.CodeDao;
import model.Code;
import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {

    CodeDao codeDao;

    @Autowired
    public CodeServiceImpl(CodeDao codeDao) {
        this.codeDao = codeDao;
    }

    @Override
    @Transactional
    public void add(Code code) {
        codeDao.add(code);
    }

    @Override
    @Transactional
    public int getCode(Orders order) {
        return codeDao.getCode(order);
    }
}
