package com.berna.parkinglotInfo.service;

import com.berna.domain.parkinglot.domain.dto.ParkingLotInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrentParkingSearchTest {

    private static ParkingLotInfo parkingLotInfo;


    @Before
    public void setUp(){
      this.parkingLotInfo = new ParkingLotInfo().builder()
              .weekdayBeginTime("1000")
              .weekdayEndTime("1900")
              .weekendBeginTime("1000")
              .weekendEndTime("1400")
              .holidayBeginTime("1000")
              .holidayEndTime("1400")
              .queStatus("3")
              .build();




    }

/*


    @Test
    public void 현재_주차여부_계산_테스트_평일_가능(){
        when(LocalDate.now()).thenReturn(LocalDate.of(2020,2,15));
        when(LocalTime.now()).thenReturn(LocalTime.of(10,00,00));
        assertEquals(true,parkingLotInfo.isCurrentParkingCheck());
       //

    }
*/


    @Test
    public void 현재_주차여부_계산_테스트_평일_불가(){

        given(LocalDate.now()).willReturn(LocalDate.of(2020,2,15));
        given(LocalTime.now()).willReturn(LocalTime.of(20,00,00));
        assertEquals(LocalTime.now().getHour(),"20");
        //assertEquals(false,parkingLotInfo.isCurrentParkingCheck());

    }
  /*  @Test
    public void 현재_주차여부_계산_테스트_공휴일_가능(){
        when(LocalDate.now()).thenReturn(LocalDate.of(2020,3,1));
        when(LocalTime.now()).thenReturn(LocalTime.of(11,30,00));
        assertEquals(true,parkingLotInfo.isCurrentParkingCheck());

    }*/
}
