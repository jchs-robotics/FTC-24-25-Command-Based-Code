package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.image.SampleModel;


enum Route {
    NET_ZONE_AUTO,
    OBERSVATION_PARK
}


public class MeepMeepTesting {
    public static final Route route = Route.NET_ZONE_AUTO;
    public static final double maxVel = 60;
    public static final double maxAccel = 60;
    public static final double maxAngVel = Math.toRadians(180);
    public static final double maxAngAccel = Math.toRadians(180);
    public static final double trackWidth = 15;


    public static final boolean red = true;

    public static double startX;
    public static double startY;
    public static double startHeading;

    public static double netZoneXY;
    public static double netZoneHeading;

    public static double sampleOneX;
    public static double sampleTwoX;
    public static double sampleThreeX;
    public static double sampleY;
    public static double fifteen;
    public static double sampleHeading;

    // Park parameters
    public static double parkX;
    public static double parkY = 0;
    public static double parkHeading;

    public static double parkTangent;




    public static void main(String[] args) {

        if (red) {
            startX = -30;
            startY = -60;
            startHeading = 180;

            netZoneXY = -53;
            netZoneHeading = Math.toRadians(45);
            sampleOneX = -37;
            sampleTwoX = -47;
            sampleThreeX = -57;
            sampleY = -25;
            fifteen = 15;
            sampleHeading = Math.toRadians(-180);
            parkX = -25;
            parkHeading = -160;
            parkTangent = 0;

        } else { // else if blue
            startX = 30;
            startY = 60;
            startHeading = 0;

            netZoneXY = 53;
            netZoneHeading = Math.toRadians(-135);
            sampleOneX = 37;
            sampleTwoX = 47;
            sampleThreeX = 57;
            sampleY = 25;
            fifteen = -15;
            sampleHeading = 0;
            parkX = 25;
            parkHeading = 160;
            parkTangent = 160;
        }








        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(maxVel, maxAccel, maxAngVel, maxAngAccel, trackWidth)
                .build();



        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(startX, startY, startHeading))

                // score preload
                .strafeToLinearHeading(new Vector2d(netZoneXY, netZoneXY), netZoneHeading)
                .waitSeconds(2)

                // line up with sample and intake
//                .splineToLinearHeading(new Pose2d(37,25, Math.toRadians(0)), 0)
                .strafeToLinearHeading(new Vector2d(sampleOneX,sampleY - fifteen), sampleHeading)
                .strafeTo(new Vector2d(sampleOneX, sampleY))
                .waitSeconds(2)


                // line up with basket and score
                .strafeToLinearHeading(new Vector2d(netZoneXY,netZoneXY), netZoneHeading)
                .waitSeconds(2)


                // line up with sample 2 and intake
//                .splineToLinearHeading(new Pose2d(47, 25, Math.toRadians(0)), 0)
                .strafeToLinearHeading(new Vector2d(sampleTwoX,sampleY), sampleHeading)
                .waitSeconds(2)

                // line up with basket and score
                .strafeToLinearHeading(new Vector2d(netZoneXY,netZoneXY), netZoneHeading)
                .waitSeconds(2)

                // line up with sample 3 and intake
//                .splineToLinearHeading(new Pose2d(57, 25, Math.toRadians(0)), 0)
                .strafeToLinearHeading(new Vector2d(sampleThreeX,sampleY), sampleHeading)
                .waitSeconds(2)

                // line up with basket and score
                .strafeToLinearHeading(new Vector2d(netZoneXY,netZoneXY), netZoneHeading)
                .waitSeconds(2)

                // park
                .splineToLinearHeading(new Pose2d(parkX, parkY, sampleHeading), parkTangent)
                .waitSeconds(2)





                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();



    }



    public static RoadRunnerBotEntity netZone(MeepMeep meepMeep) {
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(maxVel, maxAccel, maxAngVel, maxAngAccel, trackWidth)
                .build();



        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(30, 60, 0))

                // score preload
                .strafeToLinearHeading(new Vector2d(53,53), Math.toRadians(-135))
                .waitSeconds(2)

                // line up with sample and intake
//                .splineToLinearHeading(new Pose2d(37,25, Math.toRadians(0)), 0)
                .strafeToLinearHeading(new Vector2d(37,40), Math.toRadians(0))
                .strafeTo(new Vector2d(37, 25))
                .waitSeconds(2)


                // line up with basket and score
                .strafeToLinearHeading(new Vector2d(53,53), Math.toRadians(-135))
                .waitSeconds(2)

                // line up with sample 2 and intake
//                .splineToLinearHeading(new Pose2d(47, 25, Math.toRadians(0)), 0)
                .strafeToLinearHeading(new Vector2d(47,25), Math.toRadians(0))
                .waitSeconds(2)

                // line up with basket and score
                .strafeToLinearHeading(new Vector2d(53,53), Math.toRadians(-135))
                .waitSeconds(2)

                // line up with sample 3 and intake
//                .splineToLinearHeading(new Pose2d(57, 25, Math.toRadians(0)), 0)
                .strafeToLinearHeading(new Vector2d(57,25), Math.toRadians(0))
                .waitSeconds(2)

                // line up with basket and score
                .strafeToLinearHeading(new Vector2d(53,53), Math.toRadians(-135))
                .waitSeconds(2)

                // park
                .splineToLinearHeading(new Pose2d(25, 0, Math.toRadians(180)), 160)
                .waitSeconds(2)





                .build());






        return myBot;
    }





}