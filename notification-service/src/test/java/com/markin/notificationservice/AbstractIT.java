package com.markin.notificationservice;



import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public abstract class AbstractIT {

}
