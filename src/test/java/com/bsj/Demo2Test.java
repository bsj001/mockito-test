package com.bsj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
//import static org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class Demo2Test {
    
    @Spy
    private Demo2 demo;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add() {
        //Mockito.when(demo.add(1,1)).thenReturn(3);
        //int res = demo.add(1,2);
        //Assertions.assertEquals(3,res);
        //
        //Mockito.when(demo.add(1,1)).thenThrow(new RuntimeException());
        //demo.add(1,1);
        
        
        Mockito.when(demo.add(1,1)).thenCallRealMethod();
        Assertions.assertEquals(2,demo.add(1,1));
    }
}