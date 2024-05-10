package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStoreInterface mockDA1;
  private TorpedoStoreInterface mockDA2;

  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.mockDA1 = mock(TorpedoStoreInterface.class);
    this.mockDA2 = mock(TorpedoStoreInterface.class);

    this.ship = new GT4500(mockDA1, mockDA2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(false);
    when(mockDA1.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockDA1, times(1)).isEmpty();
    verify(mockDA1, times(1)).fire(1);

  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(false);
    when(mockDA1.fire(1)).thenReturn(true);
    when(mockDA2.isEmpty()).thenReturn(false);
    when(mockDA2.fire(1)).thenReturn(true);



    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockDA1, times(1)).isEmpty();
    verify(mockDA1, times(1)).fire(1);
    verify(mockDA2, times(1)).isEmpty();
    verify(mockDA2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_First_Fire_Failure(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(false);
    when(mockDA1.fire(1)).thenReturn(false);



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockDA1, times(1)).isEmpty();
    verify(mockDA1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Second_Fire_Failure(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(true);

    when(mockDA2.isEmpty()).thenReturn(false);
    when(mockDA2.fire(1)).thenReturn(false);



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockDA2, times(1)).isEmpty();
    verify(mockDA2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Empty_Failure(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(true);
    when(mockDA1.fire(1)).thenReturn(true);
    when(mockDA2.isEmpty()).thenReturn(true);
    when(mockDA2.fire(1)).thenReturn(true);



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockDA1, times(1)).isEmpty();
    verify(mockDA1, times(0)).fire(1);

    verify(mockDA2, times(1)).isEmpty();
    verify(mockDA2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Alternating(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(false);
    when(mockDA1.fire(1)).thenReturn(true);
    when(mockDA2.isEmpty()).thenReturn(false);
    when(mockDA2.fire(1)).thenReturn(true);



    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);

    verify(mockDA1, times(1)).isEmpty();
    verify(mockDA1, times(1)).fire(1);

    verify(mockDA2, times(1)).isEmpty();
    verify(mockDA2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Alternating3(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(false);
    when(mockDA1.fire(1)).thenReturn(true);
    when(mockDA2.isEmpty()).thenReturn(false);
    when(mockDA2.fire(1)).thenReturn(true);



    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result3 = ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    assertEquals(true, result3);


    verify(mockDA1, times(2)).isEmpty();
    verify(mockDA1, times(2)).fire(1);

    verify(mockDA2, times(1)).isEmpty();
    verify(mockDA2, times(1)).fire(1);
  }



}
