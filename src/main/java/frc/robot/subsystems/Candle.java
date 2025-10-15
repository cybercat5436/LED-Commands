// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANdleConfiguration;
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
  private static final RGBWColor kGreen = new RGBWColor(67, 41, 0, 0);
  private static final RGBWColor kMustard = new RGBWColor(0, 21, 69, 0);
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
  return new InstantCommand(( )-> {m_candle.setControl(new SolidColor(0, 3).withColor(kGreen));
    m_candle.setControl(new SolidColor(4, 7).withColor(kMustard));});
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
