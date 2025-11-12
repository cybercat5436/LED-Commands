// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANdleConfiguration;
import com.ctre.phoenix6.controls.ColorFlowAnimation;
import com.ctre.phoenix6.controls.EmptyAnimation;
import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.signals.RGBWColor;
import com.ctre.phoenix6.signals.StatusLedWhenActiveValue;
import com.ctre.phoenix6.signals.StripTypeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Candle extends SubsystemBase {
  /** Creates a new Candle. */
  private final CANdle m_candle = new CANdle(44);
  private static final RGBWColor kGreen = new RGBWColor(0, 217, 0, 0);
  private static final RGBWColor kMustard = new RGBWColor(255, 219, 88, 0);
  private static final RGBWColor kBlue = new RGBWColor(0, 0, 255, 0);

  private int slot1Start = 8;
  private int slot1End = 111;
  private int slot2Start = 112;
  private int slot2End = 1000;



  public Candle() {
   
    var cfg = new CANdleConfiguration();
    System.out.println("Line is being executed###################");
    /* set the LED strip type and brightness */
    cfg.LED.StripType = StripTypeValue.GRB;
    cfg.LED.BrightnessScalar = 1.0;
    /* disable status LED when being controlled */
    cfg.CANdleFeatures.StatusLedWhenActive = StatusLedWhenActiveValue.Disabled;
    m_candle.getConfigurator().apply(cfg);
    for (int i = 0; i < 8; ++i) {
        m_candle.setControl(new EmptyAnimation(i));
        
    }
  }
public Command setMultiColor(){
  return new InstantCommand(( )-> {
    m_candle.setControl(new SolidColor(slot1Start, slot1End).withColor(kGreen));
    m_candle.setControl(new SolidColor(slot2Start, slot2End).withColor(kMustard));
  });
}

public Command setMultiAnimations(){
  return new InstantCommand(()-> {
     m_candle.setControl(
      new ColorFlowAnimation(slot1Start, slot1End).withSlot(1)
      .withColor(kGreen)
      );
      m_candle.setControl(
        new ColorFlowAnimation(slot2Start, slot2End).withSlot(2)
        .withColor(kMustard)
        );

  });
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
