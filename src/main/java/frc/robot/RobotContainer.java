// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.CyberCandle;
import frc.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix6.configs.CANdleConfiguration;
import com.ctre.phoenix6.controls.ColorFlowAnimation;
import com.ctre.phoenix6.controls.EmptyAnimation;
import com.ctre.phoenix6.controls.FireAnimation;
import com.ctre.phoenix6.controls.RainbowAnimation;
import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.controls.TwinkleAnimation;
import com.ctre.phoenix6.controls.TwinkleOffAnimation;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.signals.RGBWColor;
import com.ctre.phoenix6.signals.StatusLedWhenActiveValue;
import com.ctre.phoenix6.signals.StripTypeValue;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final CyberCandle cyberCandle = new CyberCandle();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
      private final CANdle m_candle = cyberCandle.getCandle();
      private enum AnimationType {
        None,
        ColorFlow,
        Fire,
        Larson,
        Rainbow,
        RgbFade,
        SingleFade,
        Strobe,
        Twinkle,
        TwinkleOff,
    }
      private static final RGBWColor kGreen = new RGBWColor(0, 217, 0, 0);
      private final SendableChooser<AnimationType> m_anim0Chooser = new SendableChooser<AnimationType>();
      private AnimationType m_anim0State = AnimationType.None;
      private static final int kSlot0StartIdx = 0;
      private static final int kSlot0EndIdx = 7;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
        //     var cfg = new CANdleConfiguration();

        // /* set the LED strip type and brightness */
        // cfg.LED.StripType = StripTypeValue.GRB;
        // cfg.LED.BrightnessScalar = 1.0;
        // /* disable status LED when being controlled */
        // cfg.CANdleFeatures.StatusLedWhenActive = StatusLedWhenActiveValue.Disabled;

        // m_candle.getConfigurator().apply(cfg);
        // for (int i = 0; i < 8; ++i) {
        //     m_candle.setControl(new EmptyAnimation(i));
        // }
        
        // System.out.println("About to set green~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // m_candle.setControl(new SolidColor(0, 3).withColor(kGreen));
//         m_anim0Chooser.setDefaultOption("Color Flow", AnimationType.ColorFlow);
//         SmartDashboard.putData("Animation 0", m_anim0Chooser);
// final var anim0Selection = m_anim0Chooser.getSelected();
//         if (m_anim0State != anim0Selection) {
//             m_anim0State = anim0Selection;

//             switch (m_anim0State) {
//                 default:
//                 case ColorFlow:
//                     m_candle.setControl(
//                         new ColorFlowAnimation(kSlot0StartIdx, kSlot0EndIdx).withSlot(0)
//                             .withColor(kGreen)
//                     );
//                     break;
//                 case Rainbow:
//                     m_candle.setControl(
//                         new RainbowAnimation(kSlot0StartIdx, kSlot0EndIdx).withSlot(0)
//                     );
//                     break;
//                 case Twinkle:
//                     m_candle.setControl(
//                         new TwinkleAnimation(kSlot0StartIdx, kSlot0EndIdx).withSlot(0)
//                             .withColor(kGreen)
//                     );
//                     break;
//                 case TwinkleOff:
//                     m_candle.setControl(
//                         new TwinkleOffAnimation(kSlot0StartIdx, kSlot0EndIdx).withSlot(0)
//                             .withColor(kGreen)
//                     );
//                     break;
//                 case Fire:
//                     m_candle.setControl(
//                         new FireAnimation(kSlot0StartIdx, kSlot0EndIdx).withSlot(0)
//                     );
//                     break;
//             }
//         }

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem, m_candle));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    m_driverController.a().whileTrue(new ExampleCommand(m_exampleSubsystem, m_candle));

    m_driverController.b().onTrue(
      new InstantCommand(
        () -> {
          System.out.println("Turning off CANdle from Robot Container....");
          m_candle.setControl(new SolidColor(0, 7));
        }
      )
    );

    m_driverController.y().onTrue(cyberCandle.getTurnOffCommand());

    m_driverController.x().onTrue(cyberCandle.getRainbowCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
