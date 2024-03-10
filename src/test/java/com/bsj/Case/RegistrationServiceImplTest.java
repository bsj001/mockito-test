package com.bsj.Case;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.xml.bind.ValidationException;

class RegistrationServiceImplTest {
    @InjectMocks
    @Spy
    private RegistrationServiceImpl registrationService;
    
    @Mock
    private SalesDao salesDao;
    
    @Mock
    private UserDao userDao;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() throws Exception {
        String name = null;
        String phone = "15071271412";
        try{
            registrationService.register(name,phone);
            Assertions.fail("这里会挂掉");
        }catch (Exception e){
            Assertions.assertTrue(e instanceof ValidationException);
        }
        
        name = "Tom";
        phone = null;
        try{
            registrationService.register(name,phone);
            Assertions.fail("这里会挂掉");
        }catch(Exception e){
            Assertions.assertTrue(e instanceof ValidationException);
        }
        
        phone = "15071271412";
        MockedStatic<FindUtils> staticService = Mockito.mockStatic(FindUtils.class);
        staticService.when(()->FindUtils.getAreaCode("15071271412")).thenReturn("a");
        staticService.when(()->FindUtils.getOperatorCode("15071271412")).thenReturn("b");
        
        //1,数据库操作正常
        Mockito.when(salesDao.findRep("a","b")).thenCallRealMethod();
        Mockito.when(userDao.save(name,phone,"Tom")).thenCallRealMethod();
        User user = registrationService.register(name, phone);
        Assertions.assertEquals("Tom",user.getRepId());
        
        //2,数据库操作异常
        Mockito.when(userDao.save(name,phone,"Tom")).thenThrow(new UnknownError());
        try{
            registrationService.register(name,phone);
        }catch (Exception e){
            Assertions.assertTrue(e instanceof DAOException);
        }
    }
}