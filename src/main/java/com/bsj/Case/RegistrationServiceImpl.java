package com.bsj.Case;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

public class RegistrationServiceImpl implements RegistrationService{
    
    SalesDao salesDao = new SalesDao();
    UserDao userDao = new UserDao();
    @Override
    public User register(String name, String phone) throws Exception {
        //参数校验
        if(name==null || name.length() == 0){
            throw new ValidationException("number不能为空");
        }
        if(phone==null || !isValid(phone)){
            throw new ValidationException("phone 格式错误");
        }
        
        //获取手机号归属地号和运营商编号，然后通过编号找到区域内是salesRep
        String areaCode = FindUtils.getAreaCode(phone);
        String operatorCode = FindUtils.getOperatorCode(phone);
        
        
        User user;
        try{
            SalesRep rep = salesDao.findRep(areaCode,operatorCode);
            
            //最后创建用户，落盘，然后返回
            user = userDao.save(name,phone,rep.getRepId());
        }catch (UnknownError e){
            throw new DAOException("SQLException throw"+e.getMessage());
        }
        
        return user;
    }
    
    private boolean isValid(String phone){
        String pattern = "^15\\d{9}$";
        boolean flag = phone.matches(pattern);
        return true;
    }
}
