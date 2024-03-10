package com.bsj;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DemoTest {

    @Test
    void add() {
        Random random = Mockito.mock(Random.class);
        System.out.println(random.nextInt());
        Mockito.verify(random).nextInt();
        //Mockito.verify(random,Mockito.times(1)).nextInt();
    }

    @Test
    void add2() {
        Random random = Mockito.mock(Random.class, "test");
        Assertions.assertEquals(100,random.nextInt());
    }

    @Test
    void add3() {
        Random random = Mockito.mock(Random.class, "test");
        Mockito.when(random.nextInt()).thenReturn(100);
        Assertions.assertEquals(100,random.nextInt());
    }
    
    @BeforeEach
    void setUp(){
        //System.out.println("----测试前准备------");
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    private Random random;
    @Test
    public void add4(){
        //MockitoAnnotations.openMocks(this);
        Mockito.when(random.nextInt()).thenReturn(100);
        Assertions.assertEquals(100,random.nextInt());
    }

    @AfterEach
    void after(){
        System.out.println("----测试结束------");
    }
    
    @Spy
    private Demo demo;
    
    @Test
    public void add5(){
        //Demo demo = Mockito.spy(Demo.class);
        int result = demo.add(1, 2);
        Assertions.assertEquals(3,result);


        Demo demo2 = Mockito.mock(Demo.class);
        Mockito.when(demo2.add(1, 2)).thenReturn(0);
        Assertions.assertEquals(3,demo2.add(1, 2));
    }
    
    
    
}