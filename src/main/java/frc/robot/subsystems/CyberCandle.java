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

public class CyberCandle extends SubsystemBase {
  /** Creates a new CyberCandle. */
  private CANdle candle = new CANdle(44);

  private static final RGBWColor kGreen = new RGBWColor(0, 255, 0, 0);
  private static final RGBWColor kRed = new RGBWColor(255, 0, 0, 0);
  private static final RGBWColor kBlue = new RGBWColor(0, 0, 255, 0);
  private static final RGBWColor kAqua = new RGBWColor(0, 213, 255, 0);
  private static final RGBWColor kYellow = new RGBWColor(255, 255, 0, 0);
  private static final RGBWColor kPurple = new RGBWColor(128, 0, 255, 0);
  private static final RGBWColor kPink = new RGBWColor(255, 0, 255, 0);
  private static final RGBWColor kOrange = new RGBWColor(255, 136, 0, 0);
  private static final RGBWColor kOff = new RGBWColor(0, 0, 0, 0);

  
  public CyberCandle() {

    System.out.println("~~~~~~~~~~  Initiating CANDle subsystem  ~~~~~~~~~~~~~");
    var cfg = new CANdleConfiguration();
    /* set the LED strip type and brightness */
    cfg.LED.StripType = StripTypeValue.GRB;
    cfg.LED.BrightnessScalar = 1.0;
    /* disable status LED when being controlled */
    cfg.CANdleFeatures.StatusLedWhenActive = StatusLedWhenActiveValue.Disabled;
    candle.getConfigurator().apply(cfg);

    // Remove all previous animations
    for (int i = 0; i < 8; ++i) {
      candle.setControl(new EmptyAnimation(i));
    }
  }

  private void turnOffCandle(){
    System.out.println("~~~~~~~~~~  Turning off CANdle LEDs  ~~~~~~~~~~");
    candle.setControl(new SolidColor(0, 7).withColor(kOff));

  }

  public Command getRainbowCommand(){
    return new InstantCommand(
      () -> {
        System.out.println("~~~~~~~~~~  Turning CANdle LEDs to Rainbow  ~~~~~~~~~~");
        candle.setControl(new SolidColor(0, 0).withColor(kRed));
        candle.setControl(new SolidColor(1, 1).withColor(kOrange));
        candle.setControl(new SolidColor(2, 2).withColor(kYellow));
        candle.setControl(new SolidColor(3, 3).withColor(kGreen));
        candle.setControl(new SolidColor(4, 4).withColor(kAqua));
        candle.setControl(new SolidColor(5, 5).withColor(kBlue));
        candle.setControl(new SolidColor(6, 6).withColor(kPink));
        candle.setControl(new SolidColor(7, 7).withColor(kPurple));
      }
    );
  }

  public Command getTurnOffCommand(){
    return new InstantCommand(() -> {
      System.out.println("~~~~~~~~~  Turning off CANdle from SubSystem  ~~~~~~~~~~~");
      turnOffCandle();
    });
  }

  public CANdle getCandle(){
    return this.candle;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
